package utils;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KeyWords {
	static WebDriver driver;
	static WebDriverWait wait;
	
	public static void initialize(String browser) {
		switch(browser) {
		case "gc": case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "ff": case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	
	public static void navigate(String url) {
		driver.get(url);
	}
	
	public static void type(By loc, String data) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
		driver.findElement(loc).clear();
		driver.findElement(loc).sendKeys(data);
	}
	
	public static void click(By loc) {
		wait.until(ExpectedConditions.elementToBeClickable(loc));
		driver.findElement(loc).click();
	}
	
	public static void select_by_value(By loc, String data) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
		Select s = new Select(driver.findElement(loc));
		s.selectByValue(data);
	}
	
	public static void switchWindowByUrlContains(String url) {
		Set<String> winids = driver.getWindowHandles();
		for(String id:winids) {
			driver.switchTo().window(id);
			if(driver.getCurrentUrl().contains(url))
				break;
		}
	}
	
	public static void submit(By loc) {
		driver.findElement(loc).submit();
	}
	
	public static void switchFrame(By loc) {
		driver.switchTo().frame(driver.findElement(loc));
	}
	
	public static void switchToDefault() {
		driver.switchTo().defaultContent();
	}
	
	public static void acceptAlert() {
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}
	
	public static void quit() {
		driver.quit();
	}

	public static void assertText(By loc, String string) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(loc, string));
	}

	public static void assertTitle(String string) {
		// TODO Auto-generated method stub
		wait.until(ExpectedConditions.titleIs(string));
	}
	
	
}
