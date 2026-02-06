package com.hyrenet.tests;

import com.hyrenet.base.BaseTest;
import com.hyrenet.pages.CreateTestPage;
import com.hyrenet.pages.DashboardPage;
import com.hyrenet.pages.LoginPage;
import com.hyrenet.pages.QuestionLibraryPopup;
import com.hyrenet.pages.TemplateCreatePage;
import org.testng.annotations.Test;

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
