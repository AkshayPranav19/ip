public class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String taskDescription){
        this.isDone = false;
        this.taskDescription = taskDescription;
    }
    public void markTask(){
        this.isDone = true;
    }

    public void unmarkTask(){
        this.isDone = false;
    }

    public boolean isDone(){
        return this.isDone;
    }

    public String getTaskDescription(){
        return taskDescription;
    }
    public String getStatusIcon(){
        return isDone ? "[X]" : "[ ]";
    }

    public String displayString(){
        return getStatusIcon() + " " + getTaskDescription();
    }
}
