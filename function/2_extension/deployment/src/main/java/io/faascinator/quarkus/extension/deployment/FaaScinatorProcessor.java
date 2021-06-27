package io.faascinator.quarkus.extension.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class FaaScinatorProcessor {

    private static final String FEATURE = "faascinator";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }
}
