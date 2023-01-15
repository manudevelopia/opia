package info.developia.opia.domain.node.expression;

import info.developia.opia.bytecodegeneration.expression.ExpressionGenerator;
import info.developia.opia.bytecodegeneration.statement.StatementGenerator;
import info.developia.opia.domain.type.ClassType;
import info.developia.opia.domain.type.Type;

import java.util.Collections;
import java.util.List;

public class ConstructorCall implements Call {
    private final List<Argument> arguments;
    private final Type type;
    private final String identifier;

    public ConstructorCall(String identifier) {
        this(identifier, Collections.emptyList());
    }

    public ConstructorCall(String className, List<Argument> arguments) {
        this.type = new ClassType(className);
        this.arguments = arguments;
        this.identifier = type.getName();
    }

    @Override
    public void accept(ExpressionGenerator genrator) {
        genrator.generate(this);
    }

    @Override
    public List<Argument> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    @Override
    public String getIdentifier() {
        return identifier;
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
