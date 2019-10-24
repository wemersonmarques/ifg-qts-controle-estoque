package br.edu.ifg.qtscontroleestoque.integration;

import br.edu.ifg.qtscontroleestoque.entity.Movimentacao;
import br.edu.ifg.qtscontroleestoque.entity.Produto;
import br.edu.ifg.qtscontroleestoque.service.EstoqueService;
import br.edu.ifg.qtscontroleestoque.type.ETipoMovimentacao;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstoqueServiceTest {

    private Produto produto;
    private Movimentacao movimentacao;
    private EstoqueService estoqueService;

    @BeforeEach
    public void context() {
        produto = new Produto();
        estoqueService = new EstoqueService();
    }

    @Test
    public void devePermitirEntradaProduto() {
        produto.setSaldoAtual(0);
        estoqueService.movimentar(ETipoMovimentacao.ENTRADA, produto, 10);
        Assert.assertEquals(9, produto.getSaldoAtual(), 0.0001);
    }

    @Test
    public void devePermitirSaidaProduto() {

    }

    @Test
    public void naoDevePermitirSaidaProdutoSaldoInsuficiente() {

    }

    @AfterEach
    public void terminate() {
        produto = null;
        movimentacao = null;
        estoqueService = null;
    }

}
