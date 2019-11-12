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

public class ChecklistPrereqComplete {
    public void CompletetheprerequisitebeforetakingChecklist() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 30);
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


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[2]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[2]")).click();
        Thread.sleep(5500);

        //pick a class  & Assign prerequisite
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys("1bLkHwGarU");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Go']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Go']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("editAction"))));
        Thread.sleep(1000);
        driver.findElement(By.className("editAction")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))));
        Thread.sleep(1000);
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))).selectByVisibleText("Online Course 1 - Online Course 1");
        Thread.sleep(3500);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("1bLkHwGarU");

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
