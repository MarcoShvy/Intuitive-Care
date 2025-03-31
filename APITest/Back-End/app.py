from flask import Flask, jsonify
from flask_cors import CORS
from routes.api import api_blueprint


def create_app():
    app = Flask(__name__)

    # Configurações básicas
    app.config['JSON_AS_ASCII'] = False  # Para suporte a caracteres acentuados

    # Registra o blueprint
    app.register_blueprint(api_blueprint)
    CORS(app)

    return app


app = create_app()

if __name__ == '__main__':
    app.run(debug=True, port=5000)