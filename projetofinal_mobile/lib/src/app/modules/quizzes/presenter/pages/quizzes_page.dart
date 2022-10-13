import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/src/app/modules/quizzes/presenter/bloc/quizzes_bloc.dart';

class QuizzesPage extends StatefulWidget {
  static const route = '/quizzes';
  const QuizzesPage({super.key});

  @override
  State<QuizzesPage> createState() => _QuizzesPageState();
}

class _QuizzesPageState extends ModularState<QuizzesPage, QuizzesBloc> {
  @override
  Widget build(BuildContext context) {
    return Container(color: Colors.red);
  }
}
