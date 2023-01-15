package info.developia.opia.domain.node.statement;


import info.developia.opia.bytecodegeneration.statement.StatementGenerator;
import info.developia.opia.domain.node.expression.Expression;

/**
 * Created by kuba on 28.03.16.
 */
public class VariableDeclaration implements Statement {
    private final String name;
    private final Expression expression;

    public VariableDeclaration(String name, Expression expression) {
        this.expression = expression;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
