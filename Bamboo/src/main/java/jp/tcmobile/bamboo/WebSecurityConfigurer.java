package jp.tcmobile.bamboo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.tcmobile.bamboo.model.Authorization;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(bCryptPasswordEncoder);
	}
	
/*	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
				.mvcMatchers("/**")
					.hasAuthority(Authorization.Role.admin.name())
					.mvcMatchers("ArticleList")
					.hasAuthority(Authorization.Role.staff.name())
				.anyRequest().authenticated()
				.and().csrf().disable()
				.formLogin().loginPage("/LoginTest").failureUrl("/login?error=true")
				.defaultSuccessUrl("/")
				.usernameParameter("name")
				.passwordParameter("password");
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
				authorizeRequests()
				.mvcMatchers("/test/**")
				.hasAuthority(Authorization.Role.admin.name())
				.mvcMatchers("/users/**")
				.hasAuthority(Authorization.Role.admin.name())
				.mvcMatchers("/article/**")
				.permitAll()
				.mvcMatchers("/common/**")
				.permitAll()
				/*.antMatchers("/").permitAll()*/
				.antMatchers("/login").permitAll().anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/index")
				.usernameParameter("id")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}