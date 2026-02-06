package com.bugathonframework.pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.bugathonframework.utils.Config;
import com.bugathonframework.utils.DemoUtil;
import com.bugathonframework.utils.StepLogger;

public class TemplateCreatePage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By roleDropdown = By.id("custom-select-role-selectized");
    By roleOption = By.xpath("//div[@class='selectize-dropdown-content']/div[text()='Java developer']");
    By templateName = By.id("custom-test-name");
    By templatePlanDropdown = By.id("custom-test-plan-selectized");
    By templatePlanOption = By.xpath("//div[@class='selectize-dropdown-content']/div[text()='Objective & Programming']");
    By objectiveDuration = By.id("custom-obj-duration");
    By programmingDuration = By.id("custom-pgm-duration");
    By saveContinueBtn = By.id("step1-submit");
    By loader = By.cssSelector(".loading-overlay");

    public TemplateCreatePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickCreateNewTemplate() {

        StepLogger.step("Clicking Create New Template");

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[p[text()='Create New Template']]")));

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }
    }

    public void fillTemplateDetails() {

        StepLogger.step("Selecting Role");

        WebElement role = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
        role.click();

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }

        wait.until(ExpectedConditions.elementToBeClickable(roleOption)).click();

        StepLogger.step("Entering Template Name");

        wait.until(ExpectedConditions.visibilityOfElementLocated(templateName))
            .sendKeys("HyreNetBugathon-Java");

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }

        StepLogger.step("Selecting Template Plan");

        WebElement plan = wait.until(ExpectedConditions.elementToBeClickable(templatePlanDropdown));
        plan.click();

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }

        wait.until(ExpectedConditions.elementToBeClickable(templatePlanOption)).click();

        StepLogger.step("Setting Objective Duration");

        try {
            WebElement objInput = wait.until(ExpectedConditions.elementToBeClickable(objectiveDuration));
            objInput.clear();
            objInput.sendKeys("30");
        } catch (Exception e) {
            ((JavascriptExecutor) driver)
                .executeScript("document.getElementById('custom-obj-duration').value='30';");
        }

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }

        StepLogger.step("Setting Programming Duration");

        WebElement pgmDuration = wait.until(
            ExpectedConditions.visibilityOfElementLocated(programmingDuration)
        );
        pgmDuration.clear();
        pgmDuration.sendKeys("45");

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }

        StepLogger.step("Selecting Programming Language");

        WebElement langInput = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("custom-pgm-language-selectized"))
        );
        langInput.click();

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }

        WebElement javaOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@class='selectize-dropdown-content']/div[@data-value='java']")
        ));
        javaOption.click();

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }
    }

    public void saveAndContinue() {

        StepLogger.step("Clicking Save & Continue");

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(saveContinueBtn));

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }

        waitForLoaderToDisappear();
    }

    public void waitForLoaderToDisappear() {

        StepLogger.step("Waiting for Loader to disappear");

        try {
            new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception e) {
            // ignore if loader not present
        }

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }
    }

    public void createTemplateFullFlow() {

        StepLogger.step("Starting Template Creation Flow");

        clickCreateNewTemplate();
        fillTemplateDetails();
        saveAndContinue();

        StepLogger.step("Template Created Successfully");
    }
}
