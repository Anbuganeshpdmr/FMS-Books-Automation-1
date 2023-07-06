package RunnerClasses;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static BasePackage.BaseClassFMS.launchApplication;


public class TrialRunnerClass {

    @BeforeClass
    public void startTest() throws IOException, InterruptedException {
        System.out.println("Beforeclass--Before starting testing");
        launchApplication();
        LoginPage loginPage = new LoginPage();
        loginPage.loginAsPaginationTL();
    }


    @Test(description = "Hello1")
    public void run() throws InterruptedException {
        Reporter.log("Msg1");
        Thread.sleep(3000);
        Reporter.log("Msg2");
        HomePage homePage = new HomePage();
        Reporter.log("Msg3");
        homePage.navigateToListOfBooksPage();
    }
}
