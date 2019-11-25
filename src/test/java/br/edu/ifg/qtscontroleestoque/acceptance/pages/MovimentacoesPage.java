package br.edu.ifg.qtscontroleestoque.acceptance.pages;

import br.edu.ifg.qtscontroleestoque.type.ETipoMovimentacao;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MovimentacoesPage {

    private WebDriver driver;
    private final String XPATH_TIPO_MOVIMENTACAO = "/html/body/div/div[@id='viewport']/div[@class='container']/div[@id='cadastro']/form/select[1]";
    private final String XPATH_PRODUTO = "/html/body/div/div[@id='viewport']/div[@class='container']/div[@id='cadastro']/form/select[2]";
    private final String XPATH_QUANTIDADE = "/html/body/div/div[@id='viewport']/div[@class='container']/div[@id='cadastro']/form/input[1]";
    private final String XPATH_ENVIAR = "/html/body/div/div[@id='viewport']/div[@class='container']/div[@id='cadastro']/form/input[2]";
    private final String XPATH_MENSAGEM_ERRO = "/html/body/div/div[@id='viewport']/div[@class='container']/div[@id='cadastro']/form/span";
    private final String XPATH_TABELA_MOVIMENTACOES = "/html/body/div/div[@id='viewport']/div[@class='container']/div[@id='movimentacoesRecentes']";

    public MovimentacoesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selecionarTipoMovimentacao(ETipoMovimentacao tipoMovimentacao) {
        Select select = new Select(driver.findElement(By.xpath(XPATH_TIPO_MOVIMENTACAO)));
        select.selectByVisibleText(tipoMovimentacao.name());
    }

    public void selecionarProduto(String produto) {
        Select select = new Select(driver.findElement(By.xpath(XPATH_PRODUTO)));
        select.selectByVisibleText(produto);
    }

    public void preencherQuantidade(float quantidade) {
        driver.findElement(By.xpath(XPATH_QUANTIDADE)).sendKeys(String.valueOf(quantidade));
    }

    public void clicarEmEnviar() {
        driver.findElement(By.xpath(XPATH_ENVIAR)).click();
    }

    public String recuperarMensagemErro() {
        return driver.findElement(By.xpath(XPATH_MENSAGEM_ERRO)).getText();
    }

    public void cadastrarMovimentacao(ETipoMovimentacao tipoMovimentacao, String produto, float quantidade) {
        selecionarTipoMovimentacao(tipoMovimentacao);
        selecionarProduto(produto);
        preencherQuantidade(quantidade);
        clicarEmEnviar();
    }

    public boolean isCadastrado(ETipoMovimentacao tipoMovimentacao, String produto, float quantidade) {
        WebElement table = driver.findElement(By.xpath(XPATH_TABELA_MOVIMENTACOES));
        String linhasTabela[] = table.getText().split("\n");

        int i = 0;
        while (i < linhasTabela.length) {
            if (linhasTabela[i].contains(tipoMovimentacao.name()) &&
                    linhasTabela[i].contains(String.valueOf(quantidade)) &&
                    linhasTabela[i].contains(produto)) {
                return true;
            }
            i++;
        }

        return false;
    }

}
