package RunnerClasses;

import PageObjects.LoginPage;
import StepDefinitions.CommonUtitlities;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static BasePackage.BaseClassFMS.driver;
import static BasePackage.BaseClassFMS.launchApplication;


@CucumberOptions(plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
                 features = {"src/test/resources/Books/AddBooks_1.feature"},
                 glue={"SD_Books","hooksPackage"},tags = "@Test")

public class FMSTestRun1 extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void startTest() throws IOException, InterruptedException {
        System.out.println("Beforeclass--Before starting testing");
        launchApplication();
        LoginPage loginPage = new LoginPage();
        loginPage.loginAsPaginationTL();
    }

}
