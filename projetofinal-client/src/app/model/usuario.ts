export interface Usuario {
  id: number,
  nome: string,
  login: string,
  senha: string,
  perfil: number,
  flagAtivo: boolean,
  dataCriacao: Date,
  dataUltimoAcesso: Date
}
