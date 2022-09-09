import 'package:projetofinal_mobile/src/service/remote/auth/response/response_login.dart';

class LoginEntity {
  String? accessToken;
  String? tokenType;

  LoginEntity({
    this.accessToken,
    this.tokenType,
  });

  factory LoginEntity.toEntity(ResponseLogin response) {
    return LoginEntity(
      accessToken: response.accessToken,
      tokenType: response.tokenType,
    );
  }
}
