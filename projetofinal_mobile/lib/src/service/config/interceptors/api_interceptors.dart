import 'package:dio/dio.dart';
import 'package:projetofinal_mobile/src/core/util/safe_log_util.dart';

///A classe [ApiInterceptors] é responsável por gerenciar as intercepções da API
class ApiInterceptors extends QueuedInterceptorsWrapper {
  final Dio _dio;
  ApiInterceptors(this._dio);

  @override
  Future onRequest(
    RequestOptions options,
    RequestInterceptorHandler handler,
  ) async {
    SafeLogUtil.instance.onRequestLog(
      path: options.path,
      header: options.headers.toString(),
      body: options.data.toString(),
      http: options.method,
    );
    return super.onRequest(options, handler);
  }

  @override
  Future onResponse(
    Response response,
    ResponseInterceptorHandler handler,
  ) async {
    SafeLogUtil.instance.onResponseLog(
      path: response.requestOptions.path,
      statusCode: response.statusCode,
      header: response.headers.toString(),
      params: response.requestOptions.data.toString(),
      body: response.data,
    );
    return super.onResponse(response, handler);
  }

  @override
  onError(
    DioError err,
    ErrorInterceptorHandler handler,
  ) async {
    SafeLogUtil.instance.onResponseLog(
      path: err.requestOptions.path,
      statusCode: err.response?.statusCode,
      header: err.requestOptions.headers.toString(),
      params: err.requestOptions.data.toString(),
      body: err.response?.data.toString(),
      isError: true,
    );
    return super.onError(err, handler);
  }
}
