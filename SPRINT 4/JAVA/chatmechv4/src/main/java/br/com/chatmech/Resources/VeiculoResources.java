package br.com.chatmech.Resources;

import br.com.chatmech.Model.Veiculo;
import br.com.chatmech.Services.VeiculoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.print.attribute.standard.Media;
import java.sql.SQLException;
import java.util.List;

@Path("/veiculos")
public class VeiculoResources {
    VeiculoService vs = new VeiculoService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarTodos(){
        try{
             return Response.ok(vs.buscarTodos()).build();
        }catch(SQLException e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Falha ao recuperar os veículos! Erro: " + e.getMessage()).build();
        }

    }

    @DELETE
    @Path("/{placa}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletaVeiculo(@PathParam("placa") String placa){
        try {
            if (vs.deletaVeiculo(placa)){
                return Response.status(Response.Status.OK).entity("Veículo deletado com sucesso!").build();
            }else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Falha ao deletar a placa: " + placa).build();
            }
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Falha ao deletar no servidor. Erro: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{placa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscaVeiculo(@PathParam("placa") String placa){
        try{
            var v = vs.buscaVeiculo(placa);
            if(v == null){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Falha ao excluir veículo de placa: " + placa).build();
            } else {
                return Response.ok(v).build();
            }
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Falha ao buscar no servidor").build();
        }
    }

    @PUT
    @Path("/{placa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editaVeiculo(@PathParam("placa") String placa, Veiculo veiculo){
        try{
            vs.editaVeiculo(placa, veiculo);
            return Response.status(Response.Status.OK).entity("Veículo alterado com sucesso!").build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Falha ao atualizar no servidor. Erro: " + e.getMessage()).build();
        }
    }
}
