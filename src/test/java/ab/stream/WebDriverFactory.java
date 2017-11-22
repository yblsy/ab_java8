package ab.stream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by wxy on 2017/7/25.
 */
public class WebDriverFactory {

    public static WebDriver getWebDriver(String name){
        WebDriver driver = null;
        if(name.equals("firefox")) {
            System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");

            driver = new FirefoxDriver();
        }else if(name.equals("chrome")) {
            System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        //driver.manage().window().maximize();

        return driver;
    }
}
