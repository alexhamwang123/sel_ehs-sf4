package com.OnlineCourseManagement;

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
public class OnlinePrerequisiteAnd {
    public void OnlinePrerequisiteAnd() throws IOException, InterruptedException {
            WebDriver driver = new ChromeDriver();
            WebDriverWait wait= new WebDriverWait(driver,30);
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

            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            String New_User_Password = prop.getProperty("New_User_Password");
            String unfinishedOnline = prop.getProperty("unfinished_Online");
            String finishedOnline   = prop.getProperty("finished_Online");
            String finishedChecklist = prop.getProperty("finished_Checklist");
            String finishedClassroom = prop.getProperty("finished_Classroom");

            RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);

            driver.findElement(By.xpath("//button[@type='submit']")).click();

            Thread.sleep(4500);

            JavascriptExecutor js = (JavascriptExecutor)driver;
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

            Thread.sleep(1000);
            WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
            js.executeScript("arguments[0].click()", Admin);
            Thread.sleep(1000);
            WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));

            js.executeScript("arguments[0].click()", courseAdmin);

            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
            Thread.sleep(1000);
            driver.findElement(By.partialLinkText("Online Course Admin")).click();

            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
            Thread.sleep(1000);
            WebElement CreateBtn = driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
            js.executeScript("arguments[0].click()", CreateBtn);


            wait.until(ExpectedConditions.elementToBeClickable(By.id("course-category")));
            new Select(driver.findElement(By.id("course-category"))).selectByVisibleText("Circle Web Course Core");
            new Select(driver.findElement(By.id("course-fulfill"))).selectByVisibleText("Normal");

            String CapitalLetter = generator.generate(1).toUpperCase();
            String courseId = generator.generate(10);
            courseId=CapitalLetter.concat(courseId);
            System.out.println(courseId);

            System.out.println(courseId);
            //input Course Number
            driver.findElement(By.id("course-num")).sendKeys(courseId);
            //input Course Title
            driver.findElement(By.id("input-courseTitle")).clear();
            driver.findElement(By.id("input-courseTitle")).sendKeys("This is title");
            //input Course Description

            driver.findElement(By.id("input-description")).sendKeys("This is description");

            //Save btn
            driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();
            Thread.sleep(2000);
            //Click Online Details
            wait.until(ExpectedConditions.elementToBeClickable(By.id("course-num")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")).click();

            //input training time
            wait.until(ExpectedConditions.elementToBeClickable(By.id("course-trainingTime")));
            Thread.sleep(1000);
            driver.findElement(By.id("course-trainingTime")).sendKeys("1");
            //input credit
            driver.findElement(By.id("course-credit")).sendKeys("1");

            //input prereq
            //Click Select Btn
            driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary'][contains(text(),'Select')]")).click();

            //Search prereq courese
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")));
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys(unfinishedOnline);

            //Select the result
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();

            //Click Select Btn
            driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary'][contains(text(),'Select')]")).click();

            //Search prereq courese
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")));
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys(finishedChecklist);

            //Select the result
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();

            //Click Select Btn
            driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary'][contains(text(),'Select')]")).click();

            //Search prereq courese
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")));
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys(finishedClassroom);

            //Select the result
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();
            Thread.sleep(1000);
            //Save btn
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")));
            driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();
            Thread.sleep(2000);
            Thread.sleep(1000);
            //Click Online Variants
            wait.until(ExpectedConditions.elementToBeClickable(By.id("course-trainingTime")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();
            //Click Edit Btn
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[@class='btn-sm btn btn-outline-primary border-0']")).click();

            //input course type
            wait.until(ExpectedConditions.elementToBeClickable(By.id("input-type")));
            Thread.sleep(1000);
            new Select(driver.findElement(By.id("input-type"))).selectByVisibleText("Course Creator Content");

            //Save btn
            driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

            //Click Edit Course Content Btn
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Edit Course Content')]")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[contains(text(),'Edit Course Content')]")).click();

            for(String winHandle : driver.getWindowHandles()) {
                    driver.switchTo().window(winHandle);
            }
            Thread.sleep(1000);
            //input content
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
            Thread.sleep(2000);
            WebElement Frame= driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[1]/div/div[1]/div[2]/div[1]/iframe"));
            driver.switchTo().frame(Frame);
            Thread.sleep(2000);
            driver.findElement(By.id("tinymce")).sendKeys("This is a test only");

            driver.switchTo().defaultContent();

            driver.findElement(By.id("page-title-input")).sendKeys("TitileforTest");
            Thread.sleep(1000);
            //Click Save btn
            Thread.sleep(1000);

            WebElement Save_Btn=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
            js.executeScript("arguments[0].click()", Save_Btn);
            Thread.sleep(1000);
            //Click OK Btn
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
            Thread.sleep(1000);
            //Click Question Tab
            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div[2]/div/div[1]/ul/li[2]/a")).click();
            Thread.sleep(1000);
            //Click Add Question Btn
            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div[2]/div/div[1]/ul/div/button")).click();

            driver.switchTo().frame("question-content_ifr");
            Thread.sleep(2000);
            driver.findElement(By.id("tinymce")).sendKeys("This is a question content");
            driver.switchTo().defaultContent();
            Thread.sleep(1000);
            //input answers
            driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/div/div/div[3]/div/div/div[2]/div[1]/input")).sendKeys("Correct Answer");
            driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/div/div/div[3]/div/div/div[3]/div[1]/input")).sendKeys("Wrong Answer");
            //CLick the first answer as correct
            WebElement CorrectBtn=driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/div/div/div[3]/div/div/div[2]/div[2]/div/input"));
            js.executeScript("arguments[0].click()", CorrectBtn);

            //Click OK btn
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

            //Click Save Btn
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
            Thread.sleep(1000);
            js.executeScript("arguments[0].click()", Save_Btn);

            //Click OK Btn
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
            Thread.sleep(1000);

            //Close the window
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
            Thread.sleep(1000);
            driver.close();

            for(String winHandle : driver.getWindowHandles()) {
                    driver.switchTo().window(winHandle);
            }

            //Click Online Variants
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();

            //Click Visibility btn
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
            Thread.sleep(1000);

            WebElement VisibilityBtn=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]"));
            js.executeScript("arguments[0].click()", VisibilityBtn);
            Thread.sleep(1500);

            //Click viewable btn
            WebElement ViewableBtn=driver.findElement(By.xpath("//a[2]//div[1]//input[1]"));
            js.executeScript("arguments[0].click()", ViewableBtn);

            //Wait until the success message shows up
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div/header")));
            if(!ViewableBtn.isEnabled()){
                    Assert.fail("Creation Failed");
            }




            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
            Thread.sleep(1000);
            driver.findElement(By.partialLinkText("Courses")).click();

            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']"))));
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"))));
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
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

            Thread.sleep(1000);
            WebElement Admin1=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
            js.executeScript("arguments[0].click()", Admin1);
            Thread.sleep(1000);
            JavascriptExecutor js2 = (JavascriptExecutor)driver;
            WebElement courseAdmin2 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
            js2.executeScript("arguments[0].click()", courseAdmin2);

            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
            Thread.sleep(1000);
            driver.findElement(By.partialLinkText("Online Course Admin")).click();

            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(courseId);


            //Click result
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();

            //Click Online Details
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")).click();

            //Click Cancel Prereq Btn
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[2]/div/div[2]/div/div[6]/div/div/div[1]/div/div/div/button[2]")).click();

            //Click Select Btn
            driver.findElement(By.xpath("//div[@class='prereq col']//div//button[@class='btn btn-primary'][contains(text(),'Select')]")).click();

            //Search prereq courese
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")));
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys(finishedOnline);

            //Select the result
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();


            //Save btn
            driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();
            Thread.sleep(2500);
            Thread.sleep(1000);
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
            Thread.sleep(1000);
            try {
                    driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]")).click();
            }
            catch(NoSuchElementException e){
                    Assert.fail("The test failed");
            }

            driver.quit();
    }

    }
