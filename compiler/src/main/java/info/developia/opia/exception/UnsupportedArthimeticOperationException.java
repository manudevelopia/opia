package info.developia.opia.exception;

import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.node.expression.arthimetic.ArthimeticExpression;

/**
 * Created by kuba on 10.04.16.
 */
public class UnsupportedArthimeticOperationException extends RuntimeException {
    public UnsupportedArthimeticOperationException(ArthimeticExpression expression) {
        super(prepareMesage(expression));
    }

    private static String prepareMesage(ArthimeticExpression expression) {
        Expression leftExpression = expression.getLeftExpression();
        Expression rightExpression = expression.getRightExpression();
        return "Unsupported arthimetic operation between " + leftExpression + " and " + rightExpression;
    }
}
