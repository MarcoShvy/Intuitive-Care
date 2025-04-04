CREATE TABLE demonstracoes_contabeis (
    ID SERIAL PRIMARY KEY,
    DATA DATE NOT NULL,
    REG_ANS INTEGER NOT NULL,
    CD_CONTA_CONTABIL VARCHAR(20) NOT NULL,
    DESCRICAO TEXT,
    VL_SALDO_INICIAL NUMERIC(18,2) DEFAULT 0,
    VL_SALDO_FINAL NUMERIC(18,2) DEFAULT 0,
    FOREIGN KEY (REG_ANS) REFERENCES operadoras(registro_ans),
    CONSTRAINT uk_demonstracoes UNIQUE (DATA, REG_ANS, CD_CONTA_CONTABIL)
);


-- Importando arquivos csv

SET maintenance_work_mem = '1GB';
SET work_mem = '512MB';
SET session_replication_role = 'replica';

CREATE TEMP TABLE demonstracoes_contabeis_temp (
    DATA TEXT, 
    REG_ANS TEXT, 
    CD_CONTA_CONTABIL TEXT, 
    DESCRICAO TEXT, 
    VL_SALDO_INICIAL TEXT, 
    VL_SALDO_FINAL TEXT
);

COPY demonstracoes_contabeis_temp(DATA, REG_ANS, CD_CONTA_CONTABIL, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL) FROM 'C:\PostgreSQL\data\1T2023.csv' DELIMITER ';' CSV HEADER ENCODING 'UTF8';
COPY demonstracoes_contabeis_temp(DATA, REG_ANS, CD_CONTA_CONTABIL, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL) FROM 'C:\PostgreSQL\data\2T2023.csv' DELIMITER ';' CSV HEADER ENCODING 'UTF8';
COPY demonstracoes_contabeis_temp(DATA, REG_ANS, CD_CONTA_CONTABIL, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL) FROM 'C:\PostgreSQL\data\3T2023.csv' DELIMITER ';' CSV HEADER ENCODING 'UTF8';
COPY demonstracoes_contabeis_temp(DATA, REG_ANS, CD_CONTA_CONTABIL, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL) FROM 'C:\PostgreSQL\data\4T2023.csv' DELIMITER ';' CSV HEADER ENCODING 'UTF8';
COPY demonstracoes_contabeis_temp(DATA, REG_ANS, CD_CONTA_CONTABIL, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL) FROM 'C:\PostgreSQL\data\1T2024.csv' DELIMITER ';' CSV HEADER ENCODING 'UTF8';
COPY demonstracoes_contabeis_temp(DATA, REG_ANS, CD_CONTA_CONTABIL, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL) FROM 'C:\PostgreSQL\data\2T2024.csv' DELIMITER ';' CSV HEADER ENCODING 'UTF8';
COPY demonstracoes_contabeis_temp(DATA, REG_ANS, CD_CONTA_CONTABIL, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL) FROM 'C:\PostgreSQL\data\3T2024.csv' DELIMITER ';' CSV HEADER ENCODING 'UTF8';
COPY demonstracoes_contabeis_temp(DATA, REG_ANS, CD_CONTA_CONTABIL, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL) FROM 'C:\PostgreSQL\data\4T2024.csv' DELIMITER ';' CSV HEADER ENCODING 'UTF8';

INSERT INTO demonstracoes_contabeis (DATA, REG_ANS, CD_CONTA_CONTABIL, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL)
SELECT 
    DATA::DATE, 
    REG_ANS::INT, 
    CD_CONTA_CONTABIL, 
    DESCRICAO, 
    REPLACE(VL_SALDO_INICIAL, ',', '.')::NUMERIC(15,2), 
    REPLACE(VL_SALDO_FINAL, ',', '.')::NUMERIC(15,2)
FROM demonstracoes_contabeis_temp;

DROP TABLE demonstracoes_contabeis_temp;
SET session_replication_role = 'origin';


-- Criação de índices para melhor performance
CREATE INDEX idx_demo_data ON demonstracoes_contabeis(DATA);
CREATE INDEX idx_demo_ans ON demonstracoes_contabeis(REG_ANS);
CREATE INDEX idx_demo_conta ON demonstracoes_contabeis(CD_CONTA_CONTABIL);

SELECT * FROM demonstracoes_contabeis
