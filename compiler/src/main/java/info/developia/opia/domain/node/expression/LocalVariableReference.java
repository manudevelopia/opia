package info.developia.opia.domain.node.expression;

import info.developia.opia.bytecodegeneration.expression.ExpressionGenerator;
import info.developia.opia.bytecodegeneration.statement.StatementGenerator;
import info.developia.opia.domain.scope.LocalVariable;
import info.developia.opia.domain.type.Type;

public class LocalVariableReference implements Reference {

    private final LocalVariable variable;

    public LocalVariableReference(LocalVariable variable) {
        this.variable = variable;
    }

    @Override
    public String geName() {
        return variable.getName();
    }

    @Override
    public void accept(ExpressionGenerator generator) {
        generator.generate(this);
    }

    @Override
    public Type getType() {
        return variable.getType();
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
