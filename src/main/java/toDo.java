public class toDo extends Task{

    public toDo(String taskDescription){
        super(taskDescription);
    }

    @Override
    public String toString(){
        return "[T]" + getStatusIcon() + " " + getTaskDescription();
    }
}
