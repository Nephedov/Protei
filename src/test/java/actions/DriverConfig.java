package actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverConfig {
    public WebDriver driver;
    public final static int TIMEOUT = 8;
    public String pathToFile = "file:///C:/Users/Home/ProteiTmp/qa-test.html";

    public WebDriver setUpDriverChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get(pathToFile);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        return driver;
    }
}
