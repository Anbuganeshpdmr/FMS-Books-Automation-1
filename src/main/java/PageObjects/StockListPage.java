package PageObjects;

import BasePackage.BaseClassFMS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class StockListPage extends BaseClassFMS {

    int bookNameIndex = 3;
    int actionIndex = 9;

    List<WebElement> headerRows;
    List<String> headerTexts;

    @FindBy(xpath = "//input[@type='search']")
    WebElement stockListSearch;

    /*@FindBy(id = "table_stock_list")
    WebElement stockListTable;*/

    public StockListPage() throws InterruptedException {

        PageFactory.initElements(driver, this);
        Thread.sleep(2000);

        /*headerRows = stockListTable.findElements(By.xpath("thead/tr/th"));
        headerTexts = new ArrayList<String>(headerRows.size());

        for(int i=0; i< headerRows.size();i++){
            headerTexts.add(headerRows.get(i).getText());
        }

        bookNameIndex = headerTexts.indexOf("Book Name") + 1;
        actionIndex = headerTexts.indexOf("Action") + 1;*/

    }

    public void searchBook(String bookName) throws InterruptedException {

        stockListSearch.clear();
        stockListSearch.sendKeys(bookName+ Keys.ENTER);
        Thread.sleep(1500);
    }

    public boolean isBoookAvailable(String bookName) throws InterruptedException {
        searchBook(bookName);
        try{
            return driver.findElement(By.xpath("//tr/td["+bookNameIndex+"]/a[text()='"+bookName+"']")).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }

    }
    public void navigateToAssignChapter(String bookName) throws InterruptedException {


        driver.findElement(
                        By.xpath("//tr/td["+bookNameIndex+"]/a[text()='"+bookName+"']/../../td["+actionIndex+"]/a"))
                .click();

        Thread.sleep(2500);

    }

}
