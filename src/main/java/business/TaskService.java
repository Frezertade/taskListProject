package business;

import com.google.gson.Gson;
import model.Task;
import model.Team;
import model.TeamMember;
import model.User;
import util.Category;
import util.DBName;
import util.Priority;
import utility.Database;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class TaskService {
    Database<Task> database;

    public TaskService(){
        database= Database.getInstance();
    }
    // create a task object form the request
    public Task getTaskFromRequest(HttpServletRequest req, User user){
        String name= req.getParameter("name");
        String dueDate=req.getParameter("due");
        String category=req.getParameter("category");
        String priority= req.getParameter("priority");
        String team=req.getParameter("team");

        // create new task based on the input data;
        Category category1= Category.valueOf(category.toUpperCase());
        return new Task(getRandomId(),name,dueDate,category1, Priority.valueOf(priority.toUpperCase()),user,category1.equals(Category.PERSONAL)? null: getTeamById(team));

    }

    private int getRandomId(){
        Random random = new Random();
        return random.nextInt(1000);
    }

    // save the Task to the persistence database
    public void save(Task task){
        database.setValue(DBName.TASK,task);
    }

    // find all lists in the database
    public List<Task> findAll(){
       return database.getValue(DBName.TASK);
    }

    // find Task by id
    public Task findById(int id){
        Optional<Task> optionalTask=findAll().stream()
                .filter(task -> task.getId()==id)
                .findAny();
        return optionalTask.orElse(null);
    }

    // find task filtered by user
    public List<Task> findByUser(User user){
        return findAll().stream()
                .filter(user1 -> user1.getCreatedBy().getUserID() == user.getUserID())
                .collect(Collectors.toList());
    }

    public List<Task> findByUserAndTeam(User user){
        TeamMemberSetvice teamMemberSetvice = new TeamMemberSetvice();
        return findAll().stream()
                .filter(task -> {
                  return (teamMemberSetvice.findByTeam(task.getTeam()).stream()
                   .anyMatch(teamMember -> teamMember.getUserId().equals(user))|| task.getCreatedBy().equals(user));
                })
                .collect(Collectors.toList());
    }

    public void sendTaskList(HttpServletResponse resp,User user) throws IOException {
        List<Task> tasks = findByUserAndTeam(user);
        String JSONTask= new Gson().toJson(tasks);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(JSONTask);
    }

    private Team getTeamById(String team){
       int teamId;
        try {
            teamId=Integer.parseInt(team);
            return new TeamService().findById(teamId);
        }catch (NumberFormatException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
