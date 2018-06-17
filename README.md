# RMI-sorting

This project implements server and client side of RMI. 

There are remote interfaces that allow server and client communicate with each other.

Client creating task and sends its code to server, where task is being calculated and server sends back result.

For operating the execution there is provided console commands.
 - init list.txt - create file list.txt, where is recorded random integers;
 - show list.txt - shows content of list.txt;
 - ping - checks out is server active, response in console "Server is active" means that he is;
 - echo Lets sort our list - sends string to server where this string is being printed to console;
 - process list.txt result.txt - executes our task on server. I.e. here it sends file list.txt to server and this file being sorted, result writes to file result.txt, after execution shows time that was needed for execution of this task;
 - exit | quit | 0 - terminate program.
