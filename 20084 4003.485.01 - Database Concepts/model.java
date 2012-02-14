import java.io.*;
import java.sql.*;
import java.util.*;

/*
 * model.java
 * 
 * @author: Frank, Dave, Gabe
 *
 */
public class model{
    Statement stmt = null;
    private boolean loggedIn;  //whether a user is logged in
    private String  userID=""; //key  of the currently logged in user
    
    public model() {
        String dbUser= "ges7506";
        String password="";
        Connection con;
        try{
            //get login information
            InputStreamReader inReader = new InputStreamReader(System.in);
            BufferedReader breader = new BufferedReader( inReader );
            System.out.print("Enter your user id: ");
            dbUser = breader.readLine();
            System.out.print("Enter your password: ");
            password = breader.readLine();
            //connect to database
            String url = "jdbc:oracle:thin:@queeg:1521:csodb10";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, dbUser,password);
            stmt = con.createStatement(); 
        }
        catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
    
    
    // checks username/password combination and sets logged in status
    public boolean loginUser(String uname, String pass) {
      try {
        stmt.execute("select C.password from Customer C "
                      +"where C.email = '"+uname+"'");
        LinkedList<String[]> loginInfo= resultToList(stmt.getResultSet());
        try {
          String correctPass = loginInfo.get(0)[0];
          if (correctPass.equals(pass)) {
            userID = uname;
            loggedIn = true;
          } else {
            // bad password
            System.err.println("Bad Password");
            loggedIn = false;
          }
        } catch (IndexOutOfBoundsException e) {
          // bad username
          System.err.println("Bad Username");
          loggedIn = false;
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getMessage());
        return false;
      }     
      return loggedIn;
    }
    // logs a user completely out
    public void logoutUser() {
      userID = "";
      loggedIn = false;
    }
    // userID accesssor
    public String getUser() {
      return userID;
    }
    // loggedIn accessor
    public boolean loggedIn() {
      return loggedIn;
    }
  
    // sets up new rows in Customer, AddressInformation and CreditCard tables
    public void createAccount(String email, String password,
                              String address, String city, String zip,
                              String ccNum, String ccType) {
      try {
        stmt.execute("insert into Customer(email, password) values ('"+email+"', '"+password+"')");
        stmt.execute("insert into AddressInformation(email, streetAddress, city, zipCode) "+
                    "values ('"+email+"', '"+address+"', '"+city+"', "+zip+")");
        stmt.execute("insert into CreditCard(email, ccNumber, ccType) values"+
                    " ('"+email+"', '"+ccNum+"', '"+ccType+"')");
      } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getMessage());
      }
    }
    // removes all information the currently logged in user from the database
    public void deleteAccount() {
      try {
        stmt.execute("delete from AddressInformation T where T.email='"+userID+"'");
        stmt.execute("delete from CreditCard T where T.email='"+userID+"'");
        stmt.execute("delete from Customer T where T.email='"+userID+"'");
      } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getMessage());
      }
    }
    
    // runs an SQL select for a specified string within a specified field
    public String[][] runSearch(String s, String searchField){
        String[][] retArray = null;
        String searchQuery = "";
        try {
            if(searchField.equals("All")){
                searchQuery = "select * from Product "
                                +"where title like '%" + s + "%'";
            }else{
                searchQuery = "select * from Product P, " + searchField +
                        " Q where P.title like '%" + s + "%' and P.ID = Q.PID";
            }
            stmt.execute(searchQuery);
            ResultSet sR = stmt.getResultSet();
            LinkedList<String[]> sRList= resultToList(sR);
            int rowCount = sRList.size();
            retArray = new String[rowCount][6];
            for (int i=0; i<rowCount; i++) {
                String productID = sRList.get(i)[4]; //id
                retArray[i][5] = productID;
                retArray[i][0] = sRList.get(i)[0]; //title
                retArray[i][1] = sRList.get(i)[3]; //year
                retArray[i][3] = "$"+sRList.get(i)[2]+".00"; //price
                retArray[i][4] = sRList.get(i)[1]; //stock
                
                if (searchField.equals("All")) {
                  stmt.execute("select P.ID from Product P, Game Q where "
                                    + productID + " = Q.PID and P.ID = Q.PID");
                  int isGame = resultToList(stmt.getResultSet()).size();
                  stmt.execute("select P.ID from Product P, Book Q where "
                                    + productID + " = Q.PID and P.ID = Q.PID");
                  int isBook = resultToList(stmt.getResultSet()).size();
                  stmt.execute("select P.ID from Product P, Movie Q where "
                                    + productID + " = Q.PID and P.ID = Q.PID");
                  int isMovie = resultToList(stmt.getResultSet()).size();
                  stmt.execute("select P.ID from Product P, Music Q where "
                                    + productID + " = Q.PID and P.ID = Q.PID");
                  int isMusic = resultToList(stmt.getResultSet()).size();
                  if (isGame==1) {
                    retArray[i][2] = "Game";
                  } else if (isBook==1) {
                    retArray[i][2] = "Book";
                  } else if (isMovie==1) {
                    retArray[i][2] = "Movie";
                  } else if (isMusic==1) {
                    retArray[i][2] = "Music";
                  } else {
                    retArray[i][2] = "Other";
                  }
                } else {
                  retArray[i][2] = searchField;
                }   
            }
        } catch (Exception e) {
          e.printStackTrace();
          System.err.println(e.getMessage());
        }
        return retArray;
    }
    
    // converts the SQL's ResultSet into a more usable LinkedList
    private LinkedList<String[]> resultToList(ResultSet r) {
      LinkedList<String[]> list = new LinkedList<String[]>();
      try {
        int columnCount = r.getMetaData().getColumnCount();
        while(r.next()) {
          String[] row = new String[columnCount];
          for (int i=0; i<columnCount; i++) {
            row[i] = ""+r.getObject(i+1);
          }
          list.add(row);  
        }  
      } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getMessage());
      }
      return list;
    }
    
    // returns an array of attributes about an item based on it's PID
    public String[] getItem(String pid, String type){
      String[] retVal = null;
      try {
        stmt.execute("select P.title, P.amount_in_stock, P.price, P.year, "+
                      "P.id from Product P where P.id = "+pid);
        LinkedList<String[]> productData = resultToList(stmt.getResultSet());    
        if (type.equals("Game")) {
          retVal = new String[9];
          stmt.execute("select G.platform, G.developer, G.genre, G.rating "
                  +"from Product P, Game G where P.id = G.pid and P.id = "+pid);
          LinkedList<String[]> gameData = resultToList(stmt.getResultSet());
          retVal[0] = productData.get(0)[0]; //P.title-
          retVal[1] = productData.get(0)[1]; //P.stock
          retVal[2] = "$"+productData.get(0)[2]+".00"; //P.price
          retVal[3] = productData.get(0)[3]; //P.year-
          retVal[4] = productData.get(0)[4]; //P.id-
          retVal[5] = gameData.get(0)[0];    //G.platform-
          retVal[6] = gameData.get(0)[1];    //G.developer-
          retVal[7] = gameData.get(0)[2];    //G.genre
          retVal[8] = gameData.get(0)[3];    //G.rating
        } else {
          retVal = new String[8];
          if (type.equals("Book")){
            stmt.execute("select B.isbn, B.author, B.genre from Product P,"+
                          "Book B where P.id = B.pid and P.id = "+pid);
            LinkedList<String[]> bookData = resultToList(stmt.getResultSet());
            retVal[0] = productData.get(0)[0]; //P.title-
            retVal[1] = productData.get(0)[1]; //P.stock
            retVal[2] = "$"+productData.get(0)[2]+".00"; //P.price
            retVal[3] = productData.get(0)[3]; //P.year-
            retVal[4] = productData.get(0)[4]; //P.id
            retVal[5] = bookData.get(0)[0];    //B.isbn
            retVal[6] = bookData.get(0)[1];    //B.author-
            retVal[7] = bookData.get(0)[2];    //B.genre-
          } else if (type.equals("Music")) {
            stmt.execute("select M.artist, M.producer, M.genre from Product P,"+
                          "Music M where P.id = M.pid and P.id = "+pid);
            LinkedList<String[]> musicData = resultToList(stmt.getResultSet());
            retVal[0] = productData.get(0)[0]; //P.title-
            retVal[1] = productData.get(0)[1]; //P.stock-
            retVal[2] = "$"+productData.get(0)[2]+".00"; //P.price-
            retVal[3] = productData.get(0)[3]; //P.year-
            retVal[4] = productData.get(0)[4]; //P.id-
            retVal[5] = musicData.get(0)[0];   //M.artist-
            retVal[6] = musicData.get(0)[1];   //M.producer-
            retVal[7] = musicData.get(0)[2];   //M.genre-
          } else {
            stmt.execute("select M.director, M.rating, M.genre from Product P,"+
                          "Movie M where P.id = M.pid and P.id = "+pid);
            LinkedList<String[]> movieData = resultToList(stmt.getResultSet());
            retVal[0] = productData.get(0)[0]; //P.title-
            retVal[1] = productData.get(0)[1]; //P.stock
            retVal[2] = "$"+productData.get(0)[2]+".00"; //P.price
            retVal[3] = productData.get(0)[3]; //P.year-
            retVal[4] = productData.get(0)[4]; //P.id-
            retVal[5] = movieData.get(0)[0];    //M.director-
            retVal[6] = movieData.get(0)[1];    //M.rating
            retVal[7] = movieData.get(0)[2];    //M.genre-
          }        
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getMessage());
      }
      return retVal;
    }
    
    // returns a specific attribute about the currently logged in user
    public String getAttribute(String attr) {
      String retVal="";
      if (loggedIn) {
        String table="";
        if (attr.equals("name")
            || attr.equals("phone")
            || attr.equals("email")) {
          table = "Customer";
        } else if (attr.equals("streetAddress")
                  || attr.equals("city") 
                  || attr.equals("state")
                  || attr.equals("zipCode")) {
          table = "AddressInformation";
        } else {
          table = "CreditCard";
        }
        try {
          stmt.execute("select T."+attr+" from "+table+" T "
                        +"where T.email = '"+userID+"'");
          LinkedList<String[]> resultList= resultToList(stmt.getResultSet());
          retVal = resultList.get(0)[0];
        } catch (Exception e) {
          e.printStackTrace();
          System.err.println(e.getMessage());
        }
      }
      return retVal;
    }
    
    // sets a specific attribute about the currently logged in user
    public void setAttribute(String attr, String val) {
      if (loggedIn) {
        String table="";
        if (attr.equals("name")
            || attr.equals("phone")
            || attr.equals("email")) {
          table = "Customer";
        } else if (attr.equals("streetAddress")
                  || attr.equals("city") 
                  || attr.equals("state")
                  || attr.equals("zipCode")) {
          table = "AddressInformation";
        } else {
          table = "CreditCard";
        }
        try {
          stmt.execute("update "+table+" T set T."+attr+"='"+val
                        +"' where T.email='"+userID+"'");
        } catch (Exception e) {
          e.printStackTrace();
          System.err.println(e.getMessage());
        }
      }
    }
    
    public void purchaseItem(int PID) {
    	 try {
    		 stmt.execute("call stock_decrease("+PID+");");
    	 } catch (Exception e) {
             e.printStackTrace();
             System.err.println(e.getMessage());
         }
    }
    
    public static void main(String args[]){
        model m = new model();
        ProductPage p = new ProductPage(m);
    }
}
