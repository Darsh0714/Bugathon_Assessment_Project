package com.hyrenet.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.hyrenet.utils.Config;
import com.hyrenet.utils.DemoUtil;
import com.hyrenet.utils.StepLogger;

public class QuestionLibraryPopup {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By addFromLibraryBtn = By.xpath("//button[contains(@class,'add-from-library')]");
    By difficultyAllBtn = By.xpath("//div[@id='select-difficulty']//label[contains(.,'All')]");
    By sourceGuvi = By.xpath("//div[@id='select-library']//label[contains(.,'Guvi Library')]");
    By sourceMyLibrary = By.xpath("//div[@id='select-library']//label[contains(.,'My Library')]");
    By addQuestionBtns = By.xpath("//button[contains(@class,'select-row') and @title='Add this question']");
    By closePopupBtn = By.xpath("//button[.//div[text()='Close']]");

    public QuestionLibraryPopup(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    // ================= SAFE CLICK =================
    public void safeClick(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            element.click();
        } catch (ElementNotInteractableException e) {

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(element));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    // ================= WAIT FOR PAGE READY =================
    private void waitForPageReady() {
        wait.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    // ================= OPEN POPUP =================
    public void openPopup() {

        StepLogger.step("Opening Question Library Popup");

        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addFromLibraryBtn));
        safeClick(addBtn);

        if (Config.DEMO_MODE) DemoUtil.pause(1);

        wait.until(ExpectedConditions.visibilityOfElementLocated(difficultyAllBtn));
        StepLogger.step("Popup is visible");
    }

    // ================= DIFFICULTY =================
    public void setDifficultyAll() {

        StepLogger.step("Setting Difficulty to ALL");

        WebElement allDifficulty = wait.until(ExpectedConditions.elementToBeClickable(difficultyAllBtn));
        safeClick(allDifficulty);

        if (Config.DEMO_MODE) DemoUtil.pause(1);
    }

    // ================= OBJECTIVE =================
    public void addObjectiveQuestions() {

        StepLogger.step("Adding Objective Questions");

        WebElement guvi = wait.until(ExpectedConditions.elementToBeClickable(sourceGuvi));
        safeClick(guvi);

        if (Config.DEMO_MODE) DemoUtil.pause(1);

        selectQuestions(5);

        WebElement myLibrary = wait.until(ExpectedConditions.elementToBeClickable(sourceMyLibrary));
        safeClick(myLibrary);

        if (Config.DEMO_MODE) DemoUtil.pause(1);

        selectQuestions(5);
    }

    // ================= PROGRAMMING =================
    public void addProgrammingQuestions() {

        StepLogger.step("Switching to Programming Tab");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal-backdrop")));

        if (Config.DEMO_MODE) DemoUtil.pause(1);

        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(By.id("auto-pgm-tab")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tab);
        safeClick(tab);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("auto-pgm")));

        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#auto-pgm button.add-from-library")));
        safeClick(addBtn);

        if (Config.DEMO_MODE) DemoUtil.pause(1);

        WebElement pgmLibraryModal =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-question-library")));

        WebElement guvi = pgmLibraryModal.findElement(sourceGuvi);
        safeClick(guvi);

        if (Config.DEMO_MODE) DemoUtil.pause(1);

        selectProgrammingQuestions(pgmLibraryModal, 5);

        WebElement myLibrary = pgmLibraryModal.findElement(sourceMyLibrary);
        safeClick(myLibrary);

        if (Config.DEMO_MODE) DemoUtil.pause(1);

        List<WebElement> myLibQuestions = pgmLibraryModal.findElements(addQuestionBtns);

        if (!myLibQuestions.isEmpty()) {
            safeClick(myLibQuestions.get(0));

            if (Config.DEMO_MODE) DemoUtil.pause(1);
        } else {
            StepLogger.step("No questions available in My Library for Programming");
        }

        // ❌ Removed closePopup() here to avoid duplicate closing
    }

    // ================= SELECT PROGRAMMING QUESTIONS =================
    private void selectProgrammingQuestions(WebElement modal, int n) {

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(addQuestionBtns));
        List<WebElement> questions = modal.findElements(addQuestionBtns);

        int toSelect = Math.min(n, questions.size());
        StepLogger.step("Selecting " + toSelect + " programming questions");

        for (int i = 0; i < toSelect; i++) {
            try {
                safeClick(questions.get(i));

                if (Config.DEMO_MODE) DemoUtil.pause(1);

            } catch (Exception e) {
                StepLogger.step("Failed to click question index " + i);
            }
        }
    }

    // ================= SELECT OBJECTIVE QUESTIONS =================
    private void selectQuestions(int n) {

        int selected = 0;
        int attempts = 0;

        while (selected < n && attempts < 20) {

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(addQuestionBtns));
            List<WebElement> questions = driver.findElements(addQuestionBtns);

            for (WebElement q : questions) {

                if (selected >= n) break;

                try {
                    safeClick(q);
                    selected++;

                    if (Config.DEMO_MODE) DemoUtil.pause(1);

                } catch (Exception e) {
                    if (Config.DEMO_MODE) DemoUtil.pause(1);
                }
            }
            attempts++;
        }
    }

    // ================= CLOSE POPUP =================
    public void closePopup() {

        boolean closed = false;
        int retries = 0;

        while (!closed && retries < 5) {
            try {

                WebElement closeBtn =
                        wait.until(ExpectedConditions.elementToBeClickable(closePopupBtn));

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView(true);", closeBtn);

                safeClick(closeBtn);

                // STRONG verification that popup closed
                wait.until(ExpectedConditions.invisibilityOf(closeBtn));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal-backdrop")));

                StepLogger.step("Popup closed successfully.");
                closed = true;

            } catch (Exception e) {

                StepLogger.step("Close button not ready, retrying...");

                if (Config.DEMO_MODE) DemoUtil.pause(1);
                retries++;
            }
        }
    }

    // ================= SAVE & SUBMIT =================
    public void clickSaveAndSubmit() {

        StepLogger.step("Clicking Save & Submit");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal-backdrop")));

        waitForPageReady();

        if (Config.DEMO_MODE) DemoUtil.pause(1);

        WebElement saveBtn =
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a.custom-btn.custom-btn--primary.save-and-submit")));

        StepLogger.step("Waiting for Save button to become ACTIVE");

        safeClick(saveBtn);

        // VERIFY click actually triggered something
        wait.until(ExpectedConditions.or(
                ExpectedConditions.stalenessOf(saveBtn),
                ExpectedConditions.urlContains("assessment")
        ));

        StepLogger.step("Save & Submit CONFIRMED.");
    }

    // ================= CONTROLLER =================
    public void addAllQuestions() {

        StepLogger.step("Starting Add All Questions Flow");

        openPopup();
        if (Config.DEMO_MODE) DemoUtil.pause(1);

        setDifficultyAll();
        if (Config.DEMO_MODE) DemoUtil.pause(1);

        addObjectiveQuestions();
        if (Config.DEMO_MODE) DemoUtil.pause(1);

        closePopup();
        if (Config.DEMO_MODE) DemoUtil.pause(1);

        addProgrammingQuestions();
        if (Config.DEMO_MODE) DemoUtil.pause(1);

        closePopup();
        if (Config.DEMO_MODE) DemoUtil.pause(1);

        clickSaveAndSubmit();
        if (Config.DEMO_MODE) DemoUtil.pause(1);

        StepLogger.step("Finished Adding All Questions");
    }
}
