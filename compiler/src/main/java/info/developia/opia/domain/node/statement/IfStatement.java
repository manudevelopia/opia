package info.developia.opia.domain.node.statement;

import info.developia.opia.bytecodegeneration.statement.StatementGenerator;
import info.developia.opia.domain.node.expression.Expression;

import java.util.Optional;

/**
 * Created by kuba on 12.04.16.
 */
public class IfStatement implements Statement {


    private final Expression condition;
    private final Statement trueStatement;
    private final Optional<Statement> falseStatement;

    public IfStatement(Expression condition, Statement trueStatement, Statement falseStatement) {
        this.condition = condition;
        this.trueStatement = trueStatement;
        this.falseStatement = Optional.ofNullable(falseStatement);
    }

    public IfStatement(Expression condition, Statement trueStatement) {
        this.condition = condition;
        this.trueStatement = trueStatement;
        this.falseStatement = Optional.empty();
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getTrueStatement() {
        return trueStatement;
    }

    public Optional<Statement> getFalseStatement() {
        return falseStatement;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
