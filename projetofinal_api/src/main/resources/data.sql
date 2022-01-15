INSERT INTO usuario(data_criacao, data_ultimo_acesso, login, nome, perfil, senha)
values ('2021-12-20 18:47:52', '2021-12-21 18:47:52', 'neivacaju', 'Neiva', 1, 'caju');

INSERT INTO tarefa(data_entrega, descricao, titulo)
values ('2022-01-12 18:47:52', 'Printar um hello world!', 'Hello world!');

INSERT INTO caso_teste(comparacao, entrada, nome_teste, saida, tarefa_id)
values (33, 'Hello world', 'Primeira palavra', 'Hello', 1),
(34, 'Hello! world', 'Primeira palavra', 'Hello!', 1);

INSERT INTO resposta(codigo, data_envio, tarefa_id, usuario_id)
values ('Codigo', '2021-12-29 18:47:52', 1, 1);