package br.com.ucsal.projetofinal.config;

import br.com.ucsal.projetofinal.config.autenticacao.AutenticacaoService;
import br.com.ucsal.projetofinal.config.autenticacao.AutenticacaoTokenFilter;
import br.com.ucsal.projetofinal.config.token.TokenService;
import br.com.ucsal.projetofinal.usuario.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AutenticacaoService autenticacaoService;

    private final TokenService tokenService;

    private final UsuarioService usuarioService;

    public SecurityConfig(AutenticacaoService autenticacaoService, TokenService tokenService, UsuarioService usuarioService) {
        this.autenticacaoService = autenticacaoService;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/api/respostas/**").access("hasAnyAuthority('Admin', 'Professor')")
                .antMatchers(HttpMethod.GET, "/api/respostas/usuario/**").access("hasAnyAuthority('Aluno')")
                .antMatchers(HttpMethod.GET, "/api/tarefas/**").access("hasAnyAuthority('Aluno')")
                .antMatchers(HttpMethod.GET, "/api/casoteste//**").access("hasAnyAuthority('Aluno')")
                .antMatchers("/api/usuarios/**").permitAll()
                .antMatchers("/api/api/provas/**").permitAll()
                .antMatchers("/api/tarefa/**").access("hasAnyAuthority('Admin', 'Professor')")
                .antMatchers("/api/resultados/**").access("hasAnyAuthority('Admin', 'Professor')")
                .antMatchers("/api/casoteste/**").access("hasAnyAuthority('Professor')")
                .anyRequest().authenticated()
                .and().headers().frameOptions().disable()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, usuarioService), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
