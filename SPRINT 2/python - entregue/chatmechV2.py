import os
os.system("cls")

#---------------------------------- Funções
class ExitProgram(Exception):
    pass

def exibir_menu():
    while True:
        print("\nBem vindo ao ChatMech\n"
              "\n1 - Cadastrar um veiculo \n"
              "2 - Conversar com o Mechzinho \n"
              "3 - Meus orçamentos \n"
              "4 - Histórico de manutenções \n"
              "5 - Agendar serviços \n"
              "6 - Quem somos \n"
              "7 - Retornar a tela de login\n")
        
        try:
            opcao = int(input("Escolha uma opção: "))
        
            if opcao == 1:
                os.system("cls")
                cadastros()
            elif opcao == 2:
                print("\nBem vindo ao ChatMech, nosso mecânico virtual, qual o seu problema ?\n") # Aqui o programa irá retornar ao menu, pois só irá ter continuidade com a implementação do ChatBot
                
            elif opcao == 3:
                print("\nOrçamentos das peças e serviço relacionado ao diagnóstico de seu veiculo: \n") # Aqui o programa irá retornar ao menu, pois só irá ter continuidade com a implementação do ChatBot
                
            elif opcao == 4:
                print("\nHistórico de serviços feitos pelo o Mechzinho: \n") # Aqui o programa irá se retornar ao menu, pois só irá ter continuidade com a implementação do ChatBot
                
            elif opcao == 5:
                d = int(input("\nQual dia gostaria de agendar seu serviço?"))
                m = int(input("\nQual o mês?"))
                h = int(input("\nQual o horário do agendamento?"))
                return agendar_servico(dia=d,mes=m,hora=h)
            elif opcao == 6:
                print("\nSomos uma solução rapida e prática para problemas mecânicos em geral, atendemos via internet por meio do Mechzinho, nosso ChatBot, onde ele fará uma série de perguntas sobre o problema de seu veiculo e por meio de sua inteligência ele será capaz de dar um diagnóstico da possivel solução e em quais mecânicas atendem o caso, além de dar um breve orçamento das peças necessárias para a manutenção, o preço é obtido por meio do mercado geral, podendo ter mudanças por região.")

            elif opcao == 7:
                print("Saindo...")
                break
            else:
                print("Opção inválida. Tente novamente.")
            
        except ValueError:
            print("Entrada inválida! Por favor, digite um número.")
            os.system("cls")

def agendar_servico(dia, mes, hora):
    # Verificação se os valores de dia e mês são números
    dia = int(dia)
    mes = int(mes)
    hora = int(hora)
    
    # Assume o formato de hora como 'hh:mm'
    hora_parts = hora.split(':')
    hora_valid = len(hora_parts) == 2 and all(part.isdigit() for part in hora_parts)
        
    if not (1 <= dia <= 31):
        raise ValueError("Valor de dia inválido. Use um valor entre 1 e 31.")
    if not (1 <= mes <= 12):
        raise ValueError("Valor de mês inválido. Use um valor entre 1 e 12.")
    if not hora_valid:
        raise ValueError("Formato de hora inválido. Use 'hh:mm'.")
        
    # Conversão para inteiros para validações adicionais
    hora_int = list(map(int, hora_parts))
        
    # Verificação de valores de hora
    if not (0 <= hora_int[0] < 24 and 0 <= hora_int[1] < 60):
        raise ValueError("Valores de hora ou minuto inválidos.")
        
    # Se todas as validações passarem, retorna um agendamento bem-sucedido
    print(f"Serviço agendado para o dia {dia}/{mes} às {hora}.")
    return {'dia': dia, 'mes': mes, 'hora': hora}
        
def acessar_usuário():
    try:
        while True:
            print("\n1 - Registrar usuário\n"
                "2 - Fazer login\n"
                "3 - Encerrar programa\n")
            try:
                opcao = int(input("Escolha uma opção: "))
                if opcao == 1: # Irá chamar a tela de registro de usuário
                    os.system("cls")
                    registrar_usuario()
                elif opcao == 2: # Irá chamar a tela de login
                    os.system("cls")
                    if verificar_login():
                        print("Acesso concedido")

                    else:
                        os.system("cls")
                        print("Acesso negado, você não possui uma conta registrada...\n"
                            "Por favor, Registre-se aqui: \n")
                        return registrar_usuario()
                elif opcao == 3: # Sairá do programa
                    print("Saindo...")
                    raise ExitProgram
                else:
                    print("Opção inválida. Tente novamente.\n")
            except ValueError:
                print("Entrada inválida! Por favor, digite um número.")
                os.system("cls")
    except ExitProgram:
        print("Programa encerrado.")
            
usuarios = [] # Uma lista vazia que armazena os dados de login e senha

def registrar_usuario(): # Tela de registro
    '''os.system("cls")'''
    print("Bem vindo, Cadastre-se em nosso sistema: \n")
    login = input("Digite seu login: ")
    senha = input("Digite sua senha: ")
    usuarios.append({'login': login, 'senha': senha}) # Adicionará o login e senha na lista
    print("\nUsuário registrado com sucesso!\n")
    return verificar_login()
    
def verificar_login(): # Tela de verificação
    print("Para realizar seu login, preencha as informações abaixo:\n")
    login = input("Digite seu login: ")
    senha = input("Digite sua senha: ")
    
    for usuario in usuarios: # Aqui o usuário terá que digitar o mesmo login e senha que foi digitado na tela de registro, pois é a informação que está armazenada na lista, caso digite outra informação, resultará em dados incorretos, e ficará dando loop até digitar as informações dadas no registro
        if usuario['login'] == login and usuario['senha'] == senha:
            print("Login bem-sucedido!")
            os.system("cls")                
            return exibir_menu()
        else :
            print("\nLogin ou senha incorretos.\n")
            return verificar_login()
        
veiculos = [] # Uma lista vazia que armazena os dados do veiculo

def cadastros():
    while True:
        print("1 - Cadastrar veículo\n"
              "2 - Mostrar veículos cadastrados\n"
              "3 - Voltar ao Menu inicial\n")
        
        opcao = int(input("Escolha uma opção: "))
        
        if opcao == 1:
            os.system("cls")
            cadastrar_veiculo()
        elif opcao == 2:
            if veiculos:
                print("\nVeículos cadastrados:\n")
                for veiculo in veiculos:
                    print(f"Placa: {veiculo['placa']}\nModelo: {veiculo['modelo']}\nDono: {veiculo['dono']}\n")
            else:
                print("\nNenhum veículo cadastrado.\n")
        elif opcao == 3:
            print("Saindo...")
            os.system("cls")
            break
        else:
            print("Opção inválida. Tente novamente.")



def cadastrar_veiculo():
    placa = input("\nDigite a placa do seu veículo: ")
    modelo = input("\nDigite o modelo do seu veículo: ")
    dono = input("\nDigite o nome do dono do veículo: ")
    
    # Criar um dicionário com os dados do veículo
    veiculo = {
        'placa': placa,
        'modelo': modelo,
        'dono': dono
    }
    
    # Adicionar o dicionário a lista de veículos
    veiculos.append(veiculo)
    print("\nVeículo cadastrado com sucesso!\n")

#---------------------------------- Programa principal

os.system("cls")

print("Bem vindo ao ChatMech, acesse sua conta: \n")
print("1 - Acessar ou Cadastrar usuário"
      "\n2 - Acessar o Mechzinho sem cadastro")

opcao = int(input("\nEscolha uma opção: "))

match opcao:
    case 1: 
        print("\nO que você deseja ?")
        acessar_usuário()
    case 2:
        print("Bem vindo ao ChatMech, nosso mecânico virtual, qual o seu problema ?") #Aqui será a implementação com o ChatBot, então como não é possivel ainda, a opção não retorna nada