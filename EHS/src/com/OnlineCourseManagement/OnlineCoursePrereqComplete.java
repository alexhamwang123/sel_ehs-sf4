package com.OnlineCourseManagement;

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

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test

public class OnlineCoursePrereqComplete {
    public void CompletetheprerequisitebeforetakingChecklist() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir") + "/PasswordFileEHS.properties");

        FileInputStream inStream = new FileInputStream(file);
        Properties prop = new Properties();
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
        //go to onlinecourseManagement


        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Management")).click();

        //pick a class  & Assign prerequisite
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys("LElp0HwjbQ");
        driver.findElement(By.xpath("//input[@value='Go']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("editAction"))));
        Thread.sleep(1000);
        driver.findElement(By.className("editAction")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))));
        Thread.sleep(1000);
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))).selectByVisibleText("kimi-online-006 - kimi-online-006");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(2500);

        //got to the prerequisite and complete it
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("kimi-online-006"); //checklist name on localhost


        Thread.sleep(3000);



        //Click on the Enroll button
        driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-success border-0']")).click();

        Thread.sleep(3000);
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }


        //Click on 'Default-English' Language option
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        WebElement Language_English=driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
        js2.executeScript("arguments[0].click();",Language_English);


        Thread.sleep(3000);

        try {
            if (driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[1]")).isDisplayed()) {
                driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[1]")).click();
            }
        }
        catch(Exception e){
            System.out.println("There is no 'go back to last point Btn' Window");
        }


        Thread.sleep(2000);

        //Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"c-quiz\"]/ol/li/div[2]/label[4]/input"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"c-quiz\"]/ol/li/div[2]/label[4]/input")).click(); // Cat

        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"c-body\"]/div/div[2]/div[2]/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn float-right']")).click();
        Thread.sleep(2000);
        WebElement OKBtn=driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[2]"));
        js2.executeScript("arguments[0].click();",OKBtn);
        Thread.sleep(2000);
        //After completion of prerequisite, try "1bLkHwGarU" again.
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("LElp0HwjbQ");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();
        Thread.sleep(1500);
        String handle= driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {

            if(!winHandle.equals(handle)){
                driver.switchTo().window(winHandle);
                WebElement Successconfrimbtn=driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
                if(Successconfrimbtn.isDisplayed()){
                    System.out.println("Test is successful");
                }

            }
        }


        driver.quit();






    }
}
