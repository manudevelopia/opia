package info.developia.opia.domain;

import info.developia.opia.bytecodegeneration.MethodGenerator;
import info.developia.opia.domain.node.expression.Parameter;
import info.developia.opia.domain.node.statement.Statement;
import info.developia.opia.domain.scope.FunctionSignature;
import info.developia.opia.domain.type.Type;

import java.util.Collections;
import java.util.List;

public class Function {

    private final FunctionSignature functionSignature;
    private final Statement rootStatement;


    public Function(FunctionSignature functionSignature, Statement rootStatement) {
        this.functionSignature = functionSignature;
        this.rootStatement = rootStatement;
    }

    public String getName() {
        return functionSignature.getName();
    }

    public List<Parameter> getParameters() {
        return Collections.unmodifiableList(functionSignature.getParameters());
    }

    public Statement getRootStatement() {
        return rootStatement;
    }

    public Type getReturnType() {
        return functionSignature.getReturnType();
    }

    public void accept(MethodGenerator generator) {
        generator.generate(this);
    }
}
