from flask import Blueprint, request, jsonify
from services.operadoras_service import OperadoraService

api_blueprint = Blueprint('api', __name__)
service = OperadoraService()


@api_blueprint.route('/buscar_operadoras', methods=['GET'])
def buscar_operadoras():
    try:
        termo = request.args.get('termo', '').strip()
        if not termo:
            return jsonify({
                'success': False,
                'error': 'Termo de busca não fornecido',
                'data': []
            }), 400

        try:
            limite = int(request.args.get('limite', 10))
            limite = max(1, min(limite, 100))  # Limita entre 1 e 100
        except ValueError:
            limite = 10

        resultados = service.buscar_operadoras(termo, limite)

        return jsonify({
            'success': True,
            'count': len(resultados),
            'limit': limite,
            'term': termo,
            'data': resultados
        })

    except Exception as e:
        return jsonify({
            'success': False,
            'error': str(e),
            'data': []
        }), 500