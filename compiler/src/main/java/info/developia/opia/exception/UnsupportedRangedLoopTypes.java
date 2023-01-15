package info.developia.opia.exception;

import info.developia.opia.domain.node.expression.Expression;

/**
 * Created by kuba on 23.04.16.
 */
public class UnsupportedRangedLoopTypes extends RuntimeException {
    public UnsupportedRangedLoopTypes(Expression startExpression, Expression endExpression) {
        super("Only integer types are supported so far");
    }
}
