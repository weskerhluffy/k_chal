package k.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import lombok.extern.slf4j.Slf4j;

// XXX: https://www.youtube.com/watch?v=rBNOc4ymd1E
// XXX: https://www.baeldung.com/rest-api-spring-oauth2-angular
// XXX: https://www.baeldung.com/spring-security-oauth-jwt
// XXX: https://www.baeldung.com/keycloak-embedded-in-spring-boot-app
// XXX: https://blog.softtek.com/es/autenticando-apis-con-spring-y-jwt
// XXX: https://github.com/wpic/sample-keycloak-getting-token
// XXX: https://medium.com/@bcarunmail/securing-rest-api-using-keycloak-and-spring-oauth2-6ddf3a1efcc2
// XXX: https://www.baeldung.com/security-none-filters-none-access-permitAll
// XXX: http://localhost:8083/auth/admin/master/console/#/create/client-scope/baeldung/c3e253fb-7361-47cf-9d4a-86245686fdf1/mappers
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {// @formatter:off
//		http.authorizeRequests().anyRequest().permitAll();
		log.info("Configurando seguridad");
		// XXX: https://www.janua.fr/oauth2-openid-scope-usage-with-keycloak/
		http.cors().and().authorizeRequests().antMatchers("/game/ping").permitAll().and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/game/sec_ping").hasAuthority("SCOPE_read")
				.antMatchers(HttpMethod.POST, "/api/foos").hasAuthority("SCOPE_write").anyRequest().authenticated()
				.and().oauth2ResourceServer().jwt();
	}// @formatter:on

	@Bean
	JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
		NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(properties.getJwt().getJwkSetUri()).build();
		jwtDecoder.setClaimSetConverter(new OrganizationSubClaimAdapter());
		return jwtDecoder;
	}
}