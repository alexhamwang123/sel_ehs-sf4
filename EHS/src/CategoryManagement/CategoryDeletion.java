package CategoryManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test(priority = 5)
public class CategoryDeletion {
    public void CategoryDeletion() throws IOException,InterruptedException {


        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();



        WebDriverWait Wait= new WebDriverWait(driver,30);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
        try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
            actions.build().perform(); }
        catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }
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

        //Click to Expand and show Classroom sub category
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div[1]/div/span"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div[1]/div/span")).click();

        //Delete the Classroom New Sub Category
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div[2]/ul/li/div/div/div/div/button[3]")).click();
        Thread.sleep(1000);
        //Click OK btn
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[2]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[2]")).click();

        //Delete the Classroom New  Category
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Category Management"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div/div/div/div/button[3]")).click();

        //Click OK btn
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/button[2]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/button[2]")).click();
        Thread.sleep(3000);


        //Click to Expand and show Online sub category
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div[1]/div/span"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div[1]/div/span")).click();

        //Delete the Online New Sub Category
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div[2]/ul/li/div/div/div/div/button[3]")).click();
        Thread.sleep(1000);
        //Click OK btn
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/button[2]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/button[2]")).click();

        //Delete the Online New  Category
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Category Management"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div/div/div/div/button[3]")).click();

        //Click OK btn
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/button[2]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/button[2]")).click();
        Thread.sleep(3000);
    }

    }
