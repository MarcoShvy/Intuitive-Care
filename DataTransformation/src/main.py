import logging
from PdfReader import extractAndConvertPDF

# Configuração do logger
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)


def main():
    # Definindo os caminhos dos arquivos
    input_file = "data/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf"
    output_file = "data/Tabelas.csv"

    try:
        # Instanciando a classe PdfReader
        pdf_reader = extractAndConvertPDF(input_file, output_file)

        # Extraindo e convertendo o PDF para CSV
        logger.info("Iniciando a extração e conversão do PDF...")
        pdf_reader.extract_and_convert_pdf()
        logger.info(f"CSV salvo com sucesso em: {output_file}")

    except Exception as e:
        logger.error(f"Ocorreu um erro ao processar o PDF: {e}")


if __name__ == "__main__":
    main()