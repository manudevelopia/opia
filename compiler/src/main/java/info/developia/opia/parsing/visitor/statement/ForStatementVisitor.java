package info.developia.opia.parsing.visitor.statement;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ForConditionsContext;
import info.developia.opia.OpiaParser.ForStatementContext;
import info.developia.opia.OpiaParser.VariableReferenceContext;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.node.statement.Assignment;
import info.developia.opia.domain.node.statement.RangedForStatement;
import info.developia.opia.domain.node.statement.Statement;
import info.developia.opia.domain.node.statement.VariableDeclaration;
import info.developia.opia.domain.scope.LocalVariable;
import info.developia.opia.domain.scope.Scope;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by kuba on 23.04.16.
 */
public class ForStatementVisitor extends OpiaBaseVisitor<RangedForStatement> {
    private final Scope scope;
    private final ExpressionVisitor expressionVisitor;

    public ForStatementVisitor(Scope scope) {
        this.scope = scope;
        expressionVisitor = new ExpressionVisitor(this.scope);
    }

    @Override
    public RangedForStatement visitForStatement(@NotNull ForStatementContext ctx) {
        Scope newScope = new Scope(scope);
        ForConditionsContext forExpressionContext = ctx.forConditions();
        Expression startExpression = forExpressionContext.startExpr.accept(expressionVisitor);
        Expression endExpression = forExpressionContext.endExpr.accept(expressionVisitor);
        VariableReferenceContext iterator = forExpressionContext.iterator;
        StatementVisitor statementVisitor = new StatementVisitor(newScope);
        String varName = iterator.getText();
        if (newScope.isLocalVariableExists(varName)) {
            Statement iteratorVariable = new Assignment(varName, startExpression);
            Statement statement = ctx.statement().accept(statementVisitor);
            return new RangedForStatement(iteratorVariable, startExpression, endExpression, statement, varName, newScope);
        } else {
            newScope.addLocalVariable(new LocalVariable(varName, startExpression.getType()));
            Statement iteratorVariable = new VariableDeclaration(varName, startExpression);
            Statement statement = ctx.statement().accept(statementVisitor);
            return new RangedForStatement(iteratorVariable, startExpression, endExpression, statement, varName, newScope);
        }
    }

}
