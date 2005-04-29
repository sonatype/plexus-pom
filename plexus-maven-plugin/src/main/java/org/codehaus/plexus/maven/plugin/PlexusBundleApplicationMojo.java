package org.codehaus.plexus.maven.plugin;

/*
 * Copyright (c) 2004, Codehaus.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import org.codehaus.plexus.builder.application.ApplicationBuilder;
import org.codehaus.plexus.builder.application.ApplicationBuilderException;

/**
 * @goal bundle-application
 *
 * @requiresDependencyResolution
 *
 * @description Packages the Plexus application into a redistributable jar file.
 *
 * @author <a href="mailto:trygvis@inamo.no">Trygve Laugst&oslash;l</a>
 * @version $Id$
 */
public class PlexusBundleApplicationMojo
    extends AbstractMojo
{
    /**
     * @parameter name="basedir"
     * type="String"
     * required="true"
     * validator=""
     * expression="#basedir"
     * description=""
     */
    private File basedir;

    /**
     * @parameter type="String"
     * required="true"
     * validator=""
     * expression="#project.build.directory"
     * description=""
     */
    private File target;

    /**
     * @parameter name="finalName"
     * type="java.lang.String"
     * required="true"
     * validator=""
     * expression="#maven.final.name"
     * description=""
     */
    private String finalName;

    /**
     * @parameter name="applicationBuilder"
     * type="org.codehaus.plexus.builder.application.ApplicationBuilder"
     * required="true"
     * validator=""
     * expression="#component.org.codehaus.plexus.builder.application.ApplicationBuilder"
     * description=""
     */
    private ApplicationBuilder builder;

    /**
     * @parameter name="project"
     * type="org.apache.maven.project.MavenProject"
     * required="true"
     * validator=""
     * expression="#project"
     * description="current MavenProject instance"
     */
    private MavenProject project;

    public void execute()
        throws MojoExecutionException
    {
        // ----------------------------------------------------------------------
        //
        // ----------------------------------------------------------------------

        File applicationDirectory = new File( target, "plexus-application" );

        File outputFile = getApplicationJarFile( target, finalName );

        try
        {
            builder.bundle( outputFile, applicationDirectory );
        }
        catch ( ApplicationBuilderException e )
        {
            throw new MojoExecutionException( "Error while bundling application.", e );
        }
    }

    public static File getApplicationJarFile( File outputDirectory, String finalName )
    {
        return new File( outputDirectory, finalName + "-application.jar" );
    }
}
