package info.developia.opia.exception;

import info.developia.opia.domain.scope.FunctionSignature;

/**
 * Created by kuba on 08.05.16.
 */
public class MethodWithNameAlreadyDefinedException extends RuntimeException {
    public MethodWithNameAlreadyDefinedException(FunctionSignature signature) {
        super("Method already defined in scope :" + signature);
    }
}
