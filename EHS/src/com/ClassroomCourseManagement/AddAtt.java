package com.ClassroomCourseManagement;

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

//@Test
@Test
public class AddAtt {
    public void AddAtt() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebDriverWait wait= new WebDriverWait(driver,30);
        File file = new File(System.getProperty("user.dir") + "/PasswordFileEHS.properties");

        FileInputStream inStream = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);
        try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
             actions.build().perform(); }
        catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }

        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String testnormuser33 = prop.getProperty("testnormuser33");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click()", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(1000);
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
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[3]/a")).click();

        //Click Add offer
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/button[1]")));
        Thread.sleep(1000);
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

        //Click Offer Schedule
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__BVID__56___BV_tab_button__")));
        Thread.sleep(2000);
        driver.findElement(By.id("__BVID__56___BV_tab_button__")).click();
        //Click Add attendee
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[3]/a")));
        WebElement AddAttendee=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[4]/div/button[2]"));
        js.executeScript("arguments[0].click()", AddAttendee);
        //CLick Attendee Tab
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__BVID__165___BV_tab_button__")));
        Thread.sleep(1000);
        driver.findElement(By.id("__BVID__165___BV_tab_button__")).click();

        //Click Add attendee btn
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__BVID__165___BV_tab_button__")));
        WebElement AttendeeBtn=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div[2]/div/div[2]/div[1]/div[4]/ul/li[1]/a"));
        js.executeScript("arguments[0].click()", AttendeeBtn);

        //Send in Badge No
        wait.until(ExpectedConditions.elementToBeClickable(By.id("criteriaBadge")));
        Thread.sleep(1000);
        driver.findElement(By.id("criteriaBadge")).sendKeys("554752");

        //Click Search
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/footer/div/button")).click();

        //Click Result
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")));
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();


        //Click Add attendee btn
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__BVID__165___BV_tab_button__")));
        Thread.sleep(1000);
        js.executeScript("arguments[0].click()", AttendeeBtn);

        //Send in Badge No
        wait.until(ExpectedConditions.elementToBeClickable(By.id("criteriaBadge")));
        Thread.sleep(1000);
        driver.findElement(By.id("criteriaBadge")).sendKeys(testnormuser33);

        //Click Search
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/footer/div/button")).click();

        //Click Result
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")));
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();


        System.out.println("Check Point 7");
        WebElement Logout1= driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click();", Logout1);
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
        Thread.sleep(2500);
        driver.findElement(By.id("username")).sendKeys(testnormuser33);
        driver.findElement(By.id("password")).sendKeys("11111111");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(2000);
        driver.findElement(By.partialLinkText("Courses")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(2000);
        if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div[1]/table/tbody/tr[1]/td[3]")).getText().contains("Enrolled")){
            System.out.println("The test is successful");
        }
        else{
            Assert.fail("The test failed");
        }

        driver.quit();
    }
}