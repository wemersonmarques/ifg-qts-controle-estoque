package br.edu.ifg.qtscontroleestoque.acceptance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MenuPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void acessarMeuPerfil() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("perfil")));
        driver.findElement(By.id("perfil")).click();
    }

    public void acessarProdutos() {
        driver.findElement(By.id("produtos")).click();
    }

    public void acessarMovimentacoes() {
        driver.findElement(By.id("movimentacoes")).click();
    }

    public void deslogar() {
        driver.findElement(By.id("deslogar")).click();
    }

}
