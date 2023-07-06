package PageObjects;

import BasePackage.BaseClassFMS;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SelectAndAssignChaptersPage extends BaseClassFMS {


    List<WebElement> headerRows;
    List<String> headerTexts;

    int chapterNameIndex,actionIndex,MSPageIndex,processIndex,statusIndex,userIndex;

    @FindBy(xpath = "//*[@class='name']/a")
    WebElement currentlyLoggedUser;

    @FindBy(xpath="//input[@type='search']")
    WebElement searchBox;

    @FindBy(id="self_assign_modal")
    WebElement selfAssignBtn;

    @FindBy(id="chapter_assign_modal")
    WebElement  assignToOthersBtn;

    @FindBy(id="table_individual_book_chapter_list")
    WebElement bookChapterListTable;

    public SelectAndAssignChaptersPage() throws InterruptedException {
        Thread.sleep(2000);
        PageFactory.initElements(driver,this);

        headerRows = bookChapterListTable.findElements(By.xpath("thead/tr/th"));
        headerTexts = new ArrayList<String>(headerRows.size());

        for(int i=0; i< headerRows.size();i++){
            headerTexts.add(headerRows.get(i).getText());
        }

        chapterNameIndex = headerTexts.indexOf("Chapter Name") + 1;
        actionIndex = headerTexts.indexOf("Action") + 1;
        MSPageIndex = headerTexts.indexOf("No. of MS pages") + 1;
        processIndex = headerTexts.indexOf("Process") + 1;
        statusIndex = headerTexts.indexOf("Status") + 1;
        userIndex = headerTexts.indexOf("User") + 1;

    }

    public void clickAssignSelfBtn() throws InterruptedException {
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(selfAssignBtn));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", selfAssignBtn);

        //Thread.sleep(3000);
        //selfAssignBtn.click();
        js.executeScript("arguments[0].click();", selfAssignBtn);
    }

    public void clickAssignToOthersBtn(){
        assignToOthersBtn.click();
    }

    public void searchChapter(String chapterName) throws InterruptedException {
        searchBox.sendKeys(chapterName+ Keys.ENTER);
        Thread.sleep(1000);
    }

    public boolean isCheckboxErrorDisplayed() throws InterruptedException {
        Thread.sleep(500);
        try{
            WebElement chkBoxToasterMsg = driver.findElement(
                    By.xpath("//div[text()='Please select atleast one checkbox']"));
            return chkBoxToasterMsg.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isMSPageErrorDisplayed() throws InterruptedException {
        Thread.sleep(500);
        try{
            WebElement MSPageToasterMsg = driver.findElement(
                    By.xpath("//div[text()='Please update MS Page count in List of chapter!!']"));
            return MSPageToasterMsg.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public Map<String, String> getBookDatas(String chapterName) throws InterruptedException {

        List<WebElement> bodyRows = bookChapterListTable.findElements(
                By.xpath("tbody/tr/td["+chapterNameIndex+"][text()='"+chapterName+"']/../td"));
        //tbody/tr/td[6][text()='sdfn']/../td   -- sample real xpath
        Thread.sleep(1500);

        Map<String, String> bookDatas = new LinkedHashMap<String, String>(headerTexts.size());

        for(int i=0; i< headerTexts.size();i++){
            bookDatas.put(headerTexts.get(i),bodyRows.get(i).getText());
        }

        return bookDatas;
    }

    public void selectChapterToAssign(String chapterName){

        WebElement checkBox = driver.findElement(
                By.xpath("//tbody/tr/td["+chapterNameIndex+"][text()='"+chapterName+"']/../td[1]/div"));

        if(!checkBox.isSelected()){
            checkBox.click();
        }
    }

    public boolean isChapterAssignable(String chapterName){

        WebElement checkBox = driver.findElement(
                By.xpath("//tbody/tr/td["+chapterNameIndex+"][text()='"+chapterName+"']/../td[1]/div"));

        return checkBox.isEnabled();
    }

    public void deSelectChapterToAssign(String chapterName){

        WebElement checkBox = driver.findElement(
                By.xpath("//tbody/tr/td["+chapterNameIndex+"][text()='"+chapterName+"']/../td[1]/div"));

        if(checkBox.isSelected()){
            checkBox.click();
        }
    }

    public boolean isQueryErrorDisplayed(){
        try{
            WebElement queryError = driver.findElement(
                    By.xpath("//*[@class='tooltip-inner'][text()='Please approve pending queries']"));

            return queryError.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void navigateToQueryCheckPage(String chapterName){
        WebElement queryIcon = driver.findElement(
                By.xpath("//tbody/tr/td["+chapterNameIndex+"][text()='"+chapterName+"']/../td["+actionIndex+"]/i"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script1 = "arguments[0].scrollLeft = arguments[0].scrollWidth;";
        js.executeScript(script1, bookChapterListTable);

        queryIcon.click();

    }

    public void scrollWebTableBack(){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script2 = "arguments[0].scrollLeft = 0;";
        js.executeScript(script2, bookChapterListTable);

    }

    public String getCurrentlyLoggedUserName() throws InterruptedException {
        Thread.sleep(2000);
        return currentlyLoggedUser.getText();
    }

}
