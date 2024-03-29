package info.developia.opia.exception;

import info.developia.opia.domain.node.statement.Statement;

/**
 * Created by kuba on 11.04.16.
 */
public class LastStatementNotReturnableException extends RuntimeException {
    public LastStatementNotReturnableException(Statement lastStatement) {
        super("The statement " + lastStatement + " is a last statement in a functon, but it is not an expression!");
    }
}
