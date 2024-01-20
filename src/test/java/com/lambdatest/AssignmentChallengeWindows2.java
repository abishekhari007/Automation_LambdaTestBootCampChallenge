package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class AssignmentChallengeWindows2 {
	
	private RemoteWebDriver driver;
    private String Status = "passed";
    
    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "LINUX");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "abishekh");
        ltOptions.put("accessKey", "roQgM6HyphXEIBIO0wjKxXwPElhyp8lU83JJx26Y35Tpk4sA3h");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("resolution", "1920x1080");
        ltOptions.put("headless", true);
        ltOptions.put("network", true);
        ltOptions.put("build", "AbishekH_LambdaTest_BootCamp");
        ltOptions.put("project", "AbishekH_LambdaTest_BootCamp");
        String[] customTags = {"LambdaTest", "BootCamp"};
        ltOptions.put("tags", customTags);
        ltOptions.put("tunnel", false);
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("driver_version", "104.0");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        caps.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
    }
    
    String openingPageURL = "";
    String currentPageURL = "";

    @Test
    public void basicTest() throws InterruptedException {
        String spanText;
        System.out.println("Loading Url");

        driver.get("https://www.lambdatest.com");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        WebElement seeAllIntrgrationsElement = driver.findElement(By.xpath("//img[@title='See All Integrations']/parent::a[text()='See All Integrations']"));
        System.out.println(seeAllIntrgrationsElement.isDisplayed());
        openingPageURL = driver.getCurrentUrl();
        System.out.println("Opening Page URL "+ openingPageURL);
        
        //WebElement seeAllIntrgrationsElement = driver.findElement(By.xpath("a[text()='See All Integrations']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seeAllIntrgrationsElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", seeAllIntrgrationsElement);

        Thread.sleep(10000); 
        //seeAllIntrgrationsElement.click();
        
        currentPageURL = driver.getCurrentUrl();
        
        System.out.println("Current Page URL "+ currentPageURL);

        String ExpectedTitle = "https://www.lambdatest.com/integrations";
        Assert.assertEquals(ExpectedTitle, currentPageURL);
        Status = "passed";
    }
    
    
    
    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Adding Test Result Opening URL "+openingPageURL+" and URL after going to integrations page is "+currentPageURL+" and URL did not open in new tab, For Windows Execution and Closing Browser\", \"level\": \"info\"}}");
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}


//https://automation.lambdatest.com/test?build=23854323&testID=GXPC5-XZQK7-N2956-PAUHK
//https://automation.lambdatest.com/test?build=23854323&testID=PXYJV-BQDWC-RHKTS-AGASB