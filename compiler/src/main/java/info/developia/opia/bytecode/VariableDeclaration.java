package info.developia.opia.bytecode;

import info.developia.opia.OpiaLexer;
import info.developia.opia.parser.domain.Variable;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class VariableDeclaration implements Instruction, Opcodes {
    Variable variable;

    public VariableDeclaration(Variable variable) {
        this.variable = variable;
    }

    @Override
    public void apply(MethodVisitor mv) {
        final int type = variable.getType();
        if (type == OpiaLexer.NUMBER) {
            int val = Integer.parseInt(variable.getValue());
            mv.visitIntInsn(BIPUSH, val);
            mv.visitVarInsn(ISTORE, variable.getId());
        } else if (type == OpiaLexer.STRING) {
            mv.visitLdcInsn(variable.getValue());
            mv.visitVarInsn(ASTORE, variable.getId());
        }
    }
}