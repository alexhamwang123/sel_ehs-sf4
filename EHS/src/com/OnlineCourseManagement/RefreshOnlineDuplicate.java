package com.OnlineCourseManagement;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Test
public class RefreshOnlineDuplicate {
    public void OnlinecourseCreatandCheckClassroomDropdown() throws IOException, InterruptedException{
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

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

        //driver.manage().window().maximize();
/*
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

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(4500);

        String courseId = generator.generate(10);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Refresh");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(15000);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        //       driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();

        Thread.sleep(6000);
        String courseTitle = generator.generate(10);
        driver.findElement(By.name("detailCourseTitle")).sendKeys(courseTitle);
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(8000);
        driver.findElement(By.id("btn_edit_content")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("input[type='button'][value='Create Page']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"question_sortable\"]/tr/td[3]/button")).click();
        Thread.sleep(7000);
        driver.findElement(By.xpath("//*[@id=\"3\"]/img")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("courseContentTitle")).sendKeys("this is the title");
        Thread.sleep(5500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save & Back']")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("addQBtn")).click();
        Thread.sleep(5000);
        js.executeScript("tinyMCE.activeEditor.setContent('this is the test question!')");
        Thread.sleep(1500);
        driver.findElement(By.id("courseQuizAnswer1")).sendKeys("this is the correct answer");
        driver.findElement(By.id("courseQuizAnswer2")).sendKeys("no, this is the correct answer");
        driver.findElement(By.name("courseQuizCorrectAnswer")).sendKeys("2");
        driver.findElement(By.name("courseQuizSourcePage")).sendKeys("1");
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save & Back']")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(4500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(4000);
        //       driver.findElement(By.id("fancyConfirm_ok")).click();
        //       Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(6000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(6000);
        driver.findElement(By.name("langIsViewable")).click();
        Thread.sleep(3500);
        driver.findElement(By.id("detailCourseIsActive")).click();
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(3500);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();
        Thread.sleep(5500);
        driver.findElement(By.className("editAction")).click();
        Thread.sleep(4500);
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("6 months");
        Thread.sleep(500);
        try {
            new Select(driver.findElement(By.name("detailCourseRefreshCourse1"))).selectByVisibleText(courseId + " - " + courseTitle);
        } catch (NoSuchElementException e) {
            Assert.fail("the refresh online course does not show up in the refresh course menu while creating a classroom course");
        }

        Thread.sleep(3500);
        */
        driver.quit();


    }
}
