package info.developia.opia.bytecodegeneration.statement;

import info.developia.opia.bytecodegeneration.expression.ExpressionGenerator;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.node.statement.ReturnStatement;
import info.developia.opia.domain.type.Type;
import org.objectweb.asm.MethodVisitor;

public class ReturnStatemenetGenerator {
    private final ExpressionGenerator expressionGenerator;
    private final MethodVisitor methodVisitor;

    public ReturnStatemenetGenerator(ExpressionGenerator expressionGenerator, MethodVisitor methodVisitor) {
        this.expressionGenerator = expressionGenerator;
        this.methodVisitor = methodVisitor;
    }

    public void generate(ReturnStatement returnStatement) {
        Expression expression = returnStatement.getExpression();
        Type type = expression.getType();
        expression.accept(expressionGenerator);
        methodVisitor.visitInsn(type.getReturnOpcode());
    }
}