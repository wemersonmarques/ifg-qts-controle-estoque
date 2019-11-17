package br.edu.ifg.qtscontroleestoque.service;

import br.edu.ifg.qtscontroleestoque.dao.ProdutoDAO;
import br.edu.ifg.qtscontroleestoque.dto.ProdutoDTO;
import br.edu.ifg.qtscontroleestoque.entity.Movimentacao;
import br.edu.ifg.qtscontroleestoque.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoDAO produtoDAO;

    public boolean hasCadastro(ProdutoDTO produtoDTO) {
        try {
            return produtoDAO.consultarPorDescricao(new Produto(produtoDTO)) != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    public void cadastrar(ProdutoDTO produtoDTO) {
        if (hasCadastro(produtoDTO)) {
            throw new RuntimeException("Produto já cadastrado!");
        } else if (produtoDTO.getEstoqueMinimo() >= produtoDTO.getEstoqueMaximo()){
            throw new RuntimeException("O estoque máximo deve ser maior do que o estoque mínimo.");
        } else {
            produtoDAO.salvar(new Produto(produtoDTO));
        }
    }

    public boolean excluir(int id) throws NoResultException{
        Produto produto = new Produto();
        produto.setId(id);
        List<Movimentacao> movimentacaos = produtoDAO.consultarMovimentacoes(produto);

        if (movimentacaos.size() > 0) {
            return false;
        } else {
            Produto produtoBd = (Produto) produtoDAO.consultarPorId(Produto.class, id);
            produtoDAO.deletar(produtoBd);
            return true;
        }
    }


}
