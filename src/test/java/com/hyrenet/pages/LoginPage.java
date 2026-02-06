package com.hyrenet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import com.hyrenet.utils.Config;
import com.hyrenet.utils.DemoUtil;
import com.hyrenet.utils.StepLogger;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private final By emailField = By.xpath("//input[@type='email']");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By loginButton = By.id("submit");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions
    public void enterEmail(String email) {

        StepLogger.step("Entering Email");

        WebElement emailInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(emailField)
        );
        emailInput.clear();
        emailInput.sendKeys(email);

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }
    }

    public void enterPassword(String password) {

        StepLogger.step("Entering Password");

        WebElement passwordInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(passwordField)
        );
        passwordInput.clear();
        passwordInput.sendKeys(password);

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }
    }

    public void clickLogin() {

        StepLogger.step("Clicking Login Button");

        WebElement loginBtn = wait.until(
            ExpectedConditions.elementToBeClickable(loginButton)
        );

        if(Config.DEMO_MODE){
            DemoUtil.pause(2);
        }

        loginBtn.click();
    }

    public void login(String email, String password) {

        StepLogger.step("Starting Login Flow");

        enterEmail(email);
        enterPassword(password);
        clickLogin();

        StepLogger.step("Login action submitted");
    }

    public boolean isErrorDisplayed() {
        try {
            return driver.findElement(
                By.xpath("//p[contains(text(),'Email or Password is Incorrect')]")
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return driver.findElement(
                By.xpath("//p[contains(text(),'Email or Password is Incorrect')]")
            ).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clearEmail() {
        driver.findElement(emailField).clear();
    }

    public void clearPassword() {
        driver.findElement(passwordField).clear();
    }
}
