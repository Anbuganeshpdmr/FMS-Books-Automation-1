package PageObjects;

import BasePackage.BaseClassFMS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class FreshStockListPage extends BaseClassFMS {

    int bookNameIndex;
    int actionIndex;

    List<WebElement> headerRows;
    List<String> headerTexts;

    @FindBy(xpath = "//input[@type='search']")
    WebElement freshStockListSearch;

    @FindBy(id = "table_stock_list")
    WebElement freshStockListTable;

    public FreshStockListPage() throws InterruptedException {

        PageFactory.initElements(driver, this);
        Thread.sleep(2000);

        headerRows = freshStockListTable.findElements(By.xpath("thead/tr/th"));
        headerTexts = new ArrayList<String>(headerRows.size());

        for(int i=0; i< headerRows.size();i++){
            headerTexts.add(headerRows.get(i).getText());
        }

        bookNameIndex = headerTexts.indexOf("Book Name") + 1;
        actionIndex = headerTexts.indexOf("Action") + 1;

    }

    public void searchBook(String bookName) throws InterruptedException {

        freshStockListSearch.sendKeys(bookName+ Keys.ENTER);
        Thread.sleep(1500);
    }


    public void navigateToAssignChapter(String bookName) throws InterruptedException {


        driver.findElement(
                By.xpath("//tr/td["+bookNameIndex+"]/a[text()='"+bookName+"']/../../td["+actionIndex+"]/a"))
                .click();

        Thread.sleep(2500);

    }

}

