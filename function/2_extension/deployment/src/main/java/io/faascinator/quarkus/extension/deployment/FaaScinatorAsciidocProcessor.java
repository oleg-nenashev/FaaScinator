package io.faascinator.quarkus.extension.deployment;

import io.faascinator.quarkus.extension.runtime.AsciidoctorConfig;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import org.asciidoctor.Asciidoctor;

/**
 * Build-time initializer for Asciidoctor.
 * @author Oleg Nenashev
 * @since TODO
 */
public class FaaScinatorAsciidocProcessor {

    private static final String FEATURE_DOCGEN = "faascinator-asciidoctor";

    /**
     * Initializes Asciidoctor components in buildtime so that Asciidoctor and JRuby initializers are cached.
     * @return {@link #FEATURE_DOCGEN}
     */
    @BuildStep
    FeatureBuildItem feature() {
        // Prepare JRuby
        // RubyKernel.require();

        // Prepare Asciidoctor
        final AsciidoctorConfig asciidocConfig = new AsciidoctorConfig();
        Asciidoctor adoc = asciidocConfig.getAsciidoctor();
        final String output = adoc.convert(FEATURE_DOCGEN, asciidocConfig.getOptionsBuilder().build());
        System.out.println(output);

        return new FeatureBuildItem(FEATURE_DOCGEN);
    }
}
