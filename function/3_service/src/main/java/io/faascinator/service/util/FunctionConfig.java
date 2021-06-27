package io.faascinator.service.util;

import edu.umd.cs.findbugs.annotations.CheckForNull;
import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.validation.constraints.Size;
import java.io.File;

/**
 * Stores configuration of the FaaS function
 * @author Oleg Nenashev
 * @since TODO
 */
@ConfigProperties(prefix = "faascinator")
public class FunctionConfig {

    public static final String DEFAULT_CLI_APP_CLASS = "io.faascinator.demo.picocli.CheckSum";
    public static final String DEFAULT_CLI_JAR_PATH = "!";
    public static final FunctionConfig DEMO_PICOCLI_CHECKSUM =
            new FunctionConfig(DEFAULT_CLI_APP_CLASS,null);

    /**
     * Name of the class which contains the entry point.
     */
    @Size(min = 1)
    @ConfigProperty(name = "mainClass")
    public String className = DEFAULT_CLI_APP_CLASS;

    /**
     * Name of the JAR file to be loaded.
     */
    @CheckForNull
    @ConfigProperty(name = "cliJar")
    public String jarFile = DEFAULT_CLI_JAR_PATH;

    /**
     * Describes the function.
     */
    public String description = "CLI Endpoint to be triggered";

    // TODO: Add support for environment variables
    // TODO: Add support for system properties

    public FunctionConfig() {
        // To be used by mapping
    }

    /*package*/ FunctionConfig(String className, @CheckForNull String jarFile) {
        this.className = className;
        this.jarFile = jarFile;
    }

    /**
     * Gets name of the parameterized class to be used.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets source JAR file which stores the class.
     * This JAR usually includes the dependencies.
     * @return  Path to the JAR file containing CLI.
     *          {@code null} if the value is undefined.
     */
    @CheckForNull
    public String getJarFile() {
        return DEFAULT_CLI_JAR_PATH.equals(jarFile) ? null : jarFile;
    }
}
