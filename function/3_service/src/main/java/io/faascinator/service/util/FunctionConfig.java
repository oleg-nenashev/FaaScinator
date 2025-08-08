package io.faascinator.service.util;

import io.faascinator.quarkus.extension.runtime.FaaScinatorRuntimeConfig;
import io.faascinator.service.FaaScinatorResource;
import io.faascinator.service.demo.EmbeddedDemoApp;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Tools for configuration
 * @author Oleg Nenashev
 * @since TODO
 */
public class FunctionConfig {

    public static final String DEFAULT_CLI_APP_CLASS = EmbeddedDemoApp.class.getName();
    public static final FaaScinatorRuntimeConfig DEMO_PICOCLI_CHECKSUM =
            new DemoConfig();

    @ApplicationScoped
    public static class DemoConfig implements FaaScinatorRuntimeConfig {

        @Override
        public boolean enable() {
            return true;
        }

        @Override
        public String mainClass() {
            return DEFAULT_CLI_APP_CLASS;
        }

        @Override
        public String jarFile() {
            return null;
        }

        @Override
        public String description() {
            return "Demonstrates usage of FaaScinator with embedded CLI App JAR (CheckSum calculator)";
        }
    }
}
