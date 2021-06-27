package io.faascinator.service;

import io.faascinator.service.util.FunctionConfig;

import javax.inject.Inject;

public abstract class FaaScinatorResource {

    @Inject
    public FunctionConfig config;
}
