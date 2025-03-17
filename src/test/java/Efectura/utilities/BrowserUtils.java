package Efectura.utilities;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.util.NoSuchElementException;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;


/**
 * A utility class containing helper methods for common browser related operations.
 */

public class BrowserUtils {
    /**
     * Geçerli web sayfasının ekran görüntüsünü alır ve ekran görüntüsünün dosya yolunu döndürür.
     *
     * @param name Ekran görüntüsü dosyasına verilecek ad.
     * @return Ekran görüntüsünün dosya yolu.
     */
    public static String getScreenshot(String name) {
        // Adding date and time to the screenshot name to make it unique
        name = new Date().toString().replace(" ", "_").replace(":", "-") + "_" + name;
        String path;
        // Determining the file path based on the operating system
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            path = System.getProperty("user.dir") + "/test-output/screenshots/" + name + ".png";
        } else {
            path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + name + ".png";
        }
        TakesScreenshot screenshot = (TakesScreenshot) Driver.getDriver();
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(path);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    /**
     * Belirtilen web öğesinin sayfada görüntülenip görüntülenmediğini doğrular.
     * Eğer öğe görüntülenmiyorsa, test verilen mesajla başarısız olacaktır.
     *
     * @param element Görünürlüğü kontrol edecek web elementi.
     * @param message Elemanın görünmemesi durumunda görüntülenecek mesaj.
     */
    public static void verifyElementDisplayed(WebElement element, String message) {
        try {
            assertTrue(message, element.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail(message);
        }
    }

    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verilen web öğesine çift tıklama gerçekleştirir.
     *
     * @param element Çift tıklanacak web elementi.
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }
    /**
     * Kaynak elemandan hedef elemana sürükle bırak işlemi gerçekleştirir.
     *
     * @param source Sürüklenecek kaynak öğe.
     * @param target Üzerine bırakılacak hedef öğe.
     */
    public static void dragAndDrop(WebElement source, WebElement target) {
        //bir öğenin bir başka öğene sürüklenmesi işlemidir.
        Actions actions = new Actions(Driver.getDriver());
        actions.dragAndDrop(source, target).build().perform();
    }
    /**
     * Belirtilen web öğesi üzerinde fareyle üzerine gelme eylemi gerçekleştirir.
     *
     * @param element Üzerine gelinecek web elementi.
     */
    public static void hoverOver(WebElement element) {
        //üzerine gelmek veya odaklanmak için kullanılIR
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).build().perform();
    }
    /**
     * mouse  işaretçisini belirtilen web öğesine taşır.
     *
     * @param element Fare işaretçisinin taşınacağı web öğesi.
     */
    public static void moveToElement(WebElement element) {
        //üzerine gelmek veya odaklanmak için kullanılIR
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).build().perform();
    }
    /**
     * Geçerli iş parçacığını belirli bir saniye boyunca duraklatır.
     *
     * @param secs İş parçacığının duraklatılacağı saniye sayısı.
     */
    public static void wait(int secs) {
        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Geçerli iş parçacığını belirli bir saniye boyunca duraklatır.
     *
     * @param seconds İş parçacığının duraklatılacağı saniye sayısı.
     * Eğer iş parçacığı uyurken kesilirse @, InterruptedException'ı atar
     */
    public static void waitSeconds(int seconds) throws InterruptedException {
        long millis = seconds * 1000L;
        long startTime = System.currentTimeMillis();
        long remainingTime = millis;

        while (remainingTime > 0) {
            try {
                Thread.sleep(remainingTime);
            } catch (InterruptedException e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                remainingTime = millis - elapsedTime;
                // Re-interrupt the thread to propagate the interruption
                Thread.currentThread().interrupt();
                throw e;
            }
            remainingTime = millis - (System.currentTimeMillis() - startTime);
        }
    }
    /**
     * Başlığına göre bir hedef pencereye gider
     *
     * @param targetTitle hedef pencerenin başlığı
     */
    public static void navigateToWindow(String targetTitle) {
        //arklı tarayıcı pencereleri veya sekmeleri arasında geçiş yapmak için kullanılan bir işlemdir.
        String currentWindow = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(currentWindow);
    }
    /**
     * Belirtilen öğenin görünür olmasını bekler
     *
     * @param element - Beklenecek WebElement
     * @param timeToWaitInSec - Saniye cinsinden beklenecek süre
     * @return Görünür WebElement
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        //bir web sayfasındaki bir öğenin görünürlüğünü beklemek için kullanıLIR
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), ofSeconds(timeToWaitInSec) );
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * Bir öğenin görünür olmasını bekler
     *
     * @param locator - öğe bulucu
     * @param timeToWaitInSec - saniye cinsinden bekleme süresi
     * @return WebElement - görünür öğe
     */
    public static WebElement waitForVisibility(By locator, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    /**
     * Öğenin tıklanabilir olmasını bekler
     *
     * @param element - tıklanacak WebElement
     * @param timeToWaitInSec - saniye cinsinden zaman aşımı
     * @return WebElement
     */
    public static WebElement waitForClickability(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * Bir öğenin tıklanabilir olmasını bekler
     *
     * @param locator
     * @param timeToWaitInSec
     * @return WebElement
     */
    public static WebElement waitForClickable(By locator, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    /**
     * Selects an option from a dropdown by visible text.
     *
     * @param dropdownElement The dropdown WebElement.
     * @param optionToSelect  The option to select by visible text.
     */
    public static void selectDropdownOptionByVisibleText(WebElement dropdownElement, String optionToSelect) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(optionToSelect);
    }
    /**
     * Selects an option from a dropdown by index.
     *
     * @param dropdownElement The dropdown WebElement.
     * @param index           The index of the option to select.
     */
    public static void selectDropdownOptionByIndex(WebElement dropdownElement, int index) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }
    /**
     * Selects an option from a dropdown by value attribute.
     *
     * @param dropdownElement The dropdown WebElement.
     * @param value           The value attribute of the option to select.
     */
    public static void selectDropdownOptionByValue(WebElement dropdownElement, String value) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }
    /**
     * Adına veya kimliğine göre belirtilen çerçeveye geçer.
     *
     * @param frameNameOrId geçiş yapılacak çerçevenin adı veya kimliği
     */
    public static void switchToFrame(String frameNameOrId) {
        Driver.getDriver().switchTo().frame(frameNameOrId);
    }

    /**
     * Belirtilen metinle başlayan dinamik bir öğeye tıklar.
     *
     * @param searchText başlangıcında aranacak metni yazın
     */

    public static void clickDynamicElementStartsWith(String searchText) {
        String xpath = "//*[starts-with(text(),'" + searchText + "')]";
        WebElement element = Driver.getDriver().findElement(By.xpath(xpath));
        element.click();
    }
    /**
     * belirtilen nitelik değerini içeren dinamik bir öğeye tıklayın.
     *
     * @param attributeName aranacak özelliğinin adını belirtir
     * @param attributeValue aranacak özelliğin değerinin
     */
    public static void clickDynamicElementByAttribute(String attributeName, String attributeValue) {
        String xpath = "//*[@"+attributeName+"='"+attributeValue+"']";
        WebElement element = Driver.getDriver().findElement(By.xpath(xpath));
        element.click();
    }
    /**
     * Bir uyarıyı kabul eder ve metnini döndürür.
     *
     * @return uyarı metnini
     */
    public static String handleAlertAcceptByReturningMessage() {
        Alert alert = Driver.getDriver().switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }
    /**
     * Bir uyarıyı kabul eder
     *
     * @return uyarı metnini
     */
    public static void handleAlertAccept() {
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.accept();
    }
    /**
     * Bir uyarıyı reddeder ve metnini döndürür.
     *
     * @return uyarı metnini
     */
    public static String handleAlertDismissByReturningMessage() {
        Alert alert = Driver.getDriver().switchTo().alert();
        String text = alert.getText();
        alert.dismiss();
        return text;
    }/**
     * Bir uyarıyı reddeder ve metnini döndürür.
     *
     * @return uyarı metnini
     */
    public  static void handleAlertDismiss() {
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.dismiss();
    }
    /**
     * Belirtilen başlığa sahip tarayıcı sekmesine geçiş yapar.
     *
     * @param title Geçiş yapılacak tarayıcı sekmesinin başlığı.
     */
    public static void switchToTab(String title) {
        WebDriver driver = Driver.getDriver();
           // Tüm açık pencere tanıtıcılarının listesini alın
        for (String windowHandle : driver.getWindowHandles()) {
            // Her pencere tanıtıcısına geçin ve başlığını kontrol edin
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(title)) {
                // Başlık eşleşirse döngüden çık
                break;
            }
        }
    }
    public static void selectOption(WebElement selectElement, String value) {
        Select select = new Select(selectElement);
        select.selectByValue(value);
    }

    // Seçenek seçilmiş mi kontrol etme metodunu tanımla
    public static boolean isOptionSelected(WebElement selectElement, String value) {
        Select select = new Select(selectElement);
        return select.getFirstSelectedOption().getAttribute("value").equals(value);
    }


    private static final String SELDON_API_TOKEN = "perm:ZmF0aWgua2FyYQ==.NjgtMg==.YEqj0eDVyQ9kLm579EOehZIHEzQzDE";
    private static final String TELEGRAM_BOT_TOKEN = "6538211561:AAEVRYoo03lBKnqhTUUU3lne9nfvpRGHa08";
    private static final String TELEGRAM_CHAT_ID = "-4194828120";

    // Dosyayı Telegram'a gönderme
    public static void sendToTelegram(String filePath) {
        try {
            // Telegram API URL
            URL url = new URL("https://api.telegram.org/bot" + TELEGRAM_BOT_TOKEN + "/sendDocument");

            // HttpURLConnection nesnesi oluşturuluyor
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Multipart form-data gönderimi
            String boundary = "===" + System.currentTimeMillis() + "===";
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            try (OutputStream outputStream = connection.getOutputStream();
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"))) {

                // Form verilerini yazıyoruz (chat_id)
                writer.append("--" + boundary).append("\r\n");
                writer.append("Content-Disposition: form-data; name=\"chat_id\"").append("\r\n");
                writer.append("Content-Type: text/plain; charset=UTF-8").append("\r\n");
                writer.append("\r\n");
                writer.append(TELEGRAM_CHAT_ID).append("\r\n");

                // Dosyayı yazıyoruz
                writer.append("--" + boundary).append("\r\n");
                writer.append("Content-Disposition: form-data; name=\"document\"; filename=\"" + new File(filePath).getName() + "\"").append("\r\n");
                writer.append("Content-Type: application/octet-stream").append("\r\n");
                writer.append("\r\n");
                writer.flush();
                Files.copy(new File(filePath).toPath(), outputStream);
                outputStream.flush();

                // Formun sonunu belirtiyoruz
                writer.append("\r\n");
                writer.append("--" + boundary + "--").append("\r\n");
                writer.flush();
            }

            // Yanıtı kontrol ediyoruz
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("File " + filePath + " sent to Telegram successfully.");
            } else {
                System.out.println("Failed to send " + filePath + " to Telegram. Status Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageToTelegram(String message, String chatId) {
        try {
            // Telegram API URL
            String urlString = "https://api.telegram.org/bot" + TELEGRAM_BOT_TOKEN + "/sendMessage";

            // URL bağlantısı oluştur
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            // Mesajı JSON formatında gönderme
            String postData = "chat_id=" + URLEncoder.encode(chatId, "UTF-8") +
                    "&text=" + URLEncoder.encode(message, "UTF-8");

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(postData.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            }

            // Yanıtı kontrol et
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Message sent to Telegram successfully: " + message);
            } else {
                System.out.println("Failed to send message to Telegram. Status Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JSONObject executeCurl(String curlCommand) throws JSONException {
        StringBuilder output = new StringBuilder();
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder processBuilder;

            if (os.contains("win")) {
                processBuilder = new ProcessBuilder("cmd.exe", "/c", curlCommand);
            } else {
                processBuilder = new ProcessBuilder("bash", "-c", curlCommand);
            }

            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Curl command failed with exit code " + exitCode);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error executing curl command", e);
        }

        return new JSONObject(output.toString());
    }

    public static JSONObject getRecordsFiltered(String flowName, String cookie) {
        try {
            URL obj = new URL("https://diageo.efectura.com/Task/GetActiveUserTask");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
            con.setRequestProperty("Cookie", cookie);

            // Gönderilecek veri
            con.setDoOutput(true);
            String postData = "length=1&AnySearch=" + flowName;
            con.getOutputStream().write(postData.getBytes(StandardCharsets.UTF_8));

            // Yanıtı oku
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // JSON Parse işlemi
            JSONObject json = new JSONObject(response.toString());
            return json;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hata oluştu: " + e.getMessage());

        }
        return null;
    }


}




