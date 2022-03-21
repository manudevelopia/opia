package info.developia.opia;

import java.util.HashMap;
import java.util.Map;

public class OpiaWalker extends OpiaBaseListener {
    Map<String, Integer> variableMap = new HashMap<>();

    public void exitShow(OpiaParser.ShowContext ctx) {
        if (ctx.INT() != null) {
            System.out.println(ctx.INT().getText());
        } else if (ctx.VAR() != null) {
            System.out.println(this.variableMap.get(ctx.VAR().getText()));
        }
    }

    public void exitLet(OpiaParser.LetContext ctx) {
        this.variableMap.put(ctx.VAR().getText(), Integer.parseInt(ctx.INT().getText()));
    }
}
