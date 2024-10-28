public class teste {
    public static void main(String[] args) {
        CadastrarOficina oficina1 = new CadastrarOficina(123,"oficina1@gmail.com", "oficina123",325370360,"Rua da oficina 1");
        System.out.println("Cadastro da Oficina 1: \nId: "+ oficina1.getIdOficina() + " \nEmail: "+ oficina1.getEmail()+ " \nSenha: "+ oficina1.getSenha()+ " \nCnpj: "+oficina1.getCnpj()+ " \nEndereco: "+oficina1.getEndereco());
        System.out.println();

        PerfilOficina oficina2 = new PerfilOficina(234,"Oficina 2","oficina2@gmail.com","oficina2345", 123456789,"Rua da oficina 2");
        System.out.println("Perfil da Oficina 2: \nId: "+ oficina2.getIdOficina() + " \nNome: "+ oficina2.getNome()+" \nEmail: "+ oficina1.getEmail()+ " \nSenha: "+ oficina1.getSenha()+ " \nCnpj: "+oficina1.getCnpj()+ " \nEndereco: "+oficina1.getEndereco());
        System.out.println();

        CadastrarCliente cliente1 = new CadastrarCliente(456,"cliente1@gmail.com", "cliente123");
        System.out.println("Cadastro do cliente 1: \nId: "+cliente1.getIdUsuario()+ " \nEmail: "+ cliente1.getEmail()+ " \nSenha: "+cliente1.getSenha());
        System.out.println();

        PerfilCliente cliente2 = new PerfilCliente("Cliente2", "cliente2@gmail.com", "cliente234");
        System.out.println("Perfil Cliente 2: \nNome: "+cliente2.getNome() + " \nEmail: "+cliente2.getEmail()+ " \nSenha: "+cliente2.getSenha());
        System.out.println();

        CadastrarVeiculo veiculo1 = new CadastrarVeiculo(456,765,"EIC2172","Civic","Honda","2018");
        System.out.println("Veiculo cadastrado: \nId dono: "+veiculo1.getIdVeiculo()+" \nId veiculo: "+veiculo1.getIdVeiculo()+" \nPlaca: "+veiculo1.getPlaca()+" \nModelo: "+veiculo1.getModelo()+" \nMarca: "+veiculo1.getMarca()+" \nAno fabricação: "+veiculo1.getAnoFabricacao());
        System.out.println();

        ReceberVeiculo veiculo2 = new ReceberVeiculo(7823,"LFY6511","Asx","Mitsubishi","2020","Revisão de câmbio");
        System.out.println("Veiculo recebido: \nId veiculo: "+veiculo2.getIdVeiculo()+" \nPlaca: "+veiculo2.getPlaca()+" \nModelo: "+veiculo2.getModelo()+" \nMarca: "+veiculo2.getMarca()+" \nAno de fabricação: "+veiculo2.getAnoFabricacao()+" \nServiço: "+veiculo2.getServico());
        System.out.println();

        Atendimentos atendimentos = new Atendimentos("São Paulo","Mecânica do Tião","Revisões completas","Fazemos revisões no seu veiculo completo");
        System.out.println("Região de atendimento: "+atendimentos.getRegiao()+" \nMecânica parceira: "+atendimentos.getMecanicasPareceiras()+" \nServiços prestados: "+atendimentos.getServicos()+" \nDescrição do serviço: "+atendimentos.getDescricao());
        System.out.println();

        Agendamento agendamento = new Agendamento(12354,8567,983,"Troca de Óleo de Transmição","02/07/2024","14:30");
        System.out.println("Agendamentos marcados: \nId veiculo: "+agendamento.getIdVeiculo()+" \nId Dono: "+agendamento.getIdDono()+" \nId oficina: "+agendamento.getIdOficina()+" \nServiço: "+agendamento.getServico()+" \nDia: "+agendamento.getDia()+" \nHoras: "+agendamento.getHoras());
    }


}
