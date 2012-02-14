import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

/*
 * CustomerPage.java
 *
 * Created on Jul 6, 2009, 6:47:55 AM
 */

/**
 *
 * @author Gabe Smith, Dave Sweeney
 */
public class CustomerPage extends javax.swing.JFrame implements ActionListener {

    /** Creates new form CustomerPage */
    public CustomerPage(ProductPage p, String email) {
        parentPage=p;
        initComponents();
        this.setLocation(p.getX(), p.getY());
        this.setTitle("eBuy Account Settings");
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        nameField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        passwordChange = new javax.swing.JPasswordField();
        passwordConfirm = new javax.swing.JPasswordField();
        phoneField = new javax.swing.JTextField();
        addrField = new javax.swing.JTextField();
        zipField = new javax.swing.JTextField();
        cityField = new javax.swing.JTextField();
        cardnumField = new javax.swing.JTextField();
        expireField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        cardTypeField = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        stateField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener( new WindowAdapter() {
		      public void windowClosing( WindowEvent we ) {
		        dispose();
		        parentPage.setLocation(getX(), getY());
		        parentPage.setVisible(true);
		      }
		    });

        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Name");

        jLabel5.setText("Email");

        jLabel6.setText("Phone");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel7.setText("Street Address");

        jLabel8.setText("City");

        jLabel9.setText("Zip");

        jLabel10.setText("Password");

        jLabel11.setText("Confirm Password");

        saveButton.setText("Save");

        cancelButton.setText("Cancel");

        logoutButton.setText("Logout");

        jLabel1.setText("Credit Card Number");

        cardTypeField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mastercard", "Visa", "American Express", "Discovery" }));

        jLabel2.setText("Expiration Date");

        jLabel3.setText("Card Type");

        jLabel12.setText("State");
        
        deleteButton.setText("Delete Account");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(phoneField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(emailField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(passwordConfirm, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordChange, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cityField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(addrField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(stateField, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9)
                                    .addComponent(zipField, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cardTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardnumField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(62, 62, 62))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(expireField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(saveButton)
                                .addGap(18, 18, 18)
                                .addComponent(cancelButton)
                                .addGap(18, 18, 18)
                                .addComponent(logoutButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(34, 34, 34)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(passwordChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(90, 90, 90)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cardTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(zipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(stateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(40, 40, 40)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(addrField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel2)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(cardnumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(expireField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(167, 167, 167)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveButton)
                            .addComponent(cancelButton)
                            .addComponent(logoutButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(deleteButton))
                .addContainerGap())
        );
        
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
        logoutButton.addActionListener(this);
        deleteButton.addActionListener(this);
        
        
        /** set text fields to user's information **/
        nameField.setText(parentPage.getAttribute("name"));
        emailField.setText(parentPage.getAttribute("email"));
        phoneField.setText(parentPage.getAttribute("phone"));
        addrField.setText(parentPage.getAttribute("streetAddress"));
        stateField.setText(parentPage.getAttribute("state"));
        zipField.setText(parentPage.getAttribute("zipCode"));
        cityField.setText(parentPage.getAttribute("city"));
        cardnumField.setText(parentPage.getAttribute("ccNumber"));
        expireField.setText(parentPage.getAttribute("ccExpiration"));
        String cardType = parentPage.getAttribute("ccType");
        if (cardType.equals("Discovery")){
          cardTypeField.setSelectedIndex(3);
        } else if (cardType.equals("American Express")) {
          cardTypeField.setSelectedIndex(2);
        } else if (cardType.equals("Visa")) {
          cardTypeField.setSelectedIndex(1);
        } else {
          cardTypeField.setSelectedIndex(0);
        }


        pack();
    }// </editor-fold>

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
    * @param args the command line arguments
    */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerPage().setVisible(true);
               
            }
        });
    }*/

    // Variables declaration - do not modify
    private javax.swing.JTextField phoneField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox cardTypeField;
    private javax.swing.JTextField addrField;
    private javax.swing.JTextField zipField;
    private javax.swing.JTextField cityField;
    private javax.swing.JTextField cardnumField;
    private javax.swing.JTextField expireField;
    private javax.swing.JTextField nameField;
    private javax.swing.JPasswordField passwordChange;
    private javax.swing.JPasswordField passwordConfirm;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField stateField;
    
    //labelling/aesthetics
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    
    // non GUI members
    private ProductPage parentPage;
    
    // End of variables declaration
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==saveButton) { //save button pressed
		  int cont = 1;
		  if (!parentPage.loggedIn()) {
		    //if we need a new account
		    if (nameField.getText().equals("")
		        || emailField.getText().equals("")
		        || phoneField.getText().equals("")
		        || addrField.getText().equals("")
		        || stateField.getText().equals("")
		        || zipField.getText().equals("")
		        || cityField.getText().equals("")
		        || cardnumField.getText().equals("")
		        || expireField.getText().equals("")
		        || passwordChange.getText().equals("")
		        || passwordConfirm.getText().equals("")) {
          cont=0;
          JOptionPane.showMessageDialog(null, "Please fill in all fields",
                        "Missing Fields", JOptionPane.ERROR_MESSAGE);
        } else if (!passwordChange.getText().equals(passwordConfirm.getText())){
          cont=0;
          JOptionPane.showMessageDialog(null, "Password fields must match",
                            "Mismatched Passwords", JOptionPane.ERROR_MESSAGE);
        } else {
          // create the new account and log it in
          int cType = cardTypeField.getSelectedIndex();
    		  String cardTypeField="";
    		  if (cType==3) {
    		    cardTypeField="Discovery";
          } else if (cType==2){
            cardTypeField="American Express";
          } else if (cType==1){
            cardTypeField="Visa";
          } else {
            cardTypeField="Mastercard";
          }
          parentPage.createAccount(emailField.getText(), passwordChange.getText(),
                  addrField.getText(), cityField.getText(), zipField.getText(),
                  cardnumField.getText(), cardTypeField);
          parentPage.loginUser(emailField.getText(), passwordChange.getText());
        }
		  }
		  if (cont==1) {
  		  /**save fields to already created account**/
  		  parentPage.setAttribute("name", nameField.getText());
  		  parentPage.setAttribute("email", emailField.getText());
  		  parentPage.setAttribute("phone", phoneField.getText());
  		  parentPage.setAttribute("streetAddress", addrField.getText());
  		  parentPage.setAttribute("state", stateField.getText());
  		  parentPage.setAttribute("zipCode", zipField.getText());
  		  parentPage.setAttribute("city", cityField.getText());
  		  parentPage.setAttribute("ccNumber", cardnumField.getText());
  		  parentPage.setAttribute("ccExpiration", expireField.getText());
  		  int type = cardTypeField.getSelectedIndex();
  		  String cardType="";
  		  if (type==3) {
  		    cardType="Discovery";
        } else if (type==2){
          cardType="American Express";
        } else if (type==1){
          cardType="Visa";
        } else {
          cardType="Mastercard";
        }
        parentPage.setAttribute("ccType", cardType);
    		dispose();
    		parentPage.setLocation(this.getX(), this.getY());
    		parentPage.setVisible(true);
    	}

        } else
        if (e.getSource()==cancelButton) { //cancel button pressed
        	dispose();
        	parentPage.setLocation(this.getX(), this.getY());
			parentPage.setVisible(true);

        } else
        if (e.getSource()==logoutButton) { //logout button pressed
        	dispose();
        	parentPage.setLocation(this.getX(), this.getY());
			parentPage.setVisible(true);
			     parentPage.logoutUser();

        } else
        if (e.getSource()==deleteButton) { //delete the account
        	parentPage.deleteAccount();
        	dispose();
        	parentPage.setLocation(this.getX(), this.getY());
			    parentPage.setVisible(true);
			    parentPage.logoutUser();
        }
		
	}

}
