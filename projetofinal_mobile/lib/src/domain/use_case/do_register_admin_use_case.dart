import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/src/core/interfaces/safe_use_case.dart';
import 'package:projetofinal_mobile/src/domain/entity/register_entity.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/auth_service.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/auth_service_interface.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/request/request_register.dart';

enum ProfileEnum { admin, student, teacher, none }

extension ProfileEnumExtension on ProfileEnum {
  String get value {
    switch (this) {
      case ProfileEnum.admin:
        return 'Admin';
      case ProfileEnum.student:
        return 'Aluno';
      case ProfileEnum.teacher:
        return 'Professor';
      case ProfileEnum.none:
        return 'none';
    }
  }
}

class DoRegisterAdminUseCase extends SafeUseCase {
  late final IAuthService _service;

  DoRegisterAdminUseCase() {
    _service = Modular.get<AuthService>();
  }

  Future<RegisterEntity> call({
    required String name,
    required String username,
    required String password,
    bool isActive = true,
    ProfileEnum profile = ProfileEnum.student,
  }) async {
    final request = RequestRegisterAdmin(
      name: name.trim(),
      password: password.trim(),
      user: username,
      isActive: isActive,
      profileId: profile.index + 1,
    );

    final response = await _service.doRegisterAdmin(request);

    return RegisterEntity.toEntity(response);
  }
}
