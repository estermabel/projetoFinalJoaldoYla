import 'package:projetofinal_mobile/src/core/constants/key_constants.dart';
import 'package:projetofinal_mobile/src/service/local/shared_preferences_service_interface.dart';

import 'package:shared_preferences/shared_preferences.dart';

///A classe de [SharedPreferencesService] é uma classe responsável por gerenciar
///e armazenar requisições de armazenamento de dados locais.
class SharedPreferencesService implements ISharedPreferencesService {
  @override
  void saveLogin(bool value) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setBool(KeyConstants.keyUserLogged, value);
  }

  @override
  Future<bool> readLogin() async {
    final prefs = await SharedPreferences.getInstance();
    return prefs.getBool(KeyConstants.keyUserLogged) ?? false;
  }
}
