package com.ChecklistManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
            System.setProperty("webdriver.chrome.driver", "chromedriver");

            WebDriver driver = new ChromeDriver();
            WebDriverWait Wait = new WebDriverWait(driver, 30);
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

            JavascriptExecutor js = (JavascriptExecutor)driver;
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

            Thread.sleep(1000);
            WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
            js.executeScript("arguments[0].click()", Admin);
            Thread.sleep(1000);
            WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));


            js.executeScript("arguments[0].click();", courseAdmin);

            Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Admin"))));
            Thread.sleep(1000);
            driver.findElement(By.partialLinkText("Checklist Admin")).click();


            Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Admin"))));
            WebElement CreateCourse= driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
            js.executeScript("arguments[0].click();", CreateCourse);

            Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-category")));
            new Select(driver.findElement(By.id("course-category"))).selectByVisibleText("EHS - Safety");
            new Select(driver.findElement(By.id("course-fulfill"))).selectByVisibleText("Normal & Refresh");

            String CapitalLetter = generator.generate(1).toUpperCase();
            String courseId = generator.generate(10);
            courseId=CapitalLetter.concat(courseId);
            System.out.println(courseId);

            String Building = generator.generate(11);
            System.out.println(courseId);
            driver.findElement(By.id("course-num")).sendKeys(courseId);

            //input description
            driver.findElement(By.id("input-description")).sendKeys("This is description");

            //Completion Message
            driver.findElement(By.id("completion-message")).sendKeys("Congrats Completion");

            //input Course Number
            driver.findElement(By.id("input-courseTitle")).sendKeys("This is title");

            //Save btn
            driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

            //Click  Details
            Thread.sleep(1500);
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            Thread.sleep(2000);
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")));
Thread.sleep(1000);
driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")).click();
            Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-expiration")));
            new Select(driver.findElement(By.id("course-expiration"))).selectByVisibleText("6 months");
            Thread.sleep(2000);

            //Click Prereq Select
            driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary']")).click();

            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
            driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys(unfinishedOnline);

            //Click result
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();

            //Click Prereq Select
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary']")).click();

            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
            driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys(finishedChecklist);

            //Click result
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();

            //Click Prereq Select
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary']")).click();

            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
            driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys(finishedClassroom);

            //Click result
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();


            //Click Save
            driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

            //Click Course Variant
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Checklist Course Management')]")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();


            //Click Edit Btn
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[@class='btn-sm btn btn-outline-primary border-0']")).click();

            //Click Add new Version
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'New version')]")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//Button[contains(text(),'New version')]")).click();

            //Click Add Question
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add Question')]")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//Button[contains(text(),'Add Question')]")).click();

            //input all question information
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
            Thread.sleep(1000);
            driver.switchTo().frame("question-content_ifr");
            driver.findElement(By.id("tinymce")).sendKeys("This is test question content only");
            driver.switchTo().defaultContent();

            driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div/div/div/div[3]/div/div/div[2]/div[1]/div[1]/div/input")).sendKeys("Correct");
            driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div/div/div/div[3]/div/div/div[3]/div[1]/div[1]/div/input")).sendKeys("Wrong");

            //Click Default input btn
            WebElement Defaultinut= driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div/div/div/div[3]/div/div/div[2]/div[3]/div/input"));
            js.executeScript("arguments[0].click()", Defaultinut);
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

            //Click Btn Finalize
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Finalize')]")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[contains(text(),'Finalize')]")).click();

            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

            //CLick Active input
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            Thread.sleep(1000);
            WebElement activeinput= driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div[7]/div[2]/table/tbody/tr/td[1]/div/input"));
            js.executeScript("arguments[0].click()", activeinput);

            //Click Course Variant
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Checklist Course Management')]")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();

            //Click Visibility Btn
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            Thread.sleep(2000);
            WebElement visibility = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div/div/div[1]/input"));
            js.executeScript("arguments[0].click()", visibility);

            //Click Viewable Btn
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            Thread.sleep(1000);
            WebElement viewablebtn= driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[1]/div/a[2]/div/input"));
            js.executeScript("arguments[0].click()", viewablebtn);




            Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
            Thread.sleep(1000);
            driver.findElement(By.partialLinkText("Courses")).click();

            Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(courseId);

            Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"))));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();
            Thread.sleep(1500);
            String working = "";
            try {
                working = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[1]/div")).getAttribute("innerHTML");
                System.out.println(working);
            } catch (NoSuchElementException e) {
                Assert.fail("was able to register for the course without completing the prereq");
            }

            if(!working.contains(unfinishedOnline.substring(0,4))) {
                Assert.fail("user was ale to register for the course without completing the prereq");
            }
            Thread.sleep(1500);
            JavascriptExecutor js2 = (JavascriptExecutor)driver;
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

            Thread.sleep(1000);
            WebElement Admin1=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
            js.executeScript("arguments[0].click()", Admin1);
            Thread.sleep(1000);

            WebElement courseAdmin2 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
            js2.executeScript("arguments[0].click()", courseAdmin2);

            Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Admin"))));
            Thread.sleep(1000);
            driver.findElement(By.partialLinkText("Checklist Admin")).click();

            Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(courseId);


            //Click result
            Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]"))));
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();

            //Click  Details
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            Thread.sleep(2000);
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")));
Thread.sleep(1000);
driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")).click();

            //Cancel the first prereq
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[2]/div/div[2]/div/div[5]/div/div/div[1]/div/div/div/button[2]")).click();


            //Select new finished prereq
            //Click Prereq Select
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary']")).click();

            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
            driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys(finishedOnline);

            //Click result
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();

            //Click Save
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();


            Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
            Thread.sleep(2000);
            driver.findElement(By.partialLinkText("Courses")).click();

            Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']"))));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

            Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"))));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();
            Thread.sleep(1000);
            for(String handle:driver.getWindowHandles()){
                driver.switchTo().window(handle);
            }
            Thread.sleep(3000);
            try {
                driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]")).click();
            }
            catch(NoSuchElementException e){
                Assert.fail("The test failed");
            }

            driver.quit();

    }
}
