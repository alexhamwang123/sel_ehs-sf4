package com.ChecklistManagement;


import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
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
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class PrereqDecreaseChecklist {
    public void PrereqDecreaseChecklist() throws IOException, InterruptedException{
        System.setProperty("webdriver.chrome.driver", "chromedriver");

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
        driver.manage().window().maximize();
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

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
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/div/div/div/button")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/div/div/div/button")).click();

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
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[6]/div/button[2]")).click();
        //Click Calendar
        WebElement Calendar =driver.findElement(By.id("date"));
        js.executeScript("arguments[0].click()", Calendar);
        Thread.sleep(2000);
        System.out.println("CheckPoint 1");

        //Click Year(need twice click to select year)
        WebElement YearArea= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[1]/div/div/div[2]/header/span[2]"));
        js.executeScript("arguments[0].click()", YearArea);
        Thread.sleep(2000);
        System.out.println("CheckPoint 2");
        WebElement YearArea01=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[1]/div/div/div[3]/header/span[2]"));
        js.executeScript("arguments[0].click()", YearArea01);
        Thread.sleep(2000);
        System.out.println("CheckPoint 3");
        //click year 2029
        WebElement Btn2029= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[1]/div/div/div[4]/span[10]"));
        js.executeScript("arguments[0].click()", Btn2029);
        Thread.sleep(2000);
        System.out.println("CheckPoint 4");

        //Click 2029Mar
        WebElement Btn2029March= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[1]/div/div/div[3]/span[3]"));
        js.executeScript("arguments[0].click()", Btn2029March);
        Thread.sleep(2000);
        System.out.println("CheckPoint 5");

        WebElement Btn2029Mar01=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[1]/div/div/div[2]/div/span[12]"));
        js.executeScript("arguments[0].click()", Btn2029Mar01);
        Thread.sleep(2000);
        System.out.println("CheckPoint 6");

        //input time
        WebElement TimeBtn=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[2]/div/div/button"));
        js.executeScript("arguments[0].click()", TimeBtn);
        Thread.sleep(1000);
        Thread.sleep(1000);
        WebElement CLickPlus= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[2]/div/div/div/div/div/div[1]/button[1]"));
        js.executeScript("arguments[0].click()", CLickPlus);

        //click save
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[6]/div/button[1]")).click();
        Thread.sleep(1000);
        //Click the Save Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")));
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();
        Thread.sleep(1500);


        //Go to courses tab and enroll in course just made
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1800);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(1800);
//    driver.findElement(By.name("searchButton")).click();
//    driver.findElement(By.xpath("//input[@value='Go']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[1]/td[5]/button"))));
        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();
        Thread.sleep(2500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button"))));
        Thread.sleep(3500);
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button")).click();

        Thread.sleep(3500);

        //Click on Course Admin
        WebElement courseAdmin0 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click();", courseAdmin0);

        Thread.sleep(3500);

        //Click on classroom course management
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();
        Thread.sleep(8500);

        // Search up class just made and enrolled in

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='form-control']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(courseId);

        //Click result
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")).click();

        //Click Offer Schedule
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/a[1]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/a[1]")).click();
        //Click Add attendee
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[3]/a")));
        WebElement AddAttendee=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[4]/div/button[2]"));
        js.executeScript("arguments[0].click()", AddAttendee);
        //CLick Attendee Tab
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[2]/a[1]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[2]/a[1]")).click();

        //CLick Select User
        driver.findElement(By.xpath("//table[@class='table table-sm m-0 table-hover']//tbody//tr//td//input")).click();

        //Mark as No Show
        Thread.sleep(1000);
        new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/select[1]"))).
                selectByVisibleText("Completed");

        Thread.sleep(3000);


        //Go back to classroom course management and create another course to use as a second prerequisite

        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Management')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Classroom Management')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(1000);
        WebElement CreateBtn0 = driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
        js.executeScript("arguments[0].click()", CreateBtn0);


        wait.until(ExpectedConditions.elementToBeClickable(By.id("course-category")));
        new Select(driver.findElement(By.id("course-category"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.id("course-fulfill"))).selectByVisibleText("Normal & Refresh");

        String courseId0 = generator.generate(10);
        String Building0 = generator.generate(11);
        System.out.println(courseId0);
        driver.findElement(By.id("course-num")).sendKeys(courseId0);

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
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/div/div/div/button")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[2]/div/div/div/button")).click();

        //Search SCV
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys("SCV");

        //Click SCV
        driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")).click();


        //Building
        driver.findElement(By.id("building")).sendKeys(Building0);
        driver.findElement(By.id("room")).sendKeys("001");
        driver.findElement(By.id("class-size")).clear();
        driver.findElement(By.id("class-size")).sendKeys("5");

        //Click Edit Btn of time schedule
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[6]/div/button[2]")).click();
        //Click Calendar
        WebElement Calendar0 =driver.findElement(By.id("date"));
        js.executeScript("arguments[0].click()", Calendar0);
        Thread.sleep(2000);
        System.out.println("CheckPoint 1");

        //Click Year(need twice click to select year)
        WebElement YearArea0= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[1]/div/div/div[2]/header/span[2]"));
        js.executeScript("arguments[0].click()", YearArea0);
        Thread.sleep(2000);
        System.out.println("CheckPoint 2");
        WebElement YearArea02=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[1]/div/div/div[3]/header/span[2]"));
        js.executeScript("arguments[0].click()", YearArea02);
        Thread.sleep(2000);
        System.out.println("CheckPoint 3");
        //click year 2029
        WebElement Btn2029_0= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[1]/div/div/div[4]/span[10]"));
        js.executeScript("arguments[0].click()", Btn2029_0);
        Thread.sleep(2000);
        System.out.println("CheckPoint 4");

        //Click 2029Mar
        WebElement Btn2029March_0= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[1]/div/div/div[3]/span[3]"));
        js.executeScript("arguments[0].click()", Btn2029March_0);
        Thread.sleep(2000);
        System.out.println("CheckPoint 5");

        WebElement Btn2029Mar02=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[1]/div/div/div[2]/div/span[12]"));
        js.executeScript("arguments[0].click()", Btn2029Mar02);
        Thread.sleep(2000);
        System.out.println("CheckPoint 6");

        //input time
        WebElement TimeBtn0=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[2]/div/div/button"));
        js.executeScript("arguments[0].click()", TimeBtn0);
        Thread.sleep(1000);
        Thread.sleep(1000);
        WebElement CLickPlus0= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[2]/div/div/div/div/div/div[1]/button[1]"));
        js.executeScript("arguments[0].click()", CLickPlus0);

        //click save
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[3]/div[7]/table/tbody/tr/td[6]/div/button[1]")).click();
        Thread.sleep(1000);
        //Click the Save Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")));
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();
        Thread.sleep(2500);

    //Create checklist with both previously made classroom courses as prerequisites

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Management')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Classroom Management')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Admin")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(3500);
        String courseId1 = generator.generate(10);
        System.out.println("Checklist id: " + courseId1);
        driver.findElement(By.name("detailCheckListCode")).sendKeys(courseId1);
        new Select(driver.findElement(By.id("detailCategoryType"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
        new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        new Select(driver.findElement(By.id("detailCoursePrerequisitesCourse1"))).selectByVisibleText(courseId + " - This is title");
        new Select(driver.findElement(By.id("detailCoursePrerequisitesCourse2"))).selectByVisibleText(courseId0 + " - This is title");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveBtn")));
        Thread.sleep(1000);
        driver.findElement(By.id("saveBtn")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Edit']")));
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(3000);

        driver.findElement(By.id("detailCheckListTitle")).sendKeys("test checklist title");
        driver.findElement(By.id("detailCheckListHeader")).sendKeys("test checklist header");
        driver.findElement(By.id("detailCheckListDescription")).sendKeys("test checklist description");
        driver.findElement(By.id("detailCheckListFooter")).sendKeys("test checklist footer");
        driver.findElement(By.id("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("createContent"))));
        Thread.sleep(1000);
        driver.findElement(By.id("createContent")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='submit'][value='Create']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"detailChecklistContentSaveAs\"]")).click();
        driver.findElement(By.cssSelector("input[type='submit'][value='Create']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Edit']"))));
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveBtn"))));
        Thread.sleep(1000);
        driver.findElement(By.id("saveBtn")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("fancyConfirm_ok"))));
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Back']"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Back']"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

        //driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        //Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("langIsViewable")));
        Thread.sleep(1000);
        driver.findElement(By.id("langIsViewable")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("detailIsActive")));
        Thread.sleep(1000);
        driver.findElement(By.id("detailIsActive")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();


        //Go back to courses tab and try to enroll in the checklist just made
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']"))));
        Thread.sleep(1000);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId1);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();
        Thread.sleep(3000);
        String working = "";
        try {
            working = driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[1]/div")).getAttribute("innerHTML");
        } catch (NoSuchElementException e) {
            Assert.fail("user was able to register for the course without taking the required prereqs");
        }
        if(!working.contains(courseId0)) {
            Assert.fail("something is wrong with the prerequisite courses");
        }

        Thread.sleep(3500);
        driver.quit();





    }
}






