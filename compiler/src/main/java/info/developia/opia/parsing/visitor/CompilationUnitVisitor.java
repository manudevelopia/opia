package info.developia.opia.parsing.visitor;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ClassDeclarationContext;
import info.developia.opia.OpiaParser.CompilationUnitContext;
import info.developia.opia.domain.ClassDeclaration;
import info.developia.opia.domain.CompilationUnit;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by kuba on 28.03.16.
 */
public class CompilationUnitVisitor extends OpiaBaseVisitor<CompilationUnit> {

    @Override
    public CompilationUnit visitCompilationUnit(@NotNull CompilationUnitContext ctx) {
        ClassVisitor classVisitor = new ClassVisitor();
        ClassDeclarationContext classDeclarationContext = ctx.classDeclaration();
        ClassDeclaration classDeclaration = classDeclarationContext.accept(classVisitor);
        return new CompilationUnit(classDeclaration);
    }
}
