package br.com.ucsal.projetofinal.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    @NotNull
    private Boolean flagAtivo;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC-2")
    private Instant dataCriacao;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC-2")
    private Instant dataUltimoAcesso;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    public Usuario(String nome, String login, String senha, Boolean flagAtivo) {
        this.nome = nome;
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.flagAtivo = flagAtivo;
        ZoneId brazilZone = ZoneId.of("America/Sao_Paulo");
        this.dataCriacao = LocalDateTime.now(brazilZone).toInstant(ZoneOffset.UTC);
        this.dataUltimoAcesso = Instant.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
