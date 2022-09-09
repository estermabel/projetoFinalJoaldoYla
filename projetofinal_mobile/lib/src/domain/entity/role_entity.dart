import 'package:projetofinal_mobile/src/service/remote/auth/response/response_register.dart';

class RoleEntity {
  int? id;
  String? name;
  String? authority;

  RoleEntity({
    this.id,
    this.name,
    this.authority,
  });

  factory RoleEntity.toEntity(Perfil response) {
    return RoleEntity(
      id: response.id,
      name: response.name,
      authority: response.authority,
    );
  }
}
