export interface Usuario {
  id: number,
  nome: string,
  login: string,
  senha: string,
  perfil: number,
  dataCriacao: Date,
  dataUltimoAcesso: Date
}
