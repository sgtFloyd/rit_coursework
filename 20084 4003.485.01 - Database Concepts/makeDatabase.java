import java.io.*;
import java.sql.*;

/*
 * makeDatabase.java
 * 
 * @author: Gabe
 * Populates the database with everything it should initially have.
 */
public class makeDatabase{
    Statement stmt = null;
    
    public makeDatabase() {
    
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
            createTables();
            runSQL("sql/bookScript.sql");
            runSQL("sql/movieScript.sql");
            runSQL("sql/gameScript.sql");
            runSQL("sql/musicScript.sql");
            runSQL("sql/customerScript.sql");
            runSQL("sql/procsntriggers.sql"); // setup stored procedures and triggers
        }
        catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
    
      private void createTables() {
        int _VAR_CHAR_SIZE = 50;
        try {
            //the product schema			
            stmt.execute(
              "create table Product (" + 
                "title varchar(" + _VAR_CHAR_SIZE + "), " +
                "amount_in_stock integer, "+
                "price integer, "+ 
                "year integer, "+
                  "id integer, "+
                  "primary key(id))");
            
            //the game schema			
            stmt.execute(
                "create table Game (" + 
                  "platform varchar(" + _VAR_CHAR_SIZE + "), " +
                  "developer varchar(" + _VAR_CHAR_SIZE + "), "+
                  "genre varchar(" + _VAR_CHAR_SIZE + "), "+ 
                  "rating varchar(" + _VAR_CHAR_SIZE + "), "+
                  "pid integer, "+
                    "primary key(pid))");
            
            //the book schema
            stmt.execute(
              "create table Book (" + 
                "isbn varchar(" + _VAR_CHAR_SIZE + "), " +
                "author varchar(" + _VAR_CHAR_SIZE + "), "+
                "genre varchar(" + _VAR_CHAR_SIZE + "), "+ 
                "pid integer, "+
                  "primary key(pid))");
            
            //the movie schema			
            stmt.execute(
              "create table Movie (" + 
                "director varchar(" + _VAR_CHAR_SIZE + "), " +
                "rating varchar(" + _VAR_CHAR_SIZE + "), "+
                "genre varchar(" + _VAR_CHAR_SIZE + "), "+ 
                "pid integer, "+
                  "primary key(pid))");
            
            //the music schema
            stmt.execute(
                "create table Music (" + 
                  "artist varchar(" + _VAR_CHAR_SIZE + "), " +
                  "producer varchar(" + _VAR_CHAR_SIZE + "), "+
                  "genre varchar(" + _VAR_CHAR_SIZE + "), "+ 
                  "pid integer, "+
                    "primary key(pid))");
            
            //the customer schema
            stmt.execute(
              "create table Customer (" + 
                "name varchar(" + _VAR_CHAR_SIZE + "), " +
                "password  varchar(" + _VAR_CHAR_SIZE + "), "+
                "phone varchar(" + _VAR_CHAR_SIZE + "), "+ 
                "email varchar(" + _VAR_CHAR_SIZE + "), "+
                  "primary key(email))");
            
            //the credit card schema
            stmt.execute(
              "create table CreditCard (" +
                "ccName varchar(" + _VAR_CHAR_SIZE + "), " +
                "ccType  varchar(" + _VAR_CHAR_SIZE + "), "+
                "ccNumber varchar(" + _VAR_CHAR_SIZE + "), "+
                "email varchar(" + _VAR_CHAR_SIZE + "), "+
                "ccExpiration varchar(7), " + 
                  "primary key(ccNumber, ccType), "+
                  "foreign key(email) references Customer(email))" );
            
            //the Address information schema
            stmt.execute(
              "create table AddressInformation (" +
                "email varchar(" + _VAR_CHAR_SIZE + "), " +
                "streetAddress varchar(" + _VAR_CHAR_SIZE + "), " +
                "city varchar(" + _VAR_CHAR_SIZE + "), "+
                "state varchar(" + _VAR_CHAR_SIZE + "), "+
                "zipCode integer, "+ 
                  "primary key(streetAddress, city, zipCode), "+
                  "foreign key(email) references Customer(email))" );
        } catch (Exception e) {
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
        makeDatabase d = new makeDatabase();
    }
}
