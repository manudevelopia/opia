package info.developia.opia.parsing.visitor.statement;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ExpressionContext;
import info.developia.opia.OpiaParser.VariableDeclarationContext;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.node.statement.VariableDeclaration;
import info.developia.opia.domain.scope.LocalVariable;
import info.developia.opia.domain.scope.Scope;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class VariableDeclarationStatementVisitor extends OpiaBaseVisitor<VariableDeclaration> {
    private final ExpressionVisitor expressionVisitor;
    private final Scope scope;

    public VariableDeclarationStatementVisitor(ExpressionVisitor expressionVisitor, Scope scope) {
        this.expressionVisitor = expressionVisitor;
        this.scope = scope;
    }

    @Override
    public VariableDeclaration visitVariableDeclaration(@NotNull VariableDeclarationContext ctx) {
        String varName = ctx.name().getText();
        ExpressionContext expressionCtx = ctx.expression();
        Expression expression = expressionCtx.accept(expressionVisitor);
        scope.addLocalVariable(new LocalVariable(varName, expression.getType()));
        return new VariableDeclaration(varName, expression);
    }
}