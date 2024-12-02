import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Scrolling {


    //scrolling to down page
    public void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
        } catch (Exception e) {
            System.out.println("Scroll failed: " + e.getMessage());
        }
    }
}