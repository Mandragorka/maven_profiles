package ua.net.itlabs2.maven_profiles;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import ua.net.itlabs2.OpenTodoMVCWIthClearData;
import ua.net.itlabs2.maven_profiles.categories.Unit;
import ua.net.itlabs2.maven_profiles.categories.Buggy;
import ua.net.itlabs2.pages.TodoMVC;

import static com.codeborne.selenide.CollectionCondition.empty;

@Category(Unit.class)
public class TodosOperationsAtAllFilterTest extends OpenTodoMVCWIthClearData {

    @Test
    public void testAddTasks() {
        TodoMVC.add("a");
        TodoMVC.add("b");
        TodoMVC.add("c");
        TodoMVC.add("d");
        TodoMVC.assertTasks("a", "b", "c", "d");
        TodoMVC.assertItemsLeftCounter(4);
    }

    @Test
    public void testEditTask() {
        TodoMVC.add("a");
        TodoMVC.add("b");
        TodoMVC.add("c");
        TodoMVC.editTask("a", "a edited");
        TodoMVC.assertTasks("a edited", "b", "c");
        TodoMVC.assertItemsLeftCounter(3);
    }

    @Test
    public void testDeleteTask() {
        TodoMVC.add("a");
        TodoMVC.add("b");
        TodoMVC.add("c");
        TodoMVC.deleteTask("b");
        TodoMVC.assertTasks("a", "c");
        TodoMVC.assertItemsLeftCounter(2);
    }

    @Test
    @Category(Buggy.class)
    public void testMarkTaskAsCompletedReopenedAndClearCompleted() {
        TodoMVC.add("a");
        TodoMVC.add("b");
        TodoMVC.add("c");
        TodoMVC.toggleTask("a");
        TodoMVC.toggleTask("c");
        TodoMVC.assertItemsLeftCounter(1);

        TodoMVC.toggleTask("c");
        TodoMVC.assertItemsLeftCounter(2);

        TodoMVC.clearCompleted();
        TodoMVC.assertItemsLeftCounter(2);
        TodoMVC.assertTasks("a", "c");
    }

    @Test
    public void testMarkAllAsCompletedAndClear() {
        TodoMVC.add("a");
        TodoMVC.add("b");
        TodoMVC.add("c");
        TodoMVC.toggleAll();
        TodoMVC.assertItemsLeftCounter(0);
        TodoMVC.clearCompleted();
        TodoMVC.tasks.shouldBe(empty);
    }
}
