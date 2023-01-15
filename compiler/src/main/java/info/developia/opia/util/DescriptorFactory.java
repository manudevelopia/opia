package info.developia.opia.util;

import info.developia.opia.domain.Function;
import info.developia.opia.domain.node.expression.Parameter;
import info.developia.opia.domain.scope.FunctionSignature;
import info.developia.opia.domain.type.Type;

import java.util.Collection;
import java.util.stream.Collectors;

//According to https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.3
public final class DescriptorFactory {

    public static String getMethodDescriptor(Function function) {
        Collection<Parameter> parameters = function.getParameters();
        Type returnType = function.getReturnType();
        return getMethodDescriptor(parameters, returnType);
    }

    public static String getMethodDescriptor(FunctionSignature signature) {
        Collection<Parameter> parameters = signature.getParameters();
        Type returnType = signature.getReturnType();
        return getMethodDescriptor(parameters, returnType);
    }

    private static String getMethodDescriptor(Collection<Parameter> parameters, Type returnType) {
        String parametersDescriptor = parameters.stream()
                .map(parameter -> parameter.getType().getDescriptor())
                .collect(Collectors.joining("", "(", ")"));
        String returnDescriptor = returnType.getDescriptor();
        return parametersDescriptor + returnDescriptor;
    }
}
