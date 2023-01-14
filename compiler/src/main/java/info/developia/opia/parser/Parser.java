package info.developia.opia.parser;

import info.developia.opia.OpiaLexer;
import info.developia.opia.OpiaParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Parser {

    public void parse(String filePath) throws IOException {
        CharStream charStream = CharStreams.fromFileName(filePath);
        OpiaLexer lexer = new OpiaLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        OpiaParser parser = new OpiaParser(tokenStream);
    }
}
