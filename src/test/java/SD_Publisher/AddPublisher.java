package SD_Publisher;

import PageObjects.*;
import UlilityPackage.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static MandReqdUtilities.BasicRequirements.getTimeStamp;

public class AddPublisher {

    HomePage homePage;
    ListOfPublishersPage listOfPublishersPage;

    ListOfBooksPage listOfBooksPage;

    AddBookPage addBookPage;

    AddPublisherPage addPublisherPage;

    String publisherName1,publisherName2,publisherName3,publisherName4,publisherName5;
    String filePath2 = "src/test/resources/Publisher/PublisherTestData.xlsx";
    String sheetName2 = "AddPublisher_2";

    boolean publisherAvailability1;
//--------------------------------------------------------------------------------------------------------------
    @Given("Go to List of Publishers page")
    public void go_to_list_of_publishers_page() {

        homePage = new HomePage();
        homePage.navigateToListOfPublishersPage();

    }

    @And("Navigate furthur to Add Publisher page")
    public void navigate_furthur_to_add_publisher_page() throws InterruptedException {

        listOfPublishersPage = new ListOfPublishersPage();
        listOfPublishersPage.navigateToAddPublisherPage();
    }

//**************************************************************************************************************

    @Given("create new publisher")
    public void createNewPublisher() throws InterruptedException {

        publisherName1 = "Demo"+getTimeStamp();

        addPublisherPage = new AddPublisherPage();
        addPublisherPage.addNewPublisherAndClick(publisherName1);

    }

    @When("search and verify the presence of created publisher")
    public void searchAndVerifyThePresenceOfCreatedPublisher() throws InterruptedException {

        listOfPublishersPage = new ListOfPublishersPage();
        Assert.assertTrue(listOfPublishersPage.verifyPublisher(publisherName1));
    }

    @Then("validate the bookcount is zero")
    public void validateTheBookcountIsZero() throws InterruptedException {
        Assert.assertEquals(listOfPublishersPage.getBookcount(publisherName1),"0");
    }

    @And("validate the presence of Edit and Delete button")
    public void validateThePresenceOfEditAndDeleteButton() throws InterruptedException {

        Assert.assertTrue(listOfPublishersPage.isEditOptionAvailable(publisherName1));
        Assert.assertTrue(listOfPublishersPage.isDeleteOptionAvailable(publisherName1));

    }

//**************************************************************************************************************

    @Given("Add new Publisher and press Enter")
    public void add_new_publisher_and_press_enter() throws InterruptedException {
        publisherName2 = ("Demo"+getTimeStamp());

        addPublisherPage = new AddPublisherPage();
        addPublisherPage.addNewPublisherAndClick(publisherName2);
    }

    @Then("add new book {string} to the publisher")
    public void add_new_book_to_the_publisher(String bookNum) throws IOException, InvalidFormatException, InterruptedException {

        Thread.sleep(2000);
        int bookNumber = (Integer.parseInt(bookNum)-1);

        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> bookdata =  excelReader.getData(filePath2, sheetName2);

        String receivedDate = bookdata.get(bookNumber).get("Received Date");
        String publisher = publisherName2;
        String bookName = bookdata.get(bookNumber).get("Book name") + getTimeStamp();
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

        homePage = new HomePage();
        homePage.navigateToListOfBooksPage();

        listOfBooksPage = new ListOfBooksPage();
        listOfBooksPage.navigateToAddBookPage();

        addBookPage = new AddBookPage();
        Thread.sleep(2000);
        addBookPage.addNewBook(receivedDate,publisher,bookName,noOfChapters,ISBNNumber,typeOfBook,complexityLevel,
                bookColour,priority,CEReceivedDate,clientDueDate,PDMRPlanDate,stages);

        addBookPage.pressSubmitButton();
    }

    @Then("validate the bookcount changes to {string}")
    public void validate_the_bookcount_changes_to(String expectedBookCount) throws InterruptedException {

        homePage = new HomePage();
        homePage.navigateToListOfPublishersPage();

        listOfPublishersPage = new ListOfPublishersPage();
        String currentBookCount = listOfPublishersPage.getBookcount(publisherName2);

        Assert.assertEquals(currentBookCount,expectedBookCount);

    }

    @Then("also verify the delete icon vanishes")
    public void also_verify_the_delete_icon_vanishes() throws InterruptedException {

        Assert.assertFalse(listOfPublishersPage.isDeleteOptionAvailable(publisherName2), "Delete icon not present");

    }

    @Then("add another book {string} to the publisher")
    public void add_another_book_to_the_publisher(String bookNum) throws InterruptedException, IOException, InvalidFormatException {

        int bookNumber = (Integer.parseInt(bookNum)-1);

        ExcelReader excelReader = new ExcelReader();
        List<Map<String, String>> bookdata =  excelReader.getData(filePath2, sheetName2);

        String receivedDate = bookdata.get(bookNumber).get("Received Date");
        String publisher = publisherName2;
        String bookName = bookdata.get(bookNumber).get("Book name") + getTimeStamp();
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

        homePage = new HomePage();
        homePage.navigateToListOfBooksPage();

        listOfBooksPage = new ListOfBooksPage();
        listOfBooksPage.navigateToAddBookPage();

        addBookPage = new AddBookPage();
        Thread.sleep(2000);
        addBookPage.addNewBook(receivedDate,publisher,bookName,noOfChapters,ISBNNumber,typeOfBook,complexityLevel,
                bookColour,priority,CEReceivedDate,clientDueDate,PDMRPlanDate,stages);
        addBookPage.pressSubmitButton();
    }

    @Then("validate the bookcount updated to {string}")
    public void validate_the_bookcount_updated_to(String expectedBookCount) throws InterruptedException {

        homePage = new HomePage();
        homePage.navigateToListOfPublishersPage();

        listOfPublishersPage = new ListOfPublishersPage();
        String currentBookCount = listOfPublishersPage.getBookcount(publisherName2);

        Assert.assertEquals(currentBookCount,expectedBookCount);
    }

//**************************************************************************************************************

    @Given("Try to enter new publisher {string} and click save")
    public void tryToEnterNewPublisherAndClickSave(String pubName) throws InterruptedException {
        publisherName3 = pubName;

        listOfPublishersPage = new ListOfPublishersPage();
        listOfPublishersPage.checkForAddPublisherPageNavigation();

        addPublisherPage = new AddPublisherPage();
        addPublisherPage.addNewPublisherAndClick(publisherName3);
    }

    @Then("verify the availablity of getting SUCCESSFUL message and validate with {string}")
    public void verifyTheAvailablityOfGettingSUCCESSFULMessageAndValidateWith(String expected_Result) throws InterruptedException {

        boolean expectedResult = Boolean.valueOf(expected_Result);

        Assert.assertEquals(addPublisherPage.isPublisherAddedSuccessfully(publisherName3),expectedResult);

    }

//**************************************************************************************************************

    @Given("Try to enter new publisher name and click CANCEL")
    public void tryToEnterNewPublisherNameAndClickCANCEL() throws InterruptedException {

        publisherName4 = getTimeStamp();

        addPublisherPage = new AddPublisherPage();
        addPublisherPage.addPublisherButNavigateBack(publisherName4);
    }

    @Then("Verify the publisher name doesn't added up")
    public void verifyThePublisherNameDoesnTAddedUp() throws InterruptedException {

        //listOfPublishersPage = new ListOfPublishersPage();
        Assert.assertFalse(listOfPublishersPage.verifyPublisher(publisherName4));
    }

//**************************************************************************************************************

    @Given("try to provide already existing publisher name while adding new publisher")
    public void try_to_provide_already_existing_publisher_while_adding_new_publisher() throws InterruptedException {

        publisherName5 = getTimeStamp();

        addPublisherPage = new AddPublisherPage();
        addPublisherPage.addNewPublisherAndClick(publisherName5);

        listOfPublishersPage = new ListOfPublishersPage();
        listOfPublishersPage.navigateToAddPublisherPage();

        addPublisherPage = new AddPublisherPage();
        addPublisherPage.addNewPublisherAndClick(publisherName5);

    }

    @Then("press save and validate the error message is displayed")
    public void press_save_and_validate_the_error_message_is_displayed() throws InterruptedException {
        Assert.assertTrue(addPublisherPage.checkErrorMessageDisplayed());
    }

//****************************************************************************************************************

}
