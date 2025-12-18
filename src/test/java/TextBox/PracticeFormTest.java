package TextBox;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormTest {

    //Предусловие теста - настройка конфигерации
    @BeforeAll
    static void configuration() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen =  false;

    }

    //Шаги теста
    @Test
    void positiveFormTest() {
        open("/automation-practice-form"); //Добавление в URL path-параметра для открытия нужной страницы
        executeJavaScript("$('#fixedban').remove()"); //Убираем банеры
        executeJavaScript("$('footer').remove()"); //Убираем футер
        $("#firstName").setValue("Sergei");
        $("#lastName").setValue("Sergeev");
        $("#userEmail").setValue("sergei_sergeev@mail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("88003002000");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").$(byText("2070")).click();
        $(".react-datepicker__month-select").$(byText("September")).click();
        $(".react-datepicker__day--005").click();
        $("#subjectsInput").setValue("hi");
        $(".subjects-auto-complete__option").scrollTo();
        $(".subjects-auto-complete__menu-list").$(byText("Hindi")).click();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("example_image_hw3.jpg");
        $("#currentAddress-wrapper .form-control").setValue("Surviver Shtrasse 1");
        $("#state").click();
        $(".css-26l3qy-menu").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $(".css-26l3qy-menu").$(byText("Agra")).click();
        $("#submit").click();

        //Проверка заполения таблицы
        $(".table-responsive").shouldHave(text("Sergei Sergeev"));
        $(".table-responsive").shouldHave(text("sergei_sergeev@mail.ru"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("8800300200"));
        $(".table-responsive").shouldHave(text("05 September,2070")); //Баг - нет проверки на невозможность ввести будущее время
        $(".table-responsive").shouldHave(text("Hindi"));
        $(".table-responsive").shouldHave(text("Sports, Reading"));
        $(".table-responsive").shouldHave(text("example_image_hw3.jpg"));
        $(".table-responsive").shouldHave(text("Surviver Shtrasse 1"));
        $(".table-responsive").shouldHave(text("Uttar Pradesh Agra"));


    }
}
