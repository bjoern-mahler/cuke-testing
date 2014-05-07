package de.spinwork.datacuke.testutil;


import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class EmbeddedTomcat {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedTomcat.class);

    protected static final String WEBAPP_DIRECTORY = "src/main/webapp";
    protected static final int TOMCAT_PORT = 8090;

    private Tomcat tomcat;

    public void start() {
        try {
            String path = getWebappDirectory();
            logger.info("Starting tomcat on port {} in directory {}", TOMCAT_PORT, path);
            tomcat = new Tomcat();
            tomcat.setPort(TOMCAT_PORT);
            tomcat.setBaseDir(path);
            tomcat.getHost().setAppBase(path);
            tomcat.getHost().setDeployOnStartup(true);
            tomcat.getHost().setAutoDeploy(true);
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

    private String getWebappDirectory() {
        return new File(WEBAPP_DIRECTORY).getAbsolutePath();
    }

    public void stop() {
        try {
            tomcat.stop();
            tomcat.destroy();

            // Tomcat creates a work folder where the temporary files are stored
            FileUtils.deleteDirectory(new File("work"));
            FileUtils.deleteDirectory(new File("tomcat.8090"));
        } catch (LifecycleException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deploy(String appName) {
        tomcat.addWebapp(tomcat.getHost(), "/" + appName, getWebappDirectory());
    }

    public String getApplicationUrl(String appName) {
        return String.format("http://%s:%d/%s", tomcat.getHost().getName(),
                tomcat.getConnector().getLocalPort(), appName);
    }

    public boolean isRunning() {
        return tomcat != null;
    }
}
