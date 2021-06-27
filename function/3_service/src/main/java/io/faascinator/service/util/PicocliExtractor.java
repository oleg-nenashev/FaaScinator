package io.faascinator.service.util;

import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import picocli.CommandLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Extracts metadata from picocli.
 * @author Oleg Nenashev
 * @since TODO
 */
public class PicocliExtractor {

    public static CommandLine extractCommandLine(FunctionConfig config) throws IOException {
        final Class<?> clazz;
        try {
            clazz = loadClass(config.getClassName(), config.getJarFile());
        } catch (ClassNotFoundException ex) {
            throw new IOException("Cannot find command arguments class for picocli: " + config.getClassName(), ex);
        }

        return new CommandLine(clazz);
    }

    //TODO: Add some security guardrails
    @SuppressFBWarnings(value = "PATH_TRAVERSAL_IN", justification = "As designed in PoC")
    private static Class<?> loadClass(String className, @CheckForNull String jar) throws ClassNotFoundException, IOException {
        ClassLoader cl = PicocliExtractor.class.getClassLoader();
        if (jar != null) {
            final File jarFile = new File(jar);
            if (!jarFile.exists()) {
                throw new FileNotFoundException("Source JAR file not found: " + jar);
            }
            if (!jarFile.isFile()) {
                throw new FileNotFoundException("Source JAR path is not a file: " + jar);
            }
            cl = new URLClassLoader(new URL[]{jarFile.toURI().toURL()}, cl);
        }
        return Class.forName(className, true, cl);
    }

}
