import 'package:projetofinal_mobile/src/service/model/perfil_model.dart';

class Usuario {
  int? id;
  String? name;
  String? user;
  bool? isActive;
  String? createdAt;
  String? updatedAt;
  List<Perfil>? profiles;

  Usuario({
    this.id,
    this.name,
    this.user,
    this.isActive,
    this.createdAt,
    this.updatedAt,
    this.profiles,
  });

  factory Usuario.fromJson(dynamic json) {
    return Usuario(
      id: json['id'],
      name: json['nome'],
      user: json['login'],
      isActive: json['flagAtivo'],
      createdAt: json['dataCriacao'],
      updatedAt: json['dataUltimoAcesso'],
      profiles:
          (json['perfil'] as List).map((i) => Perfil.fromJson(i)).toList(),
    );
  }
}
