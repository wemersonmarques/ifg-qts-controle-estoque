package br.edu.ifg.qtscontroleestoque.acceptance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PerfilPage {

    private WebDriver driver;

    public PerfilPage(WebDriver driver) {
        this.driver = driver;
    }
    public String recuperarEmail() {
        return driver.findElement(By.id("email")).getText();
    }
}
