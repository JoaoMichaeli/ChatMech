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
    
def voltar_menu_veiculo(entrada:str):
  if entrada.lower() == '0':
    os.system('cls')
    menu_veiculo()

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
                    os.system("cls")
                    registrar_usuario()
                elif opcao == 2: # Irá chamar a tela de login
                    id_cliente = verificar_login()
                    if id_cliente is not None:
                        print("\nAcesso concedido")
                        menu_principal(id_cliente)
                    else:
                        os.system("cls")
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
                os.system("cls")
    except ExitProgram:
        print("Programa encerrado.")

def verifica_input_vazio(pergunta:str, tipo:str) -> str:
    while(True):
        entrada = ""
        entrada = input(f"Digite '0' para voltar ao menu --\n{pergunta}")
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
          os.system('cls')
          break
    return entrada

cadastros = {  # Um dicionário que armazena os dados de login e senha

}

def verifica_se_existe(dado:str,chave:str,dicionario:dict) -> bool:
  for k, v in dicionario.items():
    if dado == v[chave]:
      return True
    else:
      return False
    # break  # Sai do loop se a placa não existir

def consulta_cep(cep:str) -> None:
    url = f'https://viacep.com.br/ws/{cep}/json/'
    response = requests.get(url)

    if response.status_code == 200:
        dados = response.json()
        if 'erro' not in dados:
            return dados
        else:
            print("CEP não encontrado.")
            return None
    else:
        print("Erro ao consultar o CEP.")
        return None

def confirmar_informacoes(dados: dict):
    os.system('cls')

    print(f'''
  Informações encontradas: 
  CEP: {dados['cep']}
  Logradouro: {dados['logradouro']}
  Bairro: {dados['bairro']}
  Localidade: {dados['localidade']}
  Estado: {dados['uf']}''')

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
      print('Usuário cadastrado com sucesso!')
      acessar_usuario()
    except Exception as e:
      print('Erro ao salvar no banco de dados: ', e)

def registrar_usuario():
    while True:
        login = input("Digite o login (mínimo 4 caracteres): ").strip()
        if len(login) >= 4:
            break
        else:
            print("\nO login deve ter no mínimo 4 caracteres. Tente novamente.")
    
    while True:
        senha = input("Digite a senha (mínimo 4 caracteres, máximo 16): ").strip()
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


veiculos = {} # Um dicionário que armazena os dados do veiculo
   
def verifica_placa_valida() -> str:
    while True:
        placa = verifica_input_vazio("Digite a placa do veículo: ", 'v').upper()
        if len(placa) != 7:
            print("ERRO! Placa deve ter 7 dígitos")
        else:
            return placa

def mostrar_veiculos():
  if veiculos:
    i = 1
    print("-- VEICULOS CADASTRADOS --\n")
    for veiculo, dados in veiculos.items():
      print(f"Veiculo {i}- ")
      print(f"Placa: {dados['placa']}\nModelo: {dados['modelo']}\nDono: {dados['dono']}\n")
      i += 1
  else:
    print("\nNenhum veículo cadastrado.\n")
    input("Pressione qualquer tecla para voltar ao menu de veículos: ")
    os.system('cls')
    menu_veiculo()

def cadastrar_veiculo(id_cliente):
    print("-- CADASTRO DE VEÍCULO --")
    placa = input("Digite a placa do veículo: ").strip().upper()
    modelo = input("Digite o modelo do veículo: ").strip()
    dono = input("Digite o nome do dono: ").strip()

    sql = """
    INSERT INTO tbl_veiculos (placa, id_cliente, modelo, dono)
    VALUES (:placa, :id_cliente, :modelo, :dono)
    """
    
    try:
        inst_insert.execute(sql, {'placa': placa, 'id_cliente': id_cliente, 'modelo': modelo, 'dono': dono})
        conn.commit()
        print("\nVeículo cadastrado com sucesso!")
        continuar()
    except Exception as e:
        print("Erro ao cadastrar veículo:", e)

def excluir_veiculo():
  os.system('cls')
  selecao_valida = False
  while True:
    mostrar_veiculos()
    while True:
      selecao = verifica_input_vazio("Digite o número do veículo a ser excluído: ", 'v')
      if not selecao.isdigit():
        mostrar_veiculos()
        print("Número do veículo inexistente!")
        continue
      else:
        chave = 'veiculo' + selecao
        confirmacao = input(f"Tem certeza que deseja excluir {chave}? (s/n): ").lower()
        break
    match confirmacao:
      case 's':
        atualiza_chaves_dicionario(veiculos)
        # Excluir o veículo
        del veiculos[chave]
        print("\nVeículo excluído com sucesso!\n")
        input("Pressione qualquer tecla para voltar ao menu de veículo: ")
        os.system('cls')
        menu_veiculo()
      case 'n':
        os.system('cls')
        menu_veiculo()
        break
      
def atualiza_chaves_dicionario(dicionario:dict) -> None:
  veiculos_copy = {}
  for i, (veiculo, dados) in enumerate(dicionario.items()):
    veiculos_copy[f"veiculo{i+1}"] = dados
  
  veiculos.clear()
  veiculos.update(veiculos_copy)

def editar_veiculo():
  os.system('cls')
  selecao_valida = False
  while True:
    while selecao_valida == False:
      mostrar_veiculos()
      try:
        selecao = verifica_input_vazio("Digite o número do veículo a ser alterado: ", 'v')
        if int(selecao) > len(veiculos) or int(selecao) < 1:
          print("Não existe veículo com este número!")
        else:
          os.system('cls')
          selecao_valida = True
          break
      except:
        print("Erro! digite um número válido")
    chave = 'veiculo' + selecao
    veiculo_selecionado = veiculos.get(chave)
    placa = veiculo_selecionado['placa']
    modelo = veiculo_selecionado['modelo']
    dono = veiculo_selecionado['dono']
    dado_alterar = ""
      
    print(f"""-- DADOS VEICULOS --\n
Placa: {placa}
Modelo: {modelo}
Dono: {dono}
""")
    dado_alterar = verifica_input_vazio("\nQual dado deseja alterar (placa, modelo, dono): ", 'v').lower()
    voltar_menu_principal(dado_alterar)
    match dado_alterar:
      case "placa":
        print(f"Placa atual: {placa}\n")
        nova_placa = verifica_placa_valida()
        veiculo_selecionado['placa'] = nova_placa
        veiculos[chave] = veiculo_selecionado
        placa = nova_placa
      case "modelo":
        print(f"Modelo atual: {modelo}\n")
        novo_modelo = verifica_input_vazio("Novo modelo: ", 'v').lower()
        veiculo_selecionado['modelo'] = novo_modelo
        veiculos[chave] = veiculo_selecionado
        modelo = novo_modelo
      case "dono":
        print(f"Dono atual: {dono}\n")
        novo_dono = verifica_input_vazio("Novo dono: ", 'v').lower()
        veiculo_selecionado['dono'] = novo_dono
        dono = novo_dono
      case _:
        print("Opção inválida!")
    while True:
      alterar_mais = input("Deseja alterar mais algum item?\nEscolha (S/N): ").lower()
      match alterar_mais:
        case "s":
          os.system('cls')
          break
          
        case "n":
          os.system('cls')
          menu_veiculo()
          break
        case _:
          print("Opção inválida!")

def menu_veiculo(id_cliente): #CRUD
  while True:
    print(""" -- MENU VEÍCULOS --
1 - Cadastrar veículo
2 - Mostrar veículos cadastrados
3 - Editar veículo
4 - Excluir veículo
5 - Voltar ao menu principal
""")
    opcao = input("Escolha uma opção: ")
    match opcao:        
      case "1":
        os.system("cls")
        cadastrar_veiculo(id_cliente)
      case "2":
        os.system('cls')
        mostrar_veiculos()
        input("Pressione qualquer tecla para voltar ao menu de veículos: ")
        os.system('cls')
        menu_veiculo()
        break
      case "3":
        editar_veiculo()
        os.system('cls')
        break
      case "4":
        os.system('cls')
        excluir_veiculo()
        break
      case "5":
        menu_principal()
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
          os.system('cls')
          print("Saindo...")
          exit()
          break
        case "1":
          os.system('cls')
          acessar_usuario()
          break
        case _:
          print("ERRO! Digite uma opção válida.")
    else:
      print("ERRO! Digite uma opção válida")
      
menu_inicial()

#menu_principal()