package io.faascinator.quarkus.extension.runtime;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigMapping(prefix = "faascinator")
public interface FaaScinatorRuntimeConfig {

    public static final String DEFAULT_STRING_VALUE_NULL = "!";

    /**
     * If Swagger UI is included, it should be enabled/disabled. By default, Swagger UI is enabled if it is included (see
     * {@code always-include}).
     */
    @WithDefault("true")
    boolean enable();

    /**
     * Name of the class that contains the entry point.
     */
    @WithDefault(DEFAULT_STRING_VALUE_NULL)
    public String mainClass();

    /**
     * Name of the JAR file to be loaded.
     */
    @WithDefault(DEFAULT_STRING_VALUE_NULL)
    public String jarFile();

    /**
     * Describes the function.
     */
    @WithDefault("CLI Endpoint to be triggered")
    public String description();

    // TODO: Add support for environment variables
    // TODO: Add support for system properties


    /**
     * Gets name of the parameterized class to be used.
     * @return  Main class to be invoked.
     *          {@code null} if the value is undefined.
     */
    default public String getClassName() {
        return DEFAULT_STRING_VALUE_NULL.equals(mainClass()) ? null : mainClass();
    }

    /**
     * Gets source JAR file which stores the class.
     * This JAR usually includes the dependencies.
     * @return  Path to the JAR file containing CLI.
     *          {@code null} if the value is undefined.
     */
    default public String getJarFile() {
        return DEFAULT_STRING_VALUE_NULL.equals(jarFile()) ? null : jarFile();
    }

}
