package info.developia.opia.domain.node.expression;

import info.developia.opia.bytecodegeneration.expression.ExpressionGenerator;
import info.developia.opia.bytecodegeneration.statement.StatementGenerator;
import info.developia.opia.domain.scope.FunctionSignature;
import info.developia.opia.domain.type.Type;

import java.util.Collections;
import java.util.List;

public class FunctionCall implements Call {
    private final Expression owner;
    private final FunctionSignature signature;
    private final List<Argument> arguments;
    private final Type type;

    public FunctionCall(FunctionSignature signature, List<Argument> arguments, Expression owner) {
        this.type = signature.getReturnType();
        this.signature = signature;
        this.arguments = arguments;
        this.owner = owner;
    }

    public FunctionCall(FunctionSignature signature, List<Argument> arguments, Type ownerType) {
        this(signature, arguments, new EmptyExpression(ownerType));
    }

    @Override
    public List<Argument> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    @Override
    public String getIdentifier() {
        return signature.getName();
    }

    public Type getOwnerType() {
        return owner.getType();
    }

    public Expression getOwner() {
        return owner;
    }

    public FunctionSignature getSignature() {
        return signature;
    }

    @Override
    public void accept(ExpressionGenerator genrator) {
        genrator.generate(this);
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }

    @Override
    public Type getType() {
        return type;
    }
}
