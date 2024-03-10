package io.faascinator.quarkus.extension.runtime;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.jruby.internal.JRubyAsciidoctor;

/**
 * Asciidoctor build time configuration.
 * @author Oleg Nenashev
 * @since TODO
 */
@ConfigRoot(phase = ConfigPhase.BUILD_TIME, name = "asciidoctor")
public class AsciidoctorConfig {

    /**
     * Documentation type for the generated HTML files.
     */
    @ConfigItem(name = "docType", defaultValue = "manpage")
    public String docType;

    public static Asciidoctor asciidoctor;

    public AsciidoctorConfig() {
        // Noop
    }

    public static void setAsciidoctor(Asciidoctor asciidoctorToSet) {
        asciidoctor = asciidoctorToSet;
    }

    public Asciidoctor getAsciidoctor() {
        if (asciidoctor == null) {
            asciidoctor = new JRubyAsciidoctor();
        //    throw new IllegalStateException("The Asciidoctor instance has not been initialized yet. " +
        //            "Quarkus is expected to do it at the build time");
        }
        return asciidoctor;
    }

    public OptionsBuilder getOptionsBuilder() {
        return Options.builder().docType(docType);
    }
}
