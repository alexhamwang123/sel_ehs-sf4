package com.ClassroomCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
public class ClassroomPrerequisiteAndLogic {
    public void ClassroomPrerequisiteAndLogic()throws IOException,InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);
        try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
        actions.build().perform(); }
        catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String unfinishedOnline = prop.getProperty("unfinished_Online");
        String finishedOnline = prop.getProperty("finished_Online");
        String finishedChecklist = prop.getProperty("finished_Checklist");
        String finishedClassroom = prop.getProperty("finished_Classroom");


        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);


        //Click on course admin
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);


        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Classroom Course Management')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(1000);
        WebElement CreateBtn = driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
        js.executeScript("arguments[0].click()", CreateBtn);


        wait.until(ExpectedConditions.elementToBeClickable(By.id("course-category")));
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("course-category"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.id("course-fulfill"))).selectByVisibleText("Normal & Refresh");

        String courseId = generator.generate(10);
        String Building = generator.generate(11);
        System.out.println(courseId);
        driver.findElement(By.id("course-num")).sendKeys(courseId);

        //input description
        driver.findElement(By.id("input-desc")).sendKeys("This is description");

        //Completion Message
        driver.findElement(By.id("completion-message")).sendKeys("Congrats Completion");

        //input Course Number
        driver.findElement(By.id("course-title")).sendKeys("This is title");

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Classroom Details
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[2]/a")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("expiration")));
        new Select(driver.findElement(By.id("expiration"))).selectByVisibleText("6 months");



        //input Classroom Details
        wait.until(ExpectedConditions.elementToBeClickable(By.id("training-hour")));
        driver.findElement(By.id("training-hour")).sendKeys("3");
        driver.findElement(By.id("credit-hour")).sendKeys("3");

        //CLick Prereq Btn
        driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary'][contains(text(),'Select')]")).click();

        //Search the course
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
        driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys(unfinishedOnline);

        //CLick the result
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(1000);

        //CLick Prereq Btn
        driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary'][contains(text(),'Select')]")).click();

        //Search the course
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
        driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys(finishedChecklist);

        //CLick the result
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(1000);


        //CLick Prereq Btn
        driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary'][contains(text(),'Select')]")).click();

        //Search the course
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
        driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys(finishedClassroom);

        //CLick the result
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(1000);



        //Click Save
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Offer Schedule
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Management')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[3]/a")).click();

        //Click Add offer
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/button[1]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/button[1]")).click();

        //Click Site Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]")).click();

        //Search SCV
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys("SCV");

        //Click SCV
        driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")).click();


        //Building
        driver.findElement(By.id("building")).sendKeys(Building);
        driver.findElement(By.id("room")).sendKeys("001");
        driver.findElement(By.id("class-size")).clear();
        driver.findElement(By.id("class-size")).sendKeys("5");

        //Click Edit Btn of time schedule
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[1]/td[6]/div[1]/button[2]")).click();
        //Click Calendar
        WebElement Calendar =driver.findElement(By.id("date"));
        js.executeScript("arguments[0].click()", Calendar);
        Thread.sleep(2000);
        System.out.println("CheckPoint 1");

        //Click Year(need twice click to select year)
        WebElement YearArea= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/div/div[2]/header/span[2]"));
        js.executeScript("arguments[0].click()", YearArea);
        Thread.sleep(2000);
        System.out.println("CheckPoint 2");
        WebElement YearArea01=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/div/div[3]/header/span[2]"));
        js.executeScript("arguments[0].click()", YearArea01);
        Thread.sleep(2000);
        System.out.println("CheckPoint 3");
        //click year 2029
        WebElement Btn2029= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/div/div[4]/span[10]"));
        js.executeScript("arguments[0].click()", Btn2029);
        Thread.sleep(2000);
        System.out.println("CheckPoint 4");

        //Click 2029Mar
        WebElement Btn2029March= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/div/div[3]/span[3]"));
        js.executeScript("arguments[0].click()", Btn2029March);
        Thread.sleep(2000);
        System.out.println("CheckPoint 5");

        WebElement Btn2029Mar01=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/div/div[2]/div/span[12]"));
        js.executeScript("arguments[0].click()", Btn2029Mar01);
        Thread.sleep(2000);
        System.out.println("CheckPoint 6");

        //input time
        WebElement TimeBtn=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/button"));
        js.executeScript("arguments[0].click()", TimeBtn);
        Thread.sleep(1000);
        Thread.sleep(1000);
        WebElement CLickPlus= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/div/div/div/div[1]/button[1]"));
        js.executeScript("arguments[0].click()", CLickPlus);

        //click save
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/table/tbody/tr/td[6]/div/button[1]")).click();
        Thread.sleep(1000);
        //Click the Save Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")));
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();


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

        if(!working.contains(unfinishedOnline.substring(0,4))) {
            Assert.fail("user was ale to register for the course without completing the prereq");
        }
        Thread.sleep(1500);
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        WebElement courseAdmin2 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js2.executeScript("arguments[0].click()", courseAdmin2);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(courseId);

        //Click result
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")).click();

        Thread.sleep(2000);
        //Click Classroom Details
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[2]/a")).click();
        Thread.sleep(2000);
        //Delete the existing first Prereq
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[2]/div[5]/div/div/div[1]/div/div/div/button[2]")).click();

        //CLick Prereq Btn
        driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary'][contains(text(),'Select')]")).click();

        //Search the course
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
        driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys(finishedOnline);

        //CLick the result
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(1000);

        //Click Save
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        Thread.sleep(1000);

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
