package com.ClassroomCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class UnregisterClassroom {
    public void UnregisterClassroom() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
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
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String normuser = prop.getProperty("testnormuser");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);
         WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));


        js.executeScript("arguments[0].click()", courseAdmin);

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

                String CapitalLetter = generator.generate(1).toUpperCase();
        String courseId = generator.generate(10);
        courseId=CapitalLetter.concat(courseId);
        System.out.println(courseId);

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
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("expiration")));
        new Select(driver.findElement(By.id("expiration"))).selectByVisibleText("6 months");
        Thread.sleep(2000);;


        //input Classroom Details
        wait.until(ExpectedConditions.elementToBeClickable(By.id("training-hour")));
        //CLick Viewable btn
        WebElement ViewBtn = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[1]/div/a[2]/div/input"));
         js.executeScript("arguments[0].click()", ViewBtn);
        driver.findElement(By.id("training-hour")).sendKeys("3");
        driver.findElement(By.id("credit-hour")).sendKeys("3");

        //Click Save
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Offer Schedule
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Management')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();

        //Click Add offer
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div[1]/button")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div[1]/button")).click();

        //Click Site Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/button")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/button")).click();

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
        driver.findElement(By.xpath("//*[@id=\"classTimeTable\"]/tbody/tr/td[6]/div/button[2]")).click();
// Calendar&Time//click save
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div[2]/div[7]/table/tbody/tr/td[6]/div/button[1]")).click();
        Thread.sleep(1000);
        //Click the Save Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")));
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();
        Thread.sleep(2500);


        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Courses")));
        Thread.sleep(2000);
        driver.findElement(By.partialLinkText("Courses")).click();


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='View Detail']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@title='View Detail']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button")));
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button")).click();
        Thread.sleep(3500);


        driver.quit();


    }
}