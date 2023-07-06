package hooksPackage;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeTest;

import static BasePackage.BaseClassFMS.driver;

public class BasicUtils {

    @Before
    public void beforetrial(){
        System.out.println("Before trialing");
    }

    @After
    public void aftertrial(){
        System.out.println("After trialing");
    }



    @AfterStep
    public void afterstep(Scenario scenario){

        byte[] scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(scr,"image/png","imagetake");
        if(scenario.isFailed()){
            driver.get("http://pdmrindia.in/fms_pub_testing/Fms_books/main_dashboard");
        }
    }


}
