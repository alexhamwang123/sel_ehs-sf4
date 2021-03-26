package com.ChecklistManagement;


import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//import java.util.Properties;
//import java.util.List;

@Test
//@Test(groups="ehs" ,priority=1)
public class NormalChecklistAndAddChecklistManager implements IMethodInterceptor {
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

        Comparator<IMethodInstance> comparator = new Comparator<IMethodInstance>() {
            private int getLineNo(IMethodInstance mi) {
                int result = 0;

                String methodName = mi.getMethod().getConstructorOrMethod().getMethod().getName();
                String className  = mi.getMethod().getConstructorOrMethod().getDeclaringClass().getCanonicalName();
                ClassPool pool    = ClassPool.getDefault();

                try {
                    CtClass cc        = pool.get(className);
                    CtMethod ctMethod = cc.getDeclaredMethod(methodName);
                    result            = ctMethod.getMethodInfo().getLineNumber(0);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }

                return result;
            }

            public int compare(IMethodInstance m1, IMethodInstance m2) {
                return getLineNo(m1) - getLineNo(m2);
            }
        };

        IMethodInstance[] array = methods.toArray(new IMethodInstance[methods.size()]);
        Arrays.sort(array, comparator);
        return Arrays.asList(array);
    }

    public void AddChecklistManager() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait Wait= new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.manage().window().maximize();
        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
        try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
            actions.build().perform(); }
        catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }
        String nonscvuser = prop.getProperty("usersitenonscv");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);

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

        //select manager
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/div/div[2]/div/div[5]/div/div/div[1]/div/div/button")).click();

        //search manager
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Search')]"))));
        driver.findElement(By.id("criteriaBadge")).sendKeys("X00003890");
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
        //CLick result
        driver.findElement(By.xpath("//td[contains(text(),'X00003890')]")).click();

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
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(2000);
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")));
Thread.sleep(1000);
driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")).click();
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-expiration")));
        new Select(driver.findElement(By.id("course-expiration"))).selectByVisibleText("6 months");
        Thread.sleep(2000);


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

        Thread.sleep(3000);

        WebElement Logout0=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()", Logout0);
        Thread.sleep(1500);
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/div/span"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/div/span")).click();


        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("username"))));
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys(nonscvuser);
        driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        Thread.sleep(1000);
        WebElement Admin1=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin1);
        Thread.sleep(1000);

        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        WebElement courseAdmin1 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js1.executeScript("arguments[0].click()", courseAdmin1);

        Thread.sleep(1500);
        Wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Checklist Admin")));
        Thread.sleep(1500);
        driver.findElement(By.partialLinkText("Checklist Admin")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(courseId);

        Thread.sleep(2500);
        if(driver.getPageSource().contains(courseId)){
            System.out.println("THe test is successful");
        }
        else{
            Assert.fail("The test failed");
        }


        driver.quit();
    }
}
