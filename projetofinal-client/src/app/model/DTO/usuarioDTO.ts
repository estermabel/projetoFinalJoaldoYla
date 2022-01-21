export class UsuarioDTO {
  id: number;
  nome: string;
  login: string;
  senha: string;
  perfil: number;
  dataCriacao: Date;
  dataUltimoAcesso: Date

  constructor() {
    this.id = 0,
    this.nome = '',
    this.login = '',
    this.senha = '',
    this.perfil = 0,
    this.dataCriacao =  new Date(),
    this.dataUltimoAcesso = new Date()
  }
}
