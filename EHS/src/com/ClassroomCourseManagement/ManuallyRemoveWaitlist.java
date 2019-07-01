package com.ClassroomCourseManagement;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
public class ManuallyRemoveWaitlist {
    public void ManuallyRemoveWaitlist() throws IOException, InterruptedException {
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
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click()", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search_result\"]/div/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();
        String courseId = generator.generate(10);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(By.name("detailCourseTitle")));
        Thread.sleep(1000);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal & Refresh");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Save']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='addClass']")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@id='addClass']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("site_radio")));
        Thread.sleep(1000);
        driver.findElement(By.id("site_radio")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("selectBtnSite")));
        Thread.sleep(1000);
        driver.findElement(By.id("selectBtnSite")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("searchName")));
        Thread.sleep(1000);
        driver.findElement(By.id("searchName")).sendKeys("SCV");
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")).click();
        Thread.sleep(1500);
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

        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveClassCourse")));
        Thread.sleep(1000);
        driver.findElement(By.id("saveClassCourse")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"FirstForm\"]/div[4]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"FirstForm\"]/div[4]/a")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='attendList']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a[href*='attendList']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Add Attendee']")));
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='button'][value='Add Attendee']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("badgeNo")));
        Thread.sleep(1000);
        driver.findElement(By.name("badgeNo")).sendKeys(username);

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][value='Search']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();


        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='selectStudent']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a[href*='selectStudent']")).click();


        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Add Attendee']")));
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='button'][value='Add Attendee']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("badgeNo")));
        Thread.sleep(1000);
        driver.findElement(By.name("badgeNo")).sendKeys("X00001515");


        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][value='Search']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='selectStudent']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a[href*='selectStudent']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Add Attendee']")));
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='button'][value='Add Attendee']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("badgeNo")));
        Thread.sleep(1000);
        driver.findElement(By.name("badgeNo")).sendKeys("X00001514");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][value='Search']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='selectStudent']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a[href*='selectStudent']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"accordion_AttendeeList_Table\"]/tbody/tr/td[8]/button")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"accordion_AttendeeList_Table\"]/tbody/tr/td[8]/button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("fancyConfirm_ok")));
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(3500);
        driver.quit();



    }
}