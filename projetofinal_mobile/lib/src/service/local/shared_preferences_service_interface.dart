abstract class ISharedPreferencesService {
  void saveLogin(bool value);
  Future<bool> readLogin();
}
