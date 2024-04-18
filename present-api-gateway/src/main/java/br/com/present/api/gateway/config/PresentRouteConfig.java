package br.com.present.api.gateway.config;

import br.com.present.api.gateway.route.*;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PresentRouteConfig {

    @Bean
    public RouteLocator configRouteLocator(RouteLocatorBuilder builder) {
        return builder
        		.routes()
        		.route(PresentAttendancesRoutes.routeDocs())
        		.route(PresentAuthsRoutes.routeDocs())
        		.route(PresentClassesRoutes.routeDocs())
        		.route(PresentCoursesRoutes.routeDocs())
				.route(PresentDisciplinesRoutes.routeDocs())
        		.route(PresentEventsRoutes.routeDocs())
        		.route(PresentFaceIdRoutes.routeDocs())
        		.route(PresentGroupsRoutes.routeDocs())
        		.route(PresentUsersRoutes.routeDocs())
        		.route(PresentAttendancesRoutes.routeServiceV1())
        		.route(PresentAuthsRoutes.routeServiceV1())
        		.route(PresentClassesRoutes.routeServiceV1())
        		.route(PresentCoursesRoutes.routeServiceV1())
				.route(PresentDisciplinesRoutes.routeServiceV1())
        		.route(PresentEventsRoutes.routeServiceV1())
        		.route(PresentFaceIdRoutes.routeServiceV1())
        		.route(PresentGroupsRoutes.routeServiceV1())
        		.route(PresentUsersRoutes.routeServiceV1())
	            .build();
    }
}