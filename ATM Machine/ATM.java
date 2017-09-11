//Duc Nguyen // 1338383 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import java.awt.Color;

@SuppressWarnings("serial")
public class ATM extends JFrame implements ActionListener {

	private JPanel contentPane, loginpane, menu, depositpanel, withdrawpanel, checkbal, passwordchange, logoutpanel, transferpanel;
	private JTextField usertext, passtext,amountDepositText,amountWithdrawText, balanceamount, oldpasstext, newpasstext, newpasstext2;
	private JTextField transferaccount, transferamount;
	private JLabel password,username, amountDeposit, amountWithdraw, currentbal, oldpass, newpass, newpass2, welcome, logmessage, accnumtransfer, amountotransfer;
	private JButton logout, deposit, withdraw, chpassword, transfer, balance, login, depositbutton, backtomenu, backtomenu2, withdrawbutton;
	private JButton backtomenu3, change, backtomenu4, backtomenu5, ok, transferbutton;
	public static String last, first, atmStatus, userpass, accountNum;
	public static double accBalance;
	private JTextArea depositresult,withdrawresult, passresult, transferresult;
	private DecimalFormat df = new DecimalFormat("#.##");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM frame = new ATM();
					frame.setVisible(true);
					try 
					{
						String line = "";
						int i = 0;
						FileReader file = new FileReader("AccountInformation.txt");
						BufferedReader info = new BufferedReader(file);
						BufferedReader pass = new BufferedReader(new FileReader("Password.txt"));
						String[] lineArray = new String[5];
						String[] array = null;
						while((line = info.readLine()) != null){
							lineArray[i] = line;
							i++;
						}
						while((line = pass.readLine()) != null){
							array = line.split(" ");
						}
						accountNum = lineArray[0];
						last = lineArray[1];
						first = lineArray[2];
						accBalance = Double.parseDouble(lineArray[3]);
						atmStatus = lineArray[4];
						userpass = array[1];
						info.close();		
						pass.close();
					}
					catch(IOException e) //If file not open, print out these
					{
						System.out.println("File was not found");
						System.out.println("or could not be opened.");
						System.exit(0);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public ATM() {
		setTitle("ATM Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		loginpane = new JPanel();
		contentPane.add(loginpane, "login");
		loginpane.setLayout(null);
		username = new JLabel("Account Number:");
		username.setBounds(10, 101, 177, 14);
		loginpane.add(username);
		username.setHorizontalAlignment(SwingConstants.TRAILING);
		
		usertext = new JTextField(15);
		usertext.setBounds(197, 98, 126, 20);
		loginpane.add(usertext);
		usertext.setColumns(10);
		
		password = new JLabel("Password: ");
		password.setBounds(10, 132, 177, 14);
		loginpane.add(password);
		password.setHorizontalAlignment(SwingConstants.TRAILING);
		
		passtext = new JTextField(15);
		passtext.setBounds(197, 129, 126, 20);
		loginpane.add(passtext);
		passtext.setColumns(10);
		
		login = new JButton("Login");
		login.setBounds(151, 176, 110, 33);
		loginpane.add(login);
		login.addActionListener(this); 
		login.setFont(new Font("Stencil", Font.PLAIN, 16));
		
		welcome = new JLabel("Welcome to the ATM Machine");
		welcome.setBackground(Color.BLACK);
		welcome.setOpaque(true);
		welcome.setForeground(Color.RED);
		welcome.setFont(new Font("Tahoma", Font.BOLD, 17));
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setBounds(80, 32, 256, 41);
		loginpane.add(welcome);
		
		menu = new JPanel();
		contentPane.add(menu, "menu");
		menu.setLayout(null);
		
		deposit = new JButton("Deposit");
		deposit.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		deposit.addActionListener(this);
		deposit.setBounds(36, 21, 149, 56);
		menu.add(deposit);
		
		withdraw = new JButton("Withdraw");
		withdraw.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		withdraw.addActionListener(this);
		withdraw.setBounds(239, 21, 149, 56);
		menu.add(withdraw);
		
		balance = new JButton("Check Balance");
		balance.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		balance.addActionListener(this);
		balance.setBounds(36, 88, 149, 56);
		menu.add(balance);
		
		transfer = new JButton("Make A Transfer");
		transfer.addActionListener(this);
		transfer.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		transfer.setBounds(239, 88, 149, 56);
		menu.add(transfer);
		
		chpassword = new JButton("Change Password");
		chpassword.addActionListener(this);
		chpassword.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		chpassword.setBounds(36, 155, 149, 56);
		menu.add(chpassword);
		
		logout = new JButton("Logout");
		logout.addActionListener(this);
		logout.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		logout.setBounds(239, 155, 149, 56);
		menu.add(logout);
		
		depositpanel = new JPanel();
		contentPane.add(depositpanel, "depositpanel");
		depositpanel.setLayout(null);
		
		amountDeposit = new JLabel("Deposit Amount: ");
		amountDeposit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		amountDeposit.setHorizontalAlignment(SwingConstants.TRAILING);
		amountDeposit.setBounds(53, 48, 123, 37);
		depositpanel.add(amountDeposit);
		
		amountDepositText = new JTextField();
		amountDepositText.setBounds(179, 57, 123, 20);
		depositpanel.add(amountDepositText);
		amountDepositText.setColumns(10);
		
		depositbutton = new JButton("Deposit");
		depositbutton.addActionListener(this);
		depositbutton.setBounds(87, 93, 89, 23);
		depositpanel.add(depositbutton);
		
		backtomenu = new JButton("Menu");
		backtomenu.addActionListener(this);
		backtomenu.setBounds(213, 93, 89, 23);
		depositpanel.add(backtomenu);
		
		depositresult = new JTextArea();
		depositresult.setBounds(87, 127, 215, 54);
		depositpanel.add(depositresult);
		
		withdrawpanel = new JPanel();
		contentPane.add(withdrawpanel, "withdrawpanel");
		withdrawpanel.setLayout(null);
		
		amountWithdraw = new JLabel("Withdraw Amount: ");
		amountWithdraw.setHorizontalAlignment(SwingConstants.TRAILING);
		amountWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 13));
		amountWithdraw.setBounds(75, 54, 114, 33);
		withdrawpanel.add(amountWithdraw);
		
		amountWithdrawText = new JTextField();
		amountWithdrawText.setBounds(195, 61, 122, 20);
		withdrawpanel.add(amountWithdrawText);
		amountWithdrawText.setColumns(10);
		
		withdrawbutton = new JButton("Withdraw");
		withdrawbutton.addActionListener(this);
		withdrawbutton.setBounds(96, 98, 89, 23);
		withdrawpanel.add(withdrawbutton);
		
		backtomenu2 = new JButton("Menu");
		backtomenu2.addActionListener(this);
		backtomenu2.setBounds(216, 98, 89, 23);
		withdrawpanel.add(backtomenu2);
		
		withdrawresult = new JTextArea();
		withdrawresult.setBounds(96, 136, 215, 54);
		withdrawpanel.add(withdrawresult);
		
		checkbal = new JPanel();
		contentPane.add(checkbal, "checkbal");
		checkbal.setLayout(null);
		
		balanceamount = new JTextField();
		balanceamount.setHorizontalAlignment(SwingConstants.CENTER);
		balanceamount.setFont(new Font("Tahoma", Font.PLAIN, 28));
		balanceamount.setBounds(91, 75, 201, 83);
		checkbal.add(balanceamount);
		balanceamount.setColumns(10);
		
		backtomenu3 = new JButton("Menu");
		backtomenu3.addActionListener(this);
		backtomenu3.setBounds(147, 186, 89, 23);
		checkbal.add(backtomenu3);
		
		currentbal = new JLabel("Current Balance: ");
		currentbal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		currentbal.setHorizontalAlignment(SwingConstants.CENTER);
		currentbal.setBounds(91, 34, 201, 31);
		checkbal.add(currentbal);
		
		passwordchange = new JPanel();
		contentPane.add(passwordchange, "passwordchange");
		passwordchange.setLayout(null);
		
		oldpass = new JLabel("Enter Old Password:");
		oldpass.setHorizontalAlignment(SwingConstants.TRAILING);
		oldpass.setBounds(10, 37, 178, 27);
		passwordchange.add(oldpass);
		
		newpass = new JLabel("Enter New Password:");
		newpass.setHorizontalAlignment(SwingConstants.TRAILING);
		newpass.setBounds(20, 66, 168, 27);
		passwordchange.add(newpass);
		
		newpass2 = new JLabel("Reenter New Password:");
		newpass2.setHorizontalAlignment(SwingConstants.TRAILING);
		newpass2.setBounds(30, 91, 158, 27);
		passwordchange.add(newpass2);
		
		oldpasstext = new JTextField();
		oldpasstext.setBounds(198, 40, 123, 20);
		passwordchange.add(oldpasstext);
		oldpasstext.setColumns(10);
		
		newpasstext = new JTextField();
		newpasstext.setBounds(198, 69, 123, 20);
		passwordchange.add(newpasstext);
		newpasstext.setColumns(10);
		
		newpasstext2 = new JTextField();
		newpasstext2.setBounds(198, 94, 123, 20);
		passwordchange.add(newpasstext2);
		newpasstext2.setColumns(10);
		
		passresult = new JTextArea();
		passwordchange.add(passresult);
		passresult.setBounds(10, 160, 404, 80);

		change = new JButton("Change");
		change.addActionListener(this);
		change.setBounds(73, 126, 89, 23);
		passwordchange.add(change);
		
		backtomenu4 = new JButton("Menu");
		backtomenu4.addActionListener(this);
		backtomenu4.setBounds(254, 126, 89, 23);
		passwordchange.add(backtomenu4);
		
		logoutpanel = new JPanel();
		contentPane.add(logoutpanel, "logoutpanel");
		logoutpanel.setLayout(null);
		
		logmessage = new JLabel("You have successfully logged out.");
		logmessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		logmessage.setHorizontalAlignment(SwingConstants.CENTER);
		logmessage.setBounds(81, 70, 240, 61);
		logoutpanel.add(logmessage);
		
		ok = new JButton("Ok");
		ok.addActionListener(this);
		ok.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ok.setBounds(145, 142, 115, 34);
		logoutpanel.add(ok);
		
		transferpanel = new JPanel();
		contentPane.add(transferpanel, "transferpanel");
		transferpanel.setLayout(null);
		
		accnumtransfer = new JLabel("Enter Account Number:");
		accnumtransfer.setHorizontalAlignment(SwingConstants.TRAILING);
		accnumtransfer.setBounds(29, 74, 141, 20);
		transferpanel.add(accnumtransfer);
		
		transferaccount = new JTextField();
		transferaccount.setBounds(173, 74, 135, 20);
		transferpanel.add(transferaccount);
		transferaccount.setColumns(10);
		
		amountotransfer = new JLabel("Amount to Transfer: $");
		amountotransfer.setHorizontalAlignment(SwingConstants.TRAILING);
		amountotransfer.setBounds(39, 105, 131, 20);
		transferpanel.add(amountotransfer);
		
		transferamount = new JTextField();
		transferamount.setBounds(173, 105, 135, 20);
		transferpanel.add(transferamount);
		transferamount.setColumns(10);
		
		transferbutton = new JButton("Transfer");
		transferbutton.addActionListener(this);
		transferbutton.setBounds(81, 136, 89, 23);
		transferpanel.add(transferbutton);
		
		backtomenu5 = new JButton("Menu");
		backtomenu5.addActionListener(this);
		backtomenu5.setBounds(219, 136, 89, 23);
		transferpanel.add(backtomenu5);
		
		transferresult = new JTextArea();
		transferresult.setBounds(40, 170, 348, 81);
		transferpanel.add(transferresult);
	}

	public void actionPerformed(ActionEvent e) {
		String id = usertext.getText();
		String pass = passtext.getText();
		CardLayout cardLayout = (CardLayout) contentPane.getLayout();
		if(e.getSource() == login){
			if(id.equals(accountNum) && pass.equals(userpass)){
				cardLayout.show(contentPane,"menu");
			}
			else{
				usertext.setText("");
				passtext.setText("");
			}
		}
		else if(e.getSource() == deposit){
			cardLayout.show(contentPane, "depositpanel");			
		}
		else if(e.getSource() == depositbutton){
			double amountDep = Double.parseDouble(amountDepositText.getText());
			accBalance = accBalance + amountDep;
			depositresult.setText("Current Balance: $" + String.valueOf(df.format(accBalance)));
		}
		else if(e.getSource() == backtomenu)
			cardLayout.show(contentPane,"menu");
		else if(e.getSource() == withdraw){
			cardLayout.show(contentPane, "withdrawpanel");			
		}
		else if(e.getSource() == withdrawbutton){
			double amountWit = Double.parseDouble(amountWithdrawText.getText());
			if(accBalance <= 0)
				withdrawresult.setText("Cannot go below 0 dollars");
			else{
				accBalance = accBalance - amountWit;
				withdrawresult.setText("Current Balance: $" + String.valueOf(df.format(accBalance)));
			}
		}
		else if(e.getSource() == backtomenu2)
			cardLayout.show(contentPane,"menu");
		else if(e.getSource() == balance){
			cardLayout.show(contentPane, "checkbal");
			balanceamount.setText("$" + String.valueOf(df.format(accBalance)));
		}
		else if (e.getSource() == backtomenu3)
			cardLayout.show(contentPane,"menu");
		else if(e.getSource() == chpassword)
			cardLayout.show(contentPane, "passwordchange");
		else if(e.getSource() == change){
			if(userpass.equals(oldpasstext.getText()) && newpasstext.getText().equals(newpasstext2.getText())){
				userpass = newpasstext.getText();
				passresult.setText("The password has been changed to " + userpass);
			}
			else
				passresult.setText("Cannot change the password\n(Password does not match or "
						+ "you've enter the wrong old password)\n"
						+ "If you changed password recently, use that password as old");
		}
		else if (e.getSource() == backtomenu4)
			cardLayout.show(contentPane,"menu");
		else if(e.getSource() == logout)
			cardLayout.show(contentPane, "logoutpanel");
		else if(e.getSource() == ok){
			cardLayout.show(contentPane, "login");
			usertext.setText("");
			passtext.setText("");
		}
		else if(e.getSource() == transfer)
			cardLayout.show(contentPane, "transferpanel");
		else if(e.getSource() == transferbutton){
			double amountTrans = Double.parseDouble(transferamount.getText());
			if(accBalance <= 0)
				transferresult.setText("Cannot transfer when you have no money in your account.");
			else{
				accBalance = accBalance - amountTrans;
				transferresult.setText("Transferred $" + String.valueOf(df.format(amountTrans) + " to Account Number: " + transferaccount.getText() + "\n Current Balance: $" + 
				String.valueOf(df.format(accBalance))));
			}
		}
		else if (e.getSource() == backtomenu5)
			cardLayout.show(contentPane,"menu");
	}
}
