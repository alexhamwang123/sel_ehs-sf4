package com.OnlineCourseManagement;


import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class RefreshOnlineCourse {
    public void RefreshOnlineCourse() throws IOException, InterruptedException {

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

        driver.manage().window().maximize();
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1500);



        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Management")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();

        String courseId = generator.generate(10);


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("detailCourseNo"))));
        Thread.sleep(1000);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);

        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Refresh");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Edit']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Edit']")).click();

        String courseTitle = generator.generate(10);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='detailCourseTitle']"))));
        Thread.sleep(1000);
        driver.findElement(By.name("detailCourseTitle")).sendKeys(courseTitle);
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='btn_edit_content']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='btn_edit_content']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Create Page']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Create Page']")).click();


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"question_sortable\"]/tr/td[3]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"question_sortable\"]/tr/td[3]/button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"2\"]/img"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"2\"]/img")).click();
        driver.findElement(By.id("courseContentTitle")).sendKeys("this is the title");
        driver.findElement(By.cssSelector("input[type='button'][value='Save & Back']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("fancyConfirm_ok"))));
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("addQBtn"))));
        Thread.sleep(1000);
        driver.findElement(By.id("addQBtn")).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("courseQuizSourcePage"))));
        js.executeScript("tinyMCE.activeEditor.setContent('this is the test question!')");
        Thread.sleep(1500);
        driver.findElement(By.id("courseQuizAnswer1")).sendKeys("this is the correct answer");
        driver.findElement(By.id("courseQuizAnswer2")).sendKeys("no, this is the correct answer");
        driver.findElement(By.name("courseQuizCorrectAnswer")).sendKeys("2");
        driver.findElement(By.name("courseQuizSourcePage")).sendKeys("1");


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save & Back']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save & Back']")).click();

        System.out.println("Checkpoint 1");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("fancyConfirm_ok"))));
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(3000);

        System.out.println("Checkpoint 2");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Back']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Back']")).click();
        System.out.println("Checkpoint 3");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Back']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Back']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Back']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Back']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='langIsViewable']"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='langIsViewable']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='detailCourseIsActive']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='detailCourseIsActive']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("editAction"))));
        Thread.sleep(1000);
        driver.findElement(By.className("editAction")).click();
        Thread.sleep(4500);

        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("6 months");
        Thread.sleep(2500);
        try {
            new Select(driver.findElement(By.name("detailCourseRefreshCourse1"))).selectByVisibleText(courseId + " - " + courseTitle);
        } catch (NoSuchElementException e) {
            Assert.fail("the refresh online course does not show up in the refresh course menu while creating a classroom course");
        }


        Thread.sleep(3500);
        driver.quit();

    }

}