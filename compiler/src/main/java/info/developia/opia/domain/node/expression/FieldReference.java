package info.developia.opia.domain.node.expression;

import info.developia.opia.bytecodegeneration.expression.ExpressionGenerator;
import info.developia.opia.bytecodegeneration.statement.StatementGenerator;
import info.developia.opia.domain.scope.Field;
import info.developia.opia.domain.type.Type;

public class FieldReference implements Reference {
    private Field field;

    public FieldReference(Field field) {
        this.field = field;
    }


    @Override
    public String geName() {
        return field.getName();
    }

    @Override
    public void accept(ExpressionGenerator generator) {
        generator.generate(this);
    }

    @Override
    public Type getType() {
        return field.getType();
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }

    public String getOwnerInternalName() {
        return field.getOwnerInternalName();
    }
}
