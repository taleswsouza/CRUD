package produtos;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoService {

    static Integer contador;
    List<Produto> produtos;

    
    @PostConstruct
    public void init() {
        this.produtos = new ArrayList<>();
        contador = 1;
    }

    @GET
    public List<Produto> getProdutos() {
        return produtos;
    }

    @POST
    public Produto adicionar(Produto produto) {
        produto.setId(contador++);
        produtos.add(produto);
        return produto;
    }

    @PUT
    @Path("{id}")
    public void atualizar(@PathParam("id") Integer id, Produto produto) {
        for (Produto p : produtos) {
            if (p.getId().equals(id)) {
                p.setDescricao(produto.getDescricao());
                p.setPreco(produto.getPreco());
                break;
            }
        }
    }

    @DELETE
    @Path("{id}")
    public void excluir(@PathParam("id") Integer id) {
        for (Produto p : produtos) {
            if (p.getId().equals(id)) {
                produtos.remove(p);
                break;
            }
        }
    }

    @GET
    @Path("id")
    public Produto getProduto(@PathParam("id") Integer id) {
        for(Produto p : produtos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
