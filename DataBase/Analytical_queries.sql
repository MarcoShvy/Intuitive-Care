
-- Quais as 10 operadoras com maiores despesas em "EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR" no último trimestre? --

SELECT 
    o.Registro_ANS, 
    o.Razao_Social,
	dc.data,
    SUM(dc.VL_SALDO_INICIAL - dc.VL_SALDO_FINAL) AS Total_Despesas
FROM demonstracoes_contabeis dc
JOIN operadoras o ON dc.REG_ANS = o.Registro_ANS
WHERE dc.descricao LIKE '%EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR %' AND dc.DATA BETWEEN '2024-10-01' AND '2024-12-31'
GROUP BY o.Registro_ANS, o.Razao_Social, dc.data
ORDER BY Total_Despesas DESC
LIMIT 10;

-- Quais as 10 operadoras com maiores despesas nessa categoria no último ano? --

SELECT 
    o.Registro_ANS, 
    o.Razao_Social,
	dc.data,
    SUM(dc.VL_SALDO_INICIAL - dc.VL_SALDO_FINAL) AS Total_Despesas
FROM demonstracoes_contabeis dc
JOIN operadoras o ON dc.REG_ANS = o.Registro_ANS
WHERE dc.DESCRICAO LIKE '%EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR %'
  AND dc.DATA BETWEEN '2024-01-01' AND '2024-12-31'
GROUP BY o.Registro_ANS, o.Razao_Social, dc.data
ORDER BY Total_Despesas DESC
LIMIT 10;