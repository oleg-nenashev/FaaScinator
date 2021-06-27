// Based on the CheckSum example in https://picocli.info/#_example_application
package io.faascinator.demo.currenttime;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Date;
import java.util.concurrent.Callable;

@Command(name = "checksum", mixinStandardHelpOptions = true, version = "currenttime 1.0-SNAPSHOT",
        description = "Prints the current time")
class CurrentTime implements Callable<Integer> {

    public static final String UNDEFINED_VALUE = "!";

    @Parameters(index = "0", description = "Timezone to be used.", defaultValue = UNDEFINED_VALUE)
    private String timezone = null;

    @Override
    public Integer call() throws Exception {
        DateTimeZone zone = DateTimeZone.forID(UNDEFINED_VALUE.equals(timezone) ? null : timezone);
        DateTime dt = new DateTime(zone);
        System.out.println(dt.toString());
        return 0;
    }

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new CurrentTime()).execute(args);
        System.exit(exitCode);
    }
}
