import { UsuarioDTO } from './../../model/DTO/usuarioDTO';
import { LoginDTO } from '../../model/DTO/loginDTO';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { BehaviorSubject, Observable } from 'rxjs';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import { environment } from 'src/environments/environment';
import { IdentityStorage } from '../_models/identity.storage';
import { map } from 'rxjs/operators';

class AuthorityResponse {
  public authority!: string;
}

class UserPerfilResponse {
  public nome!: string;
}

class UserDataResponse {
  public id!: string;
  public nome!: string;
  public cpf!: string;
  public perfis!: UserPerfilResponse[];
}

class AuthenticationResponse {
  public login!: string;
  public token!: string;
  public user!: UserDataResponse;
  public authorities!: AuthorityResponse[];
}
class AuthenticationResult {
  ok: boolean;
  message: string;

  constructor(ok: boolean, message: string) {
      this.ok = ok;
      this.message = message;
  }
}


@Injectable({
  providedIn: 'root'
})

export class AccountService  {

  baseUrl = environment.url;
  urlLogin = 'api/login/';
  private messageSource = new BehaviorSubject(false);
  identityStorage: IdentityStorage;
  constructor(
    private http: HttpClient,
    private router: Router,
    private usuarioService: UsuarioService,
    private idStorage: IdentityStorage,
    @Inject(SESSION_STORAGE) private storage: StorageService) {
      this.identityStorage = this.idStorage;
  }

  login(user: LoginDTO): Observable<AuthenticationResult> {

        return this.http.post<AuthenticationResponse>(this.baseUrl + this.urlLogin, user).pipe(
          map(resp=>{
            if(resp.token){
              const token = resp.token;

              if(token){
                const userAuthData = {
                  token: token,
                };

                console.log(`Usuário autenticou: Nome: `);
                this.identityStorage.saveAuthData(userAuthData);
                this.storage.set("token", token);
                this.messageSource.next(true);
                return new AuthenticationResult(true, "");
              }
              return new AuthenticationResult(false, 'Token vazio');
            } else {
              this.messageSource.next(false);
              return new AuthenticationResult(false, 'Login ou Senha Inválidos');
            }
          })
      )

    }

  authenticate(user: LoginDTO) {
    const result = this.http.post<any>(this.baseUrl+this.urlLogin, user).subscribe(data=>
      console.log("login:", data.token));
    if(result && result){
      this.storage.set("token", result);
      return true;
    }
    return false;
  }

  isExpired() {
    this.usuarioService.sessao().subscribe(_ => { }, _ => {
        console.log('A sessão expirou, por favor realize o login.');
        this.identityStorage.clearAuthData();
        this.router.navigate(['login']);
    });
  }

  clearAuthentication(): void {
    this.storage.remove("token");
  }

  getAuthorizationToken(){
    const token = this.storage.get("token");
    return token;
  }

  isAuthenticated(): boolean {
    return this.identityStorage.authenticationPresent();
  }
}
