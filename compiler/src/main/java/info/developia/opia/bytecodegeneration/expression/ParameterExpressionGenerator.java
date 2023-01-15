package info.developia.opia.bytecodegeneration.expression;

import info.developia.opia.domain.node.expression.Parameter;
import info.developia.opia.domain.scope.Scope;
import info.developia.opia.domain.type.Type;
import org.objectweb.asm.MethodVisitor;

public class ParameterExpressionGenerator {
    private final MethodVisitor methodVisitor;
    private final Scope scope;

    public ParameterExpressionGenerator(MethodVisitor methodVisitor, Scope scope) {
        this.methodVisitor = methodVisitor;
        this.scope = scope;
    }

    public void generate(Parameter parameter) {
        Type type = parameter.getType();
        int index = scope.getLocalVariableIndex(parameter.getName());
        methodVisitor.visitVarInsn(type.getLoadVariableOpcode(), index);
    }
}