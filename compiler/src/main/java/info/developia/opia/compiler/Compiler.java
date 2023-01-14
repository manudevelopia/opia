package info.developia.opia.compiler;

import info.developia.opia.parser.Parser;

import java.io.File;

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
        parser.parse(file.getAbsolutePath());
    }
}