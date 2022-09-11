import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/src/app/modules/auth/register/presenter/bloc/register_bloc.dart';
import 'package:projetofinal_mobile/src/service/local/shared_preferences_service.dart';

class RegisterModule extends Module {
  @override
  final List<Bind> binds = [
    Bind.lazySingleton((i) => SharedPreferencesService()),
    //Bind.lazySingleton((i) => DoRegisterUseCase()),
    Bind.lazySingleton((i) => RegisterBloc(
        //doRegisterUseCase: i.get<DoRegisterUseCase>(),
        )),
  ];

  @override
  final List<ModularRoute> routes = [];
}
