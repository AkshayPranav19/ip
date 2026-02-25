# NexBot User Guide

NexBot is a personal task manager that helps you track todos, deadlines, and events via a command-line interface.

![NexBot Screenshot](screenshot.png)

## Adding a Todo

Adds a simple task with no date.

Example: `todo read book`
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

## Adding a Deadline

Adds a task with a due date and time.

Example: `deadline return book /by 25 02 2026 1800`
```
Got it. I've added this task:
[D][ ] return book (by: 25 02 2026 18:00)
Now you have 2 tasks in the list.
```

## Adding an Event

Adds a task with a start and end date/time.

Example: `event project meeting /from 25 02 2026 0900 /to 26 02 2026 1700`
```
Got it. I've added this task:
[E][ ] project meeting (from: 25 02 2026 09:00 to: 26 02 2026 17:00)
Now you have 3 tasks in the list.
```

## Listing Tasks

Shows all tasks in the list.

Example: `list`
```
1. [T][ ] read book
2. [D][ ] return book (by: 25 02 2026 18:00)
3. [E][ ] project meeting (from: 25 02 2026 09:00 to: 26 02 2026 17:00)
```

## Marking a Task

Marks a task as done.

Example: `mark 1`
```
Nice! I've marked this task as done:
[T][X] read book
```

## Unmarking a Task

Marks a task as not done.

Example: `unmark 1`
```
OK, I've marked this task as not done yet:
[T][ ] read book
```

## Deleting a Task

Removes a task from the list.

Example: `delete 1`
```
Noted. I've removed this task:
     [T][ ] read book
Now you have 2 tasks in the list.
```

## Finding Tasks

Searches for tasks by keyword.

Example: `find book`
```
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: 25 02 2026 18:00)
```

## Exiting NexBot

Exits the application.

Example: `bye`
```
Bye. Hope to see you again soon!
Shutting down NexBot...
```