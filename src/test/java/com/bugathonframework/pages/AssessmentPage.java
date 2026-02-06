package com.bugathonframework.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bugathonframework.utils.Config;
import com.bugathonframework.utils.DemoUtil;
import com.bugathonframework.utils.StepLogger;

public class AssessmentPage {

    WebDriver driver;
    WebDriverWait wait;

    public AssessmentPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    By createBtn = By.xpath("//button[contains(text(),'Create')]");

    public void clickCreate(){

        StepLogger.step("Waiting for Create button to be clickable");

        WebElement button = wait.until(
            ExpectedConditions.elementToBeClickable(createBtn)
        );

        StepLogger.step("Clicking Create button");

        // Demo Mode Pause 
        if(Config.DEMO_MODE){
            DemoUtil.pause(2);
        }

        button.click();

        StepLogger.step("Create button clicked successfully");

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }
    }
}
