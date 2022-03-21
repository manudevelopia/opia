package info.developia.opia;

public class OpiaWalker extends OpiaBaseListener {
    public void enterR(OpiaParser.RContext ctx) {
        System.out.println("Entering R : " + ctx.ID().getText());
    }

    public void exitR(OpiaParser.RContext ctx) {
        System.out.println("Exiting R");
    }
}
