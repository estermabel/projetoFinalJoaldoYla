INSERT INTO usuario(data_criacao, data_ultimo_acesso, flag_ativo, login, nome, senha)
values ('2021-12-20 18:47:52', '2021-12-21 18:47:52', true, 'neivacaju', 'Neiva', '$2a$10$Le.iEd4ISlBGn65MJuSeMuLfwlcES6zMfHKBnm07xWKfTAv1S/sw6');

INSERT INTO perfil(nome)
values('Admin'),
('Aluno'),
('Professor');

INSERT INTO tarefa(data_entrega, descricao, status, titulo)
values ('2022-01-12 18:47:52', 'Digite numeros separados por espaço que a soma seja 10', 1, 'Até 10');

INSERT INTO caso_teste(comparacao, entrada, flag_exibir, nome_teste, saida, tarefa_id)
values (1, '1 2 3 4', true, 'Até 10', '10', 1),
(2, '5 5', false, 'Até 10', '10', 1),
(2, '7 1 1 1', true, 'Até 10', '10', 1),
(2, '4 3 2 1', false, 'Até 10', '10', 1);

INSERT INTO resposta(codigo, data_envio, tarefa_id, usuario_id)
values ('public class Main {\n  public static void main(String[] args) { \n System.out.println("Olá Mundo"); \n}', '2021-12-29 18:47:52', 1, 1);

INSERT INTO resultado(compile, create, porcentagem, saida_obtida, resposta_id)
values (true, true, 22.0, 'Hello!', 1);

INSERT INTO teste(exception, execute, resultado_final, saida_esperada, saida_obtida, resultado_id)
values ('RunTimeException blá blá blá', true, true, 'Hello!', 'Hello!', 1);