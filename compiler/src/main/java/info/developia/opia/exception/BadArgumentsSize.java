package info.developia.opia.exception;

import info.developia.opia.OpiaParser.ExpressionContext;
import info.developia.opia.domain.scope.FunctionSignature;

import java.util.List;

/**
 * Created by kuba on 06.04.16.
 */
public class BadArgumentsSize extends RuntimeException {
    public BadArgumentsSize(FunctionSignature function, List<ExpressionContext> calledParameters) {
        super("Bad arguments amount");
    }
}
