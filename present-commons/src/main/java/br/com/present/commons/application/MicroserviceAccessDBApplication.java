package br.com.present.commons.application;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "br.com.present.commons.model")
public class MicroserviceAccessDBApplication extends MicroserviceApplication {}