package info.developia.opia;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.InputStream;

public class OpiaLang {
    public static void main(String[] args) {
        try {
            InputStream inputStream = OpiaLang.class.getClassLoader().getResourceAsStream("main.opia");
            OpiaLexer lexer = new OpiaLexer(CharStreams.fromStream(inputStream));
            OpiaParser parser = new OpiaParser(new CommonTokenStream(lexer));
            parser.addParseListener(new OpiaWalker());
            parser.program();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
