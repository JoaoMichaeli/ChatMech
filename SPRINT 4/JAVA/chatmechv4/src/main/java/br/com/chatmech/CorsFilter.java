package br.com.chatmech;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {
    /*
    Resumo da execução preflight OPTIONS
    1- O cliente faz uma requisição OPTIONS.
    2- O ContainerRequestFilter (primeiro filter) detecta que é uma requisição OPTIONS.
    3- Você configura a resposta CORS (cabeçalhos)
    e usa requestContext.abortWith para interromper o fluxo normal, respondendo imediatamente.
    4- A resposta é enviada de volta ao cliente.
    */

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Permite que as requisições do tipo OPTIONS passem
/*O método filter(ContainerRequestContext requestContext)
é registrado como um filtro de requisição.
Ele intercepta todas as requisições antes que o servidor processe os recursos.

Quando você envia uma requisição OPTIONS, este filtro é executado, porque o JAX-RS verifica
todas as requisições que entram, independentemente do método HTTP (GET, POST, OPTIONS, etc.).
O objetivo desse filtro é verificar se o método HTTP é OPTIONS.
Se for, ele intercepta a requisição e trata ela especificamente para CORS
(que normalmente usa OPTIONS para pré-verificação).
No caso de uma requisição OPTIONS você usa o método abortWith para interromper o processamento da requisição,
retornando uma resposta CORS com os cabeçalhos necessários.

 */

        if (requestContext.getRequest().getMethod().equalsIgnoreCase("OPTIONS")) {
            Response.ResponseBuilder response = Response.ok();
            setCorsHeaders(response);
            requestContext.abortWith(response.build());
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // Verifica se os cabeçalhos CORS já foram definidos
        /*
O método filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
é um filtro de resposta, ou seja, ele é executado após o servidor processar
a requisição e antes de enviar a resposta ao cliente.

Neste caso, a função deste filtro é garantir que, se a resposta
ainda não contiver os cabeçalhos CORS, eles sejam adicionados.
*/

        if (responseContext.getHeaders().get("Access-Control-Allow-Origin") == null ||
                responseContext.getHeaders().get("Access-Control-Allow-Origin").isEmpty()) {
            setCorsHeaders(responseContext);
        }
    }

    // Método para configurar os cabeçalhos CORS
    //ou seja adicionar os valores de acesso no cabeçalho de resposta
    private void setCorsHeaders(Response.ResponseBuilder response) {
        response.header("Access-Control-Allow-Origin", "*");
        response.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        response.header("Access-Control-Allow-Credentials", "true");
        response.header("Access-Control-Allow-Methods", "GET, PUT, DELETE, OPTIONS, HEAD");
        response.header("Access-Control-Max-Age", "1209600");
      /*  Isso significa que, se o navegador já tiver feito uma requisição preflight recentemente,
       ele pode usar a resposta em cache e pular a requisição OPTIONS, fazendo com que o filtro
        não seja acionado em requisições subsequentes.
      */
        response.header("Access-Control-Allow-Private-Network ", "true");
    }

    // Sobrecarga do método para ContainerResponseContext
    private void setCorsHeaders(ContainerResponseContext responseContext) {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, PUT, DELETE, OPTIONS, HEAD");
        responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
        responseContext.getHeaders().add("Access-Control-Allow-Private-Network ", "true");

    }
}
