package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1500x600";
        Configuration.holdBrowserOpen = true;

    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        //name, last name, email
        $("#firstName").setValue("Alex");
        $("#lastName").setValue("Egorov");
        $("#userEmail").setValue("alex@egorov.com");

        //gender
        $("#genterWrapper").$(byText("Male")).click(); // best
//        $("[for=gender-radio-1]").click();// good
//        $(by("for", "gender-radio-3")).click(); // good
//        $("#gender-radio-3").parent().click(); // good
//        $(byText("Other")).click(); // not good
//        $("#gender-radio-1").click(); // wrong


        //mobile
        $("#userNumber").setValue("1234567890");

        //date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July"); //best
//        $(".react-datepicker__month-select").selectOptionByValue("6");  //not good
        $(".react-datepicker__year-select").selectOption("2008");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();

        //subjects
        $("#subjectsInput").setValue("Math").pressEnter();

        //hobbies
        $("#hobbiesWrapper").$(byText("Sports")).click(); // best

        //picture
        $("#uploadPicture").uploadFromClasspath("img/random.jpg"); //best
//        $("#uploadPicture").uploadFile(new File("src/test/resources/img/random.jpg")); //good

        //current address
        $("#currentAddress").setValue("Some address 1");

        //state and city
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        //submit button
        $("#submit").click();

        //validation form
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive table").shouldHave(text("Alex"), text("Egorov"),
                text("alex@egorov.com"), text("1234567890"));
//        $(".table-responsive table").$(byText("Date of Birth"))
//                .parent().shouldHave(text("30 July,2008"));


    }
}

