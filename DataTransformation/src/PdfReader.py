import tabula
import pandas as pd

class PdfReader:
    def __init__(self, file_path, output_file):
        self.file_path = file_path
        self.output_file = output_file

    def read_pdf(self):
        return tabula.read_pdf(self.file_path, pages="3-181", multiple_tables=True, encoding="ISO-8859-1")

    def extract_tables(self):
        extracted_tables = self.read_pdf()
        return pd.concat(extracted_tables, ignore_index=True)

    def replace_abbreviations(self, df):
        substitutions = {
            "OD": "Seg. Odontológica",
            "AMB": "Seg. Ambulatorial"
        }
        df.replace(substitutions, inplace=True)
        return df

    def save_to_csv(self, df):
        df.to_csv(self.output_file, encoding="utf-8", index=False)
        print(f"CSV salvo em: {self.output_file}")