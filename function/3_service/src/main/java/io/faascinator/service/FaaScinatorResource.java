package io.faascinator.service;

import io.faascinator.quarkus.extension.runtime.FaaScinatorRuntimeConfig;

import javax.inject.Inject;

public abstract class FaaScinatorResource {

    @Inject
    public FaaScinatorRuntimeConfig config;
}
