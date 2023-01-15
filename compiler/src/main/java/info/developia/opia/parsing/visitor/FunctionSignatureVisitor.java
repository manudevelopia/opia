package info.developia.opia.parsing.visitor;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.FunctionDeclarationContext;
import info.developia.opia.OpiaParser.ParametersListContext;
import info.developia.opia.domain.node.expression.Parameter;
import info.developia.opia.domain.scope.FunctionSignature;
import info.developia.opia.domain.scope.Scope;
import info.developia.opia.domain.type.Type;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import info.developia.opia.parsing.visitor.expression.function.ParameterExpressionListVisitor;
import info.developia.opia.util.TypeResolver;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Created by kuba on 06.04.16.
 */
public class FunctionSignatureVisitor extends OpiaBaseVisitor<FunctionSignature> {

    private final ExpressionVisitor expressionVisitor;

    public FunctionSignatureVisitor(Scope scope) {
        this.expressionVisitor = new ExpressionVisitor(scope);
    }

    @Override
    public FunctionSignature visitFunctionDeclaration(@NotNull FunctionDeclarationContext ctx) {
        String functionName = ctx.functionName().getText();
        Type returnType = TypeResolver.getFromTypeContext(ctx.type());
        ParametersListContext parametersCtx = ctx.parametersList();
        if (parametersCtx != null) {
            List<Parameter> parameters = parametersCtx.accept(new ParameterExpressionListVisitor(expressionVisitor));
            return new FunctionSignature(functionName, parameters, returnType);
        }
        return new FunctionSignature(functionName, Collections.emptyList(), returnType);

    }
}
