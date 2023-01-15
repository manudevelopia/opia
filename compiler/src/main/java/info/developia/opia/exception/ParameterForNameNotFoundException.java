package info.developia.opia.exception;

import info.developia.opia.domain.node.expression.Parameter;

import java.util.List;

/**
 * Created by kuba on 17.04.16.
 */
public class ParameterForNameNotFoundException extends RuntimeException {
    public ParameterForNameNotFoundException(String name, List<Parameter> parameters) {
    }
}
