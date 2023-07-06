package StepDefinitions;

import BasePackage.BaseClassFMS;
import PageObjects.HomePage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TrialAndError extends BaseClassFMS {

    @Given("try the code")
    public void try_the_code() throws InterruptedException {

        /*HomePage homePage = new HomePage();
        homePage.navigateToListOfBooksPage();

        WebElement trialTable = driver.findElement(By.id("table_book"));

        List<WebElement> headerRows = trialTable.findElements(By.xpath("thead/tr/th"));
        List<String> headerTexts = new ArrayList<String>(headerRows.size());

        for(int i=0; i< headerRows.size();i++){
            headerTexts.add(headerRows.get(i).getText());
        }

        int bookNameIndex = headerTexts.indexOf("Book Name") + 1;
        int ActionIndex = headerTexts.indexOf("Action") + 1;
        String bookName = "First book for test";

        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Trial Book"+ Keys.ENTER);
        Thread.sleep(1500);

        List<WebElement> bodyRows = trialTable.findElements(
                By.xpath("tbody/tr/td["+bookNameIndex+"]/a[text()='"+bookName+"']/../../td"));
        Thread.sleep(1500);

        Map<String, String> recordvalues = new LinkedHashMap<String, String>(headerTexts.size());

        for(int i=0; i< headerTexts.size();i++){
            recordvalues.put(headerTexts.get(i),bodyRows.get(i).getText());
        }*/

    }
}
