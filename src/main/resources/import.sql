-- Inserir registros na tabela Anime
INSERT INTO tb_animes(nome, genero, NUM_EPISODIOS) VALUES ('Naruto', 'Acao', 220);
INSERT INTO tb_animes(nome, genero, NUM_EPISODIOS) VALUES('One Piece', 'Aventura', 1300);
INSERT INTO tb_animes(nome, genero, NUM_EPISODIOS) VALUES('Attack on Titan', 'Acao', 75);

-- Inserir registros na tabela Usuario
INSERT INTO tb_usuarios (nome, email, DTYPE) VALUES ('Carlos Souza', 'carlos.souza@example.com', 'Usuario');
INSERT INTO tb_usuarios (nome, email, DTYPE) VALUES('Maria Oliveira', 'maria.oliveira@example.com', 'Usuario');
INSERT INTO tb_usuarios (nome, email, DTYPE) VALUES('Carlos Souza', 'carlos.souza@example.com', 'Usuario');

-- Inserir registros na tabela Avaliação
INSERT INTO tb_avaliacoes(nota, comentario, usuario_id, anime_id) VALUES (4.5, 'Muito bom', 1, 1);

-- Adicionar animes à lista "assistindo"
INSERT INTO tb_assistindo(usuario_id, anime_id) VALUES (1, 1);  -- Carlos Souza assistindo "Naruto"
INSERT INTO tb_assistindo(usuario_id, anime_id) VALUES (1, 2);  -- Carlos Souza assistindo "One Piece"
INSERT INTO tb_assistindo(usuario_id, anime_id) VALUES (2, 2);  -- Maria Oliveira assistindo "One Piece"

-- Adicionar animes à lista "completo"
INSERT INTO tb_completo(usuario_id, anime_id) VALUES (1, 2);  -- Carlos Souza completou "One Piece"
INSERT INTO tb_completo(usuario_id, anime_id) VALUES (2, 1);  -- Maria Oliveira completou "Naruto"

-- Adicionar animes à lista "quero assistir"
INSERT INTO tb_quero_assistir(usuario_id, anime_id) VALUES (1, 3);  -- Carlos Souza quer assistir "Attack on Titan"
INSERT INTO tb_quero_assistir(usuario_id, anime_id) VALUES (2, 3);  -- Maria Oliveira quer assistir "Attack on Titan"