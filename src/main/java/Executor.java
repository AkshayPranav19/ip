public class Executor {

    public static final int MAX_TASKS = 100;
    private static final Printer printer = new Printer();
    public static Task[] tasks = new Task[MAX_TASKS];
    public static int taskCount = 0;
    private boolean shouldExit;

    public Executor() {
        this.shouldExit = false;
        printer.showGreeting();
    }

    public static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) return false;
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
            if (isNumeric(command.getArg1())) {
                int markIndex = Integer.parseInt(command.getArg1()) - 1;
                tasks[markIndex].markTask();
                printer.showMarked(tasks[markIndex]);
            }
            break;

        case UNMARK:
            if (isNumeric(command.getArg1())) {
                int unmarkIndex = Integer.parseInt(command.getArg1()) - 1;
                tasks[unmarkIndex].unmarkTask();
                printer.showUnmarked(tasks[unmarkIndex]);
            }
            break;

        case TODO:
            tasks[taskCount] = new ToDo(command.getArg1());
            taskCount += 1;
            printer.showAdded(tasks[taskCount - 1], taskCount);
            break;

        case DEADLINE:
            tasks[taskCount] = new Deadline(command.getArg1(), command.getArg2());
            taskCount += 1;
            printer.showAdded(tasks[taskCount - 1], taskCount);
            break;

        case EVENT:
            tasks[taskCount] = new Event(command.getArg1(), command.getArg2(), command.getArg3());
            taskCount += 1;
            printer.showAdded(tasks[taskCount - 1], taskCount);
            break;

        case UNKNOWN:
        default:
            printer.showDefault();
            break;
        }

    }

    public boolean shouldExit() {
        return shouldExit;
    }


}
