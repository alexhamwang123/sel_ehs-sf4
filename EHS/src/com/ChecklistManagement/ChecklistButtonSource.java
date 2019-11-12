package com.ChecklistManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
@Test
//@Test(dependsOnGroups = "ehs1",priority=8)
public class ChecklistButtonSource {
    public void ChecklistButtonSourceScript() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();



        WebDriverWait Wait= new WebDriverWait(driver,30);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);

        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Management")).click();
        Thread.sleep(1500);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();

        Thread.sleep(3500);
        String courseId = generator.generate(10);
        System.out.println(courseId);
        driver.findElement(By.name("detailCheckListCode")).sendKeys(courseId);
        new Select(driver.findElement(By.id("detailCategoryType"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
        new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        new Select(driver.findElement(By.id("detailCoursePrerequisitesCourse1"))).selectByVisibleText("EHS-1000 - EHS Essentials");

        Thread.sleep(3000);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='center']//input[@id='saveBtn']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='center']//input[@id='saveBtn']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Edit']"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(1500);

        driver.findElement(By.id("detailCheckListTitle")).sendKeys("test checklist title");
        driver.findElement(By.id("detailCheckListHeader")).sendKeys("test checklist header");
        driver.findElement(By.id("detailCheckListDescription")).sendKeys("test checklist description");
        driver.findElement(By.id("detailCheckListFooter")).sendKeys("test checklist footer");
        driver.findElement(By.id("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(1000);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("createContent"))));
        Thread.sleep(1000);
        driver.findElement(By.id("createContent")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='row1']//input[@value='Create']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"detailChecklistContentSaveAs\"]")).click();
        WebElement CreateButton = driver.findElement(By.xpath("//span[@class='row1']//input[@value='Create']"));
        js.executeScript("arguments[0].click();", CreateButton);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Edit']"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(1500);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveBtn"))));
        Thread.sleep(1000);
        driver.findElement(By.id("saveBtn")).click();


        Wait.until(ExpectedConditions.elementToBeClickable(By.id("fancyConfirm_ok")));
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Back']")));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Save']")));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Back']")));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

      //  Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Back']")));
      //  driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
      //  Thread.sleep(3000);
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("langIsViewable")));
        Thread.sleep(1000);
        driver.findElement(By.id("langIsViewable")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.id("detailIsActive")));
        Thread.sleep(1000);
        driver.findElement(By.id("detailIsActive")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Save']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
       // Thread.sleep(8000);
        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Edit']")));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
       // Thread.sleep(5000);
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Create Button Sources']")));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@value='Create Button Sources']")).click();
        Thread.sleep(1500);
        String mainWin = driver.getWindowHandle();
        WebElement landingPage = driver.findElement(By.cssSelector("input[type='button'][value='Sample Landing Page']"));
        js.executeScript("arguments[0].click();", landingPage);
        Thread.sleep(1500);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        Thread.sleep(500);

        if(driver.getPageSource().contains("Required prerequisite course: EHS-1000")){
            System.out.println("The test is successful");
        }
        else{
            Assert.fail("THe test Failed");
        }
  /*    Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"course-take\"]/div[3]/div/form/button")));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"course-take\"]/div[3]/div/form/button")).click();
        Thread.sleep(1500);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"course-start\"]/div/div/div/div[2]/div/button[1]")));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"course-start\"]/div/div/div/div[2]/div/button[1]")).click();
        Thread.sleep(2500);
       // driver.findElement(By.cssSelector("#ch-body > div > div > div.card-header > div > button.btn.btn-primary")).click();
      //  Thread.sleep(1500);
      //  driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/button[2]")).click();
      //  Thread.sleep(1500);
      //  driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/button")).click();
        driver.switchTo().window(mainWin);
        Thread.sleep(2000);
        */

        driver.quit();

    }
}
