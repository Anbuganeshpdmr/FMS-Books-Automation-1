package SD_Books;

import PageObjects.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.DataTable.TableConverter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static MandReqdUtilities.BasicRequirements.*;

public class AddBooks_1 {

//**************************************************************************************************************



    HomePage homePage;
    ListOfPublishersPage listOfPublishersPage;
    AddPublisherPage addPublisherPage;
    ListOfBooksPage listOfBooksPage;
    AddBookPage addBookPage;
    Map<String, String> bookDatasProvided, bookDatasActual;
    String currentBookName;
    String fileLocation, fileName;
    StockListPage stockListPage;


//**********************************************************************************************************************************

    @Given("navigate to Add books page")
    public void navigate_to_add_books_page () throws InterruptedException {
    homePage = new HomePage();
}

    @Then("add the valid book datas and wait")
    public void add_the_valid_book_datas_and_wait (DataTable dataTable) throws InterruptedException {

    Thread.sleep(2000);

    int bookNumber = 0;
    List<Map<String, String>> bookdata = dataTable.asMaps(String.class, String.class);

    bookDatasProvided = new HashMap<>();

    String receivedDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("Received Date").trim()));
    String publisherIndex = bookdata.get(bookNumber).get("Publisher").trim();
    currentBookName = bookdata.get(bookNumber).get("Book name") + getTimeStamp();
    String noOfChapters = bookdata.get(bookNumber).get("No of chapters");
    String ISBNNumber = getTimeStampNumber();
    String typeOfBook = bookdata.get(bookNumber).get("Type of book");
    String complexityLevel = bookdata.get(bookNumber).get("Complexity level");
    String bookColour = bookdata.get(bookNumber).get("Book color");
    String priority = bookdata.get(bookNumber).get("Priority");
    String CEReceivedDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("CE Received Date").trim()));
    String clientDueDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("Client Due Date").trim()));
    String PDMRPlanDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("PDMR Plan Date").trim()));
    String stages = bookdata.get(bookNumber).get("Stages");

    homePage.navigateToListOfBooksPage();

    listOfBooksPage = new ListOfBooksPage();
    listOfBooksPage.navigateToAddBookPage();

    addBookPage = new AddBookPage();
    Thread.sleep(2000);
    addBookPage.addNewBook(receivedDate, publisherIndex, currentBookName, noOfChapters, ISBNNumber, typeOfBook, complexityLevel,
            bookColour, priority, CEReceivedDate, clientDueDate, PDMRPlanDate, stages);

    String publisher = addBookPage.getSelectedPublisherName();

    bookDatasProvided.put("Received Date", receivedDate);
    bookDatasProvided.put("Publisher", publisher);
    bookDatasProvided.put("Book name", currentBookName);
    bookDatasProvided.put("No of chapters", noOfChapters);
    bookDatasProvided.put("ISBN Number", ISBNNumber);
    bookDatasProvided.put("Type of book", typeOfBook);
    bookDatasProvided.put("Complexity level", complexityLevel);
    bookDatasProvided.put("Book color", bookColour);
    bookDatasProvided.put("Priority", priority);
    bookDatasProvided.put("CE Received Date", CEReceivedDate);
    bookDatasProvided.put("Client Due Date", clientDueDate);
    bookDatasProvided.put("PDMR Plan Date", PDMRPlanDate);
    bookDatasProvided.put("Stages", stages);

}

    @Given("add and submit the book without uploading the file")
    public void add_and_submit_the_book_without_uploading_the_file () throws InterruptedException {
    addBookPage.pressSubmitButton();}

    @Then("verify by matching the datas of the book are correct with provided details")
    public void verify_by_matching_the_datas_of_the_book_are_correct_with_provided_details () throws
    InterruptedException {

    //Datas extracted from the Table itself. Keys are actual table header Texts.
    bookDatasActual = listOfBooksPage.getBookDatas(currentBookName);

    Assert.assertEquals(bookDatasActual.get("Publisher"), bookDatasProvided.get("Publisher"));
    Assert.assertEquals(bookDatasActual.get("Book Name"), bookDatasProvided.get("Book name"));
    Assert.assertEquals(bookDatasActual.get("No of Chapters"), bookDatasProvided.get("No of chapters"));
    Assert.assertEquals(bookDatasActual.get("ISBN No"), bookDatasProvided.get("ISBN Number"));
    Assert.assertEquals(bookDatasActual.get("Type Book"), bookDatasProvided.get("Type of book"));
    Assert.assertEquals(bookDatasActual.get("Complexity level"), bookDatasProvided.get("Complexity level"));
    Assert.assertEquals(bookDatasActual.get("Book Colour"), bookDatasProvided.get("Book color"));
    //Assert.assertEquals(bookDatasActual.get("Client Due Date"),bookDatasProvided.get("Client Due Date"));
    //Assert.assertEquals(bookDatasActual.get("PDMR Date"),bookDatasProvided.get("PDMR Plan Date"));
}

    @Then("validate the presence of mandatory icons")
    public void validate_the_presence_of_mandatory_icons () throws InterruptedException {
    Assert.assertTrue(listOfBooksPage.isEditOptionAvailable(currentBookName));
    Assert.assertTrue(listOfBooksPage.isDeleteOptionAvailable(currentBookName));
    Assert.assertTrue(listOfBooksPage.isViewChaptersOptionAvailable(currentBookName));
}

    @Then("download icon should not present")
    public void download_icon_should_not_present () throws InterruptedException {
    Assert.assertFalse(listOfBooksPage.isDownloadOptionAvailable(currentBookName));

}

//**************************************************************************************************************

    @Given("add new book with the details")
    public void add_new_book_with_the_details(DataTable dataTable) throws InterruptedException {

        Thread.sleep(2000);

        int bookNumber = 0;
        List<Map<String, String>> bookdata =  dataTable.asMaps(String.class, String.class);

        //bookDatasProvided = new HashMap<>();

        String receivedDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("Received Date").trim()));
        String publisherIndex = bookdata.get(bookNumber).get("Publisher").trim();
        currentBookName = bookdata.get(bookNumber).get("Book name") + getTimeStamp();
        String noOfChapters = bookdata.get(bookNumber).get("No of chapters");
        String ISBNNumber = getTimeStampNumber();
        String typeOfBook = bookdata.get(bookNumber).get("Type of book");
        String complexityLevel = bookdata.get(bookNumber).get("Complexity level");
        String bookColour = bookdata.get(bookNumber).get("Book color");
        String priority = bookdata.get(bookNumber).get("Priority");
        String CEReceivedDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("CE Received Date").trim()));
        String clientDueDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("Client Due Date").trim()));
        String PDMRPlanDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("PDMR Plan Date").trim()));
        String stages = bookdata.get(bookNumber).get("Stages");

      /*  bookDatasProvided.put("Received Date",receivedDate);
        bookDatasProvided.put("Publisher",publisher);
        bookDatasProvided.put("Book name",currentBookName);
        bookDatasProvided.put("No of chapters",noOfChapters);
        bookDatasProvided.put("ISBN Number",ISBNNumber);
        bookDatasProvided.put("Type of book",typeOfBook);
        bookDatasProvided.put("Complexity level",complexityLevel);
        bookDatasProvided.put("Book color",bookColour);
        bookDatasProvided.put("Priority",priority);
        bookDatasProvided.put("CE Received Date",CEReceivedDate);
        bookDatasProvided.put("Client Due Date",clientDueDate);
        bookDatasProvided.put("PDMR Plan Date",PDMRPlanDate);
        bookDatasProvided.put("Stages",stages);*/

        homePage = new HomePage();
        homePage.navigateToListOfBooksPage();

        listOfBooksPage = new ListOfBooksPage();
        listOfBooksPage.navigateToAddBookPage();

        addBookPage = new AddBookPage();
        Thread.sleep(2000);
        addBookPage.addNewBook(receivedDate,publisherIndex,currentBookName,noOfChapters,ISBNNumber,typeOfBook,complexityLevel,
                bookColour,priority,CEReceivedDate,clientDueDate,PDMRPlanDate,stages);

        //System.out.println(addBookPage.getSelectedPublisherName());

    }

    @Given("add book with valid file from location and submit")
    public void add_book_with_valid_file_from_location_and_submit(DataTable dataTable) throws InterruptedException {

        List<List<String>> fileDetails = dataTable.asLists(String.class);

        fileLocation = fileDetails.get(0).get(0);
        fileName = fileDetails.get(0).get(1);
        String completeFilePath = (fileLocation + fileName);


        addBookPage.addFileForNewBook(completeFilePath);
        addBookPage.pressSubmitButton();

    }

    @Then("verify the download icon and download the file")
    public void verifyTheDownloadIconAndDownloadTheFile() throws InterruptedException {

        Assert.assertTrue(listOfBooksPage.isDownloadOptionAvailable(currentBookName));
        listOfBooksPage.downloadFile(currentBookName);

    }

    @Then("Ensure the downloaded file is as same as uploaded")
    public void ensure_the_downloaded_file_is_as_same_as_uploaded() throws InterruptedException {
        Thread.sleep(2000);
        String downloadedFileName = getRecentlyAddedFileName();
        Assert.assertEquals(downloadedFileName, fileName);
    }

//**************************************************************************************************************

    @Given("add new book with stage {string} details and submit")
    public void addNewBookWithStageDetailsAndSubmit(String stage,DataTable dataTable) throws InterruptedException {

        homePage = new HomePage();

        Thread.sleep(2000);

        int bookNumber = 0;
        List<Map<String, String>> bookdata = dataTable.asMaps(String.class, String.class);

        bookDatasProvided = new HashMap<>();

        String receivedDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("Received Date").trim()));
        String publisherIndex = bookdata.get(bookNumber).get("Publisher").trim();
        currentBookName = bookdata.get(bookNumber).get("Book name") + getTimeStamp();
        String noOfChapters = bookdata.get(bookNumber).get("No of chapters");
        String ISBNNumber = getTimeStampNumber();
        String typeOfBook = bookdata.get(bookNumber).get("Type of book");
        String complexityLevel = bookdata.get(bookNumber).get("Complexity level");
        String bookColour = bookdata.get(bookNumber).get("Book color");
        String priority = bookdata.get(bookNumber).get("Priority");
        String CEReceivedDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("CE Received Date").trim()));
        String clientDueDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("Client Due Date").trim()));
        String PDMRPlanDate = getDate(Integer.parseInt(bookdata.get(bookNumber).get("PDMR Plan Date").trim()));
        //String stages = addedStage;

        homePage.navigateToListOfBooksPage();

        listOfBooksPage = new ListOfBooksPage();
        listOfBooksPage.navigateToAddBookPage();

        addBookPage = new AddBookPage();
        Thread.sleep(2000);
        addBookPage.addNewBook(receivedDate, publisherIndex, currentBookName, noOfChapters, ISBNNumber, typeOfBook, complexityLevel,
                bookColour, priority, CEReceivedDate, clientDueDate, PDMRPlanDate, stage.trim());
        addBookPage.pressSubmitButton();
    }

    @And("verify the presence in Fresh {string} AuthorRevisions {string} printWeb {string} and wordConversion {string}")
    public void verifyThePresenceInFreshAuthorRevisionsPrintWebAndWordConversion(String expInFresh, String expInAuthRev, String expInPrintWeb, String wordConv) throws InterruptedException {
//**
        homePage.freshPaginationSL();
        stockListPage = new StockListPage();
        Assert.assertEquals(stockListPage.isBoookAvailable(currentBookName),Boolean.valueOf(expInFresh));
        Thread.sleep(1000);

        homePage.authorCorrectionPaginationSL();
        Assert.assertEquals(stockListPage.isBoookAvailable(currentBookName),Boolean.valueOf(expInAuthRev));
        Thread.sleep(1000);

        homePage.printWebPaginationSL();
        Assert.assertEquals(stockListPage.isBoookAvailable(currentBookName),Boolean.valueOf(expInPrintWeb));
        Thread.sleep(1000);

        homePage.wordConversionPaginationSL();
        Assert.assertEquals(stockListPage.isBoookAvailable(currentBookName),Boolean.valueOf(wordConv));
        Thread.sleep(1000);

        homePage.returnToDashboard();
    }


//**************************************************************************************************************
//**************************************************************************************************************
//**************************************************************************************************************
//**************************************************************************************************************
//**************************************************************************************************************
//**************************************************************************************************************
//**************************************************************************************************************
//**************************************************************************************************************
}
