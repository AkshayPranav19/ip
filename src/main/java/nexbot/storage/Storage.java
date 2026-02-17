package nexbot.storage;

import nexbot.task.Deadline;
import nexbot.task.Event;
import nexbot.task.Task;
import nexbot.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String FILE_PATH = "data/nexbot.txt";
    private static final String FIELD_DELIMITER_REGEX = "\\s*\\|\\s*";

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        File dataFile = new File(FILE_PATH);

        try {
            ensureFileExists(dataFile);

            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                Task task = parseTaskOrNull(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with an empty task list.");
        } catch (IOException e) {
            System.out.println("Something went wrong when accessing the data file: " + e.getMessage());
        }

        return taskList;
    }

    public void save(ArrayList<Task> taskList) {
        File dataFile = new File(FILE_PATH);

        try {
            ensureFileExists(dataFile);

            FileWriter writer = new FileWriter(dataFile, false);
            for (Task task : taskList) {
                writer.write(task.toStorageString());
                writer.write(System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Something went wrong when saving tasks: " + e.getMessage());
        }
    }

    private void ensureFileExists(File dataFile) throws IOException {
        File parentDirectory = dataFile.getParentFile();

        if (parentDirectory != null && !parentDirectory.exists()) {
            boolean isCreated = parentDirectory.mkdirs();
            if (!isCreated) {
                System.out.println("Warning: Unable to create data directory at " + parentDirectory.getPath());
            }
        }

        if (!dataFile.exists()) {
            boolean isCreated = dataFile.createNewFile();
            if (!isCreated) {
                System.out.println("Warning: Unable to create data file at " + dataFile.getPath());
            }
        }
    }

    private Task parseTaskOrNull(String line) {
        String[] fields = line.split(FIELD_DELIMITER_REGEX);

        if (fields.length < 3) {
            System.out.println("Skipped corrupted line (too few fields): " + line);
            return null;
        }

        String taskType = fields[0].trim();
        String doneFlag = fields[1].trim();

        if (!doneFlag.equals("0") && !doneFlag.equals("1")) {
            System.out.println("Skipped corrupted line (invalid done flag): " + line);
            return null;
        }

        boolean isDone = doneFlag.equals("1");
        String description = fields[2].trim();

        Task task;

        switch (taskType) {
        case "T":
            if (fields.length != 3) {
                System.out.println("Skipped corrupted ToDo line: " + line);
                return null;
            }
            task = new ToDo(description);
            break;

        case "D":
            if (fields.length != 4) {
                System.out.println("Skipped corrupted Deadline line: " + line);
                return null;
            }
            task = new Deadline(description, fields[3].trim());
            break;

        case "E":
            if (fields.length != 5) {
                System.out.println("Skipped corrupted Event line: " + line);
                return null;
            }
            task = new Event(description, fields[3].trim(), fields[4].trim());
            break;

        default:
            System.out.println("Skipped corrupted line (unknown task type): " + line);
            return null;
        }

        if (isDone) {
            task.markTask();
        }

        return task;
    }
}
