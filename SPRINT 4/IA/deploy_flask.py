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
    # Receber a nova conversa como JSON
    dados = request.get_json()

    # Converter a conversa em DataFrame
    nova_conversa_df = pd.DataFrame([dados])
    
    # Concatenar as colunas em uma única coluna de texto
    nova_conversa_df['unificação'] = nova_conversa_df.apply(lambda x: ' '.join(x.astype(str)), axis=1)

    # Transformar para TF-IDF
    nova_conversa_tfidf = vetor_tfidf.transform(nova_conversa_df['unificação'])

    # Fazer a previsão
    predicao = modelo_tfidf.predict(nova_conversa_tfidf)

    # Retornar a previsão como JSON
    return jsonify({'previsao': predicao[0]})

@app.route('/cliente')
def cliente():
    return render_template('cliente.html')

if __name__ == '__main__':
    app.run(debug=True)
