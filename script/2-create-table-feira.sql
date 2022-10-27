CREATE TABLE IF NOT EXISTS tb_enderecos(
   `id` 		   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `long` 	       BIGINT NOT NULL,
   `lat` 		   BIGINT NOT NULL,
   `setcens`       BIGINT NOT NULL,
   `areap`         BIGINT NOT NULL,
   `coddist`       TINYINT NOT NULL,
   `distrito`      VARCHAR(250) NOT NULL,
   `codsubpref`    TINYINT NOT NULL,
   `subprefe`      VARCHAR(250) NOT NULL,
   `regiao5`       VARCHAR(50) NOT NULL,
   `regiao8`       VARCHAR(50) NOT NULL,
   `nome_feira`    varchar(250) NOT NULL,
   `registro`      CHAR(6) NOT NULL,
   `logradouro`    varchar(250) NOT NULL,   
   `numero`        VARCHAR(50) NULL,
   `bairro`        VARCHAR(100) NOT NULL,
   `referencia`    VARCHAR(250) NOT NULL
);