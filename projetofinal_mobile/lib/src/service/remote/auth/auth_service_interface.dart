import 'package:projetofinal_mobile/src/service/remote/auth/request/request_login.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/request/request_register.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/response/response_login.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/response/response_register.dart';

abstract class IAuthService {
  Future<ResponseLogin> doLogin(RequestLogin request);
  Future<ResponseRegisterAdmin> doRegisterAdmin(RequestRegisterAdmin request);
  Future<String> getAccessToken();
}
