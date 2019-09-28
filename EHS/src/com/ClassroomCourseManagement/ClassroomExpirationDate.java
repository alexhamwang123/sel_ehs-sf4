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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class ClassroomExpirationDate {
    public void ClassroomExpirationDate() throws IOException, InterruptedException {
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


        //Click on classroom course management
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();


        //Click on Create course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();

        String courseId = generator.generate(10);
        System.out.println("completed prerequisite course: " + courseId);

        //Fill in Fields
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("6 months");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@id='addClass']"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@id='addClass']")).click();
        Thread.sleep(1000);

        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("site_radio"))));
        Thread.sleep(1000);
        driver.findElement(By.id("site_radio")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("selectBtnSite"))));
        Thread.sleep(1000);
        driver.findElement(By.id("selectBtnSite")).click();

        Thread.sleep(3500);
        driver.findElement(By.id("searchName")).sendKeys("SCV");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='submit'][value='Search']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
        Thread.sleep(3500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")).click();
        Thread.sleep(1500);
        String building = generator.generate(15);
        driver.findElement(By.name("detailClassBuilding")).sendKeys(building);
        driver.findElement(By.name("detailClassRoom")).sendKeys("room01");
        driver.findElement(By.name("detailClassMaxSize")).sendKeys("5");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("TimeAdd"))));
        Thread.sleep(1000);
        driver.findElement(By.id("TimeAdd")).click();
        Thread.sleep(2500);
        js.executeScript("document.getElementById('TimeAdd_datepicker').value='Dec 25,2030'");
        new Select(driver.findElement(By.name("detailClassStartHourSelect"))).selectByVisibleText("06");
        new Select(driver.findElement(By.name("detailClassStartMinuteSelect"))).selectByVisibleText("45");
        new Select(driver.findElement(By.id("detailClassDuration"))).selectByVisibleText("13");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd_Save")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveClassCourse"))));
        Thread.sleep(1000);
        driver.findElement(By.id("saveClassCourse")).click();
        Thread.sleep(1500);
        //Go to courses tab and enroll in course just made
        WebElement courseAdmin1 = driver.findElement(By.xpath("//a[contains(text(),'Courses')]"));
        js.executeScript("arguments[0].click();", courseAdmin1);
        Thread.sleep(1800);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(1800);
//        driver.findElement(By.name("searchButton")).click();
//        driver.findElement(By.xpath("//input[@value='Go']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[1]/td[5]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button")).click();

        Thread.sleep(1500);

        //Click on Course Admin
        WebElement courseAdmin0 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click();", courseAdmin0);

        Thread.sleep(1500);

        //Click on classroom course management
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();


        // Search up class just made and enrolled in

        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys(courseId);
        Thread.sleep(3500);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='submit'][value='Go']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@class='editAction']"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@class='editAction']")).click();

        //manage attendees
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@class='btn field-tip btn-success btn-xs']"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@class='btn field-tip btn-success btn-xs']")).click();


        //Mark as completed
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("emailme"))));
        Thread.sleep(1000);
        driver.findElement(By.name("emailme")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.name("selectStatus"))).selectByVisibleText("Mark Completed");
        driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();
        Thread.sleep(1500);
        WebElement lilok = driver.findElement(By.id("ok"));
        js.executeScript("arguments[0].click();", lilok);

        Thread.sleep(2500);
        //Search the Course  Again
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        System.out.println(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("My History"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("My History")).click();

        driver.findElement(By.xpath("//*[@id=\"my-courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys(courseId);
        Date current = new Date();
        System.out.println(current);
        Locale Local= new Locale("en","US");
        Calendar cal = Calendar.getInstance(Local);
        cal.setTime(current);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+6));
        Date SixMonthLater = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        System.out.println(dateFormat.format(SixMonthLater));

        String ExpirationDate= "Jun 25, 2031";
        if(!driver.getPageSource().contains(ExpirationDate)){
            Assert.fail("THere is no Expiration Date");
        }

        driver.quit();


    }

    }
