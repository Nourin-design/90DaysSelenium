package day2.Nykaa1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class NykaaPrj1 {
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		1)Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");

// 		2) Mouseover on Brands and Mouseover on Popular
		WebElement brands = driver.findElementByXPath("//a[text()='brands']");
		Actions act = new Actions(driver);
		act.moveToElement(brands).perform();
		act.moveToElement(driver.findElementByXPath("//a[text()='Popular']")).pause(1000).perform();
		// 3) Click L'Oreal Paris
		act.click(driver.findElementByXPath("//li[@class='brand-logo menu-links'][5]//img")).perform();

		// 4) Go to the newly opened window and check the title contains L'Oreal Paris
		// to get the unique reference of the window opened by the current window
		// get the windowhandles by using SET
		Set<String> window1 = driver.getWindowHandles();
		System.out.println(window1.size());

		// To get a particular window id from SET & convert to list
		List<String> listOfWindows = new ArrayList<String>(window1);
//	listOfWindows.addAll(window1);

		// To switch the cursor from parent window to child window
		driver.switchTo().window(listOfWindows.get(1));
		// get the title of current page
		String title = driver.getTitle();
		System.out.println(title);
		if (title.contains("L'Oreal Paris")) {
			System.out.println("Title contains L'Oreal Paris");
		} else {
			System.out.println("Wrong title");
		}

		// 5) Click sort By and select customer top rated
		driver.findElementByXPath("//span[text()='Sort By : ']").click();
		Thread.sleep(2000);
		// driver.findElementByXPath("//span[@class='value']").click();
		driver.findElementByXPath("//span[text()='customer top rated']").click();

		// 6) Click Category and click Shampoo
		Thread.sleep(2000);
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("(//div[@class='control__indicator'])[30]").click();

		// 7) check whether the Filter is applied with Shampoo
		String filter = driver.findElementByXPath("//li[text()='Shampoo']").getText();
		if (filter.contains("Shampoo")) {
			System.out.println("Filter applied for Shampoo");
		} else {
			System.out.println("Filter is not applied");
		}
		Thread.sleep(2000);
		// 8) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElementByXPath("//span[contains(text(), 'Paris Colour Protect Shampoo')]").click();

		// 9) GO to the new window and select size as 175ml
		// Window Handling

		Set<String> window2 = driver.getWindowHandles();
		// System.out.println(window2.size());

		// To get a particular window id from SET & convert to list
		List<String> listOfWindows2 = new ArrayList<String>(window2);
		// listOfWindows.addAll(window2);

		// To switch the cursor from parent window to child window
		driver.switchTo().window(listOfWindows2.get(2));

		// driver.switchTo().window(secondWinList.get(2));
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='175ml']").click();

//		10) Print the MRP of the product
		String mrp = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		System.out.println(mrp);

//		11) Click on ADD to BAG
		driver.findElementByXPath(
				"//button[@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  ']")
				.click();
//		12) Go to Shopping Bag 
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
//		13) Print the Grand Total amount
		String grandTotal = driver.findElementByXPath("//div[@class='value medium-strong']").getText();
		System.out.println(grandTotal);

//		14) Click Proceed
		driver.findElementByXPath("//button[@class='btn full fill no-radius proceed ']").click();

//		15) Click on Continue as Guest
		driver.findElementByXPath("//button[@class='btn full big']").click();

//		16) Print the warning message (delay in shipment)
		String warningMessage = driver.findElementByXPath("//div[@class='message']").getText();
		System.out.println(warningMessage);
		Thread.sleep(2000);

//		17) Close all windows
		driver.quit();

	}
}
