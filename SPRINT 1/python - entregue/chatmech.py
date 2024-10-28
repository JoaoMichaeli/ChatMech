import os
os.system("cls")

print("Bem vindo ao ChatMech")

#Primeiro menu apresentado
menu = int(input("1 - Acessar usuário \n"
                 "2 - Mecânicas parceiras \n"
                 "3 - Regiões de atendimento \n"
                 "4 - Quem somos \n "))

match menu:
        #Escolhendo o 1 entrará na tela de login, o acesso só será possivel com o login e senha qualquer
    case 1:
        while True:
            login=str(input("Login: "))
            
            senha=str(input("Senha: "))
            
            if login == login and senha == senha:
                
                print("Bem vindo ao Mechzinho, nosso mecânico virtual \n"
                      "O que deseja acessar ? \n")
                
                #Opções disponíveis por perfil, cada perfil será único
                menu2 = int(input("1 - Cadastrar um veiculo \n"
                    "2 - Conversar com o Mechzinho \n"
                    "3 - Meus orçamentos \n"
                    "4 - Histórico de manutenções \n"
                    "5 - Agenda de serviços \n"))
                
                match menu2:

                        #Cadastramento de veiculo
                    case 1:
                        while True:
                            placa=str(input("Digite a placa do seu veiculo: "))
                            modelo=str(input("Digite o modelo do seu veiculo: "))
                            dono=str(input("Digite o nome do dono do veiculo: "))
                        
                            if placa==placa and modelo==modelo and dono==dono:
                                print("Bem vindo ao ChatMech, o que posso ajuda-lo ?")
                            
                        #Acessar o chatbot para perguntas        
                    case 2:
                        print("Bem vindo ao Mech, nosso mecânico virtual, qual o seu problema ?")
                        
                        #Orçamentos de cada pesquisa concluída
                    case 3:
                        print("Orçamentos: \n"
                        "serviço dia x/x/x \n"
                        "serviço dia y/y/y \n")
                        
                        #Histórico de todas as manutenções feitas pelas mecânicas parceiras
                    case 4:
                        print("Histórico de manutenções: \n"
                        "Serviço dia x/x/x \n"
                        "Serviço dia y/y/y \n")
                        
                        #Agenda das manutenções
                    case 5:
                        print("Agenda: ")
                        
                        #Caso digite uma opção errada
                    case _:
                        print("Digite uma opção válida")
                        
                #Se errar o login e senha retorna para logar novamente
            else:
                print("Erro, tente novamente!")
    
        #Mecânicas parceiras
    case 2:
        print("Mecânicas parceiras \n"
              "Mecânica do Tião \n"
              "Mecânica do cabeçote \n")
        
        #Região de atendimento
    case 3:
        print("Atendemos em toda região de São Paulo")
        
        #Apresentação ChatMech
    case 4:
        print("Somos uma solução rapida e prática para problemas mecânicos em geral, atendemos via internet por meio do Mechzinho, nosso ChatBot, onde ele fará uma série de perguntas sobre o problema de seu veiculo e por meio de sua inteligência ele será capaz de dar um diagnóstico da possivel solução e em quais mecânicas atendem o caso, além de dar um breve orçamento das peças necessárias para a manutenção, o preço é obtido por meio do mercado geral, podendo ter mudanças por região.")
        
        #Caso digite uma opção inválida
    case _:
        print("Erro! Digite uma opção válida!")