package com.OnlineCourseManagement;


import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;


//@Test
@Test(priority=37)
public class OnlineCourseEditContent {
    public void OnlineCourseEditContent() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
        js.executeScript("arguments[0].click()", courseAdmin);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
        Thread.sleep(1500);

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("secondmenu")));
        actions.click();
        actions.sendKeys("online");
        actions.build().perform();
        driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();
        Thread.sleep(1500);
        driver.findElement(By.className("editAction")).click();
        Thread.sleep(3500);
        driver.findElement(By.name("detailCourseNo")).clear();
        driver.findElement(By.name("detailCourseNo")).sendKeys("online testing kevin");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("detailCourseTitle")).clear();
        driver.findElement(By.id("detailCourseTitle")).sendKeys("online testing kevin");
        Thread.sleep(1000);
        driver.findElement(By.id("detailInstructionalText")).clear();
        driver.findElement(By.id("detailInstructionalText")).sendKeys("gratz dude");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String description = generator.generate(30);
        driver.findElement(By.name("detailCourseDescription")).clear();
        driver.findElement(By.name("detailCourseDescription")).sendKeys("editing this with a randomly generated description: " + description);
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit Content and Quiz']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"question_sortable\"]/tr[1]/td[6]/button[2]")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[onclick*='createPage'][value='Create Page']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"question_sortable\"]/tr/td[3]/button")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"915d8c7314d1e2c0011512a0784d1726\"]/img")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("courseContentTitle")).sendKeys("online testing kevin course title");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[value='Save & Back']")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("addQBtn")).click();
        Thread.sleep(1500);
        js.executeScript("tinyMCE.activeEditor.setContent('online testing kevin for tinyMCE!')");
        Thread.sleep(500);
        driver.findElement(By.name("courseQuizAnswer1")).sendKeys("this is the correct answer");
        driver.findElement(By.name("courseQuizAnswer2")).sendKeys("no this is the correct answer");
        driver.findElement(By.name("courseQuizCorrectAnswer")).sendKeys("2");
        driver.findElement(By.name("courseQuizSourcePage")).sendKeys("1");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[value='Save & Back']")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("saveBtn")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[value='Back']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[value='Back']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[value='Back']")).click();
        Thread.sleep(3500);
        driver.quit();






    }

}
