package info.developia.opia.parser;

import info.developia.opia.OpiaBaseListener;
import info.developia.opia.OpiaParser;
import info.developia.opia.bytecode.Instruction;
import info.developia.opia.bytecode.PrintVariable;
import info.developia.opia.bytecode.VariableDeclaration;
import info.developia.opia.parser.domain.Variable;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class OpiaTreeWalkListener extends OpiaBaseListener {
    Queue<Instruction> instructionsQueue = new ArrayDeque<>();
    Map<String, Variable> variables = new HashMap<>();

    public Queue<Instruction> getInstructionsQueue() {
        return instructionsQueue;
    }

    @Override
    public void exitVariable(@NotNull OpiaParser.VariableContext ctx) {
        final TerminalNode varName = ctx.ID();
        final OpiaParser.ValueContext varValue = ctx.value();
        final int varType = varValue.getStart().getType();
        final int varIndex = variables.size();
        final String varTextValue = varValue.getText();
        Variable var = new Variable(varIndex, varType, varTextValue);
        variables.put(varName.getText(), var);
        instructionsQueue.add(new VariableDeclaration(var));
        logVariableDeclarationStatementFound(varName, varValue);
    }

    @Override
    public void exitPrint(@NotNull OpiaParser.PrintContext ctx) {
        final TerminalNode varName = ctx.ID();
        final boolean printedVarNotDeclared = !variables.containsKey(varName.getText());
        if (printedVarNotDeclared) {
            final String erroFormat = "ERROR: WTF? You are trying to print var '%s' which has not been declared!!!111. ";
            System.out.printf(erroFormat, varName.getText());
            return;
        }
        final Variable variable = variables.get(varName.getText());
        instructionsQueue.add(new PrintVariable(variable));
        logPrintStatementFound(varName, variable);
    }

    private void logVariableDeclarationStatementFound(TerminalNode varName, OpiaParser.ValueContext varValue) {
        final int line = varName.getSymbol().getLine();
        final String format = "OK: You declared variable named '%s' with value of '%s' at line '%s'.\n";
        System.out.printf(format, varName, varValue.getText(), line);
    }

    private void logPrintStatementFound(TerminalNode varName, Variable variable) {
        final int line = varName.getSymbol().getLine();
        final String format = "OK: You instructed to print variable '%s' which has value of '%s' at line '%s'.'\n";
        System.out.printf(format,variable.getId(),variable.getValue(),line);
    }
}

