package info.developia.opia.parsing.visitor.statement;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser;
import info.developia.opia.domain.node.statement.Block;
import info.developia.opia.domain.node.statement.Statement;
import info.developia.opia.domain.scope.Scope;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class BlockStatementVisitor extends OpiaBaseVisitor<Block> {
    private final Scope scope;

    public BlockStatementVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public Block visitBlock(@NotNull OpiaParser.BlockContext ctx) {
        List<OpiaParser.StatementContext> blockstatementsCtx = ctx.statement();
        Scope newScope = new Scope(scope);
        StatementVisitor statementVisitor = new StatementVisitor(newScope);
        List<Statement> statements = blockstatementsCtx.stream().map(smtt -> smtt.accept(statementVisitor)).collect(Collectors.toList());
        return new Block(newScope, statements);
    }
}