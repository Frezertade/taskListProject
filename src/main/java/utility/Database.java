package utility;

import util.DBName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database<T> {

    private static Database single_instance = null;
    private  HashMap<DBName, List<T>> tables;
    private Database(){
       tables = new HashMap<>();
    }

    public static Database getInstance(){
        if(single_instance == null){
            single_instance = new Database();
        }

        return single_instance;
    }

    public void setValue(DBName name, T value){
        List<T> values= getValue(name);
        if(values== null) values= new ArrayList<>();
        values.add(value);
    }

    public List<T> getValue(DBName name){
        return tables.get(name);
    }
}
