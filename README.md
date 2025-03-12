# ChadJibiti - Task Manager

ChadJibiti is a task manager application that allows users to add, remove, mark, unmark, find, and list tasks. It stores tasks in a file and allows users to interact with tasks using simple commands. This application is designed to help users manage their tasks efficiently through a command-line interface.

## Features


### Add Tasks
Users can add tasks to the list (To-Do, Deadline, Event).

Example: `event meeting /from 13/02/2025 1100 /to 13/02/2025 1600`
```
Roger. I've added this task my g:
 [E][ ] meeting (from: 13 Feb 2025 11:00 AM to: 13 Feb 2025 4:00 PM)
Now you have 1 tasks in the list, better get to work!
```
Example: `deadline CS2113 assignment /by next monday`
```
Roger. I've added this task my g:
 [D][ ] CS2113 assignment (by: next monday)
Now you have 2 tasks in the list, better get to work!
```
Example: `todo return book`
```
Roger. I've added this task my g:
 [T][ ] return book
Now you have 3 tasks in the list, better get to work!
```


### Remove tasks
Tasks can be removed from the list.

Example: `delete 2`
```
Roger. I've removed this task my g:
[D][ ] CS2113 assignment (by: next monday)
Now you have 2 tasks in the list, better get to work!
```


### Mark and Unmark tasks
Tasks can be marked as completed or unmarked if they are completed.

Example: `mark 2`
```
Solid bruv! I've marked this task as done:
 [T][X] return book
```


### Find tasks
Tasks can be searched by keyword.

Example: `find assignment`
```
Roger. Here are the matching tasks in your list:
1.[D][ ] CS2113 assignment (by: next monday)
2.[T][ ] CS1010 assignment
```


### Display tasks
Users can view all the tasks in the list.

Example: `list`
```
Here are the tasks in your list:
1.[E][ ] meeting (from: 13 Feb 2025 11:00 AM to: 13 Feb 2025 4:00 PM)
2.[T][X] return book
3.[D][ ] CS2113 assignment (by: next monday)
4.[T][ ] CS1010 assignment
You have 4 tasks in the list, better get to work!
```