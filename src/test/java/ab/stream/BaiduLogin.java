package ab.stream;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;

/**
 * Created by zs on 2017/9/25.
 */
public class BaiduLogin {

    public static void main(String[] args) throws ParseException {

        WebDriver driver = WebDriverFactory.getWebDriver("firefox");//firefox,chrome
        //1 . 登录京东
        String 登录页面 = "https://passport.jd.com/new/login.aspx?ReturnUrl=https%3A%2F%2Fsale.jd.com%2Fact%2FIijS2N1vL" +
                "HhrfdE.html%3Fcu%3Dtrue%26utm_source%3Dbaidu-search%26utm_medium%3Dcpc%26utm_campaign%3Dt_262767352_ba" +
                "idusearch%26utm_term%3D13231631448_0_7353d5762d864c30918c9ce0a67d16cf";
        driver.get(登录页面);
        WebElement login = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[2]/a"));
        login.click();
        WebElement username = driver.findElement(By.xpath("//*[@id=\"loginname\"]"));
        username.sendKeys("XXXXXX");
        WebElement pwd = driver.findElement(By.xpath("//*[@id=\"nloginpwd\"]"));
        pwd.sendKeys("XXXXXXX");
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"loginsubmit\"]"));
        submit.click();
        //2  获取当前需要抢的劵的页面
        String 劵的页面 = "https://sale.jd.com/act/IijS2N1vLHhrfdE.html?cu=true&utm_source=baidu-search&utm_medium" +
                "=cpc&utm_campaign=t_262767352_baidusearch&utm_term=13231631448_0_7353d5762d864c30918c9ce0a67d16cf";
        driver.get(劵的页面);
        WebElement 劵的地址 = driver.findElement(By.xpath("//*[@id=\"29247292\"]/div[1]/div/div/div/ul/li/a[2]/div"));
        while (true) {
            劵的地址.click();
            Alert 弹出提示暂未开始 = driver.switchTo().alert();
            if (弹出提示暂未开始 != null) {
                弹出提示暂未开始.accept();
            }
        }

    }
}
