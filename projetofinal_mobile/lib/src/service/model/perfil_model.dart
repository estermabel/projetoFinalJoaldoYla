class Perfil {
  int? id;
  String? name;
  String? authority;

  Perfil({this.id, this.name, this.authority});

  factory Perfil.fromJson(Map<String, dynamic> json) {
    return Perfil(
      id: json['id'],
      name: json['nome'],
      authority: json['authority'],
    );
  }
}
