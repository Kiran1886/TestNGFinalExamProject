package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {

	static WebDriver driver;
	static String browser;
	static String url;

	public static void readConfig() {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("src\\main\\java\\util\\config.properties"));

			Properties prop = new Properties();
			prop.load(reader);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static WebDriver Init() {
		
		readConfig();
		
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
			
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().window().maximize();				
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}

	public static boolean doesDataExist(String categoryName, List<String> actualList) {

		for (int i = 0; i < actualList.size(); i++) {
			if (categoryName.equalsIgnoreCase(actualList.get(i))) {
				return true;
			}

		}
		return false;

	}

	public static boolean doesDataMatch(List<String> months, List<String> dropDownList) {
		boolean dataMatch = false;

		for (int i = 0; i < dropDownList.size(); i++) {
			if (dropDownList.containsAll(months) && months.containsAll(dropDownList)) {
				dataMatch = true;
			}

		}
		return dataMatch;
	}

}
