package info.developia.opia.compiler;

import info.developia.opia.bytecode.BytecodeGenerator;
import info.developia.opia.bytecode.Instruction;
import info.developia.opia.parser.Parser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Queue;

public class Compiler {
    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        try {
            compiler.compile(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void compile(String[] args) throws Exception {
        File file = new File(args[0]);
        Parser parser = new Parser();
        final Queue<Instruction> instructionsQueue = parser.parse(file.getAbsolutePath());
        final byte[] byteCode = new BytecodeGenerator().generateBytecode(instructionsQueue, file.getName().replace(".opia", ""));
        saveBytecodeToClassFile(file.getName(), byteCode);
    }

    private static void saveBytecodeToClassFile(String fileName, byte[] byteCode) throws IOException {
        final String classFile = fileName.replace(".opia", ".class");
        OutputStream os = new FileOutputStream(classFile);
        os.write(byteCode);
        os.close();
    }
}