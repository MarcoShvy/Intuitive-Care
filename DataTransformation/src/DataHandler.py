import pandas as pd
class DataHandler:
    @staticmethod
    def transform_data(df):
        substitutions = {
            "OD": "Seg. Odontológica",
            "AMB": "Seg. Ambulatorial"
        }
        return df.replace(substitutions)

    @staticmethod
    def save_to_csv(df, output_file):
        df.to_csv(output_file, encoding="utf-8", index=False)
        print(f"CSV salvo em: {output_file}")