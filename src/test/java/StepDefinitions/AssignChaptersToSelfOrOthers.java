package StepDefinitions;

import PageObjects.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Map;

public class AssignChaptersToSelfOrOthers {

    public String bookName = "deBook_230510_181037";

    String emptyMSPageChapterName = "1";
    String queryChapterName = "2";
    String assignSelfChapterName = "6";
    Map<String,String> emptyMSPageChapterData;
    Map<String,String> queryChapterData;
    Map<String,String> assignSelfChapterOldData;
    Map<String,String> assignSelfChapterNewData;

    String currentlyLoggedUser;


    HomePage homePage;
    FreshStockListPage freshStockListPage;

    SelectAndAssignChaptersPage selectAndAssignChaptersPage;

    QueryCheckPage queryCheckPage;

    AssignChaptersToSelfPage assignChaptersToSelfPage;

    AssignedList assignedList;

    @Given(": Navigate to Fresh stage")
    public void navigate_to_fresh_stage() {
        homePage = new HomePage();
        homePage.navigateToFreshStage();
    }

    @Given(": Search for the book in the stockList")
    public void search_for_the_in_the_stock_list() throws InterruptedException {

        freshStockListPage = new FreshStockListPage();
        freshStockListPage.searchBook(bookName);

    }

    @Then(": Navigate to assign chapters page of that book")
    public void navigateToAssignChaptersPageOfThatBook() throws InterruptedException {
        freshStockListPage.navigateToAssignChapter(bookName);
    }

    @Given(": click the Assign button while not selecting any chapter")
    public void click_the_assign_button_while_not_selecting_any_chapter() throws InterruptedException {
        selectAndAssignChaptersPage = new SelectAndAssignChaptersPage();
        selectAndAssignChaptersPage.clickAssignSelfBtn();
    }

    @Then(": Verify the CHECKBOX ERROR Toaster message is displayed")
    public void verifyTheCHECKBOXERRORToasterMessageIsDisplayed() throws InterruptedException {
        Assert.assertTrue(selectAndAssignChaptersPage.isCheckboxErrorDisplayed());
    }

    @Given(": Search for the chapter with empty MSPages and retrieve data")
    public void searchForTheChapterWithEmptyMSPagesAndRetrieveData() throws InterruptedException {

        selectAndAssignChaptersPage = new SelectAndAssignChaptersPage();
        selectAndAssignChaptersPage.searchChapter(emptyMSPageChapterName);
        Thread.sleep(1500);
        emptyMSPageChapterData = selectAndAssignChaptersPage.getBookDatas(emptyMSPageChapterName);

    }

    @Then(": Validate the MS pages count is Zero and try to assign chapter")
    public void validate_the_ms_pages_count_is_zero_and_try_to_assign_chapter() throws InterruptedException {

        //System.out.println(emptyMSPageChapterData.get("Priority"));
        Assert.assertEquals(emptyMSPageChapterData.get("No. of MS pages"),"0");
        selectAndAssignChaptersPage.selectChapterToAssign(emptyMSPageChapterName);
        selectAndAssignChaptersPage.clickAssignSelfBtn();

    }

    @And(": verify the MS PAGE ERROR Toaster message is displayed")
    public void verifyTheMSPAGEERRORToasterMessageIsDisplayed() throws InterruptedException {
        Assert.assertTrue(selectAndAssignChaptersPage.isMSPageErrorDisplayed());
    }

    @Then(": Finally still if checkbox is selected, then deselect it.")
    public void finally_still_if_checkbox_is_selected_then_deselect_it() {
        selectAndAssignChaptersPage.deSelectChapterToAssign(emptyMSPageChapterName);
    }

    @Given(": Search for the query added chapter and retrieve data")
    public void search_for_the_query_added_chapter_and_retrieve_data() throws InterruptedException {
        selectAndAssignChaptersPage = new SelectAndAssignChaptersPage();
        selectAndAssignChaptersPage.searchChapter(queryChapterName);
        Thread.sleep(1500);
        queryChapterData = selectAndAssignChaptersPage.getBookDatas(queryChapterName);
    }

    @Then(": Make sure the MS pages count greater than ZERO")
    public void make_sure_the_ms_pages_count_greater_than_zero() {

        String MSPageCount = queryChapterData.get("No. of MS pages");
        int MSPagesCount = Integer.parseInt(MSPageCount);
        Assert.assertTrue(MSPagesCount > 0);
        //System.out.println(MSPagesCount);

    }

    @When(": Try to select and verify the PENDING QUERIES error message is displayed")
    public void tryToSelectAndVerifyThePENDINGQUERIESErrorMessageIsDisplayed() throws InterruptedException {

        selectAndAssignChaptersPage.selectChapterToAssign(queryChapterName);
        Thread.sleep(500);
        Assert.assertTrue(selectAndAssignChaptersPage.isQueryErrorDisplayed());

    }

    @When(": Navigate to the corresponding query icon And check the query")
    public void navigate_to_the_corresponding_query_icon_and_check_the_query() throws InterruptedException {

        selectAndAssignChaptersPage.navigateToQueryCheckPage(queryChapterName);

        queryCheckPage = new QueryCheckPage();
        queryCheckPage.checkTheQueries();
        queryCheckPage.navigateBack();
        Thread.sleep(2000);

        selectAndAssignChaptersPage = new SelectAndAssignChaptersPage();
        selectAndAssignChaptersPage.scrollWebTableBack();

    }

    @Then(": Select the checkbox and assign to self and verify the assign page is popUP.")
    public void select_the_checkbox_and_assign_to_self_and_verify_the_assign_page_is_pop_up() throws InterruptedException {

        selectAndAssignChaptersPage.selectChapterToAssign(queryChapterName);
        selectAndAssignChaptersPage.clickAssignSelfBtn();

        assignChaptersToSelfPage = new AssignChaptersToSelfPage();
        assignChaptersToSelfPage.confirmPageNavigation();
    }

    @Then(": After navigating to Assign page navigate backwards")
    public void after_navigating_to_assign_page_navigate_backwards() {
        assignChaptersToSelfPage.navigateBack();
    }

    @Given(": Get the CURRENT USER name")
    public void get_the_current_user_name() throws InterruptedException {
        selectAndAssignChaptersPage = new SelectAndAssignChaptersPage();
        currentlyLoggedUser = selectAndAssignChaptersPage.getCurrentlyLoggedUserName();
    }

    @Then(": Search for the chapter to assign and retrieve data")
    public void search_for_the_chapter_to_assign_and_retrieve_data() throws InterruptedException {
        selectAndAssignChaptersPage.searchChapter(assignSelfChapterName);
        Thread.sleep(1500);
        assignSelfChapterOldData = selectAndAssignChaptersPage.getBookDatas(assignSelfChapterName);
    }

    @Then(": Ensure it does not have any pending queries and MS pages greater than zero")
    public void ensure_it_does_not_have_any_pending_queries_and_ms_pages_greater_than_zero() {

        String MSPageCount = assignSelfChapterOldData.get("No. of MS pages");
        int MSPagesCount = Integer.parseInt(MSPageCount);
        Assert.assertTrue(MSPagesCount > 0);

        Assert.assertTrue(selectAndAssignChaptersPage.isChapterAssignable(assignSelfChapterName));
    }

    @When(": Assign the chapter to self")
    public void assign_the_chapter_to_self() throws InterruptedException {
        selectAndAssignChaptersPage.selectChapterToAssign(assignSelfChapterName);
        selectAndAssignChaptersPage.clickAssignSelfBtn();

        assignChaptersToSelfPage = new AssignChaptersToSelfPage();
        assignChaptersToSelfPage.selectJobTypes();
        assignChaptersToSelfPage.assignChapter();
    }

    @Then(": Validate the USER is updated to CURRENT USER")
    public void validate_the_user_is_updated_to_current_user() throws InterruptedException {
        selectAndAssignChaptersPage = new SelectAndAssignChaptersPage();
        assignSelfChapterNewData = selectAndAssignChaptersPage.getBookDatas(assignSelfChapterName);

        System.out.println(currentlyLoggedUser);
        System.out.println(assignSelfChapterNewData.get("User"));
        System.out.println(assignSelfChapterOldData.get("Process"));
        System.out.println(assignSelfChapterNewData.get("Process"));
    }

    @Then(": Navigate to ASSIGNED page")
    public void navigate_to_assigned_page() throws InterruptedException {
        Thread.sleep(2500);
        homePage = new HomePage();
        Thread.sleep(2500);
        homePage.navigateToAssignedListPage();
    }

    @Then(": verify the newly assigned chapter is added in the bucket")
    public void verify_the_newly_assigned_chapter_is_added_in_the_bucket() throws InterruptedException {
        assignedList = new AssignedList();
        assignedList.searchChapter(assignSelfChapterName);
    }

}
