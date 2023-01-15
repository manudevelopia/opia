package info.developia.opia.domain.node.statement;

import info.developia.opia.bytecodegeneration.statement.StatementGenerator;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.scope.Scope;
import info.developia.opia.domain.type.Type;
import info.developia.opia.exception.UnsupportedRangedLoopTypes;
import info.developia.opia.util.TypeChecker;

/**
 * Created by kuba on 23.04.16.
 */
public class RangedForStatement implements Statement {
    private final Statement iteratorVariable;
    private final Expression startExpression;
    private final Expression endExpression;
    private final Statement statement;
    private final String iteratorVarName;
    private final Scope scope;

    public RangedForStatement(Statement iteratorVariable, Expression startExpression, Expression endExpression, Statement statement, String iteratorVarName, Scope scope) {
        this.scope = scope;
        Type startExpressionType = startExpression.getType();
        Type endExpressionType = endExpression.getType();
        boolean typesAreIntegers = TypeChecker.isInt(startExpressionType) || TypeChecker.isInt(endExpressionType);
        if (!typesAreIntegers) {
            throw new UnsupportedRangedLoopTypes(startExpression, endExpression);
        }
        this.iteratorVariable = iteratorVariable;
        this.startExpression = startExpression;
        this.endExpression = endExpression;
        this.statement = statement;
        this.iteratorVarName = iteratorVarName;
    }

    public Statement getIteratorVariableStatement() {
        return iteratorVariable;
    }

    public Expression getStartExpression() {
        return startExpression;
    }

    public Expression getEndExpression() {
        return endExpression;
    }

    public Statement getStatement() {
        return statement;
    }

    public String getIteratorVarName() {
        return iteratorVarName;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }

    public Scope getScope() {
        return scope;
    }

    public Type getType() {
        return startExpression.getType();
    }
}
