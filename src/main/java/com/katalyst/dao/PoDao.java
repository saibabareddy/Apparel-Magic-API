package com.katalyst.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import com.katalyst.model.CreateNewPO;
import com.katalyst.model.CreateNewPO1;

import net.sf.json.JSONObject;

@Repository
@PropertySource("application.properties")
public class PoDao {
public Connection conn; 
public void createConnection()
	 {
	   try
	     {	    	
	      Class.forName("com.mysql.jdbc.Driver").newInstance();
	      String url = "jdbc:mysql://wmsmysql.cun5uvzp5qky.us-east-1.rds.amazonaws.com:3306/POIntegration";
	      conn = DriverManager.getConnection(url, "wmsmysqladmin", "WMSMySQLPass1");	      
	      System.out.println("COnnection Successful");	      
	     }
	    catch (Exception e)     
	     {
	    	System.err.println(e.getMessage());
	     }
	  
	  }

// Following method is a simple select
public void doSelectTest( )
{
System.out.println("[OUTPUT FROM SELECT]");
   try
	  {
	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery("Select * from PO");
	    while (rs.next())
	      {
	       int s = rs.getInt("purchase_order_id");
	       String n = rs.getString("date_due");
	       String m = rs.getString("order_date");
	       String o = rs.getString("warehouse_id");
	       String d = rs.getString("ship_via");	
	       String p = rs.getString("vendor_id");
	       String q = rs.getString("terms_id");
	       System.out.println(s+" "+n+" "+m+" "+o+" "+d+" "+p+" "+q);
	      }
	  }
   catch (SQLException ex)
	    {
	      System.err.println(ex.getMessage());
	    }
	  }
	
public String getPO(int poid)
	  {
	    System.out.println("[OUTPUT FROM Slect of PO]");
	    Integer s = null;	  
	    try
	    {
	    	PreparedStatement st = conn.prepareStatement("Select id from PO where id = ?");
	    	st.setInt(1, poid);
	        ResultSet rs = st.executeQuery();	      
	      while (rs.next())
	      {
	        s = rs.getInt("id");
	      }
	    }
	    catch (SQLException ex)
	    {
	      System.err.println(ex.getMessage());
	    }
	    return s+"";
	  }
	  
// Following method inserts
public void doInsertPO(CreateNewPO purchaseorder)
{
	System.out.print("\n[Performing INSERT] ... ");
	  try
	  {
	    PreparedStatement st = conn.prepareStatement("INSERT INTO PO (purchase_order_id, date_due, order_date, warehouse_id, ship_via, vendor_id, terms_id) VALUES (?,?,?,?,?,?,?)");
	    st.setInt(1, Integer.parseInt(purchaseorder.getPurchase_order_id()));
		st.setString(2, purchaseorder.getDate_due());
		st.setString(3, purchaseorder.getDate());
		st.setString(4, purchaseorder.getWarehouse_id());
		st.setString(5, purchaseorder.getShip_via());
		st.setString(6, purchaseorder.getVendor_id());
		st.setString(7, purchaseorder.getTerms_id());		
	    st.executeUpdate();
	    System.out.println("Insert succesful");
	  }
	  catch (SQLException ex)
	  {
	    System.err.println(ex.getMessage());
	  }
}


public void doInsertPurchase_order_item(CreateNewPO1 poi)
{
	 System.out.print("\n[Performing INSERT] ... ");
	  try
	  {
	    PreparedStatement st = conn.prepareStatement("INSERT INTO purchase_order_items (po_id, style_number, attr2, po_size, qty, amount) VALUES (?,?,?,?,?,?)");
	    st.setInt(1, Integer.parseInt(poi.getPo_id()));
		st.setString(2, poi.getStyle_number());
		st.setString(3, poi.getAttr2());
		st.setString(4, poi.getSize());
		st.setString(5, poi.getQty());
		st.setString(6, poi.getAmount());
	    st.executeUpdate();
	    System.out.println("Insert succesful");
	  }
	  catch (SQLException ex)
	  {
	    System.err.println(ex.getMessage());
	  }
}

public void closeConnection()
	{		  
	 try 
	 {
			conn.close();
		
	 } 
	 catch (SQLException e) 
	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
	 }	  
	}
	  

}
