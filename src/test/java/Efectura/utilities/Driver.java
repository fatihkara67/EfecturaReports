package Efectura.utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {


    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<WebDriver>();

    private Driver() {
    }
    /**
     * Senkronize edilmiş yöntem iş parçacığını güvenli hale getirir.
     * Aynı anda sadece 1 thread'in kullanabilmesini sağlar.
     * İplik güvenliği performansı azaltır ancak her şeyi güvenli hale getirir.
     *
     * @return Web Sürücüsü
     */

    public synchronized static WebDriver getDriver() {
        /*
         * Öncelikle bir web sürücüsü nesnesinin var olup olmadığını kontrol ediyoruz,
         * değilse, bu yöntem onu yaratacaktır.
         *
         */
        if (driverPool.get() == null) {
            /*
             * Configuration.properties dosyasında tarayıcının tipini belirttik.
             * Daha sonra bu bilgileri Properties sınıfı nesnesine yüklüyoruz.
             * bir FileInputStream sınıfı nesnesi başlatarak.
             * Burası ConfigurationReader.java sınıf nesnesini kullanarak tarayıcı tipini alacağımız yerdir.
             */
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");

                    //     chromeOptions.setBinary("C:\\Users\\Sema\\Desktop\\PROGRAMLAR\\SELENİUM\\");
                    chromeOptions.addArguments("--disable-cache");
                    chromeOptions.addArguments("--start-maximized");

//                    chromeOptions.addArguments("--headless");
//                    chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                    chromeOptions.addArguments("--window-size=1920,1080");
//                    chromeOptions.addArguments("--disable-gpu");
//                    chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//                    chromeOptions.setExperimentalOption("useAutomationExtension", false);
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    //WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "safari":
                    driverPool.set(new SafariDriver());
                    driverPool.get().manage().window().maximize();
                    break;
                case "chromeheadless":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless"); // Headless modu etkinleştir
                    options.addArguments("--disable-gpu"); // GPU kullanımını devre dışı bırak
                    driverPool.set(new ChromeDriver(options));
                    break;
                default:
                    throw new RuntimeException("Wrong browser name !");
            }
        }
        return driverPool.get();
    }


    public static void closeDriver() {
        if (driverPool != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
