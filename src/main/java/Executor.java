public class Executor {

    private static final int MAX_TASKS = 100;
    private static final Printer printer = new Printer();
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    private boolean shouldExit;

    public Executor() {
        this.shouldExit = false;
        printer.showGreeting();
    }

    public static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void execute(Command command) {

        switch (command.getType()) {
        case BYE:
            printer.showBye();
            this.shouldExit = true;
            break;

        case LIST:
            printer.showTasks(tasks, taskCount);
            break;

        case MARK:
            updateMarkStatus(command, true);
            break;

        case UNMARK:
            updateMarkStatus(command, false);
            break;

        case TODO:
            addTask(new ToDo(command.getParam1()));
            break;

        case DEADLINE:
            addTask(new Deadline(command.getParam1(), command.getParam2()));
            break;

        case EVENT:
            addTask(new Event(command.getParam1(), command.getParam2(), command.getParam3()));
            break;

        default:
            printer.showDefault();
            break;
        }

    }

    public boolean shouldExit() {
        return shouldExit;
    }

    private void updateMarkStatus(Command command, boolean isMark) {
        if (isNumeric(command.getParam1())) {
            int index = Integer.parseInt(command.getParam1()) - 1;

            if (isMark) {
                tasks[index].markTask();
                printer.showMarked(tasks[index]);
            } else {
                tasks[index].unmarkTask();
                printer.showUnmarked(tasks[index]);
            }
        }
    }

    private void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount += 1;
        printer.showAdded(tasks[taskCount - 1], taskCount);
    }


}
