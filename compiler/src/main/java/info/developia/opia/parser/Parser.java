package info.developia.opia.parser;

import info.developia.opia.OpiaLexer;
import info.developia.opia.OpiaParser;
import info.developia.opia.bytecode.Instruction;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.Queue;

public class Parser {

    public Queue<Instruction> parse(String filePath) throws IOException {
        CharStream charStream = CharStreams.fromFileName(filePath);
        OpiaLexer lexer = new OpiaLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        OpiaParser parser = new OpiaParser(tokenStream);
        OpiaTreeWalkListener listener = new OpiaTreeWalkListener();
        BaseErrorListener errorListener = new OpiaTreeWalkErrorListener();

        parser.addErrorListener(errorListener);
        parser.addParseListener(listener);
        parser.compilationUnit();
        return listener.getInstructionsQueue();
    }
}
