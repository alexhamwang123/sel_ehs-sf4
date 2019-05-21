package com.ClassroomCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
@Test(priority=27)
public class RegisteringMechanics {
    public void RegisteringMechanics() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String normuser = prop.getProperty("testnormuser");
        String normuser0 = prop.getProperty("testnormuser2");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();
        Thread.sleep(3500);
        String courseId = generator.generate(10);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(5500);
        driver.findElement(By.id("addClass")).click();
        Thread.sleep(4500);
        driver.findElement(By.id("site_radio")).click();
        Thread.sleep(3500);
        driver.findElement(By.id("selectBtnSite")).click();
        Thread.sleep(3500);
        driver.findElement(By.xpath("//tbody//tr[6]//td[1]//a[1]")).click();
        Thread.sleep(3500);
        String building = generator.generate(15);
        driver.findElement(By.name("detailClassBuilding")).sendKeys(building);
        driver.findElement(By.name("detailClassRoom")).sendKeys("room01");
        driver.findElement(By.name("detailClassMaxSize")).sendKeys("1");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd")).click();
        Thread.sleep(1500);
        js.executeScript("document.getElementById('TimeAdd_datepicker').value='Dec 25,2030'");
        new Select(driver.findElement(By.name("detailClassStartHourSelect"))).selectByVisibleText("06");
        new Select(driver.findElement(By.name("detailClassStartMinuteSelect"))).selectByVisibleText("45");
        new Select(driver.findElement(By.id("detailClassDuration"))).selectByVisibleText("13");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd_Save")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("saveClassCourse")).click();
        Thread.sleep(5500);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(1500);
        driver.findElement(By.xpath("//button[@title='View Detail']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();
        Thread.sleep(1500);
        WebElement Logout= driver.findElement(By.xpath("//a[@class='dropdown-item'][contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click();", Logout);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();

        System.out.println("Check Point 1");
        driver.findElement(By.id("username")).sendKeys(normuser);
        driver.findElement(By.id("password")).sendKeys(normuser);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1500);

        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(1500);
        driver.findElement(By.xpath("//button[@title='View Detail']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();

        Thread.sleep(2000);
        if(!driver.getPageSource().contains("Waitlisted")) {
            Assert.fail("the user did not get waitlisted for the course");
        }
        Thread.sleep(500);
        System.out.println("Check Point 2");
        WebElement Logout1= driver.findElement(By.xpath("//a[@class='dropdown-item'][contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click();", Logout1);
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();

        driver.findElement(By.id("username")).sendKeys(normuser0);
        driver.findElement(By.id("password")).sendKeys(normuser0);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(16500);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(1500);
        driver.findElement(By.xpath("//button[@title='View Detail']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();

        Thread.sleep(2000);
        if(!driver.getPageSource().contains("Waitlisted")) {
            Assert.fail("the user did not get waitlisted for the course");
        }
            System.out.println("Check Point 3");
            WebElement Logout2= driver.findElement(By.xpath("//a[@class='dropdown-item'][contains(text(),'Logout')]"));
            js.executeScript("arguments[0].click();", Logout2);
            Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(22500);

        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(1500);
        driver.findElement(By.xpath("//button[@title='View Detail']")).click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//button[@class='btn btn-sm btn-danger']")).click();
        Thread.sleep(1500);
        System.out.println("Check Point 4");

            WebElement Logout3= driver.findElement(By.xpath("//a[@class='dropdown-item'][contains(text(),'Logout')]"));
            js.executeScript("arguments[0].click();", Logout3);
            Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
            driver.findElement(By.id("username")).sendKeys(normuser);
            driver.findElement(By.id("password")).sendKeys(normuser);

            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(1500);

            driver.findElement(By.partialLinkText("Courses")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
            Thread.sleep(1500);
            driver.findElement(By.xpath("//button[@title='View Detail']")).click();
            Thread.sleep(1500);

            if(!driver.getPageSource().contains("Enrolled")) {
            Assert.fail("the user first in line on the waitlist did not get automatically enrolled when one of the enrolled users unregistered");
        }
        Thread.sleep(2000);
        driver.quit();





    }
}