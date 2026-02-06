package com.bugathonframework.tests;

import org.testng.annotations.Test;

import com.bugathonframework.base.BaseTest;
import com.bugathonframework.pages.CreateTestPage;
import com.bugathonframework.pages.DashboardPage;
import com.bugathonframework.pages.LoginPage;
import com.bugathonframework.pages.QuestionLibraryPopup;
import com.bugathonframework.pages.TemplateCreatePage;

public class LoginTest extends BaseTest {

@Test
public void createAssessmentFlow() {

    // ===== PAGE OBJECTS =====
    LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboard = new DashboardPage(driver);
    CreateTestPage createTest = new CreateTestPage(driver);
    TemplateCreatePage templatePage = new TemplateCreatePage(driver);
    QuestionLibraryPopup qLib = new QuestionLibraryPopup(driver);

    // ===== NEGATIVE LOGIN =====
    loginPage.enterEmail("hyrenet+bugathon@guvi.in");
    loginPage.clickLogin();   // password left blank

    if (loginPage.isErrorDisplayed()) {
        System.out.println("Negative login test passed: " + loginPage.getErrorMessage());
    } else {
        System.out.println("No error displayed for negative login");
    }

    // ===== POSITIVE LOGIN =====
    loginPage.clearEmail();
    loginPage.clearPassword();
    loginPage.enterEmail("hyrenet+bugathon@guvi.in");
    loginPage.enterPassword("hyrenettest@123");
    loginPage.clickLogin();

    // ===== DASHBOARD =====
    dashboard.clickTest();

    // ===== CREATE TEST =====
    createTest.openCreateTest();
    createTest.chooseManualCreation();

    // ===== TEMPLATE FLOW =====
    templatePage.createTemplateFullFlow();   // already contains loader wait

    // ===== QUESTION LIBRARY =====
    qLib.addAllQuestions();
}

}
