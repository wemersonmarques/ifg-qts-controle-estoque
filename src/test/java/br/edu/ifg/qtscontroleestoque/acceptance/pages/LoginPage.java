package br.edu.ifg.qtscontroleestoque.acceptance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private final String URL_LOGIN = "http://localhost:8081/";
    private static WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void acessar() {
        driver.navigate().to(URL_LOGIN);
    }

    public void acessarPaginaCadastro() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cadastrar")));
        driver.findElement(By.id("cadastrar")).click();
    }

    public String getURL_LOGIN() {
        return URL_LOGIN;
    }

    public void logar(String email, String senha) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cadastrar")));
        driver.findElement(By.id("login")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(senha);
        driver.findElement(By.id("logar")).click();
    }
}
