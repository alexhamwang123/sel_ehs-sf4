package com.ClassroomCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test
public class CreateOfferingRegisterWithMgrUserAndEmail {
    public void CreateOfferingRegisterWithMgrUserAndEmail() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String testnormuser = prop.getProperty("testnormuser");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();
        Thread.sleep(3500);
        String courseId = generator.generate(10);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(3500);
        driver.findElement(By.xpath("//a[@id='addClass']")).click();
        Thread.sleep(2500);
        driver.findElement(By.id("site_radio")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("selectBtnSite")).click();
        Thread.sleep(3500);
        driver.findElement(By.id("searchName")).sendKeys("SCV");
        Thread.sleep(4000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")).click();
        Thread.sleep(1500);
        String building = generator.generate(15);
        driver.findElement(By.name("detailClassBuilding")).sendKeys(building);
        driver.findElement(By.name("detailClassRoom")).sendKeys("room01");
        driver.findElement(By.name("detailClassMaxSize")).sendKeys("5");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd")).click();
        Thread.sleep(1500);
        js.executeScript("document.getElementById('TimeAdd_datepicker').value='Dec 25,2030'");
        new Select(driver.findElement(By.name("detailClassStartHourSelect"))).selectByVisibleText("06");
        new Select(driver.findElement(By.name("detailClassStartMinuteSelect"))).selectByVisibleText("45");
        new Select(driver.findElement(By.id("detailClassDuration"))).selectByVisibleText("13");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd_Save")).click();
        Thread.sleep(1500);

        driver.findElement(By.id("saveClassCourse")).click();
        Thread.sleep(4600);
        //Let is log out and login with  mgr at this time.
        //        driver.quit();
//		Thread.sleep(2000);
        WebElement HomeElement = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
        JavascriptExecutor js3 = (JavascriptExecutor)driver;

        js3.executeScript("arguments[0].click();", HomeElement);
        Thread.sleep(4600);

        WebElement Logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()",Logout);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("username")).sendKeys(testnormuser);
        driver.findElement(By.id("password")).sendKeys(testnormuser);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//input[@id='srch_fld']")).sendKeys(courseId2);
//        driver.findElement(By.partialLinkText("Courses")).click();
//        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys(courseId);
        Thread.sleep(1800);

        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();
        Thread.sleep(2500);
//        driver.findElement(By.id("srch_fld")).sendKeys(courseId);
//        driver.findElement(By.name("searchButton")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.className("viewglass")).click();
//        Thread.sleep(1500);
        //Click Enroll Btn
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button")).click();
        Thread.sleep(2500);
        //Try find Cancel Btn
        try {
            WebElement cancelElement = driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button"));
            cancelElement.click();
        } catch (NoSuchElementException e) {
            Assert.fail("was not able to enroll in the course");
        }
        Thread.sleep(3500);

//        > log out and log in as the Admin
        WebElement HomeElement2 = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
        JavascriptExecutor js4 = (JavascriptExecutor)driver;

        js4.executeScript("arguments[0].click();", HomeElement2);
        Thread.sleep(4600);

        WebElement Logout1=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()",Logout1);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);
        WebElement courseAdmin1 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js2 = (JavascriptExecutor)driver;

        js2.executeScript("arguments[0].click();", courseAdmin1);

        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();
        Thread.sleep(1500);
//        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();
//        Thread.sleep(3500);
        WebElement inputOne = driver.findElement(By.id("secondmenu")).findElement(By.id("srch_fld"));
//        WebElement inputOne = driver.findElement(By.xpath("//input[@type='search'][@id='srch_fld']"));

//        WebElement inputOne = driver.findElement(By.className("form-control"));

// to set focus?
        inputOne.click();
        // erase any existing value (because clear does not send any events
//        for (int i = 0; i < inputOne.getAttribute("value").length(); i++) {
//            System.out.print("i=" + i);
//            inputOne.sendKeys(Keys.BACK_SPACE);
//        }

        inputOne.sendKeys(courseId);
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@type='submit'][@value='Go']")).click();


        //        Survey_Only_New
       // new Select(driver.findElement(By.id("secondmenu")).findElement(By.id("searchCategory"))).selectByVisibleText("Survey_Only_New");//By.xpath("//option[@value='f9bbb962d5b1aa58b2115a7bf3b4c9a8']")));
        System.out.println("Test 123");
        Thread.sleep(1500);
        System.out.println("Test 456");
//        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div/div/input")).click();
//        userRecord
//        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div/div/input")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//table[@id=\"userRecord\"]/tbody/tr/td/a")).click();
        //myResults = driver.findElements(By.xpath("//table[@id=\"userRecord\"]/tbody/tr/td/a"));
//        Thread.sleep(1500);
//> go to Classroom Course Management, find the course, find the offering
        Thread.sleep(3500);
        //driver.findElement(By.cssSelector("a[href*='EditClassroomClass']")).click();
        driver.findElement(By.cssSelector("a[href*='attendList']")).click();
        Thread.sleep(1500);
//        driver.findElement(By.xpath("//button[@value=\"Edit\"][@type=\"button\"]")).click();
//        Thread.sleep(1500);
//        js2.executeScript("document.getElementById('TimeAdd_datepicker').value='Dec 27,2030'");
//        new Select(driver.findElement(By.name("detailClassStartHourSelect"))).selectByVisibleText("08");
//        new Select(driver.findElement(By.name("detailClassStartMinuteSelect"))).selectByVisibleText("15");
//        new Select(driver.findElement(By.id("detailClassDuration"))).selectByVisibleText("4");
////        Thread.sleep(1500);
////        driver.findElement(By.cssSelector("a[href*='EditClassroomClass']")).click();
////        Thread.sleep(1500);
//        Thread.sleep(1500);
//        driver.findElement(By.id("TimeAdd_Save")).click();
//        Thread.sleep(1500);
//
//        driver.findElement(By.id("saveClassCourse")).click();
//        Thread.sleep(4600);
//> change the location and the time, and Save
//                > answer "Yes" to the popup msg ""Notify student by email?"
        Thread.sleep(1500);
        //driver.quit();



    }
}
