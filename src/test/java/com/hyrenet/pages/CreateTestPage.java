package com.hyrenet.pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.hyrenet.utils.Config;
import com.hyrenet.utils.DemoUtil;
import com.hyrenet.utils.StepLogger;

public class CreateTestPage {

    WebDriver driver;
    WebDriverWait wait;

    public CreateTestPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    By testMenu = By.xpath("//*[normalize-space()='Test']");
    By createTestBtn = By.xpath("//div[contains(@class,'create-test')]");
    By createManualBtn = By.xpath("//*[contains(text(),'Create Manually')]");

    public void openCreateTest() {

        StepLogger.step("Opening Test Menu");

        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(testMenu));

        if(Config.DEMO_MODE){
            DemoUtil.pause(2);
        }

        menu.click();

        StepLogger.step("Clicking Create Test button");

        WebElement createBtn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(createTestBtn)
        );

        if(Config.DEMO_MODE){
            DemoUtil.pause(2);
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createBtn);

        StepLogger.step("Create Test button clicked");
    }

    public void chooseManualCreation() {

        StepLogger.step("Choosing Manual Test Creation");

        WebElement manual = wait.until(
            ExpectedConditions.visibilityOfElementLocated(createManualBtn)
        );

        if(Config.DEMO_MODE){
            DemoUtil.pause(2);
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manual);

        StepLogger.step("Manual Creation selected");
    }
}
