package com.OnlineCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test(priority=38)
public class OnlineCoursePrereq {
    public void OnlineCoursePrereq() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

//        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", courseAdmin);

        Thread.sleep(3500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
        Thread.sleep(3500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(4500);

        String courseId = generator.generate(10);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))).selectByVisibleText("EHS-1100 - Manual Materials Handling");
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(8500);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(5500);
        String courseTitle = generator.generate(10);
        driver.findElement(By.name("detailCourseTitle")).sendKeys(courseTitle);
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("btn_edit_content")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("input[type='button'][value='Create Page']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"question_sortable\"]/tr/td[3]/button")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"3\"]/img")).click();
        Thread.sleep(3500);
        driver.findElement(By.id("courseContentTitle")).sendKeys("this is the title");
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save & Back']")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(3500);
        driver.findElement(By.id("addQBtn")).click();
        Thread.sleep(1000);
        js.executeScript("tinyMCE.activeEditor.setContent('this is the test question!')");
        Thread.sleep(2500);
        driver.findElement(By.id("courseQuizAnswer1")).sendKeys("this is the correct answer");
        driver.findElement(By.id("courseQuizAnswer2")).sendKeys("no, this is the correct answer");
        driver.findElement(By.name("courseQuizCorrectAnswer")).sendKeys("2");
        driver.findElement(By.name("courseQuizSourcePage")).sendKeys("1");
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save & Back']")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(4500);
        driver.findElement(By.name("langIsViewable")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("detailCourseIsActive")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(1500);
        driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();
        Thread.sleep(1500);
        String working = "";
        try {
            working = driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[1]")).getAttribute("innerHTML");
        } catch (NoSuchElementException e) {
            Assert.fail("was able to register for the course without completing the prereq");
        }

        if(!working.contains("EHS-1100")) {
            Assert.fail("user was ale to register for the course without completing the prereq");
        }

        Thread.sleep(2000);
        driver.quit();



    }

}
