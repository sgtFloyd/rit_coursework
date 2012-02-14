/////////////////////////////////////////////////////////
//product page frontend for database project
// by dave sweeney, gabe smith, frank ssozi
/////////////////////////////////////////////////////////

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.border.*;
import javax.swing.JOptionPane.*;


public class ProductPage extends JFrame implements ActionListener {

    private model dataBase;
    private JPanel panel;
    private JScrollPane scrollPane; 
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    private JButton loginButton; 
    private JButton acctButton;
    private JButton createButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton purchaseButton;
    JTable table;
    
    private boolean loggedIn;	//tracks whether someone is logged in
    private int thePID; 			//stores the ID of the item being looked at for purchase
    
    /** used for the table of search results **/
    private int numRows=0;
    private String[][] tableValues;
    TableModel dataModel = new AbstractTableModel() {
      String colNames[] = {"Title", "Year", "Type", "Price", "Stock"};
      public int getColumnCount() { return 5; }
      public int getRowCount() { return numRows; }
      public String getColumnName(int i) { return colNames[i]; };
      public Object getValueAt(int row, int col) {return tableValues[row][col];}
    };
    
    /** used to detect when user clicks on a row in the table **/
    MouseAdapter mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        if (row!=-1) {
          drawItemPopup(row);
        }
      }
    };
    
    // displays more specific details about a specific product
    private void drawItemPopup(int row) {
          String PID = tableValues[row][5]; //PID of item clicked
          String itemType = tableValues[row][2];
          String[] itemDetails = dataBase.getItem(PID, itemType);
          JFrame frame = new JFrame();
          frame.setTitle("Details for item " + PID);
          
          Container itemCon = frame.getContentPane();       
          itemCon.setBackground(Color.gray);
          itemCon.setLayout(new GridLayout(5,2));
          if (itemType.equals("Game")){
            itemCon.add(new JLabel("Title:"));
            itemCon.add(new JTextField(itemDetails[0], 20));            
            itemCon.add(new JLabel("Platform:"));
            itemCon.add(new JTextField(itemDetails[5], 20));
            itemCon.add(new JLabel("Year:"));
            itemCon.add(new JTextField(itemDetails[3], 20));
            itemCon.add(new JLabel("Developer:"));
            itemCon.add(new JTextField(itemDetails[6], 20));
            itemCon.add(new JLabel("Genre:"));
            itemCon.add(new JTextField(itemDetails[7], 20));
            itemCon.add(new JLabel("Rating:"));
            itemCon.add(new JTextField(itemDetails[8], 20));
            itemCon.add(new JLabel("Price:"));
            itemCon.add(new JTextField(itemDetails[2], 20));
            itemCon.add(new JLabel("Quantity:"));
            itemCon.add(new JTextField(itemDetails[1], 20));
          } else if (itemType.equals("Book")){
            itemCon.add(new JLabel("Title:"));
            itemCon.add(new JTextField(itemDetails[0], 20));            
            itemCon.add(new JLabel("Author:"));
            itemCon.add(new JTextField(itemDetails[6], 20));
            itemCon.add(new JLabel("Genre:"));
            itemCon.add(new JTextField(itemDetails[7], 20));
            itemCon.add(new JLabel("Year:"));
            itemCon.add(new JTextField(itemDetails[3], 20));
            itemCon.add(new JLabel("ISBN:"));
            itemCon.add(new JTextField(itemDetails[5], 20));
            itemCon.add(new JLabel(""));
            itemCon.add(new JLabel(""));
            itemCon.add(new JLabel("Price:"));
            itemCon.add(new JTextField(itemDetails[2], 20));
            itemCon.add(new JLabel("Quantity:"));
            itemCon.add(new JTextField(itemDetails[1], 20));
          } else if (itemType.equals("Movie")){
            itemCon.add(new JLabel("Title:"));
            itemCon.add(new JTextField(itemDetails[0], 20));            
            itemCon.add(new JLabel("Year:"));
            itemCon.add(new JTextField(itemDetails[3], 20));
            itemCon.add(new JLabel("Genre:"));
            itemCon.add(new JTextField(itemDetails[7], 20));
            itemCon.add(new JLabel("Director:"));
            itemCon.add(new JTextField(itemDetails[5], 20));
            itemCon.add(new JLabel("Rating:"));
            itemCon.add(new JTextField(itemDetails[6], 20));
            itemCon.add(new JLabel(""));
            itemCon.add(new JLabel(""));
            itemCon.add(new JLabel("Price:"));
            itemCon.add(new JTextField(itemDetails[2], 20));
            itemCon.add(new JLabel("Quantity:"));
            itemCon.add(new JTextField(itemDetails[1], 20));
          } else { //Music
            itemCon.add(new JLabel("Album Title:"));
            itemCon.add(new JTextField(itemDetails[0], 20));            
            itemCon.add(new JLabel("Artist:"));
            itemCon.add(new JTextField(itemDetails[5], 20));
            itemCon.add(new JLabel("Year:"));
            itemCon.add(new JTextField(itemDetails[3], 20));
            itemCon.add(new JLabel("Genre:"));
            itemCon.add(new JTextField(itemDetails[7], 20));
            itemCon.add(new JLabel("Producer:"));
            itemCon.add(new JTextField(itemDetails[6], 20));
            itemCon.add(new JLabel(""));
            itemCon.add(new JLabel(""));
            itemCon.add(new JLabel("Price:"));
            itemCon.add(new JTextField(itemDetails[2], 20));
            itemCon.add(new JLabel("Quantity:"));
            itemCon.add(new JTextField(itemDetails[1], 20));
          }
          
          itemCon.add(new JLabel(""));
          itemCon.add(new JButton("Cancel"));
          itemCon.add(purchaseButton);
          thePID=Integer.parseInt(PID);
           
          //take the string array itemDetails and put it into labels and values
          //add a cancel button and a purchase button on the bottom
          frame.pack();
          frame.setBounds(10,10, 450,100);	          
          frame.setVisible(true);
    }

    /** Updates and redraws the table of search results **/
    public void updateProductTable(String[][] searchResults) {
      numRows = searchResults.length;
      tableValues = searchResults;
      panel.remove(scrollPane);
      table = new JTable(dataModel);
      table.addMouseListener(mouseListener);
      scrollPane = new JScrollPane(table);
      panel.add(scrollPane);
      pack();
      setBounds(50,50, 640,480);
    }
    
    // Constructor, sets up initial main window
    public ProductPage(model m) {
        dataBase = m;
        Container content = getContentPane();       
        content.setBackground(Color.gray);
        content.setLayout(new FlowLayout()); 	
        content.add(new JLabel("Search:"));	
        
        searchField=new JTextField(20);
        searchField.addActionListener(this);
        content.add(searchField);

	      String searchCategories[]={"All", "Game", "Book", "Music", "Movie"};        
        searchCombo = new JComboBox(searchCategories);
        content.add(searchCombo);
        
        searchButton=new JButton("Search!");
        searchButton.addActionListener(this);
        content.add(searchButton);
        
       
        
        panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        add(panel);
         
        table = new JTable(dataModel);
        table.addMouseListener(mouseListener);
        scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

	      addWindowListener( new WindowAdapter() {
		      public void windowClosing( WindowEvent we ) {
		        dispose();
		        System.exit(0);
		      }
		    });

	     
	      loginButton = new JButton("Login");
	        loginButton.addActionListener(this);
	        content.add(loginButton);
	        
	    usernameLabel = new JLabel();
	    passwordLabel = new JLabel();
	    
	    usernameLabel.setText("Username:");
	    passwordLabel.setText("Password:");
	    
	    usernameField = new JTextField(10);
	    passwordField = new JPasswordField(10);
	    
	    content.add(usernameLabel);
	    content.add(usernameField);
	    
	    content.add(passwordLabel);
	    content.add(passwordField);
	    
	    acctButton = new JButton("Account Info");
	    acctButton.setVisible(false);
	    acctButton.addActionListener(this);
	    
	    content.add(acctButton);
	    
	    createButton = new JButton("Create new account");
	    createButton.addActionListener(this);
	    
	    content.add(createButton);
	    
	    purchaseButton = new JButton("Purchase Item");
	    purchaseButton.addActionListener(this);
	    
	      pack();
        setTitle("eBuy");
        setBounds(100,100,500,600);
        setVisible(true);
    }

    // function to update the GUI as necessary when logging out a user  
    public void logoutUser(){
      dataBase.logoutUser();
      acctButton.setVisible(false);
      loginButton.setText("Login");
      usernameLabel.setText("Username:");
      passwordLabel.setVisible(true);
      usernameField.setVisible(true);
      passwordField.setVisible(true);
      createButton.setVisible(true);
    }
    
    // function to update the GUI as necessary when logging in a user
    public void loginUser(String uname, String pass) {
      boolean login=dataBase.loginUser(uname, pass);
      if (login) {
        // login success
        loginButton.setText("Logout");
        acctButton.setVisible(true);
        usernameLabel.setText("Logged in as " + uname);
        passwordLabel.setVisible(false);
        usernameField.setVisible(false);
        passwordField.setVisible(false);
        createButton.setVisible(false);
      } else {
      // login fail
        JOptionPane.showMessageDialog(null, "Login Failed",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
      }
    }
    
    // accessors from model
    public void deleteAccount() {
      dataBase.deleteAccount();
    }
    public String getAttribute(String attr) {
      return dataBase.getAttribute(attr);
    }
    public void setAttribute(String attr, String val) {
      dataBase.setAttribute(attr,val);
    }
    public boolean loggedIn(){
      return dataBase.loggedIn();
    }
    public void createAccount(String e, String p,
                              String s, String c, String z,
                              String n, String t){
      dataBase.createAccount(e,p,s,c,z,n,t);
    }
    
    // handles all button presses
    public void actionPerformed(ActionEvent arg0) {
    
        /**search**/
        if (arg0.getSource()==searchButton) {
            String searchCategory = (String)searchCombo.getSelectedItem();
            String searchString = searchField.getText();  
            updateProductTable(dataBase.runSearch(searchString, searchCategory));
        }
        
        /**login/out **/
        if (arg0.getSource()==loginButton) {
          if (loginButton.getText().equals("Login")) {
            //login
            loginUser(usernameField.getText(), passwordField.getText());
          } else {
            //logout
            logoutUser();
          }
        }
        
        /** create a new account **/
        if (arg0.getSource()==createButton) {
        	this.setVisible(false);
        	new CustomerPage(this, dataBase.getUser());
        }
        
        /**when logged in, view account info**/ 
        if (arg0.getSource()==acctButton) {
        	this.setVisible(false);
        	new CustomerPage(this, dataBase.getUser());
        }
        /**simulate purchase of item from the database**/
        if (arg0.getSource()==purchaseButton) {
        	dataBase.purchaseItem(thePID);
        	
        }
        
    }
}
