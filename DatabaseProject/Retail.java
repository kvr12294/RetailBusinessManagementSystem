
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.proteanit.sql.DbUtils;
import oracle.jdbc.OracleTypes;



public class Retail {

	private static JFrame login;//window
	private JFrame f;
	private JFrame monthSaleFrame1;
	private JFrame monthSaleFrame2;
	private static JPanel loginp;//used to display components
	private JPanel p;
	private JButton purchase,add,remove,update,display,monthlySale;
	private JLabel label;
	private String listData[],listDataDisplay[],products[];
	ArrayList<String> productList,prodIdList;
	static Connection conn;
	private JComboBox<String> menu;
	private JComboBox<String> display_menu;
	private String optionSelected;
	CallableStatement cs=null;
	static JTextField tf_login;
	static JButton btnlogin;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
							 new Retail();
						 
					
					
				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
				}
			}
		});
	}
	
//	public static DefaultTableModel buildTableModel(ResultSet rs)
//	        throws SQLException {
//
//	    ResultSetMetaData metaData = rs.getMetaData();
//
//	    // names of columns
//	    Vector<String> columnNames = new Vector<String>();
//	    int columnCount = metaData.getColumnCount();
//	    for (int column = 1; column <= columnCount; column++) {
//	        columnNames.add(metaData.getColumnName(column));
//	    }
//
//	    // data of the table
//	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
//	    while (rs.next()) {
//	        Vector<Object> vector = new Vector<Object>();
//	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
//	            vector.add(rs.getObject(columnIndex));
//	        }
//	        data.add(vector);
//	    }
//
//	    return new DefaultTableModel(data, columnNames);
//
//	}

	/**
	 * Create the application.
	 */
	public  Retail() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","SYSTEM");
			JOptionPane.showMessageDialog(null, "Connection Successfull");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1);
			e1.printStackTrace();
		}
		
		
		
		login=new JFrame("Login");
		login.setVisible(true);
		login.setSize(200, 300);
		login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loginp=new JPanel();
		tf_login=new JTextField(4);
		
		btnlogin=new JButton("Login");
		loginp.add(tf_login);
		loginp.add(btnlogin);
		login.add(loginp);
		//login.pack();
		btnlogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Statement stmt = null;
			    String query = "select ename from employees where eid='"+tf_login.getText()+"'";
			    
			    try {
			        stmt = conn.createStatement();
			        System.out.println("1");
			        ResultSet rr=stmt.executeQuery(query);
			        System.out.println("2");
			        String temp="";
			        while(rr.next())
			        {
			        temp=rr.getString(1);
			        break;
			        }
			        System.out.println("3");
			        System.out.println(temp);
			        query = "insert into username(name) values('"+temp+"')";
			        stmt = conn.createStatement();
			        rr=stmt.executeQuery(query);
			        login.dispose();
			    }catch(Exception e2){System.out.println(e2);}
		
		f=new JFrame("Retail Business Manager");
		f.setVisible(true);
		f.setSize(300,300);
		//f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listData=new String[] {"Employees","Customers","Products","Suppliers"};
		listDataDisplay=new String[] {"Employees","Customers","Products","Suppliers","logs","Purchases","Supply"};
		menu=new JComboBox<String>(listData);
		display_menu=new JComboBox<String>(listDataDisplay);
		p=new JPanel(new GridBagLayout());
	//	p.setBackground(Color.yellow);
		
		GridBagConstraints c=new GridBagConstraints();
		c.insets=new Insets(10, 10,10,10);
		
		c.gridx=0;
		c.gridy=0;
		
		purchase=new JButton("Purchase");
		
		add=new JButton("Add");
		
		remove=new JButton("remove");
		
		update=new JButton("update");
	
		display=new JButton("Display");
		
		monthlySale=new JButton("Monthly Sale");
		purchase.setPreferredSize(monthlySale.getPreferredSize());
		add.setPreferredSize(monthlySale.getPreferredSize());
		remove.setPreferredSize(monthlySale.getPreferredSize());
		update.setPreferredSize(monthlySale.getPreferredSize());
		display.setPreferredSize(monthlySale.getPreferredSize());
		
		p.add(purchase,c);
		
		
		
		c.gridx=0;
		c.gridy=2; 
		
		p.add(add,c);
		
		c.gridx=0;
		c.gridy=3; 
		
		p.add(remove,c);
		
		c.gridx=0;
		c.gridy=4; 
		
		p.add(menu,c);
		
		c.gridx=0;
		c.gridy=5; 
		
		p.add(update,c);
		
		c.gridx=2;
		c.gridy=2; 
		
		p.add(menu,c);
		
		c.gridx=2;
		c.gridy=6; 
		
		p.add(display_menu,c);
		
		c.gridx=0;
		c.gridy=6;
		
		p.add(display,c);
		
		c.gridx=0;
		c.gridy=7;
		
		p.add(monthlySale,c);
		f.add(p,BorderLayout.WEST);
		f.pack();
		
		display.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JTable table;
				JFrame displayFrame=new JFrame("Display");
				displayFrame.setVisible(true);
				String optionSelected=display_menu.getSelectedItem().toString();
				displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				//displayFrame.setSize(800,800);
				JPanel displayPanel=new JPanel();
				displayPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				
				
				try {
						
						switch(optionSelected)
						{
							case "Employees":
							cs = conn.prepareCall("begin ? := refcursor_jdbc1.getemployees(); end;");
							break;
							case "Customers":
							cs = conn.prepareCall("begin ? := refcursor_jdbc1.getcustomers(); end;");
							break;
							case "Products":
							cs = conn.prepareCall("begin ? := refcursor_jdbc1.getproducts(); end;");
							break;
							case "Suppliers":
							cs = conn.prepareCall("begin ? := refcursor_jdbc1.getsuppliers(); end;");
							break;
							case "Purchases":
								cs = conn.prepareCall("begin ? := refcursor_jdbc1.getpurchase(); end;");
								break;
							case "Supply":
								cs = conn.prepareCall("begin ? := refcursor_jdbc1.getsupply(); end;");
								break;
							default:
							JOptionPane.showMessageDialog(null, "Invalid Option");
					
						}
					
					cs.registerOutParameter(1, OracleTypes.CURSOR);
					cs.execute();
					ResultSet rs = (ResultSet)cs.getObject(1);
					SQLWarning warning = rs.getWarnings();
					if (warning != null)
					{
						 while (warning != null)
						    {
							 JOptionPane.showMessageDialog(null, warning.getMessage());
							  warning = warning.getNextWarning();
						    }
					}
					
//					String query="select refcursor_jdbc1.getcustomers() from dual";
//					PreparedStatement pst=conn.prepareStatement(query);
//					ResultSet rs=pst.executeQuery();
					
					table=new JTable();
					//table.setModel(buildTableModel(rs));
					table.setModel(DbUtils.resultSetToTableModel(rs));
					JScrollPane displayScroll=new JScrollPane();
					//displayScroll.setBounds(151,81,371,295);
					displayPanel.add(displayScroll);
					displayScroll.setViewportView(table);
					displayFrame.add(displayPanel);
					displayFrame.pack();
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println(e1.toString());
				}
				
			}
		});
		
		monthlySale.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				productList=new ArrayList<String>();
				prodIdList=new ArrayList<String>();
				System.out.println("1");
				monthSaleFrame1=new JFrame("List of Products");
				monthSaleFrame1.setSize(500, 500);
				System.out.println("2");
				monthSaleFrame1.setVisible(true);
				monthSaleFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				System.out.println("3");
				JPanel monthSalePanel1=new JPanel();
				
				String query="select pid,pname from products";
				try {
					System.out.println("4");
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					SQLWarning warning = rs.getWarnings();
					if (warning != null)
					{
						 while (warning != null)
						    {
							 JOptionPane.showMessageDialog(null, warning.getMessage());
							  warning = warning.getNextWarning();
						    }
					}
					System.out.println("5");
					while(rs.next())
					{
						
						productList.add(rs.getString(2));
						prodIdList.add(rs.getString(1));
					}
					System.out.println("6");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("7");
				System.out.println(productList);
				System.out.println("7 1/2");
				
				System.out.println("8");
				System.out.println(products);
				System.out.println("9");
				products=new String[productList.size()];
				int i=0;
				while(i<productList.size())
					{
					products[i]=productList.get(i);i++;
					}
					
				JList<String>prod=new JList<String>(products);
				prod.setBackground(null);
				JLabel lb=new JLabel("List Of Products\n");
				System.out.println("10");
				//monthSalePanel1.add(lb);
				monthSalePanel1.add(prod);
				monthSaleFrame1.add(monthSalePanel1);
				monthSaleFrame1.pack();
				String temp="";
				
				prod.addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if(e.getValueIsAdjusting()==false)
						{
							System.out.println("1");
							String temp=prodIdList.get(prod.getSelectedIndex());
							monthSaleFrame2=new JFrame("Monthly Sale for "+prod.getSelectedValue());
							monthSaleFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							System.out.println("2");
							monthSaleFrame2.setVisible(true);
							monthSaleFrame2.setSize(300,100);
							System.out.println("3");
							JPanel monthSalePanel2=new JPanel();
							monthSalePanel2=new JPanel();
							System.out.println("4");
							ResultSet rs;
							try {
								cs = conn.prepareCall("begin ? :=  reportMonthlySale.getmonthlySale('"+temp+"'); end;");
								cs.registerOutParameter(1, OracleTypes.CURSOR);
								System.out.println("5");
								cs.execute();
								rs = (ResultSet)cs.getObject(1);
								SQLWarning warning = rs.getWarnings();
								if (warning != null)
								{
									 while (warning != null)
									    {
										 JOptionPane.showMessageDialog(null, warning.getMessage());
										  warning = warning.getNextWarning();
									    }
								}
								System.out.println("6");
								while(rs.next())
								{
									
									temp=rs.getString(4);
									break;
								}
							
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.out.println("7");
							JLabel lb=new JLabel("Monthly Sale: "+temp);
							monthSalePanel2.add(lb);
							System.out.println("8");
							monthSaleFrame2.add(monthSalePanel2);
							
							monthSaleFrame1.dispose();
							//monthSaleFrame2.pack();
						}
					}
				});
			}
		});
		
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				optionSelected=menu.getSelectedItem().toString();
				//JFrame addFrame=new JFrame(optionSelected);
				
				switch(optionSelected)
				{
				case "Employees":
					JFrame employeeFrame=new JFrame("Add Employee");
					
					employeeFrame.setSize(500, 500);
					employeeFrame.setVisible(true);
					employeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JPanel employeePanel=new JPanel();
					
					JLabel l_ename=new JLabel("Employee Name");
					JTextField tf_ename=new JTextField("Enter Employee Name");
					JLabel l_etel=new JLabel("Employee Telephone Number");
					JTextField tf_etel=new JTextField("Enter Employee Telephone Number");
					JButton btneAdd=new JButton("Add");
					
					employeePanel.add(l_ename);
					employeePanel.add(tf_ename);
					employeePanel.add(l_etel);
					employeePanel.add(tf_etel);
					employeePanel.add(btneAdd);
					employeeFrame.add(employeePanel);
					employeeFrame.pack();
					break;
				case "Customers":
					JFrame customerFrame=new JFrame("Add Employee");
					
					customerFrame.setSize(500, 500);
					customerFrame.setVisible(true);
					customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JPanel customerPanel=new JPanel();
					
					JLabel l_cname=new JLabel("Customer Name");
					JTextField tf_cname=new JTextField("Enter Customer Name");
					JLabel l_ctel=new JLabel("Customer Telephone Number");
					JTextField tf_ctel=new JTextField("Enter Customer Telephone Number");
					JButton btncAdd=new JButton("Add");
					
					customerPanel.add(l_cname);
					customerPanel.add(tf_cname);
					customerPanel.add(l_ctel);
					customerPanel.add(tf_ctel);
					customerPanel.add(btncAdd);
					customerFrame.add(customerPanel);
					customerFrame.pack();
					break;
				case "Products":
					JFrame productFrame=new JFrame("Add Product");
					
					productFrame.setSize(500, 500);
					productFrame.setVisible(true);
					productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JPanel productPanel=new JPanel();
					
					JLabel l_pid=new JLabel("Product ID");
					JTextField tf_pid=new JTextField(3);
					
					JLabel l_pname=new JLabel("Product Name");
					JTextField tf_pname=new JTextField(4);
					JLabel l_qoh=new JLabel("QOH");
					JTextField tf_qoh=new JTextField(3);
					
					JLabel l_qohT=new JLabel("QOH Threshold");
					JTextField tf_qohT=new JTextField(3);
					
					JLabel l_ogPrice=new JLabel("Original Price");
					JTextField tf_ogPrice=new JTextField(3);
					
					JLabel l_discount=new JLabel("Discount Rate");
					JTextField tf_discount=new JTextField(3);
					
					JButton btnpAdd=new JButton("Add");
					
					productPanel.add(l_pid);
					productPanel.add(tf_pid);
					productPanel.add(l_pname);
					productPanel.add(tf_pname);
					productPanel.add(l_qoh);
					productPanel.add(tf_qoh);
					productPanel.add(l_qohT);
					productPanel.add(tf_qohT);
					productPanel.add(l_ogPrice);
					productPanel.add(tf_ogPrice);
					productPanel.add(l_discount);
					productPanel.add(tf_discount);
					productPanel.add(btnpAdd);
					productFrame.add(productPanel);
					productFrame.pack();
					
					btnpAdd.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								cs = conn.prepareCall("begin rbms.addproduct(:1,:2,:3,:4,:5,:6); end;");
								cs.setString(1, tf_pid.getText());
								cs.setString(2, tf_pname.getText());
								
								cs.setInt(3, Integer.parseInt(tf_qoh.getText()));
								cs.setInt(4, Integer.parseInt(tf_qohT.getText()));
								cs.setInt(5, Integer.parseInt(tf_ogPrice.getText()));
								cs.setFloat(6, Float.parseFloat(tf_discount.getText()));
								cs.execute();
								//JOptionPane.showMessageDialog(null, "Added Successfully");
								productFrame.dispose();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "Try Again"+e1);
							}
						}
					});
					break;
				case "Supplier":
					JFrame supplierFrame=new JFrame("Add Supplier");
					
					supplierFrame.setSize(500, 500);
					supplierFrame.setVisible(true);
					supplierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JPanel supplierPanel=new JPanel();
					
					JLabel l_sname=new JLabel("Supplier Name");
					JTextField tf_sname=new JTextField("Enter Supplier Name");
					
					JLabel l_scity=new JLabel("Supplier's City");
					JTextField tf_scity=new JTextField("Enter Supplier's City");
					JLabel l_stel=new JLabel("Supplier Telephone Number");
					JTextField tf_stel=new JTextField("Enter Supplier Telephone Number");
					JButton btnsAdd=new JButton("Add");
					
					supplierPanel.add(l_sname);
					supplierPanel.add(tf_sname);
					supplierPanel.add(l_scity);
					supplierPanel.add(tf_scity);
					supplierPanel.add(l_stel);
					supplierPanel.add(tf_stel);
					supplierPanel.add(btnsAdd);
					supplierFrame.add(supplierPanel);
					supplierFrame.pack();
					break;
				}
			}
		});
		purchase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame purchaseFrame=new JFrame("Purchase");
				
				purchaseFrame.setVisible(true);
				purchaseFrame.setSize(300,400);
				purchaseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JPanel purchasePanel=new JPanel();
				JLabel l_eid=new JLabel("Employee Id");
				JTextField tf_eid=new JTextField(3);
				//tf_eid.setSize(200,400);
				JLabel l_cid=new JLabel("Customer Id");
				JTextField tf_cid=new JTextField(3);
				//tf_cid.setSize(200,400);
				JLabel l_pid=new JLabel("Product Id");
				JTextField tf_pid=new JTextField(3);
				//tf_pid.setSize(200,400);
				JLabel l_qty=new JLabel("Purchase Quantity");
				JTextField tf_qty=new JTextField(3);
				//tf_qty.setSize(200,400);
				JButton btnAdd=new JButton("Add");
				purchasePanel.add(l_eid);
				purchasePanel.add(tf_eid);
				purchasePanel.add(l_cid);
				purchasePanel.add(tf_cid);
				purchasePanel.add(l_pid);
				purchasePanel.add(tf_pid);
				purchasePanel.add(l_qty);
				purchasePanel.add(tf_qty);
				purchasePanel.add(btnAdd);
				purchaseFrame.add(purchasePanel);
				purchaseFrame.pack();
				
				btnAdd.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						try {
							cs = conn.prepareCall("begin rbms.addpurchases(:1,:2,:3,:4); end;");
							cs.setString(1, tf_eid.getText());
							cs.setString(2, tf_pid.getText());
							cs.setString(3, tf_cid.getText());
							cs.setInt(4, Integer.parseInt(tf_qty.getText()));
							cs.execute();
							SQLWarning warning = cs.getWarnings();
							if (warning != null)
							{
								 while (warning != null)
								    {
									 JOptionPane.showMessageDialog(null, warning.getMessage());
									  warning = warning.getNextWarning();
								    }
							}
							else
							//JOptionPane.showMessageDialog(null, "Added Successfully");
							purchaseFrame.dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Try Again"+e1);
						}
						System.out.println("5");
						
					}
				});
			}
		});
	}
		});
			

	/**
	 * Initialize the contents of the frame.
	 */
	}

}


