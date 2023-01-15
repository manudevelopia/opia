package info.developia.opia.parsing;

import info.developia.opia.OpiaLexer;
import info.developia.opia.OpiaParser;
import info.developia.opia.domain.CompilationUnit;
import info.developia.opia.parsing.visitor.CompilationUnitVisitor;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.nio.file.Path;

public class Parser {
    public CompilationUnit parse(Path filePath) throws IOException {
        CharStream charStream = CharStreams.fromPath(filePath);
        return parse(charStream);
    }

    public CompilationUnit parse(String code){
        CharStream charStream = CharStreams.fromString(code);
        return parse(charStream);
    }

    private static CompilationUnit parse(CharStream charStream) {
        OpiaLexer lexer = new OpiaLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        OpiaParser parser = new OpiaParser(tokenStream);

        ANTLRErrorListener errorListener = new OpiaTreeWalkErrorListener();
        parser.addErrorListener(errorListener);

        CompilationUnitVisitor compilationUnitVisitor = new CompilationUnitVisitor();
        return parser.compilationUnit().accept(compilationUnitVisitor);
    }


}
