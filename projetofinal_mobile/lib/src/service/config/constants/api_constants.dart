import 'dart:convert';

/// [ApiConstants] é uma classe que contém todas as constantes usadas nas requisições da API
class ApiConstants {
  ///Credenciais de autenticação
  static const String kClientCredentials = '';
  static const String kAuthorization = 'Authorization';
  static const String kContentType = 'Content-Type';

  ///Base64 de credenciais de autenticação
  static String kEncodedClientCredentials = base64Encode(
    utf8.encode(kClientCredentials),
  );

  ///Url default para requisições da API
  static const String kUrl = 'http://localhost:8081/api/';

  /*--------------------------------------------------------------------*/

  //Auth
  static const String doAuth = '${kUrl}login/';
  static const String doRegister = '';
  static const String doRegisterAdmin = '${kUrl}usuarios/';

  //Tasks
  static const String getTasks = '${kUrl}tarefa/';
}
