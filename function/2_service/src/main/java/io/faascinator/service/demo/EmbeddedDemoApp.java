// Based on the CheckSum example in https://picocli.info/#_example_application
package io.faascinator.service.demo;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(name = "checksum", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Prints the checksum (MD5 by default) of a specified string to STDOUT.")
public class EmbeddedDemoApp implements Callable<Integer> {

    @Parameters(index = "0", description = "The string whose checksum to calculate.")
    private String value = "testValue";

    @Option(names = {"-a", "--algorithm"}, description = "MD5, SHA-1, SHA-256, ...")
    private String algorithm = "MD5";

    @Override
    @SuppressFBWarnings(value = "WEAK_MESSAGE_DIGEST_MD5", justification = "As designed in the demo, not used for crypto")
    public Integer call() throws Exception {
        byte[] digest = MessageDigest.getInstance(algorithm).digest(value.getBytes(StandardCharsets.UTF_8));
        System.out.printf("%0" + (digest.length*2) + "x%n", new BigInteger(1, digest));
        return 0;
    }

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new EmbeddedDemoApp()).execute(args);
        System.exit(exitCode);
    }
}
