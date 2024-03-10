package io.faascinator.service;

import io.faascinator.quarkus.extension.runtime.FaaScinatorRuntimeConfig;
import io.faascinator.service.util.FunctionConfig;
import jakarta.inject.Inject;


public abstract class FaaScinatorResource {

    @Inject
    public FaaScinatorRuntimeConfig config;
}
