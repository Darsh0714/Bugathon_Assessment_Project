package com.bugathonframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bugathonframework.utils.Config;
import com.bugathonframework.utils.DemoUtil;
import com.bugathonframework.utils.StepLogger;

import java.time.Duration;

public class DashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Test button in sidebar/top menu
    By testBtn = By.xpath("//span[contains(text(),'Test')]");

    public void clickTest(){

        StepLogger.step("Waiting for Test menu button");

        WebElement btn = wait.until(
            ExpectedConditions.elementToBeClickable(testBtn)
        );

        StepLogger.step("Clicking Test menu");

        if(Config.DEMO_MODE){
            DemoUtil.pause(2);
        }

        btn.click();

        StepLogger.step("Test menu opened");

        if(Config.DEMO_MODE){
            DemoUtil.pause(1);
        }
    }
}
