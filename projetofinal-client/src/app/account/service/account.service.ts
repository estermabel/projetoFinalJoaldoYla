import { LoginDTO } from './../../model/DTO/loginDTO';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { Observable } from 'rxjs';
import { GenericService } from 'src/app/commons/generic.service';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import { environment } from 'src/environments/environment';

class AuthorityResponse {
  public authority!: string;
}

class UserPerfilResponse {
  public nome!: string;
  public descricao!: string;
  public email!: string;
  public comarca!: string;
  public lotacao!: string;
  public cargo!: string;
  public cpf!: string;
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

  credentials: any;
  baseUrl = environment.url;
  urlLogin = 'api/login/';

  constructor(
    private http: HttpClient,
    private router: Router,
    private usuarioService: UsuarioService,
    @Inject(SESSION_STORAGE) private storage: StorageService) {
  }

  login(user: LoginDTO): Observable<any> {
    // const result = await this.http.post<any>(`${environment.url}api/login`, user).toPromise();
    // if(result && result.token){
    //   this.storage.set("token", result.token);
    //   return true;
    // }
    // return false;
    //console.log(JSON.stringify(user))
    return this.http.post(this.baseUrl+this.urlLogin, user)
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

  clearAuthentication(): void {
    this.storage.remove("token");
  }

  getAuthorizationToken(){
    const token = this.storage.get("token");
    return token;
  }

}
