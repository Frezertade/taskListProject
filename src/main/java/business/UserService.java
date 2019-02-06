package business;

import model.Task;
import model.User;
import util.DBName;
import utility.Database;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UserService {

    Database<User> database;
    public UserService(){
        database= Database.getInstance();
    }

    public User getUserFromRequest(HttpServletRequest req){
        String firstName= req.getParameter("firstName");
        String lastName=req.getParameter("lastName");
        String email= req.getParameter("email");
        String phone= req.getParameter("phone");
        String loction= req.getParameter("location");
        String password=req.getParameter("password");

        return new User(getRandomId(),firstName,lastName,email,phone,loction,password);


    }

    private int getRandomId(){
        Random random = new Random();
        return random.nextInt(1000);
    }

    public void save(User user){
        database.setValue(DBName.USER,user);
    }

    // find all users form the database
    public List<User> findAll(){
        return database.getValue(DBName.USER);
    }
    // find user filter by name
    public List<User> findByName(String name){
        return findAll().stream()
                .filter(user -> user.getFirstName().equals(name))
                .collect(Collectors.toList());
    }
    // find user filtered by location
    public List<User> findByLocation(String location){
        return findAll().stream()
                .filter(user -> user.getLocation().equals(location))
                .collect(Collectors.toList());
    }
}
