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
public class NormalClassroomAndAddClassroomCourseManager {
    public void AddClassroomManger() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        driver.get(urladdr);

        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search_result\"]/div/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("selectBtnCreMaA")));
        Thread.sleep(1000);
        driver.findElement(By.id("selectBtnCreMaA")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("badgeNo")));
        Thread.sleep(1000);
        driver.findElement(By.name("badgeNo")).sendKeys("X00003037");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][value='Search']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
        Thread.sleep(1500);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"teammanager_result\"]/div/table/tbody/tr/td[1]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"teammanager_result\"]/div/table/tbody/tr/td[1]/a")).click();
        Thread.sleep(3500);

        String courseId = generator.generate(10);
        System.out.println(courseId);
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
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(4000);
        WebElement Logout1= driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click();", Logout1);
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
        Thread.sleep(2500);
        driver.findElement(By.id("username")).sendKeys("X00003037");
        driver.findElement(By.id("password")).sendKeys("11111111");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);

        WebElement courseAdmin1 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js1 = (JavascriptExecutor)driver;

        js1.executeScript("arguments[0].click();", courseAdmin1);

        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Classroom Course Management")));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div/div[2]/div/form/div[1]/div/div[4]/input")));
        Thread.sleep(1500);
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/form/div[1]/div/div[4]/input")).sendKeys(courseId);

        Thread.sleep(1500);
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/form/div[1]/div/input")).click();
        Thread.sleep(3500);
        if(driver.getPageSource().contains(courseId)){
            System.out.println("The test is successful");
        }
        else{
            Assert.fail("The test failed");
        }

        driver.quit();



    }

}
