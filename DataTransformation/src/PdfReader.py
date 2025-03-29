import tabula
import pandas as pd

def extractAndConvertPDF(file_path, file_output):

    # LENDO ARQUIVO PDF COM O ENCODING CERTO
    extracted_tables = tabula.read_pdf(file_path, pages="3-181", multiple_tables=True, encoding="ISO-8859-1")

    # CONCTENANDO TABELAS PARA MODIFICAR TODAS
    extracted_tables_final = pd.concat(extracted_tables, ignore_index=True)

    substitutions = {
        "OD": "Seg. Odontológica",
        "AMB": "Seg. Ambulatorial"
    }

    # REESCREVENDO AS SUBSTITUIÇÕES
    extracted_tables_final.replace(substitutions, inplace=True)

    # SALVANDO ARQUIVO CSV
    extracted_tables_final.to_csv(file_output, encoding="utf-8", index=False)


    print(f"CSV salvo em: {file_output}")