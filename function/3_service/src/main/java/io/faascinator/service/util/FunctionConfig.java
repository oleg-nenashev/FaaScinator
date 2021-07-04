package io.faascinator.service.util;

import io.faascinator.quarkus.extension.runtime.FaaScinatorRuntimeConfig;
import io.faascinator.service.demo.EmbeddedDemoApp;

/**
 * Tools for configuration
 * @author Oleg Nenashev
 * @since TODO
 */
public class FunctionConfig {

    public static final String DEFAULT_CLI_APP_CLASS = EmbeddedDemoApp.class.getName();
    public static final FaaScinatorRuntimeConfig DEMO_PICOCLI_CHECKSUM =
            new FaaScinatorRuntimeConfig(DEFAULT_CLI_APP_CLASS, null);
}
