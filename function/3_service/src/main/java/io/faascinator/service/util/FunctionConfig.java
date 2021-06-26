package io.faascinator.service.util;

import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Stores configuration of the FaaS function
 * @author Oleg Nenashev
 * @since TODO
 */
public class FunctionConfig {

    public static final FunctionConfig DEMO_PICOCLI_CHECKSUM =
            new FunctionConfig("io.faascinator.demo.picocli.CheckSum","doesnotmatter.jar");

    private final String className;
    private final String jarName;

    // TODO: Add support for environment variables
    // TODO: Add support for system properties

    /*package*/ FunctionConfig(String className, String jarName) {
        this.className = className;
        this.jarName = jarName;
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
     */
    public String getJarName() {
        return jarName;
    }
}
