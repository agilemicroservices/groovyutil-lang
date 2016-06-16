package org.groovyutil.lang

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ModuleNode
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.groovyutil.email.EmailUtility
import org.groovyutil.file.FileUtility
import org.groovyutil.ftp.FTPUtility
import org.groovyutil.http.HttpUtility
import org.groovyutil.log.LogUtility
import org.groovyutil.pgp.PgpUtility
import org.groovyutil.sql.SqlUtility
import org.groovyutil.property.PropertyUtility
import org.hibernate.annotations.SQLUpdate


/**
 * Groovy ASTTransformation implementation decorating scripts with static imports for standard utility classes.
 */
@GroovyASTTransformation(phase = CompilePhase.CONVERSION)
class DefaultImportASTTransformation implements ASTTransformation {
    // list of classes to be statically imported
    private static final Class[] DEFAULT_STATIC_IMPORT_CLASSES = [
            FileUtility, EmailUtility, FTPUtility, HttpUtility, PgpUtility, SqlUtility, LogUtility, PropertyUtility]

    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
        ModuleNode node = source.getAST()
        DEFAULT_STATIC_IMPORT_CLASSES.each {
            node.addStaticStarImport(it.simpleName, ClassHelper.make(it))
        }
    }
}
