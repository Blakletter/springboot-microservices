package com.cancerup.apigateway.models;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public Tasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
