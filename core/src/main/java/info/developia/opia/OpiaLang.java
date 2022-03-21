package info.developia.opia;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.InputStream;

public class OpiaLang {
    public static void main(String[] args) {
        try {
            InputStream inputStream = OpiaLang.class.getClassLoader().getResourceAsStream("main.opia");
            CharStream input = CharStreams.fromStream(inputStream);
            OpiaLexer lexer = new OpiaLexer(input);
            OpiaParser parser = new OpiaParser(new CommonTokenStream(lexer));
            parser.addParseListener(new OpiaWalker());
            parser.program();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
