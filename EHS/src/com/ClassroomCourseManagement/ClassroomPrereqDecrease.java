package com.ClassroomCourseManagement;


import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
@Test(priority=19)
public class ClassroomPrereqDecrease {
    public void ClassroomPrereqDecrease() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);


        //Click on course admin
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);


        //Click on classroom course management
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();
        Thread.sleep(1500);

        //Click on Create course
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();
        Thread.sleep(3500);
        String courseId = generator.generate(10);
        System.out.println("completed prerequisite course: " + courseId);

        //Fill in Fields
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Survey_Only_Selenium");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(1500);
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
        //Go to courses tab and enroll in course just made
        Thread.sleep(1800);
        driver.findElement(By.id("srch_fld")).sendKeys(courseId);
        Thread.sleep(1800);
//        driver.findElement(By.name("searchButton")).click();
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        Thread.sleep(4200);
        driver.findElement(By.className("viewglass")).click();
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Enroll']")).click();
        Thread.sleep(1500);

        //Click on Course Admin
        WebElement courseAdmin0 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click();", courseAdmin0);

        Thread.sleep(1500);

        //Click on classroom course management
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();
        Thread.sleep(1500);

        // Search up class just made and enrolled in
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("secondmenu")));
        actions.click();
        actions.sendKeys(courseId);
        actions.build().perform();

        driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();
        Thread.sleep(1500);

        driver.findElement(By.className("editAction")).click();
        Thread.sleep(3500);

        //manage attendees
        driver.findElement(By.xpath("//*[@id=\"crseRecord\"]/tbody/tr/td[7]/a[1]")).click();
        Thread.sleep(2000);

        //Mark as completed
        driver.findElement(By.name("emailme")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.name("selectStatus"))).selectByVisibleText("Mark Completed");
        driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();
        Thread.sleep(1500);
        WebElement lilok = driver.findElement(By.id("ok"));
        js.executeScript("arguments[0].click();", lilok);

        Thread.sleep(2500);

        //Go back to classroom course management and create another course to use as a second prerequisite

        driver.findElement(By.partialLinkText("Classroom Course Management")).click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();
        Thread.sleep(3500);
        String courseId0 = generator.generate(10);
        System.out.println("non completed prerequisite course: " + courseId0);

        //Fill in Fields
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId0);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
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
        String building0 = generator.generate(15);
        driver.findElement(By.name("detailClassBuilding")).sendKeys(building0);
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
        Thread.sleep(2500);

        //Create classroom course with both previously made classroom courses as prerequisites
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();
        Thread.sleep(1500);
        driver.findElement(By.className("editAction")).click();
        Thread.sleep(1500);


        String courseId1 = generator.generate(10);
        System.out.println("classroom course id: " + courseId1);

        //Fill in Fields
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId1);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))).selectByVisibleText(courseId + " - test classroom course");
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse2"))).selectByVisibleText(courseId0 + " - test classroom course");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
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
        String building1 = generator.generate(15);
        driver.findElement(By.name("detailClassBuilding")).sendKeys(building1);
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



        //Go to courses tab and enroll in course just made
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("srch_fld")).sendKeys(courseId1);
        driver.findElement(By.name("searchButton")).click();
        Thread.sleep(1500);
        driver.findElement(By.className("viewglass")).click();

        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Enroll']")).click();
        Thread.sleep(1500);
        String working = "";
        try {
            working = driver.findElement(By.className("errMsg")).getAttribute("innerHTML").substring(17);
        } catch (NoSuchElementException e) {
            Assert.fail("user was able to register for the course without taking the required prereqs");
        }
        if(!working.equals("Required prerequisite course: " + courseId0)) {
            Assert.fail("something is wrong with the prerequisite courses");
        }

        Thread.sleep(3500);
        driver.quit();



    }
}
