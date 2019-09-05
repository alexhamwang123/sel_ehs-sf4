package com.ClassroomCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
@Test
public class ClassroomPrerequisiteORLogic {
    public void ClassroomPrerequisiteORLogic()throws IOException,InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
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
        String unfinishedOnline = prop.getProperty("unfinished_Online");
        String finishedOnline = prop.getProperty("finished_Online");
        String unfinishedChecklist = prop.getProperty("Unfinished_Checklist");
        String unfinishedClassroom = prop.getProperty("unfinished_Classroom");


        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);


        //Click on course admin
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);


        //Click on classroom course management
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();


        //Click on Create course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();

        String courseId = generator.generate(10);
        System.out.println("completed prerequisite course: " + courseId);

        //Fill in Fields
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))).selectByVisibleText(unfinishedOnline);
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse2"))).selectByVisibleText(unfinishedChecklist);
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse3"))).selectByVisibleText(unfinishedClassroom);

        //Click And Btn turn it into Or
        driver.findElement(By.xpath("//div[@id='ao-toggle1']//button[@class='btn btn-primary'][contains(text(),'and')]")).click();
        driver.findElement(By.xpath("//div[@id='ao-toggle2']//button[@class='btn btn-primary'][contains(text(),'and')]")).click();

        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");



        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@id='addClass']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='addClass']")).click();

        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("site_radio"))));
        Thread.sleep(1000);
        driver.findElement(By.id("site_radio")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("selectBtnSite"))));
        Thread.sleep(1000);
        driver.findElement(By.id("selectBtnSite")).click();

        Thread.sleep(3500);
        driver.findElement(By.id("searchName")).sendKeys("SCV");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='submit'][value='Search']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
        Thread.sleep(3500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")).click();
        Thread.sleep(1500);
        String building = generator.generate(15);
        driver.findElement(By.name("detailClassBuilding")).sendKeys(building);
        driver.findElement(By.name("detailClassRoom")).sendKeys("room01");
        driver.findElement(By.name("detailClassMaxSize")).sendKeys("5");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("TimeAdd"))));
        Thread.sleep(1000);
        driver.findElement(By.id("TimeAdd")).click();
        Thread.sleep(2500);
        js.executeScript("document.getElementById('TimeAdd_datepicker').value='Dec 25,2030'");
        new Select(driver.findElement(By.name("detailClassStartHourSelect"))).selectByVisibleText("06");
        new Select(driver.findElement(By.name("detailClassStartMinuteSelect"))).selectByVisibleText("45");
        new Select(driver.findElement(By.id("detailClassDuration"))).selectByVisibleText("13");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd_Save")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveClassCourse"))));
        Thread.sleep(1000);
        driver.findElement(By.id("saveClassCourse")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();
        Thread.sleep(1500);
        String working = "";
        try {
            working = driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[1]/div")).getAttribute("innerHTML");
            System.out.println(working);
        } catch (NoSuchElementException e) {
            Assert.fail("was able to register for the course without completing the prereq");
        }

        if(!working.contains(unfinishedOnline.substring(0,9))) {
            Assert.fail("user was ale to register for the course without completing the prereq");
        }
        Thread.sleep(1500);
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        WebElement courseAdmin2 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js2.executeScript("arguments[0].click()", courseAdmin2);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys(courseId);

        //CLick Go Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='btn btn-primary']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText(courseId))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText(courseId)).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))).selectByVisibleText(finishedOnline);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();
        Thread.sleep(1000);
        for(String handle:driver.getWindowHandles()){
            driver.switchTo().window(handle);
        }
        Thread.sleep(1000);

        String Required= driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[1]")).getAttribute("innerHTML");
        if(Required.contains("Required prerequisite course")) {
            Assert.fail("Prerequisite still exists when all the courses are complete ");
        }
        else{
            System.out.println("The test is successful");
        }

        driver.quit();
    }
}
