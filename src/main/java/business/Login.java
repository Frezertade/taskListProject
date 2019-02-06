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
            // search for any much with the provided email and password
          return users.stream().anyMatch(user ->
                  (user.getEmail().equals(username) && user.getPassword().equals(password))
            );
        }
    }
    // find the user using email address (username)
    public static User findUserByEmail(String email){
        List<User> users= database.getValue(DBName.USER);
        Optional<User> user= users.stream().filter(user1 -> user1.getEmail().equals(email)).findAny(); // filter the list to find the user with the provided email address
        return user.orElse(null); // return the user if exits or return null if not exits
    }

    public static void addStaticUser(){
        database.setValue(DBName.USER,new User(100,"Michael", "Berhanu","mikesol@gmail.com","319 616 0107","1000 N 4th St. fairfield, iowa ", "mikesol"));
        database.setValue(DBName.USER,new User(101,"Frezer", "Tadesse","fire@gmail.com","319 616 0107","1000 N 4th St. fairfield, iowa ", "fire"));
        database.setValue(DBName.USER,new User(102,"Gebreslassie", "Kahasay","gebre@gmail.com","319 616 0107","1000 N 4th St. fairfield, iowa ", "gebre"));
    }

}
