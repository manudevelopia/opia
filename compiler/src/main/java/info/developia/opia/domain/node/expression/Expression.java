package info.developia.opia.domain.node.expression;

import info.developia.opia.bytecodegeneration.expression.ExpressionGenerator;
import info.developia.opia.bytecodegeneration.statement.StatementGenerator;
import info.developia.opia.domain.node.statement.Statement;
import info.developia.opia.domain.type.Type;

public interface Expression extends Statement {
    Type getType();

    void accept(ExpressionGenerator genrator);

    @Override
    void accept(StatementGenerator generator);
}
