package PageObjects;

import BasePackage.BaseClassFMS;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AssignChapters extends BaseClassFMS {

    @FindBy(xpath = "//table[@id='table_individual_book_chapter_list']/thead/tr/th")
    List<WebElement> individualBookChapterListTable;

    @FindBy(xpath = "//*[@type='search']")
    WebElement searchBox;

    @FindBy(id = "chapter_assign_modal")
    WebElement assignToOthers;

    @FindBy(id = "self_assign_modal")
    WebElement assignToSelf;

    int actionColumnIndex;
    int chapterNameColumnIndex;
    int checkBoxColumnIndex;

    public AssignChapters() throws InterruptedException {
        Thread.sleep(3000);
        PageFactory.initElements(driver, this);
        Thread.sleep(2500);

        for (int i = 0; i < individualBookChapterListTable.size(); i++) {
            if (individualBookChapterListTable.get(i).getText().equals("Action")) {
                actionColumnIndex = (i+1);
            } else if (individualBookChapterListTable.get(i).getText().equals("Chapter Name")) {
                chapterNameColumnIndex = (i+1);
            }
            else if (individualBookChapterListTable.get(i).getText().equals("")) {
                checkBoxColumnIndex = (i+1);
            }
        }

    }

    public void searchChapterNameVise(String chapterName) throws InterruptedException {
        Thread.sleep(2500);
        searchBox.clear();
        searchBox.sendKeys(chapterName+ Keys.ENTER);
        Thread.sleep(2000);
    }

    //Check whether checkbox to corresponding chapter is selectable
    public void checkWhetherCheckboxSelectable(String chapterName){
       // driver.findElement(By.xpath(""));
    }







}
