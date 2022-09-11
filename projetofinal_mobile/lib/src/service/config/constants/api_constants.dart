import 'dart:convert';

/// [ApiConstants] é uma classe que contém todas as constantes usadas nas requisições da API
class ApiConstants {
  ///Credenciais de autenticação
  static const String kClientCredentials = '';
  static const String kAuthorization = '';
  static const String kContentType = 'Content-Type';

  ///Base64 de credenciais de autenticação
  static String kEncodedClientCredentials = base64Encode(
    utf8.encode(kClientCredentials),
  );

  ///Url default para requisições da API
  static const String kUrl = '';

  /*--------------------------------------------------------------------*/

  //Auth
  static const String doAuth = '';
}
