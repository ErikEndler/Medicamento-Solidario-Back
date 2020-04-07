//package com.apirest.MedicamentoSolidario.security;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Service;
//import org.springframework.web.cors.CorsConfiguration;
//
//
//public class CorsCOnfig extends CorsConfiguration {
//	
//	
//	public List<String> allowedOrigins;
//
//	
//	public List<String> allowedMethods;
//
//	
//	public List<String> allowedHeaders;
//
//	
//	public List<String> exposedHeaders;
//	
//	private List<HttpMethod> resolvedMethods = DEFAULT_METHODS;
//
//	
//	private Boolean allowCredentials ;
//
//	
//	public Long maxAge;
//	
//	public static final String ALL = "*";
//
//	private static final List<HttpMethod> DEFAULT_METHODS = Collections.unmodifiableList(
//			Arrays.asList(HttpMethod.GET, HttpMethod.HEAD));
//
//	private static final List<String> DEFAULT_PERMIT_METHODS = Collections.unmodifiableList(
//			Arrays.asList(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name()));
//
//	private static final List<String> DEFAULT_PERMIT_ALL = Collections.unmodifiableList(
//			Collections.singletonList(ALL));
//	
//	public CorsCOnfig() {
////		//, , , , DELETE, PATCH"
////		this.allowedHeaders = DEFAULT_PERMIT_ALL;
////		this.allowedOrigins = DEFAULT_PERMIT_ALL;
////		this.allowedMethods = DEFAULT_PERMIT_METHODS;
//////		this.allowedMethods.add("POST");
//////		this.allowedMethods.add("GET");
//////		this.allowedMethods.add("PUT");
//////		this.allowedMethods.add("OPTIONS");
//////		this.allowedMethods.add("DELETE");
////		this.resolvedMethods = DEFAULT_PERMIT_METHODS
////				.stream().map(HttpMethod::resolve).collect(Collectors.toList());
//		
//	}
//	@Override
//	public CorsConfiguration applyPermitDefaultValues() {
//		if (this.allowedOrigins == null) {
//			this.allowedOrigins = DEFAULT_PERMIT_ALL;
//		}
//		if (this.allowedMethods == null) {
//			this.allowedMethods = DEFAULT_PERMIT_METHODS;
//			this.resolvedMethods = DEFAULT_PERMIT_METHODS
//					.stream().map(HttpMethod::resolve).collect(Collectors.toList());
//		}
//		if (this.allowedHeaders == null) {
//			this.allowedHeaders = DEFAULT_PERMIT_ALL;
//		}
//		if (this.maxAge == null) {
//			this.maxAge = 1800L;
//		}
//		return this;
//	}
//	
//	
//
//}
