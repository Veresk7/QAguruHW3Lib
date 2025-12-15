package TextBox;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.commands.ShouldHave;
import com.codeborne.selenide.conditions.Text;
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
        Configuration.holdBrowserOpen =  true;

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
        $(byText("Male")).click(); //Клик по названию чек-бокса
        $(byText("Female")).click(); //проверка переключения радоикнопки
        $(byText("Other")).click();
        $(byText("Male")).click();
        $("#userNumber").setValue("88003002000");
        $(".react-datepicker__input-container").click(); //Клик по виджету
        $("[aria-label='Choose Wednesday, December 24th, 2025']").click(); //Выбор значения в текущем месяце
        $("#subjectsContainer input").setValue("in"); //Ввод значения в текстовое поле выпадающего списка
        $(byText("Hindi")).click();
        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("example_image_hw3.jpg");
        $("#currentAddress-wrapper .form-control").setValue("Surviver Shtrasse 1");
        $(byText("Select State")).click();
        $(byText("Uttar Pradesh")).click();
        $(byText("Select City")).click();
        $(byText("Agra")).click();
        $("#submit").click();

        //Проверка заполения таблицы
        $(".table-responsive").shouldHave(text("Sergei Sergeev"));
        $(".table-responsive").shouldHave(text("sergei_sergeev@mail.ru"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("8800300200"));
        $(".table-responsive").shouldHave(text("24 December,2025")); //Баг - нет проверки на невозможность ввести будущее время
        $(".table-responsive").shouldHave(text("Hindi"));
        $(".table-responsive").shouldHave(text("Sports, Reading"));
        $(".table-responsive").shouldHave(text("example_image_hw3.jpg"));
        $(".table-responsive").shouldHave(text("Surviver Shtrasse 1"));
        $(".table-responsive").shouldHave(text("Uttar Pradesh Agra"));
    }
}
