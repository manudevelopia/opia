package info.developia.opia.domain.node.statement;

import info.developia.opia.bytecodegeneration.statement.StatementGenerator;
import info.developia.opia.domain.node.Node;

/**
 * Created by kuba on 02.04.16.
 */
@FunctionalInterface
public interface Statement extends Node {
    void accept(StatementGenerator generator);
}
