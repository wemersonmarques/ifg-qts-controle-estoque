package br.edu.ifg.qtscontroleestoque.acceptance.tests.cadastro.produto;

import br.edu.ifg.qtscontroleestoque.acceptance.pages.LoginPage;
import br.edu.ifg.qtscontroleestoque.acceptance.pages.MenuPage;
import br.edu.ifg.qtscontroleestoque.acceptance.pages.ProdutoPage;
import br.edu.ifg.qtscontroleestoque.utils.Navegador;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ProdutoAcceptanceTest {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static MenuPage menuPage;
    private static ProdutoPage produtoPage;

    private final String NOME_USUARIO = "USUARIO AUTOMAÇÃO";
    private final String EMAIL_USUARIO = "test@automation.com";
    private final String SENHA_USUARIO = "123";

    private final String DESCRICAO_PRODUTO = "PRODUTO AUTOMAÇÃO";

    @Before
    public void setup() {
        driver = Navegador.getInstance();
        loginPage = new LoginPage(driver);
        produtoPage = new ProdutoPage(driver);
        menuPage = new MenuPage(driver);
    }

    @Test
    public void naoDevePermitirCadastroProdutoComEstoqueMinimoMaiorQueEstoqueMaximo() {
        loginPage.acessar();
        loginPage.logar(EMAIL_USUARIO, SENHA_USUARIO);
        //menuPage.acessarProdutos();
        //produtoPage.cadastrarProduto(DESCRICAO_PRODUTO, 10, 20);
        produtoPage.getProdutosCadastrados();
    }




    @After
    public void tearDown() {
        Navegador.fechar();
    }
}
