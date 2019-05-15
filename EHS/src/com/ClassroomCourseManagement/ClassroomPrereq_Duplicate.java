package com.ClassroomCourseManagement;


import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test(priority=18)
public class ClassroomPrereq_Duplicate {
    public void ClassroomPrereq() throws InterruptedException, IOException {
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
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Survey_Only_Selenium");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))).selectByVisibleText("Classroom1 - Classroom1");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(2500);
        driver.findElement(By.xpath("//a[@id='addClass']")).click();
        Thread.sleep(2500);
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
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
        Thread.sleep(1500);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1800);
        driver.findElement(By.xpath("//input[@id='srch_fld']")).sendKeys(courseId);
        Thread.sleep(1800);
//        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        Thread.sleep(4200);
        driver.findElement(By.className("viewglass")).click();
//        WebElement viewGlass= driver.findElement(By.xpath("//img[@class='viewglass']"));
//        JavascriptExecutor js1 = (JavascriptExecutor)driver;
//        js1.executeScript("arguments[0].click();", viewGlass);
//        driver.findElement(By.cssSelector("input[class='viewglass']")).click();
//        driver.findElement(By.xpath("//div[@class='msg_head']/table/tbody/tr/td[5]/img[@class='viewglass']")).click();//img[@class="viewglass"]
//        WebElement submitResultsEle=driver.findElement(By.xpath("//div[@class='msg_head']/table/tbody/tr/td[5]/img"));//img[@class="viewglass"]]
//        Actions builderSR = new Actions(driver);
//        Action drawActionSR = builderSR.moveToElement(submitResultsEle,16/2,16/2)  // start point
//                .click()
//                .build();
//        Thread.sleep(1800);
//        drawActionSR.perform();
//        Thread.sleep(1800);
        //System.out.println("submitResultsStr=" + submitResultsStr);
        //div[@class='msg_head']/table/tbody/tr/td[5]/img[@class='viewglass']
        Thread.sleep(2500);
//        driver.switchTo().defaultContent();
//        Thread.sleep(1900);
        //Enroll
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button")).click();
        Thread.sleep(2500);
        if(!driver.getPageSource().contains("Required prerequisite course: Classroom1")) {
            System.out.println("the user was able to register for the course without completing the required prereq");
        }

        Thread.sleep(2000);
        driver.quit();


    }

}
