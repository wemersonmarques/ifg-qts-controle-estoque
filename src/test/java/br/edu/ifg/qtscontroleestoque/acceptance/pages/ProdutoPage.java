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

    public void cadastrarProduto(String descricao, float estoqueMaximo, float estoqueMinimo) {
        preencherDescricao(descricao);
        preencherEstoqueMaximo(estoqueMaximo);
        preencherEstoqueMinimo(estoqueMinimo);
    }

    public List<String> getProdutosCadastrados() {
        WebElement element = driver.findElement(By.id("produtoscadastrados"));


        return new ArrayList<>();
    }
}
