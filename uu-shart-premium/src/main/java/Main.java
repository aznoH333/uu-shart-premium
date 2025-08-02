import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("https://en.wikipedia.org/wiki/Menger_sponge");

        String title = driver.getTitle();
        System.out.println("Hello, World!" + title);
    }
}