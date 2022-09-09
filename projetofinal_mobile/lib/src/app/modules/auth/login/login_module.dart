import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/src/app/modules/auth/login/presenter/bloc/login_bloc.dart';
import 'package:projetofinal_mobile/src/app/modules/auth/login/presenter/pages/login_page.dart';
import 'package:projetofinal_mobile/src/domain/use_case/save_user_login_use_case.dart';

class LoginModule extends Module {
  @override
  final List<Bind> binds = [
    Bind.lazySingleton((i) => SaveUserLoginUseCase()),
    //Bind.lazySingleton((i) => DoLoginUseCase()),
    Bind.lazySingleton((i) => LoginBloc(
          //doLoginUseCase: i.get<DoLoginUseCase>(),
          saveUserLoginUseCase: i.get<SaveUserLoginUseCase>(),
        )),
  ];

  @override
  final List<ModularRoute> routes = [
    ChildRoute(
      Modular.initialRoute,
      child: (context, args) => const LoginPage(),
    ),
    //ModuleRoute(HomePage.route, module: HomeModule()),
  ];
}
