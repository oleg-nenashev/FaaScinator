package io.faascinator.quarkus.extension.runtime;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(phase = ConfigPhase.RUN_TIME, name = "faascinator")
public class FaaScinatorRuntimeConfig {

    public static final String DEFAULT_STRING_VALUE_NULL = "!";

    /**
     * If Swagger UI is included, it should be enabled/disabled. By default, Swagger UI is enabled if it is included (see
     * {@code always-include}).
     */
    @ConfigItem(defaultValue = "true")
    boolean enable;

    /**
     * Name of the class which contains the entry point.
     */
    @ConfigItem(name = "mainClass", defaultValue = DEFAULT_STRING_VALUE_NULL)
    public String mainClass;

    /**
     * Name of the JAR file to be loaded.
     */
    @ConfigItem(name = "cliJar", defaultValue = DEFAULT_STRING_VALUE_NULL)
    public String jarFile;

    /**
     * Describes the function.
     */
    public String description = "CLI Endpoint to be triggered";

    // TODO: Add support for environment variables
    // TODO: Add support for system properties

    public FaaScinatorRuntimeConfig() {
        // To be used by mapping
    }

    public FaaScinatorRuntimeConfig(String mainClass, String jarFile) {
        this.mainClass = mainClass;
        this.jarFile = jarFile;
    }

    /**
     * Gets name of the parameterized class to be used.
     * @return  Main class to be invoked.
     *          {@code null} if the value is undefined.
     */
    public String getClassName() {
        return DEFAULT_STRING_VALUE_NULL.equals(mainClass) ? null : mainClass;
    }

    /**
     * Gets source JAR file which stores the class.
     * This JAR usually includes the dependencies.
     * @return  Path to the JAR file containing CLI.
     *          {@code null} if the value is undefined.
     */
    public String getJarFile() {
        return DEFAULT_STRING_VALUE_NULL.equals(jarFile) ? null : jarFile;
    }

}
