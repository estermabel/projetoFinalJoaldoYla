import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/src/app/modules/auth/login/presenter/bloc/login_bloc.dart';
import 'package:projetofinal_mobile/src/core/util/safe_log_util.dart';

class LoginPage extends StatefulWidget {
  static const route = '/login/';
  const LoginPage({Key? key}) : super(key: key);

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends ModularState<LoginPage, LoginBloc> {
  final _scaffoldKey = GlobalKey<ScaffoldState>();
  final _formKey = GlobalKey<FormState>();

  @override
  void initState() {
    super.initState();
    SafeLogUtil.instance.route(Modular.to.path);
  }

  void navigateToHome() {
    // Modular.to.pushNamedAndRemoveUntil(
    //   NavigationPage.route + HomePage.route,
    //   (r) => false,
    // );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      body: Container(),
    );
  }
}
