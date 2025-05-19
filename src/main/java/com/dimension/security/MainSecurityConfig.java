package com.dimension.security;

import com.dimension.security.jwt.JwtAuthenticationEntryPoint;
import com.dimension.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity  //Permite crear una clase de seguridad personalizada
public class MainSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService jpaUserDetailsService;

  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  /**
   * Genera un Bean PasswordEncoder (BCryptPasswordEncoder) para ser administrado
   * por el contenedor de Spring
   */
  @Bean
  public PasswordEncoder getEncoder(){

    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

  // AuthenticationManager: Interfaz que delega la autenticación
  // al AuthenticationProvider apropiado.
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * Configura la fuente de datos que proporciona las credenciales
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(jpaUserDetailsService);
  }

  /**
   * Configura el acceso a las URLs de la aplicación
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
            //El filtro csrf esta habilitado por defecto, asi que se deshabilita
            //porque se va a utilizar otro mecanismo de token
            //.csrf().disable()

            .csrf()
            .ignoringAntMatchers("/h2-console/**", "/api/intercambios/**","/api/auth/**") // Ignora CSRF para H2 Console y autenticación
            //.ignoringAntMatchers("/h2-console/**","/api/auth/**")
            .and()

            //Permite configurar el manejo de excepciones
            .exceptionHandling()
            //El manejo de excepciones se delega a un AuthenticationEntryPoint
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()

            //Permite configurar la gestion de sesiones
            .sessionManagement()
            //Se le especifica a Spring que deshabilite el uso de sesiones
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            //Le dice a spring que reglas usar al autorizar solicitudes,
            .authorizeRequests()
                //Los recursos en cuya URL se le antecede "public" no requieren autenticacion.
                .antMatchers("/api/public/**",
                        "/api/encryption/**",
                        //"/api/intercambios/**",
                        "/h2-console/**",
                        "/api/auth/**").permitAll()

                //Asigna permisos a URL's por roles.
                //Los usuarios con rol "ADMIN" tienen acceso a los recursos en cuya URL se le antecede "admin"
                .antMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")

            //Todas las demas URLs de la aplicacion requieren autenticacion
            .anyRequest().authenticated()
            .and()
        .headers()
            .frameOptions().sameOrigin() // Permite el uso de frames para H2 Console
            .and()
        .headers()
            .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));

    //Permite agregar un filtro (jwtAuthenticationFilter) personalizado antes del
    //filtro UsernamePasswordAuthenticationFilter
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }


}

