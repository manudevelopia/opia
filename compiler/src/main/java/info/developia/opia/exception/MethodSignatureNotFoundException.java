package info.developia.opia.exception;

import info.developia.opia.domain.node.expression.Argument;
import info.developia.opia.domain.scope.Scope;

import java.util.List;

/**
 * Created by kuba on 09.04.16.
 */
public class MethodSignatureNotFoundException extends RuntimeException {
    public MethodSignatureNotFoundException(Scope scope, String methodName, List<Argument> parameterTypes) {
        super("There is no method '" + methodName + "' with parameters " + parameterTypes);
    }
}
