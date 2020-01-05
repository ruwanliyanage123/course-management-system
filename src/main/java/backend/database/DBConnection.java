package backend.database;

public class DBConnection {
    private static DBConnection dbConnection;
    private DBConnection(){
        //keep constructor as private to make sure restrict to the single object creation
    }

    public static DBConnection getInstance(){
        if(dbConnection==null){
            //create an object inside the static method to take instance without using the new keyword
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
}
