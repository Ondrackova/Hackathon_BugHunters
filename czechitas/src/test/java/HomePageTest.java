import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest {

    //browser initialization
    WebDriver browser = WebDriverManager.chromedriver().create();
    //add time for waiting 5 s
    WebDriverWait browserWait = new WebDriverWait(browser, Duration.ofSeconds(5));

    Scrolling scrolling;

    //test which is started before each test
    @BeforeEach
    void beforeTest () {

        //loading the web
        browser.get("https://datoph2024-bug-hunters.czechitas.fun/en/");


        }

    //random surname generator
    String randomEmail = RandomEmailGenerator.randomNameGenerator();


    @Test
    void createUserAccount () {
        //click on button Sign in
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.cssSelector(".hide_xs")))
                .click();

        //click on field email address and fill valid email address
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath(".//*[@id='email_create']")))
                .sendKeys(randomEmail);

        //click on button Create an account
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("SubmitCreate")))
                .click();
        //fill in user first name
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("customer_firstname")))
                .sendKeys("Jana");
        //fill in user last name
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("customer_lastname")))
                .sendKeys("Novakova");
        //fill in password
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("passwd")))
                .sendKeys("Jana123");
        //click on button Register
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("submitAccount")))
                .click();

        var ExpectedOutput = browserWait.until
                (ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='center_column']/p[1]")))
                .getText();
        //Assert
        assertTrue(ExpectedOutput.contains("Your account has been created."),
                "The account creation success message did not appear as expected.");
    }

    @Test
    void loginLogoutUser() {
        // Click on the "Sign in" button
        browserWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".hide_xs"))).click();

        // Fill in the email address
        browserWait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).sendKeys("jana@seznam.cz");

        // Fill in the password
        browserWait.until(ExpectedConditions.elementToBeClickable(By.id("passwd"))).sendKeys("Jana123");

        // Click on the "Sign in" button
        browserWait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin"))).click();

        // Get the text of the "My personal information" link (or whatever text appears after logging out)
        WebElement myAccount = browserWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='center_column']/div/div[1]/ul/li[4]/a"))
        );

        System.out.println(myAccount);

        // Assert that "My personal information" is part of the text retrieved after logout
        Assertions.assertTrue(myAccount.isDisplayed());

        // Click on the "Login user" button (user info)
        browserWait.until(ExpectedConditions.elementToBeClickable(By.id("user_info_acc"))).click();

        // Click on "Logout"
        browserWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='header']/div[3]/div/div/div[7]/ul/li/ul/li[3]/a"))).click();


    }


    @Test
    void bookingBudgetCabin () {

        //click on button Book Now for Budget Cabin
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='hotelRoomsBlock']/div/div[2]/div/div[1]/div[1]/div/div[3]/a")))
                .click();


        //click on button Book now
       browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id='add_to_cart']/button")))
                .click();

       //click on button Proceed to checkout
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a")))
                .click();

        //click on button to Procced
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id='collapse-shopping-cart']/div/div[2]/div[3]/div/a")))
                .click();

        //click on button to Procced to Paynment
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='collapse-guest-info']/div/div[4]/div/a")))
                .click();

        //click on checkted GDPR
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("uniform-cgv")))
                .click();

        //click on pay by bank wire payment method
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id='HOOK_PAYMENT']/div[1]/div/p/a")))
                .click();

        //click on button I confirm my order
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("cart_navigation")))
                .click();
    }

    @Test
    void bookingDeluxeApartments () {

        //click on button Book Now for Budget Cabin
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='hotelRoomsBlock']/div/div[2]/div/div[1]/div[2]/div/div[3]/a")))
                .click();

        //click on button Book now
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='add_to_cart']")))
                .click();
    }

    @Test
    void bookingFullNatureExperience () {

        //click on button Book Now for Budget Cabin
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='hotelRoomsBlock']/div/div[2]/div/div[2]/div[1]/div/div[3]/a")))
                .click();

        //click on button Book now
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='add_to_cart']")))
                .click();
    }

    @Test
    void bookingLuxuryInTheWoods () {

        //click on button Book Now for Budget Cabin
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='hotelRoomsBlock']/div/div[2]/div/div[2]/div[2]/div/div[3]/a")))
                .click();

        //click on button Book now
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[contains(text(), 'Book Now')]")))
                .click();

    }

    @Test
    void findFreeRooms () {

        //click on Select Hotel
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("hotel_cat_name")))
                .click();

        //click on Three Foxes Lounge
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='search_hotel_block_form']/div[2]/div/ul/li")))
                .click();

        //click and fill in field Check In Date
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[6]/a")))
                .click();

        //click and fill in field Check Out Date
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[7]")))
                .click();

        //click on button  Search now
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("search_room_submit")))
                .click();
    }








}
