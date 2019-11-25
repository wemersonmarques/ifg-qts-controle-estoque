package br.edu.ifg.qtscontroleestoque.acceptance.tests.cadastro.movimentacao;

import br.edu.ifg.qtscontroleestoque.acceptance.pages.LoginPage;
import br.edu.ifg.qtscontroleestoque.acceptance.pages.MenuPage;
import br.edu.ifg.qtscontroleestoque.acceptance.pages.MovimentacoesPage;
import br.edu.ifg.qtscontroleestoque.acceptance.pages.ProdutoPage;
import br.edu.ifg.qtscontroleestoque.type.ETipoMovimentacao;
import br.edu.ifg.qtscontroleestoque.utils.Navegador;
import br.edu.ifg.qtscontroleestoque.utils.Utils;
import org.junit.*;
import org.openqa.selenium.WebDriver;

public class MovimentacaoAcceptanceTest {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static MenuPage menuPage;
    private static ProdutoPage produtoPage;
    private static MovimentacoesPage movimentacoesPage;

    private static final String EMAIL_USUARIO = "test@automation.com";
    private static final String SENHA_USUARIO = "123";

    private static final String MENSAGEM_ERRO_ENTRADA = "A quantidade da entrada somada ao estoque atual é maior do que o estoque máximo permitido para o produto";
    private static final String MENSAGEM_ERRO_SAIDA = "A quantidade da saida é maior que o estoque atual ou a quantidade do estoque atual menos a quantidade da saída é maior que a quantidade do estoque mínimo";

    private static String DESCRICAO_PRODUTO = "PRODUTO";
    private static final float ESTOQUE_MAXIMO_PRODUTO = 30f;
    private static final float ESTOQUE_MINIMO_PRODUTO = 10f;

    @BeforeClass
    public static void setup() {
        driver = Navegador.getInstance();
        loginPage = new LoginPage(driver);
        produtoPage = new ProdutoPage(driver);
        menuPage = new MenuPage(driver);
        movimentacoesPage = new MovimentacoesPage(driver);

        cadastrarProdutoMovimentacoes();
    }

    public static void cadastrarProdutoMovimentacoes() {
        loginPage.acessar();
        loginPage.logar(EMAIL_USUARIO, SENHA_USUARIO);
        menuPage.acessarProdutos();
        DESCRICAO_PRODUTO = DESCRICAO_PRODUTO.concat(" " + Utils.gerarStringAleatoria());
        produtoPage.cadastrarProduto(DESCRICAO_PRODUTO, ESTOQUE_MAXIMO_PRODUTO, ESTOQUE_MINIMO_PRODUTO);
        menuPage.acessarMovimentacoes();
    }

    @Test
    public void naoDevePermitirEntradaCujoSaldoSejaMaiorQueEstoqueMaximo() {
        Navegador.atualizarPagina();
        movimentacoesPage.cadastrarMovimentacao(ETipoMovimentacao.ENTRADA, DESCRICAO_PRODUTO, 500f);
        Assert.assertFalse(movimentacoesPage.isCadastrado(ETipoMovimentacao.ENTRADA, DESCRICAO_PRODUTO, 500f));
        Assert.assertEquals(MENSAGEM_ERRO_ENTRADA, movimentacoesPage.recuperarMensagemErro());
    }

   @Test
    public void devePermitirEntradaCujoSaldoSejaMenorQueEstoqueMaximo() {
        Navegador.atualizarPagina();
        movimentacoesPage.cadastrarMovimentacao(ETipoMovimentacao.ENTRADA, DESCRICAO_PRODUTO, 29);
        Assert.assertTrue(movimentacoesPage.isCadastrado(ETipoMovimentacao.ENTRADA, DESCRICAO_PRODUTO, 29));
    }

    @Test
    public void naoDevePermitirSaidaCujoSaldoSejaMenorQueEstoqueMinimo() {
        Navegador.atualizarPagina();
        movimentacoesPage.cadastrarMovimentacao(ETipoMovimentacao.SAIDA, DESCRICAO_PRODUTO, 25);
        Assert.assertFalse(movimentacoesPage.isCadastrado(ETipoMovimentacao.SAIDA, DESCRICAO_PRODUTO, 25));
        Assert.assertEquals(MENSAGEM_ERRO_SAIDA, movimentacoesPage.recuperarMensagemErro());
    }

    @Test
    public void devePermitirSaidaCujoSaldoSejaMaiorQueEstoqueMinimo() {
        Navegador.atualizarPagina();
        movimentacoesPage.cadastrarMovimentacao(ETipoMovimentacao.SAIDA, DESCRICAO_PRODUTO, 10);
        Assert.assertTrue(movimentacoesPage.isCadastrado(ETipoMovimentacao.SAIDA, DESCRICAO_PRODUTO, 10));
    }

    @AfterClass
    public static void tearDown() {
        Navegador.fechar();
    }
}

