package com.OnlineCourseManagement;


import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test(priority=34)
public class CreateScormClass {
    public void CreateScormClass() throws InterruptedException, IOException, AWTException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

        Robot robot = new Robot();
        robot.setAutoDelay(250);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_TAB);

        //driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", courseAdmin);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(4500);

        String courseId = generator.generate(10);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Survey_Only_New");//We have to make it via manually, just in case.
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(10000);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(3500);
        String courseTitle = generator.generate(10);
        driver.findElement(By.name("detailCourseTitle")).sendKeys(courseTitle);
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        java.awt.datatransfer.Clipboard clipboard = toolkit.getSystemClipboard();
        String path = System.getProperty("user.dir") + "/Intro_OneDrive.zip";
        System.out.println("path="+path);
        StringSelection str = new StringSelection(path);
        clipboard.setContents(str, str);


        driver.findElement(By.cssSelector("input[type='radio'][value='scorm']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(      "input[type='file']")).sendKeys(path);
        Thread.sleep(1000);
//        driver.findElement(By.id("file_Scorm_File")).click();
//        Thread.sleep(2500);

        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_G);

        Thread.sleep(1500);

        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_V);

        Thread.sleep(1500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1500);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(1500);
        driver.findElement(By.id("btn_Scorm_UploadFile")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("langIsViewable")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("detailCourseIsActive")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(7000);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("srch_fld")).sendKeys(courseId);

        driver.findElement(By.name("searchButton")).click();
        Thread.sleep(1500);
        String currentWin = driver.getWindowHandle();
        try {
            driver.findElement(By.className("onelang")).click();
        } catch (NoSuchElementException e) {
            Assert.fail("something went wrong while creating and uploading the scorm course");
        }
        Thread.sleep(1500);

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        Thread.sleep(500);
        driver.findElement(By.className("crselink1")).click();
        Thread.sleep(1500);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_W);
        Thread.sleep(500);
        driver.switchTo().window(currentWin);
        Thread.sleep(3500);
        driver.findElement(By.id("srch_fld")).sendKeys(courseId);
        Thread.sleep(3500);
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        Thread.sleep(1500);

        if (!driver.getPageSource().contains("Paused")) {
            Assert.fail("the scorm class does not show up as paused");
        }
        Thread.sleep(3500);
        driver.quit();


    }

}
