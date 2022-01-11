package com.OnlineCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class OnlineCoursewithExpiration {
    public void OnlineCourseExpiration() throws IOException, InterruptedException{
        int number;
        int integer;
        Random rand = new Random();
        number= rand.nextInt(10000);
        integer=rand.nextInt(10000);

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait Wait = new WebDriverWait(driver, 30);
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
        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1500);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);
         WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));

        js.executeScript("arguments[0].click()", courseAdmin);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Admin")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        WebElement CreateBtn = driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
        js.executeScript("arguments[0].click()", CreateBtn);


        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-category")));
        new Select(driver.findElement(By.id("course-category"))).selectByVisibleText("EHS - Others");
        new Select(driver.findElement(By.id("course-fulfill"))).selectByVisibleText("Normal & Refresh");

        String CapitalLetter = generator.generate(1).toUpperCase();
        String courseId = generator.generate(10);
        courseId=CapitalLetter.concat(courseId);
        System.out.println(courseId);

        System.out.println(courseId);
        //input Course Number
        driver.findElement(By.id("course-num")).sendKeys(courseId);
        //input Course Title
        driver.findElement(By.id("input-courseTitle")).clear();
        driver.findElement(By.id("input-courseTitle")).sendKeys("This is title");
        //input Course Description

        driver.findElement(By.id("input-description")).sendKeys("This is description");

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Online Details
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-num")));
        Thread.sleep(2000);
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")));
Thread.sleep(1000);
driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")).click();

        //input training time
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-trainingTime")));
        Thread.sleep(1000);
        driver.findElement(By.id("course-trainingTime")).sendKeys("1");
        //input credit
        driver.findElement(By.id("course-credit")).sendKeys("1");

        //Select 48 Months as course Expiration(Then Variant Expiration is set as 6 months )
        new Select(driver.findElement(By.id("course-expiration"))).selectByVisibleText("48 months");

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Online Variants
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-trainingTime")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();
        //Click Edit Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn-sm btn btn-outline-primary border-0']")).click();

        //input course type
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("input-type")));
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("input-type"))).selectByVisibleText("Course Builder");

        //Select current variant expiration as 6 months(Course Expiration is set as 48 months)
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div[6]/div[1]/select[1]")));
        Thread.sleep(1000);
        new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div[6]/div[1]/select[1]"))).selectByVisibleText("6 months");

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Edit Course Content Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Edit Course Content')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Edit Course Content')]")).click();

        for(String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
        }
        Thread.sleep(1000);
        //input content
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(2000);
        WebElement Frame= driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[1]/div/div[1]/div[2]/div[1]/iframe"));
        driver.switchTo().frame(Frame);
        Thread.sleep(2000);
        driver.findElement(By.id("tinymce")).sendKeys("This is a test only");

        driver.switchTo().defaultContent();

        driver.findElement(By.id("page-title-input")).sendKeys("TitileforTest");
        Thread.sleep(1000);
        //Click Save btn
        Thread.sleep(1000);

        WebElement Save_Btn=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        js.executeScript("arguments[0].click()", Save_Btn);
        Thread.sleep(1000);
        //Click OK Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(1000);
        //Click Question Tab
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div[2]/div/div[1]/ul/li[2]/a")).click();
        Thread.sleep(1000);
        //Click Add Question Btn
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div[2]/div/div[1]/ul/div/button")).click();

        driver.switchTo().frame("question-content_ifr");
        Thread.sleep(2000);
        driver.findElement(By.id("tinymce")).sendKeys("This is a question content");
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
        //input answers
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/div/div/div[3]/div/div/div[2]/div[1]/input")).sendKeys("Correct Answer");
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/div/div/div[3]/div/div/div[3]/div[1]/input")).sendKeys("Wrong Answer");
        //CLick the first answer as correct
        WebElement CorrectBtn=driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/div/div/div[3]/div/div/div[2]/div[2]/div/input"));
        js.executeScript("arguments[0].click()", CorrectBtn);

        //Click OK btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

        //Click Save Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        js.executeScript("arguments[0].click()", Save_Btn);

        //Click OK Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(1000);

        //Close the window
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        driver.close();

        for(String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
        }

        //Click Online Variants
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();

        //Click Visibility btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);

        WebElement VisibilityBtn=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]"));
        js.executeScript("arguments[0].click()", VisibilityBtn);

        //Click viewable btn
        WebElement ViewableBtn=driver.findElement(By.xpath("//a[2]//div[1]//input[1]"));
        js.executeScript("arguments[0].click()", ViewableBtn);

        //Wait until the success message shows up
        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div/header")));
        if(!ViewableBtn.isEnabled()){
            Assert.fail("Creation Failed");
        }
        //Find and Complete the course
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        Thread.sleep(1000);

        //click the result
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();

        for(String winHandle : driver.getWindowHandles()) {
                    driver.switchTo().window(winHandle);
            }
        //Click English btn
        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]")));
        WebElement English= driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]"));
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js1.executeScript("arguments[0].click()", English);


        //Click Correct answer
        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Correct Answer')]")));
        driver.findElement(By.xpath("//label[contains(text(),'Correct Answer')]")).click();

        //Click Grade Quiz Btn
        driver.findElement(By.xpath("//button[contains(text(),'Grade Quiz')]")).click();

        //Click OK Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

        //CLick Exit Btn
        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Exit Course')]")));
        driver.findElement(By.xpath("//button[contains(text(),'Exit Course')]")).click();

        //Click OK Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        for(String winHandle : driver.getWindowHandles()) {
                    driver.switchTo().window(winHandle);
            }

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("My History"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("My History")).click();

        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(courseId);
        Date current = new Date(System.currentTimeMillis() - 15 * 60 * 60 * 1000);
        System.out.println(current);
        /*
        Locale Local= new Locale("en","US");
        Calendar cal = Calendar.getInstance(Local);
        cal.setTime(current);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+6));
        Date SixMonthLater = cal.getTime();

         */
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy",Locale.ENGLISH);

        String Expiration= dateFormat.format(current);
        System.out.println(Expiration);
        Thread.sleep(2000);
        if(!driver.getPageSource().contains(Expiration)){
                Assert.fail("THere is no Expiration Date");
        }

        driver.quit();



    }
}
