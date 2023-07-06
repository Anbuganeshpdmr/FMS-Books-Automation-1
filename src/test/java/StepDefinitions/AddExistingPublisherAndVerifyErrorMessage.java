package StepDefinitions;

import PageObjects.AddPublisherPage;
import PageObjects.ListOfPublishersPage;
import PageObjects.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AddExistingPublisherAndVerifyErrorMessage {

    HomePage homePage;
    ListOfPublishersPage listOfPublishersPage;
    AddPublisherPage addPublisher;

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

    @When("Try to enter already existing publisher")
    public void try_to_enter_already_existing_publisher() throws InterruptedException {
        addPublisher = new AddPublisherPage();
        addPublisher.addNewPublisherAndClick("Patriot");
    }

    @Then("Error message be displayed")
    public void error_message_be_displayed() throws InterruptedException {
        Assert.assertTrue(addPublisher.checkErrorMessageDisplayed());
    }

    @And("Navigate back by pressing CLOSE button")
    public void navigate_back_by_pressing_close_button() throws InterruptedException {
        //addPublisher.navigateBack();
    }
}
