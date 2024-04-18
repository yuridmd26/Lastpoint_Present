package br.com.present.api.gateway.route;

import java.util.function.Function;

import br.com.present.api.gateway.exception.filters.GatewayExceptionFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.http.HttpMethod;

import br.com.present.api.gateway.util.PresentRouteFormatterUtil;
import br.com.present.commons.util.consts.MicroservicesConsts;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PresentFaceIdRoutes {

	public static Function<PredicateSpec, Buildable<Route>> routeDocs() {
		return route -> route.path(PresentRouteFormatterUtil.formatDocsRoute(MicroservicesConsts.FaceId.NAME))
				.and().method(HttpMethod.GET).uri(MicroservicesConsts.FaceId.REFERENCE);
	}

	public static Function<PredicateSpec, Buildable<Route>> routeServiceV1() {
		return route -> route.path(PresentRouteFormatterUtil.formatServiceRoute(MicroservicesConsts.ApiVersions.V1,
				MicroservicesConsts.FaceId.ROUTE)).filters(f -> f.filter(new GatewayExceptionFilter()))
				.uri(MicroservicesConsts.FaceId.REFERENCE);
	}
}