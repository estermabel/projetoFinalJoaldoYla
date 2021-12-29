INSERT INTO usuario(data_criacao, data_ultimo_acesso, login, nome, perfil, senha)
values ('2021-12-20 18:47:52', '2021-12-21 18:47:52', 'neivacaju', 'Neiva', 1, 'caju');

INSERT INTO caso_teste(comparacao, entrada, nome_teste, saida)
values (33, 'Hello world', 'Primeira palavra', 'Hello'),
(34, 'Hello! world', 'Primeira palavra', 'Hello!');

INSERT INTO tarefa(data_entrega, descricao, titulo)
values ('2022-01-12 18:47:52', 'Printar um hello world!', 'Hello world!');

INSERT INTO tarefa_testes(tarefa_id, testes_id)
values (1, 1), (1, 2);