package br.com.present.api.gateway.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PresentRouteFormatterUtil {

	private static final String BAR = "/";
	private static final String ALL_PATHS = "/**";
	private static final String DOCS_SUFFIX = "/api-docs";

	public String formatDocsRoute(String routeName) {
		return BAR + routeName + DOCS_SUFFIX;
	}

	public String formatServiceRoute(String version, String route) {
		return PresentRouteFormatterUtil.BAR + version + route + PresentRouteFormatterUtil.ALL_PATHS;
	}
}