package info.developia.opia.domain;

import info.developia.opia.bytecodegeneration.MethodGenerator;
import info.developia.opia.domain.node.statement.Statement;
import info.developia.opia.domain.scope.FunctionSignature;
import info.developia.opia.domain.type.BultInType;
import info.developia.opia.domain.type.Type;

/**
 * Created by kuba on 07.05.16.
 */
public class Constructor extends Function {
    public Constructor(FunctionSignature signature, Statement block) {
        super(signature, block);
    }

    @Override
    public Type getReturnType() {
        return BultInType.VOID;
    }

    @Override
    public void accept(MethodGenerator generator) {
        generator.generate(this);
    }
}
