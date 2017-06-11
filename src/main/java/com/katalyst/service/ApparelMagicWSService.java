package com.katalyst.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.katalyst.controller.ApparelMagicWSController;
import com.katalyst.dao.PoDao;
import com.katalyst.model.CreateNewPO;
import com.katalyst.model.MapShipment;
import com.katalyst.model.NewOrder;
import com.katalyst.model.Shipment;
import com.katalyst.util.HttpClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ApparelMagicWSService {
	
	private static final Logger logger = LoggerFactory.getLogger(ApparelMagicWSService.class);
	
	private static String token= "64ebd05e550b23a15be09ccef57b27c6";
    private static String time="171114279788";
    
    @Autowired
    public PoDao padao;
	
	public ArrayList<Shipment> getShipments()
	{
		
		JSONObject response = null;
		JSONObject shipment = null;
		JSONArray responsearray=null;
		ArrayList<Shipment> Shipments = null;
		try {
			response = HttpClient.sendto(null, "GET", "ship_methods?time=171114279788&token=64ebd05e550b23a15be09ccef57b27c6");
			responsearray=(JSONArray)response.get("response");
			int j=responsearray.size();
			Shipments =new ArrayList<>();
			for(int i=0;i < j; i++)
			{
				shipment=(JSONObject) responsearray.get(i);
				Shipments.add((Shipment) JSONObject.toBean(shipment, Shipment.class));
				logger.debug( i +" Shipment :"+shipment.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	     return Shipments;
		
	}
	
	public Shipment getShipment(long id){
		JSONObject response = null;
		JSONObject shipment = null;
		JSONArray responsearray=null;
		logger.debug("ID for path:"+id);
		System.out.println("ID for path:"+id);
		Map<Integer, Shipment> Shipments = null;
		try {
			response = HttpClient.sendto(null, "GET", "ship_methods?time=171114279788&token=64ebd05e550b23a15be09ccef57b27c6");
			responsearray=(JSONArray)response.get("response");
			int j=responsearray.size();
			Shipments = new HashMap<Integer,Shipment>();
			Integer k;
			for(int i=0;i < j; i++)
			{
				shipment=(JSONObject) responsearray.get(i);
				//logger.debug( i +" Shipment :"+ shipment.toString());
				String Jid= (String) shipment.get("id");
				logger.debug("Jid:"+ Jid);
				k = new Integer(Integer.parseInt(Jid));
				logger.debug("K value is "+k);
				if(k==id){
					return (Shipment) JSONObject.toBean(shipment, Shipment.class);
				}
				Shipments.put(k, (Shipment) JSONObject.toBean(shipment, Shipment.class));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	     return null;
		
	}
	
	public String createNewShipment(MapShipment requestString){
		JSONObject response = null;
		logger.debug("Data to be sent:"+requestString);
		JSONObject postData = new JSONObject();
		postData = requestString.toJson();
		 postData.put("time", time);
         postData.put("token", token);
		
		try {
			response = HttpClient.sendto(postData, "POST", "ship_methods");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Response from HttpClient: "+response.toString());
		return response.toString();
	}
	public String createNewOrder(NewOrder requestString){
		JSONObject response = null;
		logger.debug("Data to be sent:"+requestString.toString());
		JSONObject postData = new JSONObject();
		postData = requestString.toJson();
		 postData.put("time", time);
         postData.put("token", token);
		try {
		response = HttpClient.sendto(postData, "POST", "orders");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Response from HttpClient: "+response.toString());
		return response.toString();
	}
	
	public String getPO()
	{
		
		JSONObject response = null;
		JSONObject PO = null;
		JSONArray responsearray=null;
		JSONArray purchase_order_items= null;
		ArrayList<Shipment> Shipments = null;
		try {
			response = HttpClient.sendto(null, "GET", "purchase_orders?time=171114279788&token=64ebd05e550b23a15be09ccef57b27c6");
			responsearray=(JSONArray)response.get("response");
			int j=responsearray.size();
			logger.debug("Number of Arrays:"+j);
			Shipments =new ArrayList<>();
			for(int i=0; i < j; i++)
			{
				PO=(JSONObject) responsearray.get(i);
				CreateNewPO newpo = new CreateNewPO();
				newpo.setPurchase_order_id(PO.getString("purchase_order_id"));
				newpo.setDate(PO.getString("date"));
				newpo.setDate_due(PO.getString("date_due"));
				newpo.setShip_via(PO.getString("ship_via"));
				newpo.setTerms_id(PO.getString("terms_id"));
				newpo.setWarehouse_id(PO.getString("warehouse_id"));
				newpo.setVendor_id(PO.getString("vendor_id"));
				
				padao.createConnection();
				String id= padao.getPO(Integer.parseInt(newpo.getPurchase_order_id()));
				
				if(id.isEmpty() || id == null)
				{
				padao.doInsertCustomer(newpo);
				padao.doSelectTest();
				}
				padao.closeConnection();
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	     return PO.toString();
		
	}


}
