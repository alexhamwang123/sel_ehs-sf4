package com.EHS.ChecklistManagement;


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

@Test
public class PrereqDecreaseChecklist {
    public void PrereqDecreaseChecklist() throws IOException, InterruptedException{
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("https://twn:WrongAdeeDow2-@demo.accentrixus.com:8330");

        driver.manage().window().maximize();

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);


        //Click on course admin
        WebElement courseAdmin = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
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
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("addClass")).click();
        Thread.sleep(2500);
        driver.findElement(By.id("site_radio")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("selectBtnSite")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("searchName")).sendKeys("SCV");
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")).click();
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
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("srch_fld")).sendKeys(courseId);
        driver.findElement(By.name("searchButton")).click();
        Thread.sleep(1500);
        driver.findElement(By.className("viewglass")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Enroll']")).click();
        Thread.sleep(1500);

        //Click on Course Admin
        WebElement courseAdmin0 = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
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

        Thread.sleep(1500);

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
        driver.findElement(By.id("addClass")).click();
        Thread.sleep(2500);
        driver.findElement(By.id("site_radio")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("selectBtnSite")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("searchName")).sendKeys("SCV");
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")).click();
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

        //Create checklist with both previously made classroom courses as prerequisites
        driver.findElement(By.partialLinkText("Checklist Management")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(3500);
        String courseId1 = generator.generate(10);
        System.out.println("Checklist id: " + courseId1);
        driver.findElement(By.name("detailCheckListCode")).sendKeys(courseId1);
        new Select(driver.findElement(By.id("detailCategoryType"))).selectByVisibleText("Survey Only");
        new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
        new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        new Select(driver.findElement(By.id("detailCoursePrerequisitesCourse1"))).selectByVisibleText(courseId + " - test classroom course");
        new Select(driver.findElement(By.id("detailCoursePrerequisitesCourse2"))).selectByVisibleText(courseId0 + " - test classroom course");
        Thread.sleep(1000);
        driver.findElement(By.id("saveBtn")).click();
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("detailCheckListTitle")).sendKeys("test checklist title");
        driver.findElement(By.id("detailCheckListHeader")).sendKeys("test checklist header");
        driver.findElement(By.id("detailCheckListDescription")).sendKeys("test checklist description");
        driver.findElement(By.id("detailCheckListFooter")).sendKeys("test checklist footer");
        driver.findElement(By.id("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("createContent")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Create']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("saveBtn")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(3500);
        driver.findElement(By.id("langIsViewable")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("detailIsActive")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);

        //Go back to courses tab and try to enroll in the checklist just made
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("srch_fld")).sendKeys(courseId1);
        driver.findElement(By.name("searchButton")).click();
        Thread.sleep(1500);
        driver.findElement(By.className("viewglass")).click();
        Thread.sleep(1500);
        String working = "";
        try {
            working = driver.findElement(By.xpath("//*[@id=\"errorMsg_data\"]")).getAttribute("innerHTML");
        } catch (NoSuchElementException e) {
            Assert.fail("user was able to register for the course without taking the required prereqs");
        }
        if(!working.equals("Required prerequisite course:" + courseId0)) {
            Assert.fail("something is wrong with the prerequisite courses");
        }

        driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();
        Thread.sleep(3500);
        driver.quit();





    }
}






