INSERT INTO usuario(data_criacao, data_ultimo_acesso, flag_ativo, login, nome, perfil, senha)
values ('2021-12-20 18:47:52', '2021-12-21 18:47:52', true, 'neivacaju', 'Neiva', 1, 'caju');

INSERT INTO tarefa(data_entrega, descricao, status, titulo)
values ('2022-01-12 18:47:52', 'Printar um hello world!', 1, 'Hello world!');

INSERT INTO caso_teste(comparacao, entrada, flag_exibir, nome_teste, saida, tarefa_id)
values (1, '\n', true, 'Hello World', 'Olá Mundo', 1),
(2, 'Hello! world', false, 'Primeira palavra', 'Hello!', 1);

INSERT INTO resposta(codigo, data_envio, tarefa_id, usuario_id)
values ('public class Main {\n  public static void main(String[] args) { \n System.out.println("Olá Mundo"); \n}', '2021-12-29 18:47:52', 1, 1);

INSERT INTO resultado(caso_teste_id, resultado, saida_obtida, resposta_id)
values (1, true, 'Primeira palavra', 1);