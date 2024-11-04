package br.com.chatmech.Resources;


import br.com.chatmech.Model.Cadastro;
import br.com.chatmech.Services.CadastroService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("cadastro")
public class CadastroResources {

    CadastroService cadastroService = new CadastroService();

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response buscarTodos() {
//        try {
//            return Response.ok(cadastroService.buscarTodos()).build();
//        } catch (SQLException e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//        }
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirCadastro(Cadastro cadastro) {
        try {
            cadastroService.cadastrarUsuario(cadastro);
            return Response.status(Response.Status.CREATED).entity("Usuário cadastrado com sucesso!").build();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Falha ao cadastrar o usuário...." + e.getLocalizedMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletaUsuario(@PathParam("id") Integer idCadastro) {
        try {
            cadastroService.desativarConta(idCadastro);
            return Response.status(Response.Status.OK).entity("Usuário deletado com sucesso!").build();
        } catch (SQLException | RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Falha ao deletar o usuário! erro: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/login/{email}/{senha}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response autorizaLogin(@PathParam("email") String email, @PathParam("senha") String senha) {
        try {
            Cadastro c = cadastroService.autorizaLogin(email, senha);
            if (c == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Login ou senha inválidos").build();
            } else {
                return Response.ok(c).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Não foi possível validar as credenciais. Erro: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/alterar/{email}/{senha}")
    public Response alterarSenha(@PathParam("email") String email, @PathParam("senha") String senha){
         try{
             if (cadastroService.alterarSenha(email, senha).equals(1)){
                 return Response.status(Response.Status.OK).entity("Senha atualizada com sucesso!").build();
             } else {
                 return Response.status(Response.Status.BAD_REQUEST).entity("Email não encontrado! Verifique e tente novamente.").build();
             }
         } catch (SQLException e){
             return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Falha ao atualizar a senha no Servidor...").build();
         }
    }

}