package info.developia.opia;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

public class OpiaLang {
    public static void main(String[] args) {
        try {
            InputStream inputStream = OpiaLang.class.getClassLoader().getResourceAsStream("main.opia");
            CharStream input = CharStreams.fromStream(inputStream);
            OpiaLexer lexer = new OpiaLexer(input);
            OpiaParser parser = new OpiaParser(new CommonTokenStream(lexer));
            parser.addParseListener(new OpiaBaseListener());
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new OpiaWalker(), parser.r());
            System.out.println(parser.r());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
