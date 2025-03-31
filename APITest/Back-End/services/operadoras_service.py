from models.operadora_model import OperadoraModel
from config import Config


class OperadoraService:
    def __init__(self):
        self.model = OperadoraModel(Config.DATA_FILE_PATH)

    def buscar_operadoras(self, termo: str, limite: int = 10) -> dict:
        try:
            # Garante que o limite é um inteiro válido
            limite = max(1, min(limite, 10))
            resultados = self.model.buscar(termo, limite)

            return {
                'success': True,
                'data': resultados,
                'count': len(resultados),
                'limit': limite
            }
        except Exception as e:
            return {
                'success': False,
                'error': str(e)
            }