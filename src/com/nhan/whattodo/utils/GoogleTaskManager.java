package com.nhan.whattodo.utils;

import com.google.api.services.tasks.Tasks;
import com.google.api.services.tasks.model.Task;
import com.google.api.services.tasks.model.TaskList;
import com.google.api.services.tasks.model.TaskLists;
import com.nhan.whattodo.data_manager.TaskModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ivanle on 7/2/14.
 */
public class GoogleTaskManager {


    /* Task List Manager */

    public static TaskLists getAllTaskList(Tasks service) {
        try {
            return service.tasklists().list().execute();
        } catch (IOException e) {
            L.e(e.getMessage());
        }
        return null;
    }

    public static TaskList insertTaskList(Tasks service, String title) {
        try {
            TaskList taskList = new TaskList();
            taskList.setTitle(title);
            return service.tasklists().insert(taskList).execute();
        } catch (IOException e) {
            L.e(e.getMessage());
        }
        return null;
    }

    public static void clearAllTaskList(Tasks service) {
        L.i("Clear All TL");
        TaskLists temp = getAllTaskList(service);
        for (int i = 0; i < temp.getItems().size(); i++) {
            try {
                L.d("Task List " + temp.getItems().get(i).toString());
                service.tasklists().delete(temp.getItems().get(i).getId()).execute();
            } catch (IOException e) {
                L.e("Default Task Cannot Be Deleted");
            }
        }
    }


    /* Task Manager */
    public static com.google.api.services.tasks.model.Tasks getAllTaskInTaskList(Tasks service, String taskListID){
        try {
            return service.tasks().list(taskListID).execute();
        } catch (IOException e) {
            L.e(e.getMessage());
        }
        return null;
    }

    public static Task insertTask(Tasks service,String parentId, Task task) {
        try {
            return service.tasks().insert(parentId, task).execute();
        } catch (IOException e) {
            L.e(e.getMessage());
        }
        return null;
    }
}