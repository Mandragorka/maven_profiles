package ua.net.itlabs2.maven_profiles;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import ua.net.itlabs2.OpenTodoMVCWIthClearData;
import ua.net.itlabs2.maven_profiles.categories.Smoke;
import ua.net.itlabs2.pages.TodoMVC;

import static com.codeborne.selenide.CollectionCondition.empty;


@Category(Smoke.class)
    public class TodoE2ETest extends OpenTodoMVCWIthClearData {

        @Test
        public void lifeCycle() {
            // Create tasks
            TodoMVC.add("a");
            TodoMVC.add("b");
            TodoMVC.add("c");
            TodoMVC.add("d");
            TodoMVC.assertTasks("a", "b", "c", "d");
            TodoMVC.assertItemsLeftCounter(4);

            // Editing of existing task
            TodoMVC.editTask("a", "a edited");
            TodoMVC.assertTasks("a edited", "b", "c", "d");

            // Delete task
            TodoMVC.deleteTask("b");
            TodoMVC.assertTasks("a edited", "c", "d");
            TodoMVC.assertItemsLeftCounter(3);

            // Mark tasks as completed
            TodoMVC.toggleTask("d");
            TodoMVC.toggleTask("c");
            TodoMVC.assertItemsLeftCounter(1);

            // Mark task as reopened
            TodoMVC.toggleTask("c");
            TodoMVC.assertItemsLeftCounter(2);

            // Switch to filter active
            TodoMVC.filterActive();
            TodoMVC.assertVisibleTasks("a edited", "c");

            // Switch to filter completed
            TodoMVC.filterCompleted();
            TodoMVC.assertVisibleTasks("d");

            // Back to All filter
            TodoMVC.filterAll();

            // Delete completed tasks
            TodoMVC.clearCompleted();
            TodoMVC.assertTasks("a edited", "c");

            // Mark all left tasks as completed and then their removing
            TodoMVC.toggleAll();
            TodoMVC.assertItemsLeftCounter(0);
            TodoMVC.clearCompleted();
            TodoMVC.tasks.shouldBe(empty);
        }
    }
