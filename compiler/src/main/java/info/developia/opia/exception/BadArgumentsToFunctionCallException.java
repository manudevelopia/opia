package info.developia.opia.exception;

import info.developia.opia.domain.node.expression.Call;

/**
 * Created by kuba on 16.04.16.
 */
public class BadArgumentsToFunctionCallException extends RuntimeException {
    public BadArgumentsToFunctionCallException(Call functionCall) {
        super("You called function with bad arguments " + functionCall);
    }
}
