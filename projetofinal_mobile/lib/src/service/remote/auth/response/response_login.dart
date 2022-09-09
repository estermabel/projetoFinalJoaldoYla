class ResponseLogin {
  String? accessToken;
  String? tokenType;

  ResponseLogin({
    this.accessToken,
    this.tokenType,
  });

  factory ResponseLogin.fromJson(dynamic json) {
    return ResponseLogin(
      accessToken: json["token"],
      tokenType: json["tipo"],
    );
  }
}
