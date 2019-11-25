package br.edu.ifg.qtscontroleestoque.acceptance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProdutoPage {

    private WebDriver driver;

    public ProdutoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherDescricao(String descricao) {
        driver.findElement(By.id("descricao")).sendKeys(descricao);
    }

    public void preencherEstoqueMaximo(float estoqueMaximo) {
        driver.findElement(By.id("estoqueMaximo")).sendKeys(String.valueOf(estoqueMaximo));
    }

    public void preencherEstoqueMinimo(float estoqueMinimo) {
        driver.findElement(By.id("estoqueMinimo")).sendKeys(String.valueOf(estoqueMinimo));
    }

    public void clicarEmEnviar() {
        driver.findElement(By.id("btnEnviar")).click();
    }

    public void cadastrarProduto(String descricao, float estoqueMaximo, float estoqueMinimo) {
        preencherDescricao(descricao);
        preencherEstoqueMaximo(estoqueMaximo);
        preencherEstoqueMinimo(estoqueMinimo);
        clicarEmEnviar();
    }

    public List<String> getProdutosCadastrados() {
        List<WebElement> elements = driver.findElements(By.id("descricaoCadastrado"));
        List<String> produtosCadastrados = new ArrayList<>();

        for (WebElement element : elements) {
            produtosCadastrados.add(element.getText());
        }

        return produtosCadastrados;
    }

    public boolean isProdutoCadastrado(List<String> produtosCadastrados, String produtoBusca) {
        for (String prod : produtosCadastrados) {
            if (prod.equals(produtoBusca)) {
                return true;
            }
        }

        return false;
    }
}
