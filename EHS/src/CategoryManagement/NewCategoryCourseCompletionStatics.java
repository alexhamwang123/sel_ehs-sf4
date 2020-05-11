package CategoryManagement;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
@Test(priority = 4)
public class NewCategoryCourseCompletionStatics {
    public void NewCategoryCourseCompletionStatics() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

        //Clicking on EHS Reports under Reports
        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'EHS Reports')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",ele);
        Thread.sleep(1500);
        //Click on Course Completion Statistic
        driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[2]")).click();
        Thread.sleep(4500);

        //Select a Date Range. Click on Date From
        WebElement From= driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[1]/div/div/div[1]/div[1]/input"));
        js.executeScript("arguments[0].value='01/01/2019'",From);
        Thread.sleep(500);
        WebElement To=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[1]/div/div/div[2]/div[1]/input"));
        js.executeScript("arguments[0].value='01/04/2019'",To);

        //Click Course Type Button
        WebElement CourseTypeBtn=driver.findElement(By.xpath("//label[contains(text(),'Course Type')]"));
        js.executeScript("arguments[0].click();",CourseTypeBtn);

        Thread.sleep(1000);

        //Select Course Type
        new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[11]/div[1]/select[1]"))).selectByVisibleText("Classroom");
        //Select Course Category
        new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[11]/div[2]/select[1]"))).selectByVisibleText(CreateAndEditCategory.CLassroom_Sub_Category_Name);

        //Date
        //Click From
        //CLick Jan 01
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/div/div[1]/div[1]/input")).click();

        WebElement Jan= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/div/div[1]/div[3]/span[1]"));
        js.executeScript("arguments[0].click();",Jan);
        Thread.sleep(2000);
        WebElement Date_01= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/div/div[1]/div[2]/div/span[11]"));
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js1.executeScript("arguments[0].click();",Date_01);
        Thread.sleep(2000);

        //Click To
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/div/div[2]/div[1]/input")).click();
        //Click March 03
        WebElement March= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/div/div[2]/div[3]/span[3]"));
        js.executeScript("arguments[0].click();",March);

        WebElement Date_02= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/div/div[2]/div[2]/div/span[10]"));
        JavascriptExecutor js2=(JavascriptExecutor)driver;
        js2.executeScript("arguments[0].click();",Date_02);


        //Click on Go to display the complete Course Completion Statistic
        WebElement Go=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[13]/div/button[1]"));
        js.executeScript("arguments[0].click();",Go);

        Thread.sleep(1500);

        //Scroll down
        JavascriptExecutor js3 = ((JavascriptExecutor) driver);
        js3.executeScript("window.scrollBy(0,250)", "");


        Thread.sleep(2000);



    }

}
