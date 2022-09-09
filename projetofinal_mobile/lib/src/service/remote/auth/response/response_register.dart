class ResponseRegisterAdmin {
  int? id;
  String? name;
  String? user;
  String? password;
  bool? isActive;
  String? createdAt;
  String? updatedAt;
  List<Perfil>? profiles;

  ResponseRegisterAdmin({
    this.id,
    this.name,
    this.user,
    this.password,
    this.isActive,
    this.createdAt,
    this.updatedAt,
    this.profiles,
  });

  factory ResponseRegisterAdmin.fromJson(Map<String, dynamic> json) {
    return ResponseRegisterAdmin(
      id: json["id"],
      name: json["nome"],
      user: json["login"],
      password: json["senha"],
      isActive: json["flagAtivo"],
      createdAt: json["dataCriacao"],
      updatedAt: json["dataUltimoAcesso"],
      profiles: List<Perfil>.from(
        json["perfil"].map((x) => Perfil.fromJson(x)),
      ),
    );
  }
}

class Perfil {
  int? id;
  String? name;
  String? authority;

  Perfil({this.id, this.name, this.authority});

  factory Perfil.fromJson(Map<String, dynamic> json) {
    return Perfil(
      id: json['id'],
      name: json['nome'],
      authority: json['authority'],
    );
  }
}
