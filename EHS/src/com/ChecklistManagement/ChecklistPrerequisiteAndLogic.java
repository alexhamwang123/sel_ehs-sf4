package com.ChecklistManagement;

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
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class ChecklistPrerequisiteAndLogic {
    public void ChecklistPrerequisiteAndLogic()throws IOException,InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebDriverWait Wait= new WebDriverWait(driver,60);
        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

        driver.manage().window().maximize();
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String unfinishedOnline = prop.getProperty("unfinished_Online");
        String finishedOnline = prop.getProperty("finished_Online");
        String finishedChecklist = prop.getProperty("finished_Checklist");
        String finishedClassroom = prop.getProperty("finished_Classroom");


        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);

        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Management")).click();
        Thread.sleep(1500);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(3500);
        String courseId = generator.generate(11);
        driver.findElement(By.name("detailCheckListCode")).sendKeys(courseId);
        new Select(driver.findElement(By.id("detailCategoryType"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
        new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        new Select(driver.findElement(By.id("detailCourseFulfillType"))).selectByVisibleText("Normal");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveBtn"))));
        Thread.sleep(1000);
        driver.findElement(By.id("saveBtn")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Edit']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("detailCheckListTitle")).sendKeys("test checklist title");
        driver.findElement(By.id("detailCheckListHeader")).sendKeys("test checklist header");
        driver.findElement(By.id("detailCheckListDescription")).sendKeys("test checklist description");
        driver.findElement(By.id("detailCheckListFooter")).sendKeys("test checklist footer");
        driver.findElement(By.id("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.id("createContent")));
        Thread.sleep(1500);
        driver.findElement(By.id("createContent")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][value='Create']")));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"detailChecklistContentSaveAs\"]")).click();
        driver.findElement(By.cssSelector("input[type='submit'][value='Create']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Edit']")));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@value='Edit']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.id("saveBtn")));
        Thread.sleep(1500);
        driver.findElement(By.id("saveBtn")).click();

        Thread.sleep(3000);
        Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("fancyConfirm_ok")));
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("fancyConfirm_ok")));
        Thread.sleep(1500);
        driver.findElement(By.id("fancyConfirm_ok")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Back']")));
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Save']")));
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Back']")));
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.id("langIsViewable")));
        Thread.sleep(1500);
        driver.findElement(By.id("langIsViewable")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.id("detailIsActive")));
        Thread.sleep(1500);
        driver.findElement(By.id("detailIsActive")).click();

        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))).selectByVisibleText(unfinishedOnline);
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse2"))).selectByVisibleText(finishedChecklist);
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse3"))).selectByVisibleText(finishedClassroom);

        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Save']")));
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();
        Thread.sleep(1500);
        String working = "";
        try {
            working = driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[1]/div")).getAttribute("innerHTML");
            System.out.println(working);
        } catch (NoSuchElementException e) {
            Assert.fail("was able to register for the course without completing the prereq");
        }

        if(!working.contains(unfinishedOnline.substring(0,4))) {
            Assert.fail("user was ale to register for the course without completing the prereq");
        }
        Thread.sleep(1500);
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        WebElement courseAdmin2 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js2.executeScript("arguments[0].click()", courseAdmin2);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Management")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys(courseId);

        //CLick Go Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='btn btn-primary']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText(courseId))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText(courseId)).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))).selectByVisibleText(finishedOnline);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();
        Thread.sleep(1000);
        for(String handle:driver.getWindowHandles()){
            driver.switchTo().window(handle);
        }
        Thread.sleep(3000);
        try {
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div/button[1]")).click();
        }
        catch(NoSuchElementException e){
            Assert.fail("The test failed");
        }

        driver.quit();

    }
}
