import tabula
import pandas as pd

def extractAndConvertPDF():
    # VARIAVEIS
    file = "data/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf"
    output_file = "data/Tabelas.csv"

    # LENDO ARQUIVO PDF COM O ENCODING CERTO
    extracted_tables = tabula.read_pdf(file, pages="3-181", multiple_tables=True, encoding="ISO-8859-1")

    # CONCTENANDO TABELAS PARA MODIFICAR TODAS
    extracted_tables_final = pd.concat(extracted_tables, ignore_index=True)

    substitutions = {
        "OD": "Seg. Odontológica",
        "AMB": "Seg. Ambulatorial"
    }

    # REESCREVENDO AS SUBSTITUIÇÕES
    extracted_tables_final.replace(substitutions, inplace=True)

    # SALVANDO ARQUIVO CSV
    extracted_tables_final.to_csv(output_file, encoding="utf-8", index=False)


    print(f"CSV salvo em: {output_file}")