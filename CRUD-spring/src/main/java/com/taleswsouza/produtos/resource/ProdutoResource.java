package com.taleswsouza.produtos.resource;

import com.taleswsouza.produtos.exceptions.ProdutoNotFoundException;
import com.taleswsouza.produtos.model.Produto;
import com.taleswsouza.produtos.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class ProdutoResource {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }


    @GetMapping("/produtos/{id}")
    public Produto getProduto(@PathVariable(value="id") Integer id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto com id " + id + " não encontrado."));
    }

    @PostMapping("/produtos")
    public Produto adicionar(@RequestBody Produto produto) {
        produtoRepository.save(produto);
        return produto;
    }
//
//    @PUT
//    @Path("{id}")
//    public void atualizar(@PathParam("id") Integer id, Produto produto) {
//        entityManager.merge(produto);
//    }
//
//    @DELETE
//    @Path("{id}")
//    public void excluir(@PathParam("id") Integer id) {
//        entityManager.remove(getProduto(id));
//    }
//
}
