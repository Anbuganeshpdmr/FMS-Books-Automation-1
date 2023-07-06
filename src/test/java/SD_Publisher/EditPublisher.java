package SD_Publisher;

import PageObjects.*;
import UlilityPackage.ExcelReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static MandReqdUtilities.BasicRequirements.getTimeStamp;

public class EditPublisher {

//***************************************************************************************************************

    HomePage homePage;
    ListOfPublishersPage listOfPublishersPage;
    AddPublisherPage addPublisherPage;
    ListOfBooksPage listOfBooksPage;
    AddBookPage addBookPage;
    EditPublisherPage editPublisherPage;
    String publisherName_initial = "";
    String publisherName_new = "";
    String bookName_Scenario2;

//***************************************************************************************************************

    @Given("navigate to List of Publishers page")
    public void navigate_to_list_of_publishers_page() throws InterruptedException {
        Thread.sleep(2000);
        homePage = new HomePage();
        homePage.navigateToListOfPublishersPage();
    }

    @Then("go the Add publisher page and add new publisher")
    public void go_the_add_publisher_page_and_add_new_publisher() throws InterruptedException {
        Thread.sleep(2000);
        publisherName_initial = ("DemoEP1_"+getTimeStamp());

        listOfPublishersPage = new ListOfPublishersPage();
        listOfPublishersPage.navigateToAddPublisherPage();

        addPublisherPage = new AddPublisherPage();
        addPublisherPage.addNewPublisherAndClick(publisherName_initial);
    }

//***************************************************************************************************************

    @Given("edit the publisher name")
    public void edit_the_publisher_name() throws InterruptedException {

        publisherName_new = ("DemoEP2_"+getTimeStamp());

        listOfPublishersPage.navigateToEditPublisherPage(publisherName_initial);

        editPublisherPage = new EditPublisherPage();

        Thread.sleep(2000);
        editPublisherPage.enterPublisherName(publisherName_new);
        editPublisherPage.pressUpdateButton();

    }

    @Then("verify the bookcount is still {string}")
    public void verify_the_bookcount_is_still(String expBookcount) throws InterruptedException {
        Assert.assertEquals(listOfPublishersPage.getBookcount(publisherName_new),expBookcount);
    }

    @And("verify the presence of Edit and Delete button")
    public void verify_the_presence_of_edit_and_delete_button() throws InterruptedException {
        Assert.assertTrue(listOfPublishersPage.isEditOptionAvailable(publisherName_new));
        Assert.assertTrue(listOfPublishersPage.isDeleteOptionAvailable(publisherName_new));
    }

//***************************************************************************************************************

    @Given("add new book to the publisher")
    public void add_new_book_to_the_publisher(DataTable dataTable) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.

        Thread.sleep(2000);

        int bookNumber = 0;
        List<Map<String, String>> bookdata =  dataTable.asMaps(String.class, String.class);

        String receivedDate = bookdata.get(bookNumber).get("Received Date");
        String publisher = publisherName_initial;
        bookName_Scenario2 = bookdata.get(bookNumber).get("Book name") + getTimeStamp();
        String noOfChapters = bookdata.get(bookNumber).get("No of chapters");
        String ISBNNumber = getTimeStamp();
        String typeOfBook = bookdata.get(bookNumber).get("Type of book");
        String complexityLevel = bookdata.get(bookNumber).get("Complexity level");
        String bookColour = bookdata.get(bookNumber).get("Book color");
        String priority = bookdata.get(bookNumber).get("Priority");
        String CEReceivedDate = bookdata.get(bookNumber).get("CE Received Date");
        String clientDueDate = bookdata.get(bookNumber).get("Client Due Date");
        String PDMRPlanDate = bookdata.get(bookNumber).get("PDMR Plan Date");
        String stages = bookdata.get(bookNumber).get("Stages");

        homePage.navigateToListOfBooksPage();

        listOfBooksPage = new ListOfBooksPage();
        listOfBooksPage.navigateToAddBookPage();

        addBookPage = new AddBookPage();
        Thread.sleep(2000);
        addBookPage.addNewBook(receivedDate,publisher,bookName_Scenario2,noOfChapters,ISBNNumber,typeOfBook,complexityLevel,
                bookColour,priority,CEReceivedDate,clientDueDate,PDMRPlanDate,stages);
        addBookPage.pressSubmitButton();
    }

    @But("edit the current publisher name")
    public void edit_the_current_publisher_name() throws InterruptedException {

        homePage.navigateToListOfPublishersPage();

        listOfPublishersPage.navigateToEditPublisherPage(publisherName_initial);

        publisherName_new = ("DemoEP2_"+getTimeStamp());

        editPublisherPage = new EditPublisherPage();
        editPublisherPage.enterPublisherName(publisherName_new);
        editPublisherPage.pressUpdateButton();
    }

    @Then("verify the absence of Delete button")
    public void verify_the_absence_of_delete_button() throws InterruptedException {
        Assert.assertFalse(listOfPublishersPage.isDeleteOptionAvailable(publisherName_new));
        Assert.assertTrue(listOfPublishersPage.isEditOptionAvailable(publisherName_new),"Checking the status of Edit button");
    }

    @And("go to the recently added book page and confirm the publisher name is modified")
    public void go_to_the_recently_added_book_page_and_confirm_the_publisher_name_is_modified() throws InterruptedException {
        homePage.navigateToListOfBooksPage();

        listOfBooksPage = new ListOfBooksPage();
        String actualPublisherName = listOfBooksPage.getPublisherName(bookName_Scenario2);

        Assert.assertEquals(actualPublisherName,publisherName_new);
        Assert.assertNotEquals(actualPublisherName,publisherName_initial);

    }
//***************************************************************************************************************
    @Given("proceed to edit the publisher but press cancel button")
    public void proceed_to_edit_the_publisher_but_press_cancel_button() throws InterruptedException {

        homePage.navigateToListOfPublishersPage();

        listOfPublishersPage.navigateToEditPublisherPage(publisherName_initial);

        publisherName_new = ("DemoEP2_"+getTimeStamp());

        editPublisherPage = new EditPublisherPage();
        editPublisherPage.enterPublisherName(publisherName_new);
        editPublisherPage.pressCancelButton();

    }

    @Then("verify the publisher name is not modified")
    public void verify_the_publisher_name_is_not_modified() throws InterruptedException {

        Assert.assertTrue(listOfPublishersPage.verifyPublisher(publisherName_initial),"Initially given name available");
        Assert.assertFalse(listOfPublishersPage.verifyPublisher(publisherName_new),"Name not modified");
    }
//***************************************************************************************************************
    @Given("retrieve one older publisher name")
    public void retrieve_one_older_publisher_name() {

        System.out.println("Retrived publisher name is:"+publisherName_initial);
    }

    @Then("create new publisher and edit with retrieved name")
    public void create_new_publisher_and_edit_with_retrieved_name() throws InterruptedException {

        publisherName_new = ("DemoEP2_"+getTimeStamp());
        Thread.sleep(1500);
        listOfPublishersPage.navigateToAddPublisherPage();

        addPublisherPage.addNewPublisherAndClick(publisherName_new);
        listOfPublishersPage.navigateToEditPublisherPage(publisherName_new);

        editPublisherPage = new EditPublisherPage();
        Thread.sleep(2000);
        editPublisherPage.enterPublisherName(publisherName_initial);
        editPublisherPage.pressUpdateButton();

        //addPublisherPage.addNewPublisherAndClick(publisherName_initial);
    }

    @And("verify the error message is displayed")
    public void verify_the_error_message_is_displayed() throws InterruptedException {
        Assert.assertTrue(editPublisherPage.isErrorMessageDisplayed());
        editPublisherPage.pressCancelButton();
    }

//***************************************************************************************************************

    @Given("edit the publisher with invalid text")
    public void edit_the_publisher_with_invalid_text() throws InterruptedException {

        listOfPublishersPage.navigateToEditPublisherPage(publisherName_initial);
        System.out.println("old name"+publisherName_initial);

        publisherName_new = ("@@_DemoEp2_"+getTimeStamp());

        editPublisherPage = new EditPublisherPage();
        Thread.sleep(2000);
        editPublisherPage.enterPublisherName(publisherName_new);
        System.out.println("new name"+publisherName_new);
        Thread.sleep(2000);

        editPublisherPage.pressUpdateButton();
    }

    @Then("verify the error message be displayed for invalid characters")
    public void verify_the_error_message_be_displayed_for_invalid_characters() throws InterruptedException {
        Assert.assertTrue(editPublisherPage.isErrorMessageDisplayed());
    }
//***************************************************************************************************************

}
