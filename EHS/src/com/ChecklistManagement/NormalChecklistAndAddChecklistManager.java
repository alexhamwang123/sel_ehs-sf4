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
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();;

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

        js.executeScript("arguments[0].click()", courseAdmin);

        Thread.sleep(1500);
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Admin")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button"))));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(3500);
                String CapitalLetter = generator.generate(1).toUpperCase();
        String courseId = generator.generate(10);
        courseId=CapitalLetter.concat(courseId);
        System.out.println(courseId);

        System.out.println(courseId);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"saveBtn\"]"))));

        driver.findElement(By.name("detailCheckListCode")).sendKeys(courseId);
        new Select(driver.findElement(By.id("detailCategoryType"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
        new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("selectBtnCreMaA")));
        Thread.sleep(1500);
        driver.findElement(By.id("selectBtnCreMaA")).click();
        Thread.sleep(1500);

        Wait.until(ExpectedConditions.elementToBeClickable(By.name("badgeNo")));
        Thread.sleep(1500);
        driver.findElement(By.name("badgeNo")).sendKeys("X00002380");

        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][value='Search']")));
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
        Thread.sleep(1500);

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"teammanager_result\"]/div/table/tbody/tr/td[1]/a")));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"teammanager_result\"]/div/table/tbody/tr/td[1]/a")).click();
        Thread.sleep(3500);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        Thread.sleep(3000);

        WebElement Logout0=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()", Logout0);
        Thread.sleep(1500);
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/img"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/img")).click();


        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("username"))));
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys("X00002380");
        driver.findElement(By.id("password")).sendKeys("X00002380");
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

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div/div[2]/div/form/div[1]/div/div[5]/input")));
        Thread.sleep(1500);
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/form/div[1]/div/div[5]/input")).sendKeys(courseId);

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input")));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input")).click();
        Thread.sleep(4500);
        if(driver.getPageSource().contains(courseId)){
            System.out.println("THe test is successful");
        }
        else{
            Assert.fail("The test failed");
        }


        driver.quit();
    }
}
