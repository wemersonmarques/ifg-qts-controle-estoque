package br.edu.ifg.qtscontroleestoque.acceptance.tests.cadastro.usuario;

import br.edu.ifg.qtscontroleestoque.acceptance.pages.CadastroPage;
import br.edu.ifg.qtscontroleestoque.acceptance.pages.LoginPage;
import br.edu.ifg.qtscontroleestoque.acceptance.pages.MenuPage;
import br.edu.ifg.qtscontroleestoque.acceptance.pages.PerfilPage;
import br.edu.ifg.qtscontroleestoque.utils.Navegador;
import br.edu.ifg.qtscontroleestoque.utils.Utils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class UsuarioAcceptanceTest {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static CadastroPage cadastroPage;
    private static MenuPage menuPage;
    private static PerfilPage perfilPage;

    private final String NOME_USUARIO = "USUARIO AUTOMAÇÃO";
    private final String JA_UTILIZADO_EMAIL_USUARIO = "test@automation.com";
    private final String SENHA_USUARIO = "123";

    @Before
    public void setup() {
        driver = Navegador.getInstance();
        loginPage = new LoginPage(driver);
        cadastroPage = new CadastroPage(driver);
        menuPage = new MenuPage(driver);
        perfilPage = new PerfilPage(driver);
    }

    //@Test
    public void cadastrarUsuarioComEmailExistente() {
        loginPage.acessar();
        loginPage.acessarPaginaCadastro();
        cadastroPage.preencherNome(NOME_USUARIO);
        cadastroPage.preencherEmail(JA_UTILIZADO_EMAIL_USUARIO);
        cadastroPage.preencherSenha(SENHA_USUARIO);
        cadastroPage.clicarEmCadastrar();

        Assert.assertEquals(cadastroPage.recuperarMensagemErro(), "Email já cadastrado!");
    }

    @Test
    public void cadastrarUsuarioComEmailInexistente() {
        String email = Utils.gerarEmailAleatorio();

        loginPage.acessar();
        loginPage.acessarPaginaCadastro();
        cadastroPage.preencherNome(NOME_USUARIO);
        cadastroPage.preencherEmail(email);
        cadastroPage.preencherSenha(SENHA_USUARIO);
        cadastroPage.clicarEmCadastrar();

        // Loga com o usuário cridao
        loginPage.logar(email, SENHA_USUARIO);
        menuPage.acessarMeuPerfil();

        // Valida que o usuário logado possui o mesmo email passado na criação
        Assert.assertEquals(perfilPage.recuperarEmail(), email);
    }

    @After
    public void tearDown() {
        Navegador.fechar();
    }
}
