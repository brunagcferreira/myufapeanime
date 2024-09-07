-- Inserir registros na tabela Anime
INSERT INTO tb_animes(nome, genero, NUM_EPISODIOS, NOTA_MEDIA, PONTUACAO, AVALIACOES_TOTAIS) VALUES ('Naruto', 'Acao', 220, 0, 0,0);
INSERT INTO tb_animes(nome, genero, NUM_EPISODIOS, NOTA_MEDIA, PONTUACAO, AVALIACOES_TOTAIS) VALUES('One piece', 'Aventura', 1300, 0, 0,0);
INSERT INTO tb_animes(nome, genero, NUM_EPISODIOS, NOTA_MEDIA, PONTUACAO, AVALIACOES_TOTAIS) VALUES('Attack on titan', 'Acao', 75, 0, 0,0);

-- Inserir registros na tabela Usuario
INSERT INTO tb_usuarios (nome, email, DTYPE) VALUES ('Carlos Souza', 'carlos.souza@example.com', 'Usuario');
INSERT INTO tb_usuarios (nome, email, DTYPE) VALUES('Maria Oliveira', 'maria.oliveira@example.com', 'Usuario');
INSERT INTO tb_usuarios (nome, email, DTYPE) VALUES('Carlos Souza', 'carlos.souza@example.com', 'Usuario');

-- Inserir registros na tabela Avaliação
INSERT INTO tb_avaliacoes(nota, comentario, usuario_id, anime_id) VALUES (4.5, 'Muito bom', 1, 1);
INSERT INTO tb_avaliacoes(nota, comentario, usuario_id, anime_id) VALUES (5.0, 'Incrível!', 2, 2);  -- Maria Oliveira avaliou One Piece
INSERT INTO tb_avaliacoes(nota, comentario, usuario_id, anime_id) VALUES (4.0, 'Ótimo', 3, 3);     -- Ana Silva avaliou Attack on Titan

-- Adicionar animes à lista "assistindo"
INSERT INTO tb_assistindo(usuario_id, anime_id) VALUES (1, 1);  -- Carlos Souza assistindo "Naruto"
INSERT INTO tb_assistindo(usuario_id, anime_id) VALUES (1, 2);  -- Carlos Souza assistindo "One piece"
INSERT INTO tb_assistindo(usuario_id, anime_id) VALUES (2, 2);  -- Maria Oliveira assistindo "One piece"

-- Adicionar animes à lista "completo"
INSERT INTO tb_completo(usuario_id, anime_id) VALUES (1, 2);  -- Carlos Souza completou "One piece"
INSERT INTO tb_completo(usuario_id, anime_id) VALUES (2, 1);  -- Maria Oliveira completou "Naruto"

-- Adicionar animes à lista "quero assistir"
INSERT INTO tb_quero_assistir(usuario_id, anime_id) VALUES (1, 3);  -- Carlos Souza quer assistir "Attack on titan"
INSERT INTO tb_quero_assistir(usuario_id, anime_id) VALUES (2, 3);  -- Maria Oliveira quer assistir "Attack on titan"
