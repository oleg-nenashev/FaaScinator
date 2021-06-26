package io.faascinator.service.util;

import picocli.CommandLine;

import java.io.IOException;

/**
 * Extracts metadata from picocli.
 * @author Oleg Nenashev
 * @since TODO
 */
public class PicocliExtractor {

    public static CommandLine extractCommandLine(FunctionConfig config) throws IOException {

        final Class<?> clazz;
        try {
            clazz = Class.forName(config.getClassName());
        } catch (ClassNotFoundException ex) {
            throw new IOException("Cannot find command arguments class for picocli: " + config.getClassName(), ex);
        }

        return new CommandLine(clazz);
    }

}
