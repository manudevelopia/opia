package info.developia.opia.domain.node.expression.arthimetic;

import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.type.BultInType;
import info.developia.opia.domain.type.Type;

/**
 * Created by kuba on 10.04.16.
 */
public abstract class ArthimeticExpression implements Expression {

    private final Expression leftExpression;
    private final Expression rightExpression;
    private final Type type;

    protected ArthimeticExpression(Expression leftExpression, Expression rightExpression) {
        this.type = getCommonType(leftExpression, rightExpression);
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    private static Type getCommonType(Expression leftExpression, Expression rightExpression) {
        if (rightExpression.getType() == BultInType.STRING) return BultInType.STRING;
        return leftExpression.getType();
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    @Override
    public Type getType() {
        return type;
    }
}
