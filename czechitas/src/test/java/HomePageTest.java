import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
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

        //full screen mode
        browser.manage().window().maximize();

        scrolling = new Scrolling();

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
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.cssSelector(".hide_xs"))).click();

        // Fill in the email address
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("email"))).
                sendKeys("jana@seznam.cz");

        // Fill in the password
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("passwd"))).
                sendKeys("Jana123");

        // Click on the "Sign in" button
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("SubmitLogin"))).
                click();

        // Get the text of the "My personal information" link (or whatever text appears after logging out)
        WebElement myAccount = browserWait.until(
                ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id='center_column']/div/div[1]/ul/li[4]/a")));

        System.out.println(myAccount);

        // Assert that "My personal information" is part of the text retrieved after logout
        Assertions.assertTrue(myAccount.isDisplayed());

        // Click on the "Login user" button (user info)
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("user_info_acc"))).click();

        // Click on "Logout"
        browserWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//*[@id='header']/div[3]/div/div/div[7]/ul/li/ul/li[3]/a")))
                .click();
    }

    @Test
    void bookingBudgetCabinUnregisteredUser () {

        //click on button Book Now for Budget Cabin
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='hotelRoomsBlock']/div/div[2]/div/div[1]/div[1]/div/div[3]/a")))
                .click();

        //Switch to new tab
        browser.switchTo().window((String) browser.getWindowHandles().toArray()[1]);


       //click on button Book now
       WebElement bookNow = browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.cssSelector("button.exclusive.book_now_submit")));
        if (bookNow.isDisplayed()) {
            bookNow.click();
        } else {
            System.out.println("Element Book Now is not visible");
        }

        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("arguments[0].scrollIntoView(true);", bookNow);

       //click on button Proceed to checkout
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a")))
                .click();

        //switch to new tab
        browser.switchTo().window((String) browser.getWindowHandles().toArray()[1]);

        //click on button to Procced
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id='collapse-shopping-cart']/div/div[2]/div[2]/div/a")))
                .click();


        //click on button to Procced to Paynment
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("opc_guestCheckout")))
                .click();
        //fill the login form
        //--first name
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("customer_firstname")))
                        .sendKeys("Jana");
        //--last name
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("customer_lastname")))
                .sendKeys("Nova");
        //--email
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("email")))
                .sendKeys("n@gmail.com");
        //--mobil phone
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("phone_mobile")))
                        .sendKeys("777222111");
        //--date of birth
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("days")))
                .sendKeys("1");
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='months']/option[2]")))
                .click();
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("years")))
                .sendKeys("1985");

        //--address
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("address1")))
                .sendKeys("Na Dobraku 2");
        //--city
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("city")))
                .sendKeys("Brno");
        //--post code
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("postcode")))
                .sendKeys("60200");
        //--mobil phone
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("(//*[@id='phone_mobile'])[2]")))
                .sendKeys("777222111");


        //click on SAFE button
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("submitGuestAccount")))
                .click();

        //click on Proceed
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id='collapse-guest-info']/div/div[4]/div/a")))
                        .click();


        //click on checked GDPR
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

        //confirm order
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id='cart_navigation']/button")))
                .click();

        //pom variable for completed booking
        WebElement completedBooking =  browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='center_column']/div/p[1]")));

        //Assert
        Assertions.assertTrue(completedBooking.isDisplayed());
    }

    @Test
    void bookingDeluxeApartmentsLoginUser () {
        // Click on the "Sign in" button
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.cssSelector(".hide_xs"))).click();

        // Fill in the email address
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("email"))).
                sendKeys("jana@seznam.cz");

        // Fill in the password
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("passwd"))).
                sendKeys("Jana123");

        // Click on the "Sign in" button
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("SubmitLogin"))).
                click();

        //go to home page
        browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("header_logo")))
                        .click();

        //click on button Book Now
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='hotelRoomsBlock']/div/div[2]/div/div[1]/div[2]/div/div[3]/a")))
                .click();

        //switch to new tab
        browser.switchTo().window((String) browser.getWindowHandles().toArray()[1]);

        //click on button Book now
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='add_to_cart']")))
                .click();
        //click on button Proceed to checkout
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a")))
                .click();

        //switch to new tab
        browser.switchTo().window((String) browser.getWindowHandles().toArray()[1]);

        //click on button to Proceed
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='collapse-shopping-cart']/div/div[2]/div[2]/div/a")))
                .click();

        //click on Proceed
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='collapse-guest-info']/div/div[4]/div/a")))
                .click();


        //click on checked GDPR
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

        //confirm order
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='cart_navigation']/button")))
                .click();

        //pom variable for completed booking
        WebElement completedBooking =  browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@id='center_column']/div/p[1]")));

        //Assert
        Assertions.assertTrue(completedBooking.isDisplayed());
    }

    @Test
    void bookingFullNatureExperienceOnlyBooking () {

        //go to home page
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("header_logo")))
                .click();

        //click on button Book Now
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='hotelRoomsBlock']/div/div[2]/div/div[1]/div[2]/div/div[3]/a")))
                .click();

        //switch to new tab
        browser.switchTo().window((String) browser.getWindowHandles().toArray()[1]);

        //click on button Book now
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='add_to_cart']")))
                .click();

        //pom variable for assertion - adding room to cart
        var accessInCart = browserWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".icon-check")));

        //assert
        Assertions.assertTrue(accessInCart.isDisplayed());

    }



    @Test
    void bookingLuxuryInTheWoodsOnlyBooking () {

        //click on button Book Now for Budget Cabin
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='hotelRoomsBlock']/div/div[2]/div/div[2]/div[2]/div/div[3]/a")))
                .click();

        //Switch to new tab
        browser.switchTo().window((String) browser.getWindowHandles().toArray()[1]);

        //click on button Book now
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[contains(text(), 'Book Now')]")))
                .click();

        //pom variable for assertion - adding room to cart
        var accessInCart = browserWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".icon-check")));

        //assert
        Assertions.assertTrue(accessInCart.isDisplayed());

    }

    @Test
    void findFreeRooms () {

        //click on Select Hotel
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("id_hotel_button")))
                .click();

        //click on Three Foxes Lounge
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='search_hotel_block_form']/div[2]/div/ul/li")))
                .click();

        //click and fill in field Check In Date
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("check_in_time")))
                .click();
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[3]")))
                .click();


        //click and fill in field Check Out Date
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("check_out_time")))
                .click();
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[2]")))
                .click();

        //click on button  Search now
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.id("search_room_submit")))
                .click();

        WebElement pomSearch = browserWait.until
                (ExpectedConditions.elementToBeClickable
                        (By.id("category_data_cont")));
        //assert
        Assertions.assertTrue(pomSearch.isDisplayed());
    }

    @Test
    void legalPolicies () {
        //click find pages button
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='header']/div[3]/div/div/div[2]/button")))
                .click();
        //click on Legal Policies
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='menu_cont']/div/div/div[2]/ul[1]/li[6]/a")))
                .click();


        var pomLegal = browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//*[@id='center_column']/div/h2")))
                .getText();
        //assert
        Assertions.assertEquals("Legal", pomLegal);
    }

    @Test
    void policiesTest () {
        browserWait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//a[@href='https://datoph2024-bug-hunters.czechitas.fun/en/content/1-policies']")))
                .click();

        // pom variable for assert visibility of element
        WebElement policies = browserWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".center_column"))).get(1);

        //assert for policies section
        Assertions.assertTrue(policies.isDisplayed());
    }

    @Test
    void legalTerms () {
        //click find pages button
        browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.xpath("//a[@href='https://datoph2024-bug-hunters.czechitas.fun/en/content/3-terms-and-conditions-of-use']")))
                        .click();

        var pomLegal = browserWait.until
                        (ExpectedConditions.elementToBeClickable
                                (By.cssSelector(".rte")));

        // pom variable for assert visibility of element
        WebElement terms = browserWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".center_column"))).get(1);

        //assert for policies section
        Assertions.assertTrue(terms.isDisplayed());
    }

}
