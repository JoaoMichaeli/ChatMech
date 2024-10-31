from flask import Flask, request, jsonify, render_template
import pandas as pd
import pickle

app = Flask(__name__)

# Carregar o modelo e o vetor TF-IDF
with open('modelo_tfidf.pkl', 'rb') as modelo_file:
    modelo_tfidf = pickle.load(modelo_file)

with open('vetor_tfidf.pkl', 'rb') as vetor_file:
    vetor_tfidf = pickle.load(vetor_file)

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/prever', methods=['POST'])
def prever():
    # Receber as perguntas como JSON
    dados = request.get_json()

    # Unificar todas as perguntas em uma única string
    conversa_unificada = ' '.join(dados.values())

    # Criar um DataFrame para a conversa unificada
    conversa_df = pd.DataFrame({'unificação': [conversa_unificada]})

    # Transformar para TF-IDF
    conversa_tfidf = vetor_tfidf.transform(conversa_df['unificação'])

    # Fazer a previsão
    predicao = modelo_tfidf.predict(conversa_tfidf)

    # Retornar a previsão como JSON
    return jsonify({'previsao': predicao[0]})

@app.route('/cliente')
def cliente():
    return render_template('cliente.html')

if __name__ == '__main__':
    app.run(debug=True)
