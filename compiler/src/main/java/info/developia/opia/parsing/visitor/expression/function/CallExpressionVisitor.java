package info.developia.opia.parsing.visitor.expression.function;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ArgumentListContext;
import info.developia.opia.OpiaParser.ConstructorCallContext;
import info.developia.opia.OpiaParser.FunctionCallContext;
import info.developia.opia.OpiaParser.SupercallContext;
import info.developia.opia.domain.node.expression.*;
import info.developia.opia.domain.scope.FunctionSignature;
import info.developia.opia.domain.scope.LocalVariable;
import info.developia.opia.domain.scope.Scope;
import info.developia.opia.domain.type.ClassType;
import info.developia.opia.exception.FunctionNameEqualClassException;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CallExpressionVisitor extends OpiaBaseVisitor<Call> {
    private final ExpressionVisitor expressionVisitor;
    private final Scope scope;

    public CallExpressionVisitor(ExpressionVisitor expressionVisitor, Scope scope) {
        this.expressionVisitor = expressionVisitor;
        this.scope = scope;
    }

    @Override
    public Call visitFunctionCall(@NotNull FunctionCallContext ctx) {
        String functionName = ctx.functionName().getText();
        if (functionName.equals(scope.getClassName())) {
            throw new FunctionNameEqualClassException(functionName);
        }
        List<Argument> arguments = getArgumentsForCall(ctx.argumentList());
        boolean ownerIsExplicit = ctx.owner != null;
        if (ownerIsExplicit) {
            Expression owner = ctx.owner.accept(expressionVisitor);
            FunctionSignature signature = scope.getMethodCallSignature(Optional.of(owner.getType()), functionName, arguments);
            return new FunctionCall(signature, arguments, owner);
        }
        ClassType thisType = new ClassType(scope.getClassName());
        FunctionSignature signature = scope.getMethodCallSignature(functionName, arguments);
        LocalVariable thisVariable = new LocalVariable("this", thisType);
        return new FunctionCall(signature, arguments, new LocalVariableReference(thisVariable));
    }

    @Override
    public Call visitConstructorCall(@NotNull ConstructorCallContext ctx) {
        String className = ctx.className().getText();
        List<Argument> arguments = getArgumentsForCall(ctx.argumentList());
        return new ConstructorCall(className, arguments);
    }

    @Override
    public Call visitSupercall(@NotNull SupercallContext ctx) {
        List<Argument> arguments = getArgumentsForCall(ctx.argumentList());
        return new SuperCall(arguments);
    }

    private List<Argument> getArgumentsForCall(ArgumentListContext argumentsListCtx) {
        if (argumentsListCtx != null) {
            ArgumentExpressionsListVisitor visitor = new ArgumentExpressionsListVisitor(expressionVisitor);
            return argumentsListCtx.accept(visitor);
        }
        return Collections.emptyList();
    }
}