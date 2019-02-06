package business;

import model.User;
import util.DBName;
import utility.Database;

import java.util.List;
import java.util.Optional;

public class Login {
    private  static Database<User> database = Database.getInstance();
    public static boolean login(String username,String password){

        List<User> users= database.getValue(DBName.USER);
        if(users == null) return false;
        else {
          return users.stream().anyMatch(user ->
                  (user.getEmail().equals(username) && user.getPassword().equals(password))
            );
        }
    }
    public static User findUserByEmail(String email){
        List<User> users= database.getValue(DBName.USER);
        Optional<User> user= users.stream().filter(user1 -> user1.getEmail().equals(email)).findAny();
        return user.orElse(null);
    }

}
