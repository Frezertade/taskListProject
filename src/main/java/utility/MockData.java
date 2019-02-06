package utility;

import business.TaskService;
import business.TeamService;
import business.UserService;
import model.Task;
import util.Category;
import util.Priority;

import java.util.ArrayList;
import java.util.Random;

/** utility class to return mock data for testing
 *  @since 11/19/2017
 *  @author kl */
public class MockData {

    public ArrayList<Task> taskList = new ArrayList<>();

    TeamService teamService= new TeamService();
    TaskService taskService= new TaskService();
    UserService userService= new UserService();
    public ArrayList<Task> retrieveTaskList() {

        /*taskList.add(new Task(101, "first task", "2017-11-19", "Personal"));
        taskList.add(new Task(102, "second task", "2017-11-23", "Work"));
        taskList.add(new Task(103, "third task", "2017-12-19", "Work"));*/


        return taskList;
    }

    public void addTeam(){
        teamService.creatTeam(100,"Programmer team", 100);
        teamService.creatTeam(101,"Quality Assurance team", 100);
        teamService.creatTeam(102,"Management team", 100);

    }

    public void addTeamMember(){
        teamService.addUserToTeam(102,100);
        teamService.addUserToTeam(101,100);
        teamService.addUserToTeam(102,101);
        teamService.addUserToTeam(100,102);

    }

    public void addTask(){
        taskService.save(new Task(100,"task 1","2017-11-11", Category.WORK, Priority.URGENT,userService.findByUserId(102),teamService.findById(100)));
        taskService.save(new Task(101,"task 2","2017-01-11", Category.WORK, Priority.LOW,userService.findByUserId(100),teamService.findById(100)));
        taskService.save(new Task(102,"task 3","2017-10-11", Category.PERSONAL, Priority.HIGH,userService.findByUserId(102),null));
        taskService.save(new Task(103,"task 4","2017-05-11", Category.WORK, Priority.MIDUME,userService.findByUserId(101),teamService.findById(100)));
        taskService.save(new Task(104,"task 5","2017-07-11", Category.PERSONAL, Priority.HIGH,userService.findByUserId(101),null));
        taskService.save(new Task(105,"task 6","2017-08-11", Category.WORK, Priority.HIGH,userService.findByUserId(102),teamService.findById(101)));
        taskService.save(new Task(106,"task 7","2017-04-11", Category.WORK, Priority.LOW,userService.findByUserId(102),teamService.findById(100)));
        taskService.save(new Task(107,"task 8","2017-08-11", Category.WORK, Priority.HIGH,userService.findByUserId(101),teamService.findById(102)));
        taskService.save(new Task(108,"task 9","2017-12-11", Category.PERSONAL, Priority.URGENT,userService.findByUserId(100),null));
}

}


