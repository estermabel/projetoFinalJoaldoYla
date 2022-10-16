import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/src/app/modules/quizzes/presenter/bloc/quizzes_bloc.dart';
import 'package:projetofinal_mobile/src/app/modules/quizzes/presenter/pages/quizzes_page.dart';

class QuizzesModule extends Module {
  @override
  final List<Bind> binds = [
    Bind.lazySingleton((i) => QuizzesBloc()),
  ];

  @override
  final List<ModularRoute> routes = [
    ChildRoute(
      Modular.initialRoute,
      child: (context, args) => const QuizzesPage(),
    ),
  ];
}
