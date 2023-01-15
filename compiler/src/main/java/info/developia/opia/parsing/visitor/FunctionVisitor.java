package info.developia.opia.parsing.visitor;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.BlockContext;
import info.developia.opia.OpiaParser.FunctionContext;
import info.developia.opia.domain.Constructor;
import info.developia.opia.domain.Function;
import info.developia.opia.domain.node.statement.Statement;
import info.developia.opia.domain.scope.FunctionSignature;
import info.developia.opia.domain.scope.LocalVariable;
import info.developia.opia.domain.scope.Scope;
import info.developia.opia.parsing.visitor.statement.StatementVisitor;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by kuba on 01.04.16.
 */
public class FunctionVisitor extends OpiaBaseVisitor<Function> {

    private final Scope scope;

    public FunctionVisitor(Scope scope) {
        this.scope = new Scope(scope);
    }

    @Override
    public Function visitFunction(@NotNull FunctionContext ctx) {
        FunctionSignature signature = ctx.functionDeclaration().accept(new FunctionSignatureVisitor(scope));
        scope.addLocalVariable(new LocalVariable("this", scope.getClassType()));
        addParametersAsLocalVariables(signature);
        Statement block = getBlock(ctx);
        if (signature.getName().equals(scope.getClassName())) {
            return new Constructor(signature, block);
        }
        return new Function(signature, block);
    }

    private void addParametersAsLocalVariables(FunctionSignature signature) {
        signature.getParameters().stream()
                .forEach(param -> scope.addLocalVariable(new LocalVariable(param.getName(), param.getType())));
    }

    private Statement getBlock(FunctionContext functionContext) {
        StatementVisitor statementVisitor = new StatementVisitor(scope);
        BlockContext block = functionContext.block();
        return block.accept(statementVisitor);
    }
}
