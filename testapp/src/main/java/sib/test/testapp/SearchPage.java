package sib.test.testapp;

import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SearchPage{
	private WebDriver driver;
	private String request;
	private ArrayList<String> arrTOBase = new ArrayList<String>();
	private int visioFlag;
	
	
	public SearchPage() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\res\\chromedriver.exe");
		checkVisio();
		if (visioFlag == 1){
				this.driver = new ChromeDriver();
		}else
			hideBrowser();
	}
	
	public void pushRequest(String request){
		this.request = request;
		sendSearchReq();
	}
	
	public ArrayList<String> getContent(){
		return arrTOBase;
	}
	
	private void hideBrowser() {
		 ChromeOptions options = new ChromeOptions();
		 options.setHeadless(true);
		 this.driver = new ChromeDriver(options);

	}
	
	private void sendSearchReq() {
		driver.get("http://www.yandex.ru");
        driver.findElement(By.name("text")).sendKeys(request + "\n");
        String ad =   driver.findElements(By.className("serp-item")).get(randInt()).getText();
        creatMap(ad);
        driver.quit();
	}
	
	private void creatMap(String ad) {
		for (String str: ad.split("\n")) {
			arrTOBase.add(str);
		}
	}
	
	private int randInt() {
		Random random = new Random();
		return random.nextInt(10);
		
	}
	
	private void checkVisio() {
		visioFlag = App.checkVisio();
	}
	
}
