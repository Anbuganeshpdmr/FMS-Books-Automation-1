package RunnerClasses;

import PageObjects.LoginPage;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

import java.io.IOException;


@CucumberOptions(plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = "src/test/resources/AssignChapterToSelfOrOthers.feature",
        glue={"StepDefinitions","hooksPackage"}
        )

public class NewRun2 extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void beforeTesting() throws IOException {
        System.out.println("Beforeclass--Before starting testing");
        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername("1639");
        loginPage.enterPassword("1639");
        loginPage.clickLogin();
    }


}
