import 'dart:async';

import 'package:projetofinal_mobile/src/core/interfaces/safe_bloc.dart';
import 'package:projetofinal_mobile/src/core/util/safe_log_util.dart';
import 'package:projetofinal_mobile/src/domain/use_case/save_user_login_use_case.dart';

class LoginBloc extends SafeBloC {
  //final DoLoginUseCase doLoginUseCase;
  final SaveUserLoginUseCase saveUserLoginUseCase;

  LoginBloc({
    //required this.doLoginUseCase,
    required this.saveUserLoginUseCase,
  }) {
    init();
  }

  @override
  Future<void> init() async {}

  Future<void> doLogin() async {}

  void toogleLoginButton() {}

  Future<void> saveUserLogin(bool value) async {
    try {
      await saveUserLoginUseCase.call(value);
    } catch (e) {
      SafeLogUtil.instance.logError(e);
    }
  }

  @override
  Future<void> dispose() async {}
}
