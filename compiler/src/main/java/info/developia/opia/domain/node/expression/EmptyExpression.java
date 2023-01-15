package info.developia.opia.domain.node.expression;

import info.developia.opia.bytecodegeneration.expression.ExpressionGenerator;
import info.developia.opia.bytecodegeneration.statement.StatementGenerator;
import info.developia.opia.domain.type.Type;

public class EmptyExpression implements Expression {

    private final Type type;

    public EmptyExpression(Type type) {
        this.type = type;
    }

    @Override
    public void accept(ExpressionGenerator genrator) {
        genrator.generate(this);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
