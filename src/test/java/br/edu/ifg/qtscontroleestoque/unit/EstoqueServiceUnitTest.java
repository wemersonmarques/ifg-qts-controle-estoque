package br.edu.ifg.qtscontroleestoque.unit;

import br.edu.ifg.qtscontroleestoque.entity.Movimentacao;
import br.edu.ifg.qtscontroleestoque.entity.Produto;
import br.edu.ifg.qtscontroleestoque.service.EstoqueService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstoqueServiceUnitTest {

    private static final String QUANTIDADE_SALDO_DIFERENTE_ENTRADA = "A quantidade do saldo é diferente para operação de entrada";
    private static final String QUANTIDADE_SALDO_DIFERENTE_SAIDA = "A quantidade do saldo é diferente para operação de saída";
    private Produto produto;
    private Movimentacao movimentacao;
    private EstoqueService estoqueService;
    private final float TOLERANCIA_CASAS_DECIMAIS = 0.0001f;

    @BeforeEach
    public void context() {
        produto = new Produto();
        movimentacao = new Movimentacao();
        estoqueService = new EstoqueService();
    }


    @Test
    public void devePermitirEntradaMenorQueEstoqueMaximo() {
        produto.setEstoqueMaximo(15);
        estoqueService.adicionarSaldoProduto(produto, 14.09f);
        Assert.assertEquals(QUANTIDADE_SALDO_DIFERENTE_ENTRADA, 14.09, produto.getSaldoAtual(), TOLERANCIA_CASAS_DECIMAIS);
    }

    @Test
    public void naoDevePermitirEntradaMaiorQueEstoqueMaximo() {
        produto.setEstoqueMaximo(15);
        try {
            estoqueService.adicionarSaldoProduto(produto, 15.001f);
        } catch (RuntimeException e) {}

        Assert.assertEquals(QUANTIDADE_SALDO_DIFERENTE_ENTRADA, 0, produto.getSaldoAtual(), TOLERANCIA_CASAS_DECIMAIS);
    }

    @Test
    public void devePermitirEntradaIgualAoEstoqueMaximo() {
        produto.setEstoqueMaximo(15f);
        estoqueService.adicionarSaldoProduto(produto, 15f);
        Assert.assertEquals(QUANTIDADE_SALDO_DIFERENTE_ENTRADA, 15, produto.getSaldoAtual(), TOLERANCIA_CASAS_DECIMAIS);
    }

    @Test
    public void devePermitirSaidaMenorQueEstoqueAtual() {
        produto.setSaldoAtual(10);
        estoqueService.subtrairSaldoProduto(produto, 9.002f);
        Assert.assertEquals(QUANTIDADE_SALDO_DIFERENTE_SAIDA, 0.998, produto.getSaldoAtual(), TOLERANCIA_CASAS_DECIMAIS);
    }

    @Test
    public void naoDevePermitirSaidaMaiorQueEstoqueAtual() {
        produto.setSaldoAtual(15);
        try {
            estoqueService.subtrairSaldoProduto(produto, 16);
        } catch (RuntimeException e) {}
        Assert.assertEquals(QUANTIDADE_SALDO_DIFERENTE_SAIDA, 15, produto.getSaldoAtual(), 0.0001);
    }

    @Test
    public void devePermitirSaidaIgualAoEstoqueAtual() {
        produto.setSaldoAtual(9.002f);
        estoqueService.subtrairSaldoProduto(produto, 9.002f);
        Assert.assertEquals(QUANTIDADE_SALDO_DIFERENTE_SAIDA, 0, produto.getSaldoAtual(), TOLERANCIA_CASAS_DECIMAIS);
    }

    @Test
    public void naoDevePermitirSaidaMenorQueEstoqueMinimo() {
        produto.setSaldoAtual(15);
        produto.setEstoqueMinimo(10);
        try {
            estoqueService.subtrairSaldoProduto(produto, 10);
        } catch (RuntimeException e) {}

        Assert.assertEquals(QUANTIDADE_SALDO_DIFERENTE_SAIDA, 15, produto.getSaldoAtual(), TOLERANCIA_CASAS_DECIMAIS);
    }

    @Test
    public void devePermitirSaidaMenorQueEstoqueMinimo() {
        produto.setSaldoAtual(15);
        produto.setEstoqueMinimo(10);
        estoqueService.subtrairSaldoProduto(produto, 4);

        Assert.assertEquals(QUANTIDADE_SALDO_DIFERENTE_SAIDA, 11, produto.getSaldoAtual(), TOLERANCIA_CASAS_DECIMAIS);
    }

    @Test
    public void devePermitirSaidaIgualAoEstoqueMinimo() {
        produto.setSaldoAtual(15);
        produto.setEstoqueMinimo(10.03f);
        estoqueService.subtrairSaldoProduto(produto, 4.07f);

        Assert.assertEquals(QUANTIDADE_SALDO_DIFERENTE_SAIDA, 10.93f, produto.getSaldoAtual(), TOLERANCIA_CASAS_DECIMAIS);
    }

}
