package org.codehaus.plexus;

import junit.framework.TestCase;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.codehaus.plexus.context.Context;

public class PlexusTestCase
    extends TestCase
{
    private DefaultPlexusContainer container;

    public String basedir = System.getProperty( "basedir" );

    public PlexusTestCase()
    {
    }

    public PlexusTestCase( String testName )
    {
        super( testName );
    }

    protected void setUp()
        throws Exception
    {

        InputStream configuration = null;

        try
        {
            configuration = getCustomConfiguration();

            if ( configuration == null )
            {
                configuration = getConfiguration();
            }
        }
        catch ( Exception e )
        {
            System.out.println( "Error with configuration:" );

            System.out.println( "configuration = " + configuration );

            fail( e.getMessage() );
        }

        container = new DefaultPlexusContainer();

        container.addContextValue( "basedir", basedir );

        customizeContext();
        
        boolean hasPlexusHome =  getContext().contains( "plexus.home" );

        if ( !hasPlexusHome )
        {

            File f = new File( basedir, "target/plexus-home" );

            if ( !f.isDirectory() )
            {
                f.mkdir();
            }

            getContext().put( "plexus.home", f.getAbsolutePath() );

        }
        
        
        if ( configuration != null )
        {
            container.setConfigurationResource( new InputStreamReader( configuration ) );
        }

        container.initialize();

        container.start();
    }

    /**
     * @return
     */
    private Context getContext()
    {
        return container.getContext();
    }

    //!!! this should probably take a context as a parameter so that the
    //    user is not forced to do getContainer().addContextValue(..)
    //    this would require a change to PlexusContainer in order to get
    //    hold of the context ...
    protected void customizeContext()
        throws Exception
    {
    }

    protected InputStream getCustomConfiguration()
        throws Exception
    {
        return null;
    }

    protected void tearDown()
        throws Exception
    {
        container.dispose();

        container = null;
    }

    protected DefaultPlexusContainer getContainer()
    {
        return container;
    }

    protected InputStream getConfiguration()
        throws Exception
    {
        return getConfiguration( null );
    }

    protected InputStream getConfiguration( String subname )
        throws Exception
    {
        String className = getClass().getName();

        String base = className.substring( className.lastIndexOf( "." ) + 1 );

        String config = null;

        if ( subname == null
            || subname.equals( "" ) )
        {
            config = base + ".xml";
        }
        else
        {
            config = base + "-" + subname + ".xml";
        }

        InputStream configStream = getResourceAsStream( config );

        return configStream;
    }

    protected InputStream getResourceAsStream( String resource )
    {
        return getClass().getResourceAsStream( resource );
    }

    protected ClassLoader getClassLoader()
    {
        return getClass().getClassLoader();
    }

    protected Object lookup( String componentKey )
        throws Exception
    {
        return getContainer().lookup( componentKey );
    }

    protected Object lookup( String role, String id )
        throws Exception
    {
        return getContainer().lookup( role, id );
    }

    protected void release( Object component )
        throws Exception
    {
        getContainer().release( component );
    }

    public String getTestFile( String path )
    {
        return new File( basedir, path ).getAbsolutePath();
    }

    public String getTestFile( String basedir, String path )
    {
        return new File( basedir, path ).getAbsolutePath();
    }
}
