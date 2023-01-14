package info.developia.opia.bytecode;

import org.objectweb.asm.MethodVisitor;

public interface Instruction {
    void apply(MethodVisitor methodVisitor);
}
