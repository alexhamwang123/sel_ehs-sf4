package com.ChecklistManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class FinalDisabledCourseIfNeed {

    public void FinalDisabledCourseIfNeed() throws InterruptedException, IOException, AWTException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

        Robot robot = new Robot();
        robot.setAutoDelay(250);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_TAB);

        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);

        JavascriptExecutor js = (JavascriptExecutor)driver;

        WebElement courseAdmin = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
        js.executeScript("arguments[0].click()", courseAdmin);

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[2]")).click();
        Thread.sleep(1500);

        Thread.sleep(1500);// find element
        WebElement inputOne = driver.findElement(By.className("form-control"));

// to set focus?
        inputOne.click();
        // erase any existing value (because clear does not send any events
        for (int i = 0; i < inputOne.getAttribute("value").length(); i++) {
            inputOne.sendKeys(Keys.BACK_SPACE);
        }

// type in value
        inputOne.sendKeys("Category");


// click away to fire blur event
        driver.findElement(By.className("form-control")).click();
        Thread.sleep(1500);
        WebElement inputSec = driver.findElement(By.className("form-control"));
// to set focus?
        inputSec.click();
        for (int i = 0; i < inputSec.getAttribute("value").length(); i++) {
            inputSec.sendKeys(Keys.BACK_SPACE);
        }
        inputSec.sendKeys(Keys.TAB);
//        Survey_Only_New
        new Select(driver.findElement(By.id("secondmenu")).findElement(By.id("searchCategory"))).selectByVisibleText("Survey_Only_New");//By.xpath("//option[@value='f9bbb962d5b1aa58b2115a7bf3b4c9a8']")));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div/div/input")).click();
        Thread.sleep(1500);

        java.util.List<WebElement> myResults;

        myResults = driver.findElements(By.tagName("td"));
        Boolean isBreak=false;
        int $i = 0;
if (myResults.size() > 0){
    System.out.println("There is one record above.");
//    for (WebElement myResult : myResults) {
    for(int $p=0; $p<myResults.size();$p++){
        if(isBreak) break;
    WebElement myResult4 = (WebElement) myResults.get($p);
    String idstr2 = myResult4.getText();
    if("Active".equals(idstr2)) {
        int $k=0;
        $k = $p;
        $p = $p - 4;

        WebElement myResult5 = (WebElement) myResults.get($p);
        String idstr5 = myResult5.getText();

        java.util.List<WebElement> myResults3a = driver.findElements(By.tagName("a"));
        int $j = 0;
        $p = $k;
        if (myResults3a.size() > 0) {
            for (WebElement myResult3a : myResults3a) {
                String idstr3a = myResult3a.getText();
                String id3a = myResult3a.getAttribute("id");

                if (idstr5.equals(idstr3a)) {

                    driver.findElement(By.xpath("//a[@id='" + id3a + "']")).click();
                    Thread.sleep(1500);
                    driver.findElement(By.name("detailIsActive")).click();
                    Thread.sleep(1500);
                    driver.findElement(By.id("langIsViewable")).click();
                    Thread.sleep(1500);
                    driver.findElement(By.cssSelector("input[type='submit'][value='Yes']")).click();
                    Thread.sleep(2500);
                    driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
                    Thread.sleep(1500);
                    driver.findElement(By.partialLinkText("Courses")).click();
                    Thread.sleep(1500);
                    driver.findElement(By.id("srch_fld")).sendKeys(idstr3a);
                    Thread.sleep(1500);
                    driver.findElement(By.name("searchButton")).click();
                    Thread.sleep(1500);
                    String currentWin = driver.getWindowHandle();
                    try {
                        driver.findElement(By.className("onelang")).click();
                    } catch (NoSuchElementException e) {
                        isBreak = true;
                        break ;
                    }
                    Thread.sleep(1500);
                }
            }
            $j++;
        }
    $p++;
    }
    }
}
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_G);

        Thread.sleep(1500);

        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_V);

        Thread.sleep(1500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1500);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(1500);

        driver.quit();

        }

}
