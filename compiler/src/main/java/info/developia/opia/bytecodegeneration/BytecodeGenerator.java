package info.developia.opia.bytecodegeneration;

import info.developia.opia.domain.ClassDeclaration;
import info.developia.opia.domain.CompilationUnit;

public class BytecodeGenerator {
    public byte[] generate(CompilationUnit compilationUnit) {
        ClassDeclaration classDeclaration = compilationUnit.getClassDeclaration();
        ClassGenerator classGenerator = new ClassGenerator();
        return classGenerator.generate(classDeclaration).toByteArray();
    }
}
