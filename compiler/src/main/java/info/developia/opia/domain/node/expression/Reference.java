package info.developia.opia.domain.node.expression;

import info.developia.opia.bytecodegeneration.expression.ExpressionGenerator;
import info.developia.opia.bytecodegeneration.statement.StatementGenerator;

public interface Reference extends Expression {
    String geName();

    @Override
    void accept(ExpressionGenerator genrator);

    @Override
    void accept(StatementGenerator generator);
}
