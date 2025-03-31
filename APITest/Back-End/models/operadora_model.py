import pandas as pd
from typing import List, Dict, Optional
from fuzzywuzzy import fuzz

class OperadoraModel:
    def __init__(self, data_path: str):
        self.df = self._load_data(data_path)

    def _calcular_relevancia(self, row: pd.Series, termo: str) -> int:
        """Calcula a relevância para uma linha"""
        texto = f"{row['Nome_Fantasia']} {row['Razao_Social']}"
        return fuzz.token_set_ratio(termo.lower(), texto.lower())

    def _load_data(self, path: str) -> pd.DataFrame:
        """Carrega e prepara os dados"""
        df = pd.read_csv(
            path,
            encoding='utf-8',
            sep=';',
            dtype={'Registro_ANS': str, 'CNPJ': str}
        )
        df['Nome_Fantasia'] = df['Nome_Fantasia'].fillna(df['Razao_Social'])
        return df

    def buscar(self, termo: str, limite: int = 10) -> List[Dict]:
        """Realiza a busca fuzzy nas operadoras com limite de resultados"""
        if not termo or self.df.empty:
            return []

        df_temp = self.df.copy()
        df_temp['Relevancia'] = df_temp.apply(
            lambda x: self._calcular_relevancia(x, termo),
            axis=1
        )

        # Aplica o limite após ordenar por relevância
        resultados = (
            df_temp[df_temp['Relevancia'] > 50]
            .sort_values('Relevancia', ascending=False)
            .head(limite)  # Aqui aplicamos o limite
            .to_dict('records')
        )



        return resultados