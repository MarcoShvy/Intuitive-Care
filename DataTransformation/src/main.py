from PdfReader import PdfReader
from DataHandler import DataHandler

def extract_and_convert_pdf():
    file_path = "data/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf"
    output_file = "data/Tabelas.csv"

    # Leitura e extração do PDF
    pdf_reader = PdfReader(file_path, output_file)
    extracted_df = pdf_reader.extract_tables()

    # Transformação dos dados (substituições)
    transformed_df = DataHandler.transform_data(extracted_df)

    # Salvando o arquivo CSV
    DataHandler.save_to_csv(transformed_df, output_file)

if __name__ == "__main__":
    extract_and_convert_pdf()
