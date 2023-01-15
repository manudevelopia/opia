package info.developia.opia.compiler;

import info.developia.opia.bytecodegeneration.BytecodeGenerator;
import info.developia.opia.domain.CompilationUnit;
import info.developia.opia.parsing.Parser;
import info.developia.opia.validation.ARGUMENT_ERRORS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Compiler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Compiler.class);

    public static void main(String... args) throws Exception {
        try {
            new Compiler().compile(args);
        } catch (IOException exception) {
            LOGGER.error("ERROR: " + exception.getMessage());
        }
    }

    public void compile(String... args) throws Exception {
        ARGUMENT_ERRORS argumentsErrors = getArgumentValidationErrors(args);
        if (argumentsErrors != ARGUMENT_ERRORS.NONE) {
            String errorMessage = argumentsErrors.getMessage();
            LOGGER.error(errorMessage);
            return;
        }
        File opiaFile = new File(args[0]);
        LOGGER.info("Trying to parse '{}'.", opiaFile.getAbsolutePath());
        CompilationUnit compilationUnit = new Parser().parse(opiaFile.toPath());
        LOGGER.info("Finished Parsing. Started compiling to bytecode.");
        saveBytecodeToClassFile(compilationUnit);
    }

    public void compile(String code) throws IOException {
        CompilationUnit compilationUnit = new Parser().parse(code);
        LOGGER.info("Finished Parsing. Started compiling to bytecode.");
        saveBytecodeToClassFile(compilationUnit);
    }

    private ARGUMENT_ERRORS getArgumentValidationErrors(String[] args) {
        if (args.length != 1) {
            return ARGUMENT_ERRORS.NO_FILE;
        }
        String filePath = args[0];
        if (!filePath.endsWith(".opia")) {
            return ARGUMENT_ERRORS.BAD_FILE_EXTENSION;
        }
        return ARGUMENT_ERRORS.NONE;
    }

    private void saveBytecodeToClassFile(CompilationUnit compilationUnit) throws IOException {
        BytecodeGenerator bytecodeGenerator = new BytecodeGenerator();
        byte[] bytecode = bytecodeGenerator.generate(compilationUnit);
        String className = compilationUnit.getClassName();
        String fileName = className + ".class";
        Path path = Paths.get(fileName);
        LOGGER.info("Finished Compiling. Saving bytecode to '{}'.", path.toAbsolutePath());
        try (OutputStream os = Files.newOutputStream(path)) {
            os.write(bytecode);
        }
        LOGGER.info("Done. To run compiled file execute: 'java {}' in current dir", className);
    }
}
