class RequestRegisterAdmin {
  int? profileId; // 1 - Admin, 2 - Aluno, 3 - Professor
  String? name;
  String? user;
  String? password;
  bool? isActive;

  RequestRegisterAdmin({
    this.name,
    this.user,
    this.password,
    this.isActive,
    this.profileId,
  });

  Map<String, dynamic> toJson(RequestRegisterAdmin request) {
    return {
      'nome': request.name,
      'login': request.user,
      'senha': request.password,
      'flagAtivo': request.isActive,
      'perfilId': request.profileId,
    };
  }
}
