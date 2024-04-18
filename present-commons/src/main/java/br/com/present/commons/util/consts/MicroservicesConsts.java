package br.com.present.commons.util.consts;

import lombok.experimental.UtilityClass;

public interface MicroservicesConsts {

	@UtilityClass
	class ApiVersions {
		public static final String V1 = "v1";
	}
	
	@UtilityClass
	class Gateway {
		public static final String NAME = "present-api-gateway";
		public static final String REFERENCE = "lb://present-api-gateway";
		public static final String ROUTE = "/";
	}
	
	@UtilityClass
	class Attendance {
		public static final String NAME = "present-attendances";
		public static final String REFERENCE = "lb://present-attendances";
		public static final String ROUTE = "/attendance";
	}
	
	@UtilityClass
	class Auth {
		public static final String NAME = "present-auths";
		public static final String REFERENCE = "lb://present-auths";
		public static final String ROUTE = "/auth";
	}
	
	@UtilityClass
	class Class {
		public static final String NAME = "present-classes";
		public static final String REFERENCE = "lb://present-classes";
		public static final String ROUTE = "/class";
	}
	
	@UtilityClass
	class Course {
		public static final String NAME = "present-courses";
		public static final String REFERENCE = "lb://present-courses";
		public static final String ROUTE = "/course";
	}
	
	@UtilityClass
	class Discipline {
		public static final String NAME = "present-disciplines";
		public static final String REFERENCE = "lb://present-disciplines";
		public static final String ROUTE = "/discipline";
	}
	
	@UtilityClass
	class Event {
		public static final String NAME = "present-events";
		public static final String REFERENCE = "lb://present-events";
		public static final String ROUTE = "/event";
	}
	
	@UtilityClass
	class FaceId {
		public static final String NAME = "present-face-id";
		public static final String REFERENCE = "lb://present-face-id";
		public static final String ROUTE = "/faceid";
	}
	
	@UtilityClass
	class Group {
		public static final String NAME = "present-groups";
		public static final String REFERENCE = "lb://present-groups";
		public static final String ROUTE = "/group";
	}

	@UtilityClass
	class User {
		public static final String NAME = "present-users";
		public static final String REFERENCE = "lb://present-users";
		public static final String ROUTE = "/user";
	}
}