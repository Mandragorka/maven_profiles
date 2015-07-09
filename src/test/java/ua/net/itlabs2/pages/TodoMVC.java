package ua.net.itlabs2.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

public class TodoMVC {

    public static ElementsCollection tasks = $$("#todo-list>li");

    public static void filterAll() {
        $("[href='#/']").click();
    }

    public static void filterActive() {
        $("[href='#/active']").click();
    }

    public static void filterCompleted() {
        $("[href='#/completed']").click();
    }

    public static void add(String text) {
        $("#new-todo").val(text);
        sleep(200);
        $("#new-todo").pressEnter();
    }

    public static void deleteTask(String text) {
        tasks.find(exactText(text)).hover().find(".destroy").click();
    }

    public static void toggleTask(String text) {
        tasks.findBy(exactText(text)).find(".toggle").click();
    }

    public static void toggleAll () {
        $("#toggle-all").click();
    }

    public static void clearCompleted(){
        $("#clear-completed").click();
    }

    public static void editTask(String textToEdit, String newText) {
        tasks.findBy(exactText(textToEdit)).find("label").doubleClick();
        tasks.find(cssClass("editing")).find(".edit").val(newText).pressEnter();
    }

    public static void assertVisibleTasks(String... visibleTaskText) {
        tasks.filter(visible).shouldHave(exactTexts(visibleTaskText));
    }

    public static void assertTasks(String... taskText) {
        tasks.shouldHave(exactTexts(taskText));
    }

    public static void assertItemsLeftCounter(int n) {
        $("#todo-count").find("strong").shouldHave(exactText(Integer.toString(n)));
    }
}
