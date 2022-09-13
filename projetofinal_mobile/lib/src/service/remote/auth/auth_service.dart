import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:projetofinal_mobile/src/service/config/constants/api_constants.dart';
import 'package:projetofinal_mobile/src/service/config/interceptors/api_service.dart';
import 'package:projetofinal_mobile/src/service/config/interceptors/http_method.dart';
import 'package:projetofinal_mobile/src/service/config/interceptors/request_config.dart';
import 'package:projetofinal_mobile/src/service/local/shared_preferences_service.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/auth_service_interface.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/request/request_login.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/request/request_register.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/response/response_get_user_by_id.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/response/response_login.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/response/response_register_admin.dart';

class AuthService implements IAuthService {
  final ApiService _service = ApiService();
  @override
  Future<ResponseLogin> doLogin(RequestLogin request) async {
    //TODO Request mockada
    request = RequestLogin(
      user: 'neivacaju',
      password: 'caju',
    );
    final requestConfig = RequestConfig(
      path: ApiConstants.doAuth,
      method: HttpMethod.post,
      body: request.toJson(request),
    );

    final response = await _service.doRequest(requestConfig);

    return ResponseLogin.fromJson(jsonDecode(response.data));
  }

  @override
  Future<String> getAccessToken() async {
    String token = await SharedPreferencesService().readToken();

    return 'Bearer $token';
  }

  @override
  Future<ResponseRegisterAdmin> doRegisterAdmin(
    RequestRegisterAdmin request,
  ) async {
    final token = await getAccessToken();

    final requestConfig = RequestConfig(
      path: ApiConstants.doRegisterAdmin,
      method: HttpMethod.post,
      options: Options(headers: {ApiConstants.kAuthorization: token}),
      body: request.toJson(request),
    );

    final response = await _service.doRequest(requestConfig);

    return ResponseRegisterAdmin.fromJson(jsonDecode(response.data));
  }

  @override
  Future<ResponseGetUserById> getUserById(int id) async {
    final token = await getAccessToken();

    final requestConfig = RequestConfig(
      path: ApiConstants.getUserById + id.toString(),
      method: HttpMethod.get,
      options: Options(headers: {ApiConstants.kAuthorization: token}),
    );

    final response = await _service.doRequest(requestConfig);

    return ResponseGetUserById.fromJson(jsonDecode(response.data));
  }
}
