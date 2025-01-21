CREATE database BANCODEDADOSBR

USE BANCODEDADOSBR;

-- Tabela de Estádios
CREATE TABLE Estadio (
    id_estadio INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    capacidade INT CHECK (capacidade > 0)
);

-- Tabela de Campeonatos
CREATE TABLE Campeonato (
    id_campeonato INT PRIMARY KEY AUTO_INCREMENT,
    serie ENUM('Série A', 'Série B', 'Série C', 'Série D') NOT NULL,
    UNIQUE(serie)
);

-- Tabela de Times
CREATE TABLE Time (
    id_time INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) UNIQUE NOT NULL,
    id_estadio INT,
    id_campeonato INT,
    FOREIGN KEY (id_estadio) REFERENCES Estadio(id_estadio),
    FOREIGN KEY (id_campeonato) REFERENCES Campeonato(id_campeonato)
);

-- Tabela de Jogadores
CREATE TABLE Jogador (
    id_jogador INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    idade INT CHECK (idade >= 16),
    posicao ENUM('Goleiro', 'Zagueiro', 'Meio-Campo', 'Atacante') NOT NULL,
    id_time INT,
    status ENUM('Ativo', 'Suspenso', 'Lesionado') DEFAULT 'Ativo',
    FOREIGN KEY (id_time) REFERENCES Time(id_time)
);

-- Tabela de Partidas
CREATE TABLE Partida (
    id_partida INT PRIMARY KEY AUTO_INCREMENT,
    data_hora DATETIME NOT NULL,
    id_estadio INT NOT NULL,
    id_time_mandante INT NOT NULL,
    id_time_visitante INT NOT NULL,
    gols_mandante INT DEFAULT 0,
    gols_visitante INT DEFAULT 0,
    status ENUM('Agendada', 'Encerrada', 'Adiada') DEFAULT 'Agendada',
    FOREIGN KEY (id_estadio) REFERENCES Estadio(id_estadio),
    FOREIGN KEY (id_time_mandante) REFERENCES Time(id_time),
    FOREIGN KEY (id_time_visitante) REFERENCES Time(id_time),
    CHECK (id_time_mandante <> id_time_visitante)
);

-- Tabela de Classificação
CREATE TABLE Classificacao (
    id_classificacao INT PRIMARY KEY AUTO_INCREMENT,
    id_time INT NOT NULL,
    pontos INT DEFAULT 0,
    vitorias INT DEFAULT 0,
    empates INT DEFAULT 0,
    derrotas INT DEFAULT 0,
    gols_marcados INT DEFAULT 0,
    gols_sofridos INT DEFAULT 0,
    saldo_gols INT GENERATED ALWAYS AS (gols_marcados - gols_sofridos) STORED,
    FOREIGN KEY (id_time) REFERENCES Time(id_time)
);

-- Tabela de Penalidades e Cartões
CREATE TABLE Cartao (
    id_cartao INT PRIMARY KEY AUTO_INCREMENT,
    id_jogador INT NOT NULL,
    id_partida INT NOT NULL,
    tipo ENUM('Amarelo', 'Vermelho') NOT NULL,
    FOREIGN KEY (id_jogador) REFERENCES Jogador(id_jogador),
    FOREIGN KEY (id_partida) REFERENCES Partida(id_partida)
);

-- Tabela de Árbitros
CREATE TABLE Arbitro (
    id_arbitro INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(100) NOT NULL
);

-- Relação entre Partida e Árbitros
CREATE TABLE Partida_Arbitro (
    id_partida INT NOT NULL,
    id_arbitro INT NOT NULL,
    funcao ENUM('Principal', 'Assistente') NOT NULL,
    PRIMARY KEY (id_partida, id_arbitro),
    FOREIGN KEY (id_partida) REFERENCES Partida(id_partida),
    FOREIGN KEY (id_arbitro) REFERENCES Arbitro(id_arbitro)
);

-- Tabela de Comissão Técnica
CREATE TABLE ComissaoTecnica (
    id_comissao INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    funcao VARCHAR(100) NOT NULL,
    id_time INT NOT NULL,
    FOREIGN KEY (id_time) REFERENCES Time(id_time)
);


--  Estadios
INSERT INTO BANCODEDADOSBR.estadio (nome, cidade, capacidade) VALUES
('Maracanã', 'Rio de Janeiro', 78838),
('Mineirão', 'Belo Horizonte', 61500),
('Arena Corinthians', 'São Paulo', 49900),
('Allianz Parque', 'São Paulo', 43000),
('Arena da Baixada', 'Curitiba', 42000),
('Morumbi', 'São Paulo', 67207),
('Beira-Rio', 'Porto Alegre', 50712),
('Castelão', 'São Luís', 64000),
('Arena da Fonte', 'Araraquara', 25200),
('Estádio do Engenhão', 'Rio de Janeiro', 47000),
('Estádio do Maracanãzinho', 'Rio de Janeiro', 5000),
('Arena Pantanal', 'Cuiabá', 44000),
('Aflitos', 'Recife', 20000),
('Arena Castelão', 'Fortaleza', 64000),
('Estádio Alfredo Jaconi', 'Caxias do Sul', 25300),
('Arena do Grêmio', 'Porto Alegre', 55000),
('Estádio Couto Pereira', 'Curitiba', 40000),
('Arena Pernambuco', 'Recife', 46000),
('Arena das Dunas', 'Natal', 31500),
('Estádio da Ilha do Retiro', 'Recife', 20400),
('Estádio Centenário', 'Caxias do Sul', 25400),
('Estádio de Pituaçu', 'Salvador', 32000),
('Estádio Independência', 'Belo Horizonte', 23000),
('Estádio da Ressacada', 'Florianópolis', 19100),
('Estádio Novelli Júnior', 'Itu', 19000),
('Estádio Barradão', 'Salvador', 32300),
('Estádio do Café', 'Campo Mourão', 12000),
('Estádio Passo das Emas', 'Luverdense', 15000),
('Estádio do Interior', 'São José', 10000),
('Estádio do Arruda', 'Recife', 60000),
('Estádio São Januário', 'Rio de Janeiro', 23000),
('Estádio Anacleto Campanella', 'São Caetano do Sul', 16000),
('Estádio Otoni Martins', 'Maceió', 10000),
('Estádio King Abdullah', 'Petrolina', 7000),
('Estádio Pedro Bortolozzo', 'Maringá', 18000),
('Estádio José Lins do Rêgo', 'João Pessoa', 18000),
('Estádio Batistão', 'Aracaju', 18000),
('Estádio Mário Helênio', 'Juiz de Fora', 17000),
('Estádio Arena de Pernambuco', 'Recife', 46000),
('Estádio Floresta', 'Fortaleza', 20000),
('Estádio Nogueirão', 'Mossoró', 18000),
('Estádio do Jockey Club', 'Curitiba', 16000),
('Estádio Almeidão', 'Gama', 10000),
('Estádio Augusto Bauer', 'Joinville', 19000),
('Estádio Genervino da Fonseca', 'Cascavel', 17000),
('Estádio João Saldanha', 'Teresina', 13000),
('Estádio o Morumbi', 'São Paulo', 67000),
('Estádio do Recife', 'Recife', 22000),
('Estádio Juliano Galvão', 'São Bernardo do Campo', 15000),
('Estádio dos Eucaliptos', 'Goiânia', 13000),
('Estádio 24 de Outubro', 'Sinop', 15000),
('Estádio Iaraldino Costa', 'Cuiabá', 12000),
('Estádio Edvaldo Oliveira', 'Espírito Santo', 10000),
('Estádio Palhoça', 'Palhoça', 12000),
('Estádio Mário Pereira', 'Araucária', 14000),
('Estádio Olímpico', 'Anápolis', 15000),
('Estádio Estrela D´Alva', 'São Paulo', 8000),
('Estádio Tardelli', 'Limoeiro', 10000),
('Estádio Doutor Cleberson Oliveira', 'Teresina', 16000),
('Estádio Machado de Carvalho', 'Rio Branco', 7000),
('Estádio Rio Grande', 'Florianópolis', 13000),
('Estádio Beira-Mar', 'Teresópolis', 8000),
('Estádio Futebol Clube', 'Londrina', 11000),
('Estádio Reviver', 'Macapá', 9000),
('Estádio Professor Lauro Azevedo', 'Caucaia', 12000),
('Estádio Vicente Leme', 'São Paulo', 15000),
('Estádio Doze de Outubro', 'Santa Maria', 11000),
('Estádio do Rio Bonito', 'Rio Bonito', 12000),
('Estádio Estádio Mundial', 'Fortaleza', 14000),
('Estádio Internacional', 'Palhoça', 13000);

-- Inserir Series
INSERT INTO BANCODEDADOSBR.campeonato (serie) VALUES
('Série A'),
('Série B'),
('Série C');

INSERT INTO BANCODEDADOSBR.Time (nome, id_estadio, id_campeonato) VALUES
('Corinthians', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Arena Corinthians'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Palmeiras', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Allianz Parque'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Athletico Paranaense', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Arena da Baixada'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Flamengo', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Maracanã'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Atlético Mineiro', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Mineirão'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('São Paulo', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Morumbi'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Internacional', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Beira-Rio'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Fortaleza', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Castelão'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Ponte Preta', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Arena da Fonte'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Botafogo', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Estádio do Engenhão'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Cuiabá', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Arena Pantanal'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Ceará', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Arena Castelão'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Grêmio', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Arena do Grêmio'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('Coritiba', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Estádio Couto Pereira'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série A')),
('CRB', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Estádio Novelli Júnior'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série B')),
('Chapecoense', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Estádio Barradão'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série B')),
('Sampaio Corrêa', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Estádio do Café'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série C')),
('Botafogo-SP', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Estádio Passo das Emas'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série C')),
('Novo Hamburgo', (SELECT id_estadio FROM BANCODEDADOSBR.estadio WHERE nome = 'Estádio do Interior'), (SELECT id_campeonato FROM BANCODEDADOSBR.Campeonato WHERE serie = 'Série C'));

INSERT INTO BANCODEDADOSBR.Jogador (nome, idade, posicao, id_time) VALUES
('Cássio', 36, 'Goleiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Corinthians')),
('Fagner', 34, 'Zagueiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Corinthians')),
('Jô', 37, 'Atacante', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Corinthians')),
('Weverton', 32, 'Goleiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Palmeiras')),
('Gustavo Gómez', 31, 'Zagueiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Palmeiras')),
('Rafael Veiga', 28, 'Meio-Campo', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Palmeiras')),
('Diego Alves', 39, 'Goleiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Flamengo')),
('Rodrigo Caio', 28, 'Zagueiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Flamengo')),
('Gabigol', 27, 'Atacante', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Flamengo')),
('Hugo Souza', 25, 'Goleiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Athletico Paranaense')),
('Thiago Heleno', 35, 'Zagueiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Athletico Paranaense')),
('Nikão', 31, 'Meio-Campo', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Athletico Paranaense')),
('Felipe Alves', 35, 'Goleiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Fortaleza')),
('Tinga', 32, 'Zagueiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Fortaleza')),
('Lucero', 29, 'Atacante', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Fortaleza')),
('Felipe', 36, 'Goleiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Botafogo')),
('Natan', 24, 'Zagueiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Botafogo')),
('Tiquinho Soares', 32, 'Atacante', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Botafogo')),
('Walter', 35, 'Goleiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Cuiabá')),
('Marllon', 31, 'Zagueiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Cuiabá')),
('Clayson', 31, 'Atacante', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Cuiabá')),
('Gustavo', 26, 'Goleiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Grêmio')),
('Pedro Geromel', 37, 'Zagueiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Grêmio')),
('Douglas Costa', 33, 'Meio-Campo', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Grêmio')),
('Ezequiel', 33, 'Goleiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Novo Hamburgo')),
('Marcão', 28, 'Zagueiro', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Novo Hamburgo')),
('Jadson', 32, 'Meio-Campo', (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Novo Hamburgo'));



iNSERT INTO Partida (data_hora, id_estadio, id_time_mandante, id_time_visitante, gols_mandante, gols_visitante, status) VALUES
('2025-01-23 20:00:00', (SELECT id_estadio FROM Estadio WHERE nome = 'Arena da Baixada'), (SELECT id_time FROM Time WHERE nome = 'Athletico Paranaense'), (SELECT id_time FROM Time WHERE nome = 'Fortaleza'), 1, 1, 'Encerrada'),
('2025-01-24 20:00:00', (SELECT id_estadio FROM Estadio WHERE nome = 'Beira-Rio'), (SELECT id_time FROM Time WHERE nome = 'Internacional'), (SELECT id_time FROM Time WHERE nome = 'Cuiabá'), 3, 0, 'Encerrada'),
('2025-01-25 20:00:00', (SELECT id_estadio FROM Estadio WHERE nome = 'Arena do Grêmio'), (SELECT id_time FROM Time WHERE nome = 'Grêmio'), (SELECT id_time FROM Time WHERE nome = 'Botafogo'), 1, 4, 'Encerrada')
('2025-01-22 20:00:00', (SELECT id_estadio FROM Estadio WHERE nome = 'Maracanã'), (SELECT id_time FROM Time WHERE nome = 'Flamengo'), (SELECT id_time FROM Time WHERE nome = 'Athletico Paranaense'), 0, 2, 'Encerrada'),
('2025-01-21 20:00:00', (SELECT id_estadio FROM Estadio WHERE nome = 'Arena Corinthians'), (SELECT id_time FROM Time WHERE nome = 'Corinthians'), (SELECT id_time FROM Time WHERE nome = 'Palmeiras'));

INSERT INTO Classificacao (id_time, pontos, vitorias, empates, derrotas, gols_marcados, gols_sofridos)
VALUES 
(43, 10, 3, 1, 1, 8, 5),  
(44, 12, 4, 0, 1, 9, 4),  
(45, 7, 2, 1, 2, 6, 5),  
(46, 9, 3, 0, 2, 7, 6),   
(47, 6, 2, 0, 3, 5, 7)
(42, 10, 3, 1, 0, 15, 5);

INSERT INTO BANCODEDADOSBR.Cartao (id_jogador, id_partida, tipo) VALUES
(18, (SELECT id_partida FROM BANCODEDADOSBR.Partida WHERE id_time_mandante = (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Grêmio') AND id_time_visitante = (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Botafogo') AND data_hora = '2025-01-25 20:00:00'), 'Amarelo'),
(1, (SELECT id_partida FROM BANCODEDADOSBR.Partida WHERE id_time_mandante = (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Corinthians') AND id_time_visitante = (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Palmeiras') AND data_hora = '2025-01-21 20:00:00'), 'Amarelo'),
(3, (SELECT id_partida FROM BANCODEDADOSBR.Partida WHERE id_time_mandante = (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Corinthians') AND id_time_visitante = (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Palmeiras') AND data_hora = '2025-01-21 20:00:00'), 'Vermelho'),
(7, (SELECT id_partida FROM BANCODEDADOSBR.Partida WHERE id_time_mandante = (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Flamengo') AND id_time_visitante = (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Athletico Paranaense') AND data_hora = '2025-01-22 20:00:00'), 'Vermelho'),
(9, (SELECT id_partida FROM BANCODEDADOSBR.Partida WHERE id_time_mandante = (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Athletico Paranaense') AND id_time_visitante = (SELECT id_time FROM BANCODEDADOSBR.Time WHERE nome = 'Fortaleza') AND data_hora = '2025-01-23 20:00:00'), 'Amarelo');

INSERT INTO Arbitro (nome, cidade, estado) VALUES
('Carlos Oliveira', 'São Paulo', 'SP'),
('Roberto Silva', 'Rio de Janeiro', 'RJ'),
('Fábio Santos', 'Belo Horizonte', 'MG'),
('Luiz Souza', 'Porto Alegre', 'RS'),
('Marcelo Lima', 'Curitiba', 'PR');

INSERT INTO Partida_Arbitro (id_partida, id_arbitro, funcao) VALUES
(1, 1, 'Principal'),
(1, 2, 'Assistente'),
(2, 3, 'Principal'),
(2, 4, 'Assistente'),
(3, 5, 'Principal');


INSERT INTO ComissaoTecnica (nome, funcao, id_time) VALUES
('Fernando Diniz', 'Técnico', (SELECT id_time FROM Time WHERE nome = 'Flamengo')),
('Maurício de Souza', 'Preparador Físico', (SELECT id_time FROM Time WHERE nome = 'Flamengo')),
('Rogério Ceni', 'Técnico', (SELECT id_time FROM Time WHERE nome = 'São Paulo')),
('Waldemar Lemos', 'Preparador de Goleiros', (SELECT id_time FROM Time WHERE nome = 'Corinthians')),
('Ricardo Gomes', 'Diretor Técnico', (SELECT id_time FROM Time WHERE nome = 'Palmeiras'));
