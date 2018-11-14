package com.ChecklistManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test(groups = "ehs1",priority=5)
public class CreateChecklist {

	public void CreateChecklist() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);
		//driver.manage().window().maximize();
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();;
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("login_login_id")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);

		driver.findElement(By.name("submit")).click();
		
		Thread.sleep(4500);
		
		WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;

		js.executeScript("arguments[0].click();", courseAdmin);

		Thread.sleep(1500);
		driver.findElement(By.partialLinkText("Checklist Management")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
		Thread.sleep(3500);
		String courseId = generator.generate(10);
		driver.findElement(By.name("detailCheckListCode")).sendKeys(courseId);
		new Select(driver.findElement(By.id("detailCategoryType"))).selectByVisibleText("Survey_Only_New");
		new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
		new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
		Thread.sleep(3000);
		driver.findElement(By.id("saveBtn")).click();
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("detailCheckListTitle")).sendKeys("test checklist title");
		driver.findElement(By.id("detailCheckListHeader")).sendKeys("test checklist header");
		driver.findElement(By.id("detailCheckListDescription")).sendKeys("test checklist description");
		driver.findElement(By.id("detailCheckListFooter")).sendKeys("test checklist footer");
		driver.findElement(By.id("detailInstructionalText")).sendKeys("gratz dude");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("createContent")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[type='submit'][value='Create']")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("saveBtn")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("fancyConfirm_ok")).click();
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
		Thread.sleep(3500);
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
		Thread.sleep(3500);
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
		Thread.sleep(7000);
		driver.findElement(By.id("langIsViewable")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("detailIsActive")).click();
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
		Thread.sleep(1500);
		driver.findElement(By.partialLinkText("Courses")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("srch_fld")).sendKeys(courseId);
		driver.findElement(By.name("searchButton")).click();
		Thread.sleep(1500);
		driver.findElement(By.className("onelang")).click();
		Thread.sleep(10000);

		driver.findElement(By.id("crselink1")).click();
		Thread.sleep(3500);
		driver.quit();



	}

}
