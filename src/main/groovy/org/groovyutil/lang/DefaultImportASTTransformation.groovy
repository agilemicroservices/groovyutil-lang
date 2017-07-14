package org.groovyutil.lang

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ModuleNode
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.jvmscript.cli.CliUtility
import org.jvmscript.datetime.DateTimeUtility
import org.jvmscript.drools.DroolsUtility
import org.jvmscript.email.EmailUtility
import org.jvmscript.file.FileUtility
import org.jvmscript.ftp.FTPUtility
import org.jvmscript.http.HttpUtility
import org.jvmscript.jams.JamsUtility
import org.jvmscript.jira.JiraUtility
import org.jvmscript.log.LogUtility
import org.jvmscript.pgp.PgpUtility
import org.jvmscript.process.ProcessUtility
import org.jvmscript.property.PropertyUtility
import org.jvmscript.sql.SqlUtility

import org.hibernate.annotations.SQLUpdate


/**
 * Groovy ASTTransformation implementation decorating scripts with static imports for standard utility classes.
 */
@GroovyASTTransformation(phase = CompilePhase.CONVERSION)
class DefaultImportASTTransformation implements ASTTransformation {
    // list of classes to be statically imported
    private static final Class[] DEFAULT_STATIC_IMPORT_CLASSES = [
            CliUtility,
            DateTimeUtility,
            DroolsUtility,
            FileUtility,
            EmailUtility,
            FTPUtility,
            HttpUtility,
            JamsUtility,
            JiraUtility,
            LogUtility,
            PgpUtility,
            ProcessUtility,
            PropertyUtility,
            SqlUtility ]

    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
        ModuleNode node = source.getAST()
        DEFAULT_STATIC_IMPORT_CLASSES.each {
            node.addStaticStarImport(it.simpleName, ClassHelper.make(it))
        }
    }
}
