package produtos;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

    @PersistenceContext(unitName = "produtosPU")
    private EntityManager entityManager;

    @GET
    public List<Produto> getProdutos() {
        TypedQuery<Produto> query = entityManager.createQuery("SELECT p FROM Produto p", Produto.class);
        return query.getResultList();
    }

    @POST
    public Produto adicionar(Produto produto) {
        entityManager.persist(produto);
        return produto;
    }

    @PUT
    @Path("{id}")
    public void atualizar(@PathParam("id") Integer id, Produto produto) {
        entityManager.merge(produto);
    }

    @DELETE
    @Path("{id}")
    public void excluir(@PathParam("id") Integer id) {
        entityManager.remove(getProduto(id));
    }

    @GET
    @Path("id")
    public Produto getProduto(@PathParam("id") Integer id) {
        return entityManager.find(Produto.class, id);
    }
}
