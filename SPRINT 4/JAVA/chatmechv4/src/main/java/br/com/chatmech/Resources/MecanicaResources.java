package br.com.chatmech.Resources;

import br.com.chatmech.Services.MecanicaService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("mecanicas")
public class MecanicaResources {
    MecanicaService ms = new MecanicaService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscaTodos(){
        try{
            return Response.ok(ms.buscarTodos()).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Falha ao buscar mecânicas no servidor: " + e.getMessage()).build();
        }
    }
}
