package StepDefinitions;

import PageObjects.AddPublisherPage;
import PageObjects.HomePage;
import PageObjects.ListOfPublishersPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AddNewPublisherAndVerifyPubPresence {

    HomePage homePage = new HomePage();
    ListOfPublishersPage listOfPublishersPage = new ListOfPublishersPage();
    AddPublisherPage addPublisher = new AddPublisherPage();

    boolean publisherAvailability;

    @Given("Navigate to List of Publishers page")
    public void navigate_to_list_of_publishers_page() {
        homePage.navigateToListOfPublishersPage();
    }

    @And("Navigate then to Add Publisher page")
    public void navigate_then_to_add_publisher_page() throws InterruptedException {
        listOfPublishersPage.navigateToAddPublisherPage();
    }

    @When("Try to enter new publisher name and click save")
    public void try_to_enter_new_publisher_name_and_click_save() throws InterruptedException {
        addPublisher.addNewPublisherAndClick("Patriot007");
    }

    @Then("Search the same name in search bar")
    public void search_the_same_name_in_search_bar() throws InterruptedException {
        publisherAvailability = listOfPublishersPage.verifyPublisher("Patriot007");
    }

    @And("The searched name should be available")
    public void the_searched_name_should_be_available() {
        Assert.assertTrue(publisherAvailability);
    }
}
