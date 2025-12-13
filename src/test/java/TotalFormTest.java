import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;

public class TotalFormTest {

        @BeforeAll
        static void beforeAll() {
            Configuration.browserSize = "1920x1080";
            Configuration.baseUrl = "https://demoqa.com";
            //Configuration.timeout = 5000;
            Configuration.pageLoadStrategy = "eager";
            Configuration.holdBrowserOpen = false;

        }

        @Test
        void formTest() {
            open("https://demoqa.com/automation-practice-form");
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
            $("#userEmail").setValue("v.stepanova@mail.ru");
            sleep(3000);

        }
}
