package br.edu.ifg.qtscontroleestoque.acceptance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroPage {

    private WebDriver driver;

    public CadastroPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherNome(String nome) {
        driver.findElement(By.id("nome")).sendKeys(nome);
    }

    public void preencherEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    public void preencherSenha(String senha) {
        driver.findElement(By.id("senha")).sendKeys(senha);
    }

    public void clicarEmCadastrar() {
        driver.findElement(By.id("btnCadastrar")).click();
    }

    public String recuperarMensagemErro() {
        return driver.findElement(By.id("erro")).getText();
    }
}
