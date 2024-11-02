import os
import oracledb
import pandas as pd
import requests

os.system("cls")

# Conexão com o banco de dados
try: 
    conn = oracledb.connect(user="RM554456", password="080995", dsn="oracle.fiap.com.br:1521/ORCL")
    inst_insert = conn.cursor()
    inst_select = conn.cursor()
    inst_update = conn.cursor()
    inst_delete = conn.cursor()
except Exception as e:
    print("Conexão deu erro: ", e)
    conexao = False
else:
    conexao = True

#---------------------------------- Funções
class ExitProgram(Exception):
    pass

def clear():
    return os.system('cls' if os.name == 'nt' else 'clear')

def continuar():
    input("\nPressione enter para continuar...")
    clear()

def voltar_menu_inicial(entrada:str):
  if entrada.lower() == '0':
    menu_inicial()
  
def voltar_menu_principal(entrada:str):
  if entrada.lower() == '0':
    os.system('cls')
    menu_principal()

def voltar_menu_agendamento(entrada:str):
  if entrada.lower() == '0':
    os.system('cls')
    menu_servico()

def presione_qualquer_tecla_inicial():
  input("Pressione qualquer tecla para voltar ao menu inicial: ")
  menu_inicial()
  
def presione_qualquer_tecla_principal():
  input("Pressione qualquer tecla para voltar ao menu principal: ")
  menu_principal()
  
def menu_principal(id_cliente):
    os.system('cls')
    print(
          " -- CHATMECH --\n"
          "\n1 - Veiculos \n"
          "2 - Serviços \n"
          "3 - Retornar a tela inicial\n"
          "0 - SAIR\n")
    while True:
        opcao = input("Escolha uma opção: ")
        match opcao:
          case '1':
            os.system("cls")
            menu_veiculo(id_cliente)
          case '2':
            menu_servico()
            break
          case '3':
            menu_inicial()
            break
          case '0':
            print("Saindo....")
            exit()
          case _:
            print("\nOpção inválida! digite novamente.")

servicos = {
  } # Um dicionário que armazena os dados de serviços agendados

def mostra_servicos_agendados():
  if not servicos:
    os.system('cls')
    print("Não há nenhum serviço cadastrado!\n")
  else:
    os.system('cls')
    print(" -- SERVIÇOS AGENDADOS -- \n")
    i = 1
    for servico, dados in servicos.items():
      dia = dados.get('dia')
      mes = dados.get('mes')
      
      if dia < 10:
        dia = "0" + str(dia)
      if mes < 10:
        mes = "0" + str(mes)
        
      print(f"- Serviço {i}: {dia}/{mes} ás {dados['hora']}hrs\n")
      i += 1

def atualiza_chaves_agendamento(dicionario:dict) -> None:
  servicos_copy = {}
  for i, (servico, dados) in enumerate(dicionario.items()):
    servicos_copy[f"servico{i+1}"] = dados
  servicos.clear()
  servicos.update(servicos_copy)

def cancelar_agendamento():
  os.system('cls')
  selecao_valida = False
  while True:
    mostra_servicos_agendados()
    while True:
      selecao = verifica_input_vazio("Digite o número do agendamento a ser cancelado: ", 'a')
      if not selecao.isdigit():
        mostra_servicos_agendados()
        print("Número de agendamento inexistente!")
        continue
      else:
        chave = 'servico' + selecao
        confirmacao = input(f"Tem certeza que deseja excluir {chave}? (s/n): ").lower()
        break
    match confirmacao:
      case 's':
        atualiza_chaves_agendamento(servicos)
        # Cancelar o agendamento
        del servicos[chave]
        print("\nAgendamento cancelado com sucesso!\n")
        input("Pressione qualquer tecla para voltar ao menu de servicos: ")
        os.system('cls')
        menu_servico()
      case 'n':
        os.system('cls')
        menu_servico()
        break
  
def menu_servico():
    os.system('cls')
    print(" -- SERVIÇOS --")
    while True:
      opcao = input("""
1 - Agendar serviço
2 - Serviços agendados
3 - Cancelar agendamento
4 - Menu principal

Escolha: """)
      match opcao:
        case "1":
          os.system('cls')
          agendar_servico()
        case "2":
          mostra_servicos_agendados()
          input("Pressione qualquer tecla para voltar: ")
          menu_servico()
          break
        case "3":
          cancelar_agendamento()
        case "4":
          menu_principal()
          break
        case _:
          print(" **ERRO! Digite uma opção válida**")
          
def agendar_servico():
    os.system('cls')
    while True:
        try:
            # Verifica o dia
            while True:
                dia = verifica_input_vazio("\nQual dia gostaria de agendar seu serviço?\nEscolha: ", 'a')
                try:
                    dia = int(dia)
                    if not (1 <= dia <= 31):
                        raise ValueError("dia")
                    break  # Sai do loop quando o dia for válido
                except ValueError:
                    print("**ERRO! Dia deve estar entre 1 e 31.**")

            # Verifica o mês
            while True:
                mes = verifica_input_vazio("\nQual o mês?\nEscolha: ", 'a')
                try:
                    mes = int(mes)
                    if not (1 <= mes <= 12):
                        raise ValueError("mes")
                    break  # Sai do loop quando o mês for válido
                except ValueError:
                    print("**ERRO! Mês deve estar entre 01 e 12.**")

            # Verifica o horário
            while True:
                hora = verifica_input_vazio("\nQual o horário do agendamento? (Formato hh:mm)\nEscolha: ", 'a')
                try:
                    hora_parts = hora.split(':')
                    if len(hora_parts) != 2 or not all(part.isdigit() for part in hora_parts):
                        raise ValueError("hora_formato")
                    
                    # Valida a hora e minuto
                    hora_int = int(hora_parts[0])
                    minuto_int = int(hora_parts[1])
                    if not (0 <= hora_int < 24 and 0 <= minuto_int < 60):
                        raise ValueError("hora_valor")
                    break  # Sai do loop quando o horário for válido
                except ValueError as e:
                    if str(e) == "hora_formato":
                        print("**ERRO! O horário deve estar no formato hh:mm.**")
                    elif str(e) == "hora_valor":
                        print("**ERRO! Horário inválido. Hora deve estar entre 00:00 e 23:59.**")
            break  # Sai do loop geral se todos os dados forem válidos
        
        except Exception as e:
            os.system("cls")
            print(f"Erro inesperado: {e}")
    
    servicos[f"servico{len(servicos)+1}"] = {'dia':dia,'mes':mes,'hora':hora}
    
    # Aqui segue o fluxo normal após o agendamento correto
    os.system('cls')
    print(f"Serviço agendado para {dia:02}/{mes:02} às {hora}.")
    input("\nPressione qualquer tecla para retornar ao menu anterior...")
    menu_servico()
        
def acessar_usuario():
    try:
        while True:
            print("-- MENU ACESSO --")
            print("\n1 - Registrar usuário\n"
                "2 - Fazer login\n"
                "3 - Encerrar programa\n")
            try:
                opcao = int(input("Escolha uma opção: "))
                if opcao == 1: # Irá chamar a tela de registro de usuário
                    clear()
                    registrar_usuario()
                elif opcao == 2: # Irá chamar a tela de login
                    id_cliente = verificar_login()
                    if id_cliente is not None:
                        print("\nAcesso concedido")
                        menu_principal(id_cliente)
                    else:
                        clear()
                        print("Acesso negado, você não possui uma conta registrada...\n"
                            "Por favor, Registre-se aqui: \n")
                        return registrar_usuario()
                elif opcao == 3: # Sairá do programa
                    print("Saindo...")
                    exit()
                else:
                    print("Opção inválida. Tente novamente.\n")
            except ValueError:
                print("Entrada inválida! Por favor, digite um número.")
                clear()
    except ExitProgram:
        print("Programa encerrado.")

def verifica_input_vazio(pergunta:str, tipo:str) -> str:
    while(True):
        entrada = ""
        entrada = input(f"Digite '0' para voltar ao menu\n{pergunta}")
        if tipo == 'i':
          voltar_menu_inicial(entrada)
        elif tipo == 'p':
          voltar_menu_principal(entrada)
        elif tipo == 'a':
          voltar_menu_agendamento(entrada)
        elif tipo == 'v':
          voltar_menu_veiculo(entrada)
        if entrada == "":
          print("ERRO! campo não pode estar vazio!")
        else:
          clear()
          break
    return entrada

def consulta_cep(cep:str) -> None:
    url = f'https://viacep.com.br/ws/{cep}/json/'
    resposta = requests.get(url)

    if resposta.status_code == 200:
        dados = resposta.json()
        if 'erro' not in dados:
            return dados
        else:
            print("CEP não encontrado.")
            return None
    else:
        print("Erro ao consultar o CEP.")
        return None

def confirmar_informacoes(dados_endereco: dict):
    clear()

    print(f'''
  Informações encontradas: 
  CEP: {dados_endereco['cep']}
  Logradouro: {dados_endereco['logradouro']}
  Bairro: {dados_endereco['bairro']}
  Localidade: {dados_endereco['localidade']}
  Estado: {dados_endereco['uf']}''')

    confirmacao = input("\nEssas informações estão corretas? (s/n): ").strip().lower()
    return confirmacao == 's'

def salvar_usuario (login:str, senha:str, cep:str, dados_endereco:dict) -> None:
    
    sql = """
    INSERT INTO tbl_cadastros (login, senha, cep, logradouro,bairro, localidade, uf)
    VALUES (:login, :senha, :cep, :logradouro, :bairro, :localidade, :uf)
    """

    dados_para_inserir = {
      'login': login,
      'senha': senha,
      'cep': cep,
      'logradouro': dados_endereco['logradouro'],
      'bairro': dados_endereco['bairro'],
      'localidade': dados_endereco['localidade'],
      'uf': dados_endereco['uf']
    }

    try:
      inst_insert.execute(sql, dados_para_inserir)
      conn.commit()
      print('\nUsuário cadastrado com sucesso!')
      pressione = print('\nPressione enter para acessar o Menu')
      clear()
      acessar_usuario()
    except Exception as e:
      print('Erro ao salvar no banco de dados: ', e)

def registrar_usuario():
    while True:
        print('Para realizar o seu cadastro, preencha as informações abaixo:')
        login = verifica_input_vazio("\nDigite o login (mínimo 4 caracteres): ", 'i').strip()
        if len(login) >= 4:
            break
        else:
            print("\nO login deve ter no mínimo 4 caracteres. Tente novamente.")
    
    while True:
        senha = verifica_input_vazio("\nDigite a senha (mínimo 4 caracteres, máximo 16): ", 'i').strip()
        if 4 <= len(senha) <= 16:
            break
        else:
            print("\nA senha deve ter entre 4 e 16 caracteres. Tente novamente.")
    
    cep = input("Digite o CEP: ")

    dados_endereco = consulta_cep(cep)

    if dados_endereco and confirmar_informacoes(dados_endereco):
        salvar_usuario(login, senha, cep, dados_endereco)
    else:
        print("\nRegistro cancelado ou CEP inválido.")
        continuar()


def verificar_login():
    clear()
    print("Para realizar seu login, preencha as informações abaixo:\n")
    login = verifica_input_vazio("Digite seu login: ", 'i').strip()
    senha = verifica_input_vazio("Digite sua senha: ", 'i').strip()

    sql = """
    SELECT id_cliente FROM tbl_cadastros WHERE login = :login AND senha = :senha
    """
    try:
        inst_select.execute(sql, {'login': login, 'senha': senha})
        resultado = inst_select.fetchone()

        if resultado:
            id_cliente = resultado[0]
            return id_cliente
        else:
            print("Login ou senha incorretos. Tente novamente.")
            return None
    except Exception as e:
      print('Ocorreu um erro inesperado: ', e)
   
def mostrar_veiculos(id_cliente):
    sql = """
    SELECT placa, modelo FROM tbl_veiculos WHERE id_cliente = :id_cliente
    """
    try:
        inst_select.execute(sql, {'id_cliente': id_cliente})
        veiculos = inst_select.fetchall()

        if veiculos:
            print("--- Veículos Cadastrados ---")
            for veiculo in veiculos:
                print(f"\nPlaca: {veiculo[0]}\nModelo: {veiculo[1]}")
        else:
            print("\nNenhum veículo cadastrado para este cliente.")
    except Exception as e:
        print("Ocorreu um erro ao listar os veículos:", e)

def verifica_placa_valida(id_cliente) -> str:
    while True:
        print('Caso queira retornar ao menu, digite "0"')
        placa = input("\nDigite a placa do veículo: ").strip().upper()
        if placa == '0':
          clear()
          menu_veiculo(id_cliente)
        elif len(placa) != 7:
            clear()
            print("ERRO! Placa deve ter 7 dígitos")
        else:
            return placa

def verifica_modelo_valio(id_cliente):
   while True:
      clear()
      print('Caso queira retornar ao menu, digite "0"')
      modelo = input("\nDigite o modelo do veículo: ").strip().lower()
      if modelo == '0':
        clear()
        menu_veiculo(id_cliente)
      elif modelo:
        return modelo
      else:
        print('\nErro! O modelo do veiculo não pode ser vazio')
      

def cadastrar_veiculo(id_cliente):
    print("-- CADASTRO DE VEÍCULO --")
    placa = verifica_placa_valida(id_cliente)
    modelo = verifica_modelo_valio(id_cliente)

    sql = """
    INSERT INTO tbl_veiculos (placa, id_cliente, modelo)
    VALUES (:placa, :id_cliente, :modelo)
    """
    
    try:
        inst_insert.execute(sql, {'placa': placa, 'id_cliente': id_cliente, 'modelo': modelo})
        conn.commit()
        print("\nVeículo cadastrado com sucesso!")
        continuar()
    except Exception as e:
        print("Erro ao cadastrar veículo:", e)

def excluir_veiculo(id_cliente):
    sql_listar_veiculos = """
    SELECT placa, modelo FROM tbl_veiculos WHERE id_cliente = :id_cliente
    """
    try:
        inst_select.execute(sql_listar_veiculos, {'id_cliente': id_cliente})
        veiculos = inst_select.fetchall()

        if not veiculos:
          print("Nenhum veículo cadastrado para este cliente.")
          pressione = input('\nPressione enter para retornar ao menu...')
          clear()
          menu_veiculo(id_cliente)
        
        print("\--- Veículos Cadastrados ---")
        for i, veiculo in enumerate(veiculos):
            print(f"\n{i + 1} - Placa: {veiculo[0]}, Modelo: {veiculo[1]}")

        escolha = int(input("\nEscolha o número do veículo que deseja excluir (ou 0 para voltar): "))

        if escolha == 0:
          clear()
          menu_veiculo(id_cliente)

        if 1 <= escolha <= len(veiculos):
            placa = veiculos[escolha - 1][0]

            sql_excluir = """
            DELETE FROM tbl_veiculos WHERE placa = :placa AND id_cliente = :id_cliente
            """
            inst_select.execute(sql_excluir, {'placa': placa, 'id_cliente': id_cliente})
            conn.commit()
            print(f"\nVeículo com placa {placa} excluído com sucesso!")
            pressione = input('\nPressione enter para retornar ao menu...')
            clear()
            menu_veiculo(id_cliente)
        else:
            print("Escolha inválida. Nenhum veículo excluído.")
    except Exception as e:
        print("Ocorreu um erro ao excluir o veículo:", e)

def editar_veiculo(id_cliente):
  sql_listar_veiculos = """
  SELECT placa, modelo FROM tbl_veiculos WHERE id_cliente = :id_cliente
  """
  try:
      inst_select.execute(sql_listar_veiculos, {'id_cliente': id_cliente})
      veiculos = inst_select.fetchall()

      if not veiculos:
          clear()
          print("Nenhum veículo cadastrado para este cliente.")
          pressione = input('\nPressione enter para retornar ao menu...')
          clear()
          menu_veiculo(id_cliente)

      clear()
      print("--- Veículos Cadastrados ---")
      for i, veiculo in enumerate(veiculos):
          print(f"\n{i + 1} - Placa: {veiculo[0]}, Modelo: {veiculo[1]}")

      escolha = int(input("\nEscolha o número do veículo que deseja editar (ou 0 para voltar): "))
      
      if escolha == 0:
        clear()
        menu_veiculo(id_cliente)

      if 1 <= escolha <= len(veiculos):
          placa_atual = veiculos[escolha - 1][0]

          nova_placa = input("\nDigite a nova placa (ou pressione ENTER para manter a atual): ").strip()
          if not nova_placa:
            nova_placa = placa_atual
          
          novo_modelo = input("\nDigite o novo modelo (ou pressione ENTER para manter o atual): ").strip()
          if not novo_modelo:
              novo_modelo = veiculos[escolha - 1][1]

          sql_editar = """
          UPDATE tbl_veiculos
          SET modelo = :modelo, placa = :placa
          WHERE placa = :placa AND id_cliente = :id_cliente
          """
          inst_select.execute(sql_editar, {'modelo': novo_modelo, 'placa': placa_atual, 'id_cliente': id_cliente})
          conn.commit()
          print(f"\nVeículo com placa {placa_atual} atualizado com sucesso!")

      else:
          print("Escolha inválida. Nenhum veículo editado.")
  except Exception as e:
      print("Ocorreu um erro ao editar o veículo:", e)

def voltar_menu_veiculo(id_cliente) -> None:
  input("\nPressione enter para voltar ao menu de veículos...")
  os.system('cls')
  menu_veiculo(id_cliente)

def menu_veiculo(id_cliente): #CRUD
  while True:
    print(""" -- MENU VEÍCULOS --
\n1 - Cadastrar veículo
2 - Mostrar veículos cadastrados
3 - Editar veículo
4 - Excluir veículo
5 - Voltar ao menu principal
""")
    opcao = input("Escolha uma opção: ")
    match opcao:        
      case "1":
        clear()
        cadastrar_veiculo(id_cliente)
      case "2":
        clear()
        mostrar_veiculos(id_cliente)
        voltar_menu_veiculo(id_cliente)
        break
      case "3":
        clear()
        editar_veiculo(id_cliente)
        voltar_menu_veiculo(id_cliente)
        break
      case "4":
        clear()
        excluir_veiculo(id_cliente)
      case "5":
        menu_principal(id_cliente)
        break
      case _:
        print("Opção inválida. Tente novamente.")
        
#---------------------------------- Programa principal ----------------------------------

def menu_inicial():
  os.system("cls")
  print("Bem vindo ao ChatMech, acesse sua conta: \n")
  print("0 - SAIR\n1 - Acessar ou Cadastrar usuário\n")

  while True:
    opcao = input("\nEscolha uma opção: ")
    if opcao != "":  
      match opcao:
        case "0":
          clear()
          print("Saindo...")
          exit()
          break
        case "1":
          clear()
          acessar_usuario()
          break
        case _:
          print("ERRO! Digite uma opção válida.")
    else:
      print("ERRO! Digite uma opção válida")
      
menu_inicial()

#menu_principal()