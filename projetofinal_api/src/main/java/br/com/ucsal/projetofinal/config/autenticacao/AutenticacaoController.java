package br.com.ucsal.projetofinal.config.autenticacao;

import br.com.ucsal.projetofinal.config.token.LoginForm;
import br.com.ucsal.projetofinal.config.token.TokenDTO;
import br.com.ucsal.projetofinal.config.token.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        super();
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/")
    public ResponseEntity<TokenDTO> autenticarLogin(@RequestBody @Valid LoginForm login){
        UsernamePasswordAuthenticationToken dados = login.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(dados);
            String token = tokenService.gerarToken(authentication);
            System.out.println(token);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
