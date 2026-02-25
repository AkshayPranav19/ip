package nexbot.task;

import nexbot.exception.InvalidTaskNumberException;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = (tasks == null) ? new ArrayList<>() : tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int taskNumber) throws InvalidTaskNumberException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        return tasks.get(index);
    }

    public Task remove(int taskNumber) throws InvalidTaskNumberException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}