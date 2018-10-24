package com.OnlineCourseManagement;


import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test(priority=39)
public class OnlinePrereqComplete {
    public void OnlinePrereqComplete() throws InterruptedException, IOException, AWTException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("https://twn:WrongAdeeDow2-@demo.accentrixus.com:8330");

        Robot robot = new Robot();
        robot.setAutoDelay(250);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_TAB);


        driver.manage().window().maximize();

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);
        WebElement courseAdmin = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
//        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();
        Thread.sleep(1500);
        String courseId = generator.generate(10);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
//        driver.findElement(By.name("detailCourseTitle")).sendKeys("test online course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Survey_Only_New");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(1500);
        String courseTitle = generator.generate(10);
        driver.findElement(By.name("detailCourseTitle")).sendKeys(courseTitle);
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("btn_edit_content")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Create Page']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"question_sortable\"]/tr/td[3]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"915d8c7314d1e2c0011512a0784d1726\"]/img")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("courseContentTitle")).sendKeys("this is the title");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save & Back']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("addQBtn")).click();
        Thread.sleep(1000);
        js.executeScript("tinyMCE.activeEditor.setContent('this is the test question!')");
        Thread.sleep(500);
        driver.findElement(By.id("courseQuizAnswer1")).sendKeys("this is the correct answer");
        driver.findElement(By.id("courseQuizAnswer2")).sendKeys("no, this is the correct answer");
        driver.findElement(By.name("courseQuizCorrectAnswer")).sendKeys("2");
        driver.findElement(By.name("courseQuizSourcePage")).sendKeys("1");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save & Back']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        java.awt.datatransfer.Clipboard clipboard = toolkit.getSystemClipboard();
//        String path = System.getProperty("user.dir") + "/Intro_OneDrive.zip";
//        System.out.println("path="+path);
//        StringSelection str = new StringSelection(path);
//        clipboard.setContents(str, null);
//
//
//        driver.findElement(By.cssSelector("input[type='radio'][value='scorm']")).click();
//        Thread.sleep(1000);
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
        driver.findElement(By.name("langIsViewable")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("detailCourseIsActive")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[value='Edit']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[value='Create Button Sources ']")).click();
        Thread.sleep(1500);
        String mainWin = driver.getWindowHandle();
        WebElement landingPage = driver.findElement(By.cssSelector("input[value='Sample Landing Page']"));
        js.executeScript("arguments[0].click();", landingPage);

        Thread.sleep(1500);
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[value='Take Course']")).click();
        Thread.sleep(1500);
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(500);
        WebElement exit = driver.findElement(By.xpath("//*[@id=\"nav_exit\"]"));
        js.executeScript("arguments[0].click();", exit);
        Thread.sleep(1500);
        driver.findElement(By.className("scormPauseCourse")).click();
        Thread.sleep(1500);
        driver.switchTo().window(mainWin);
        Thread.sleep(2000);
        driver.quit();

    }


}
