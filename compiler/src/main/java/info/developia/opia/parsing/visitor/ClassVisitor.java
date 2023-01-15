package info.developia.opia.parsing.visitor;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ClassDeclarationContext;
import info.developia.opia.OpiaParser.FunctionContext;
import info.developia.opia.domain.ClassDeclaration;
import info.developia.opia.domain.Constructor;
import info.developia.opia.domain.Function;
import info.developia.opia.domain.MetaData;
import info.developia.opia.domain.node.expression.ConstructorCall;
import info.developia.opia.domain.node.expression.FunctionCall;
import info.developia.opia.domain.node.expression.Parameter;
import info.developia.opia.domain.node.statement.Block;
import info.developia.opia.domain.scope.Field;
import info.developia.opia.domain.scope.FunctionSignature;
import info.developia.opia.domain.scope.Scope;
import info.developia.opia.domain.type.BultInType;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by kuba on 01.04.16.
 */
public class ClassVisitor extends OpiaBaseVisitor<ClassDeclaration> {

    private Scope scope;

    @Override
    public ClassDeclaration visitClassDeclaration(@NotNull ClassDeclarationContext ctx) {
        MetaData metaData = new MetaData(ctx.className().getText(), "java.lang.Object");
        scope = new Scope(metaData);
        String name = ctx.className().getText();
        FieldVisitor fieldVisitor = new FieldVisitor(scope);
        FunctionSignatureVisitor functionSignatureVisitor = new FunctionSignatureVisitor(scope);
        List<FunctionContext> methodsCtx = ctx.classBody().function();
        List<Field> fields = ctx.classBody().field().stream()
                .map(field -> field.accept(fieldVisitor))
                .peek(scope::addField)
                .collect(toList());
        methodsCtx.stream()
                .map(method -> method.functionDeclaration().accept(functionSignatureVisitor))
                .forEach(scope::addSignature);
        boolean defaultConstructorExists = scope.isParameterLessSignatureExists(name);
        addDefaultConstructorSignatureToScope(name, defaultConstructorExists);
        List<Function> methods = methodsCtx.stream()
                .map(method -> method.accept(new FunctionVisitor(scope)))
                .collect(toList());
        if (!defaultConstructorExists) {
            methods.add(getDefaultConstructor());
        }
        boolean startMethodDefined = scope.isParameterLessSignatureExists("start");
        if (startMethodDefined) {
            methods.add(getGeneratedMainMethod());
        }

        return new ClassDeclaration(name, fields, methods);
    }

    private void addDefaultConstructorSignatureToScope(String name, boolean defaultConstructorExists) {
        if (!defaultConstructorExists) {
            FunctionSignature constructorSignature = new FunctionSignature(name, Collections.emptyList(), BultInType.VOID);
            scope.addSignature(constructorSignature);
        }
    }

    private Constructor getDefaultConstructor() {
        FunctionSignature signature = scope.getMethodCallSignatureWithoutParameters(scope.getClassName());
        return new Constructor(signature, Block.empty(scope));
    }

    private Function getGeneratedMainMethod() {
        Parameter args = new Parameter("args", BultInType.STRING_ARR, Optional.empty());
        FunctionSignature functionSignature = new FunctionSignature("main", Collections.singletonList(args), BultInType.VOID);
        ConstructorCall constructorCall = new ConstructorCall(scope.getClassName());
        FunctionSignature startFunSignature = new FunctionSignature("start", Collections.emptyList(), BultInType.VOID);
        FunctionCall startFunctionCall = new FunctionCall(startFunSignature, Collections.emptyList(), scope.getClassType());
        Block block = new Block(new Scope(scope), Arrays.asList(constructorCall, startFunctionCall));
        return new Function(functionSignature, block);
    }
}
