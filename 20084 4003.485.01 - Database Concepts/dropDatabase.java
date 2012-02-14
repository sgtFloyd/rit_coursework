import java.io.*;
import java.sql.*;

/*
 * dropDatabase.java
 * 
 * @author: Gabe
 * Deletes all data and drops all tables from the database.
 */
public class dropDatabase{
    Statement stmt = null;
    
    public dropDatabase() {
    
          /***************************/
         /** ENTER LOGIN INFO HERE **/
        /***************************/
        String url = "jdbc:oracle:thin:@queeg:1521:csodb10";
        String dbUser= "ges7506";
        String password="";      
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, dbUser,password);
            stmt = con.createStatement();
            runSQL("sql/dropDatabase.sql");
        }
        catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
    
    /* method for running an external SQL script */
    private void runSQL(String filename) {
        try {
            BufferedReader d = new BufferedReader(new FileReader(filename));
            String thisLine, sqlQuery;
            sqlQuery = "";
            while ((thisLine = d.readLine()) != null) {
                //Skip comments and empty lines
                if(thisLine.length() > 0 && thisLine.charAt(0) == '-'
                   || thisLine.length() == 0 )
                    continue;
                sqlQuery = sqlQuery + " " + thisLine;
                if(sqlQuery.charAt(sqlQuery.length() - 1) == ';') {
                    sqlQuery = sqlQuery.replace(';' , ' ');
                    try {
                      stmt.execute(sqlQuery);
                    } catch (SQLException e) {
                      //e.printStackTrace();
                      System.err.print(e.getMessage());
                    }
                    sqlQuery = "";
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
    
    public static void main(String args[]){
        dropDatabase d = new dropDatabase();
    }
}
