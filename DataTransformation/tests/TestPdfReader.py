import unittest
from unittest.mock import patch, MagicMock
import pandas as pd
from io import StringIO
from src.PdfReader import PdfReader


class TestPdfReader(unittest.TestCase):

    @patch('tabula.read_pdf')
    def test_read_pdf(self, mock_read_pdf):
        # Mock da resposta do tabula.read_pdf
        mock_read_pdf.return_value = [pd.DataFrame({'A': [1, 2], 'B': [3, 4]})]

        pdf_reader = PdfReader(file_path="fake_path.pdf", output_file="output.csv")
        result = pdf_reader.read_pdf()

        mock_read_pdf.assert_called_once_with("fake_path.pdf", pages="3-181", multiple_tables=True,
                                              encoding="ISO-8859-1")
        self.assertEqual(len(result), 1)
        self.assertEqual(result[0].shape, (2, 2))

    def test_extract_tables(self):
        pdf_reader = PdfReader(file_path="fake_path.pdf", output_file="output.csv")

        # Simulando o retorno de read_pdf
        fake_data = [pd.DataFrame({'A': [1, 2], 'B': [3, 4]}), pd.DataFrame({'A': [5, 6], 'B': [7, 8]})]
        pdf_reader.read_pdf = MagicMock(return_value=fake_data)

        result = pdf_reader.extract_tables()

        # Testa se o DataFrame concatenado tem o número correto de linhas
        self.assertEqual(result.shape[0], 4)  # 2 linhas de cada DataFrame concatenado
        self.assertEqual(result.shape[1], 2)  # 2 colunas

    def test_replace_abbreviations(self):
        pdf_reader = PdfReader(file_path="fake_path.pdf", output_file="output.csv")

        # Criando um DataFrame de exemplo
        df = pd.DataFrame({'A': ['OD', 'AMB'], 'B': ['x', 'y']})

        result = pdf_reader.replace_abbreviations(df)

        # Verificando se as substituições ocorreram corretamente
        self.assertEqual(result['A'].iloc[0], "Seg. Odontológica")
        self.assertEqual(result['A'].iloc[1], "Seg. Ambulatorial")

    @patch('pandas.DataFrame.to_csv')
    def test_save_to_csv(self, mock_to_csv):
        pdf_reader = PdfReader(file_path="fake_path.pdf", output_file="output.csv")

        # Criando um DataFrame de exemplo
        df = pd.DataFrame({'A': [1, 2], 'B': [3, 4]})

        # Chamando a função para salvar como CSV
        pdf_reader.save_to_csv(df)

        mock_to_csv.assert_called_once_with("output.csv", encoding="utf-8", index=False)
        print("Test Save To CSV passed!")


if __name__ == '__main__':
    unittest.main()