from flask import Flask, request, jsonify
import pandas as pd
import pickle

app = Flask(__name__)

# Carregar o modelo e o escalonador
model_filename = 'sprint4_modelo.pkl'

with open(model_filename, 'rb') as file:
    model = pickle.load(file)

@app.route('/')
def home():
    return "Bem-vindo ao ChatMech!"

@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json(force=True)
    
    # Transformar o dicionário em um DataFrame
    nova_amostra_df = pd.DataFrame.from_dict(data, orient='index').transpose()
    
    
    # Fazer a previsão
    prediction = model.predict(nova_amostra_scaled)
    
    # Mapear o resultado para o nome da flor
    prediction_name = prediction[0]
    
    return jsonify({'prediction': prediction_name})

if __name__ == '__main__':
    app.run(debug=True)