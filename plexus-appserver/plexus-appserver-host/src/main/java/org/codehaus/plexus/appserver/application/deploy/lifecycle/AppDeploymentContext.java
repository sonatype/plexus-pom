package org.codehaus.plexus.appserver.application.deploy.lifecycle;

import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.codehaus.plexus.appserver.application.profile.AppRuntimeProfile;

import java.io.File;
import java.util.Map;
import java.util.Properties;

/**
 * @author Jason van Zyl
 */
public class AppDeploymentContext
{
    private File par;

    private File applicationsDirectory;

    private Map deployments;

    private DefaultPlexusContainer appServerContainer;

    private Properties context;

    //

    private String applicationId;

    private PlexusConfiguration appConfiguration;

    private AppRuntimeProfile appRuntimeProfile;

    private DefaultPlexusContainer applicationContainer;

    private File appConfigurationFile;

    // app.home/lib used for populating the class realm.
    private File appLibDirectory;

    public AppDeploymentContext( File par,
                                 File applicationsDirectory,
                                 Map deployments,
                                 DefaultPlexusContainer appServerContainer,
                                 Properties context )
    {
        this.par = par;
        this.applicationsDirectory = applicationsDirectory;
        this.deployments = deployments;
        this.appServerContainer = appServerContainer;
        this.context = context;
    }

    // Read-only

    public File getPar()
    {
        return par;
    }

    public File getApplicationsDirectory()
    {
        return applicationsDirectory;
    }

    public Map getDeployments()
    {
        return deployments;
    }

    public DefaultPlexusContainer getAppServerContainer()
    {
        return appServerContainer;
    }

    public Properties getContext()
    {
        return context;
    }

    // Properties

    public String getApplicationId()
    {
        return applicationId;
    }

    public void setApplicationId( String applicationId )
    {
        this.applicationId = applicationId;
    }

    public PlexusConfiguration getAppConfiguration()
    {
        return appConfiguration;
    }

    public void setAppConfiguration( PlexusConfiguration appConfiguration )
    {
        this.appConfiguration = appConfiguration;
    }

    public AppRuntimeProfile getAppRuntimeProfile()
    {
        return appRuntimeProfile;
    }

    public void setAppRuntimeProfile( AppRuntimeProfile appRuntimeProfile )
    {
        this.appRuntimeProfile = appRuntimeProfile;
    }

    public DefaultPlexusContainer getApplicationContainer()
    {
        return applicationContainer;
    }

    public void setApplicationContainer( DefaultPlexusContainer applicationContainer )
    {
        this.applicationContainer = applicationContainer;
    }

    public File getAppConfigurationFile()
    {
        return appConfigurationFile;
    }

    public void setAppConfigurationFile( File appConfigurationFile )
    {
        this.appConfigurationFile = appConfigurationFile;
    }

    public File getAppLibDirectory()
    {
        return appLibDirectory;
    }

    public void setAppLibDirectory( File appLibDirectory )
    {
        this.appLibDirectory = appLibDirectory;
    }
}

