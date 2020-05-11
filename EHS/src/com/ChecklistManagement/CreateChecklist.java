package com.ChecklistManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class CreateChecklist {

	public void CreateChecklist() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

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
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();;
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();


		WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;

		js.executeScript("arguments[0].click();", courseAdmin);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Admin"))));
		Thread.sleep(1000);
		driver.findElement(By.partialLinkText("Checklist Admin")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button"))));
		driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
		Thread.sleep(3500);
		String courseId = generator.generate(10);
		System.out.println(courseId);
		driver.findElement(By.name("detailCheckListCode")).sendKeys(courseId);
		new Select(driver.findElement(By.id("detailCategoryType"))).selectByVisibleText("EHS - Ergonomics");
		new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
		new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("saveBtn")));
		Thread.sleep(1000);
		driver.findElement(By.id("saveBtn")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Edit']")));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("detailCheckListTitle")).sendKeys("test checklist title");
		driver.findElement(By.id("detailCheckListHeader")).sendKeys("test checklist header");
		driver.findElement(By.id("detailCheckListDescription")).sendKeys("test checklist description");
		driver.findElement(By.id("detailCheckListFooter")).sendKeys("test checklist footer");
		driver.findElement(By.id("detailInstructionalText")).sendKeys("gratz dude");

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("createContent"))));
		Thread.sleep(1000);
		driver.findElement(By.id("createContent")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='submit'][value='Create']"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"detailChecklistContentSaveAs\"]")).click();
		driver.findElement(By.cssSelector("input[type='submit'][value='Create']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Edit']"))));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveBtn"))));
		Thread.sleep(1000);
		driver.findElement(By.id("saveBtn")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("fancyConfirm_ok"))));
		Thread.sleep(1000);
		driver.findElement(By.id("fancyConfirm_ok")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Back']"))));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Back']"))));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

		//driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
		//Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("langIsViewable")));
		Thread.sleep(1000);
		driver.findElement(By.id("langIsViewable")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("detailIsActive")));
		Thread.sleep(1000);
		driver.findElement(By.id("detailIsActive")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
		Thread.sleep(1000);
		driver.findElement(By.partialLinkText("Courses")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
		//driver.findElement(By.name("searchButton")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-success border-0']"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-success border-0']")).click();

		driver.quit();



	}

}
