package com.katalyst.service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.katalyst.dao.PoDao;
import com.katalyst.model.CreateNewPO;
import com.katalyst.model.CreateNewPO1;
import com.katalyst.model.ShipVia;
import com.katalyst.model.SkuJson;
import com.katalyst.model.SkuLineItemsJson;
import com.katalyst.util.HttpClient;
import com.katalyst.util.SkuHttpClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ApparelMagicWSService {
	
private static final Logger logger = LoggerFactory.getLogger(ApparelMagicWSService.class);

/*@Autowired
public PoDao padao;*/
	
public ArrayList<JSONObject> SyncPOusingDate(String _date)
	{		
	
		JSONObject response = null;
		JSONObject PO = null;
		JSONArray responsearray=null;
		JSONArray purchase_order_items= null;
		JSONObject poi = null;	
		ArrayList<JSONObject> responsefromskuvault= new ArrayList<>();	
		try 
		{
			response = HttpClient.sendto(null, "GET", "purchase_orders?time=171114279788&token=64ebd05e550b23a15be09ccef57b27c6");
			logger.debug("The data we get from apparel Magic:"+response.toString());
			responsearray=(JSONArray)response.get("response");
			int j=responsearray.size();
			//padao.createConnection();
			for(int i=0; i < j; i++)
			{
				PO=(JSONObject) responsearray.get(i);
				SkuJson post1= mapPost1(PO);
				ShipVia post3 = getNameForShipvia(PO.getString("ship_via"));
				CreateNewPO newpo =mapPO(PO);
				purchase_order_items = (JSONArray) PO.get("purchase_order_items");
				int k = purchase_order_items.size();
				//String id= padao.getPO(Integer.parseInt(newpo.getPurchase_order_id()));
				ArrayList<SkuLineItemsJson> post2 = new ArrayList<>();
				Date date1=(Date) new SimpleDateFormat("MM/dd/yyyy").parse(PO.getString("date"));
				//if(id.equals("null"))
				logger.debug("date we sent through post" + _date);
				Date _date1 = (Date) new SimpleDateFormat("MM/dd/yyyy").parse(_date);
				int result = _date1.compareTo(date1);
				SkuLineItemsJson sku = new SkuLineItemsJson();
				if( result == 0)
				{
				int v = 0;
				for(int m=0; m < k ; m++)
					{
						poi =  purchase_order_items.getJSONObject(m);
						sku = mapPost2(poi);
						post2.add(sku);
						v++;
						CreateNewPO1 poiobj = mapPO1(poi, PO);
						//padao.doInsertPurchase_order_item(poiobj);
					}
				JSONObject postdataJson = postJson(post1, post2, post3);
				logger.debug("The Json to be posted:"+ postdataJson.toString());
				if(!(postdataJson == null))
				{
					Date date = new Date();
					JSONObject addingOutput = SkuHttpClient.sendto(postdataJson,"POST","purchaseorders/createPO");
					addingOutput.accumulate("Integration_Type", "Purchase Orders");
					addingOutput.accumulate("Date_of_Retrieval", date.toString());
					addingOutput.accumulate("Corresponding_PO_Number", PO.getString("purchase_order_id"));					
					addingOutput.accumulate("Corresponding_PO_Created_Date", PO.getString("date"));
					addingOutput.accumulate("Corresponding_WarehouseId", ((getNameForWarehouseId(PO.getString("warehouse_id"))==null)? "Empty":getNameForWarehouseId(PO.getString("warehouse_id"))));
					addingOutput.accumulate("Corresponding_TermsId", ((getNameForTermsId(PO.getString("terms_id"))==null)? "Empty":getNameForTermsId(PO.getString("terms_id"))));
					addingOutput.accumulate("Corresponding_VendorId", ((getNameForVendorId(PO.getString("vendor_id"))==null)? "Empty":getNameForVendorId(PO.getString("vendor_id"))));
					addingOutput.accumulate("Corresponding_Carrier_details",post3.toString());
					addingOutput.accumulate("Corresponding_SKU", sku.getSKU());
					responsefromskuvault.add(addingOutput);
					Thread.sleep(10000);
					//padao.doInsertStatus(responsefromskuvault);
				}
				logger.debug("Response from Sku Vault:"+ responsefromskuvault);
				//padao.doInsertPO(newpo);
			}
			}
			//padao.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
	     return responsefromskuvault;
		
	}
		
private JSONObject postJson(SkuJson post1,ArrayList<SkuLineItemsJson>post2 ,ShipVia post3)
	{
		JSONArray listorderitems = new JSONArray();
		JSONObject postJson = new JSONObject();
		int j= post2.size();
		for(int i=0; i<j ; i++){
			listorderitems.add(i, post2.get(i).toJson());	
		}
		postJson = post1.toJson();
		postJson.accumulate("LineItems", listorderitems);
		postJson.accumulate("ShippingCarrierClass", post3.toJSON());
		return postJson;		
	}
		
private SkuJson mapPost1(JSONObject PO) 
{
		SkuJson post1 = new SkuJson();
		post1.setArrivalDueDate(PO.getString("date_due"));
		post1.setOrderDate(PO.getString("date"));
		post1.setOrderCancelDate(PO.getString("date_due"));
		post1.setRequestedShipDate(PO.getString("date_due"));
		post1.setPoNumber(PO.getString("purchase_order_id"));
		//logger.info("warehouse id from ");
		post1.setShipToWarehouse(getNameForWarehouseId(PO.getString("warehouse_id")));
		post1.setTermsName(getNameForTermsId(PO.getString("terms_id")));
		post1.setSupplierName(getNameForVendorId(PO.getString("vendor_id")));
		post1.setShipToAddress("N/A");
		post1.setTenantToken("Kels6k5wARKewRWwCs4aNtXqWNUKO+pDtuQH0/pGN1Q=");
		post1.setUserToken("HU516hJO+DBzcpmwNu/O/RM5FFvwY2qcKK4MuXBYdRo=");
		return post1;
}
	
private SkuLineItemsJson mapPost2(JSONObject poi)
	{
		SkuLineItemsJson post = new SkuLineItemsJson();
		post.setCost(poi.getString("amount"));
		post.setQuantity(getQty(poi.getString("qty")));
		post.setQuantityTo3PL(getQty(poi.getString("qty")));
		post.setSKU(poi.getString("style_number")+"-"+poi.getString("attr_2")+"-"+getSize(poi.getString("size")));
		post.setIdentifier("Shipping");
		post.setPrivateNotes("String");
		post.setPublicNotes("String");
		post.setVariant("String");
		return post;
	}
private String getQty(String quantity){
	String qty;
	qty =String.valueOf(quantity).split("\\.")[0];
	return (String)qty;
	}
private String getSize(String siz){
	String size = null;
	if(siz.equals("S")){
		size = "01";		
	}
	else if(siz.equals("M"))
	{
		 size = "02";
	}
	else if(siz.equals("L"))
	{
		size = "03";
	}
	else if(siz.equals("XL"))
	{
		 size = "04";
	}
	else {
		size = "05";
	}
	
	return size;
}
private String getNameForWarehouseId(String id){
		String name = null;
		try {
			if(id.equals(null)){
				name = "BULK";
			}
			else{
			JSONObject response = HttpClient.sendto(null, "GET", "warehouses/"+id+"?time=171114279788&token=64ebd05e550b23a15be09ccef57b27c6");
			JSONArray responsearray = (JSONArray) response.get("response");
			int i = responsearray.size();
			logger.info("Response of warehouseid:"+ response.toString());
			if(i == 0)
			{
				name = "BULK";
			}
			else
			{
			JSONObject required = (JSONObject) responsearray.get(0);
			name = (String)required.get("BULK");
			//logger.debug("getting name:"+name);
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
}

private String getNameForVendorId(String id)
{
		String vendor_name = null;
		try {
			if(id.equals(null)){
				vendor_name = "Gerald Ford";
			}
			else{
			JSONObject response = HttpClient.sendto(null, "GET", "vendors/"+ id +"?time=171114279788&token=64ebd05e550b23a15be09ccef57b27c6");
			JSONArray responsearray = (JSONArray) response.get("response");
			int i = responsearray.size();
			logger.info("Response of vendorid:"+ response.toString());
			if(i == 0)
			{
				vendor_name = "Gerald Ford";
			}
			else{
			JSONObject required = (JSONObject) responsearray.get(0);
			vendor_name = (String)required.get("vendor_name");
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vendor_name;
}

private String getNameForTermsId(String id)
{
		String name = null;
		try {
			if(id.equals(null)){
				name = "String";
			}
			else{
				
			
			JSONObject response = HttpClient.sendto(null, "GET", "terms/"+ id +"?time=171114279788&token=64ebd05e550b23a15be09ccef57b27c6");
			JSONArray responsearray = (JSONArray) response.get("response");
			int i = responsearray.size();
			logger.info("Response of termsid:"+ response.toString());
			if(i == 0)
			{
				name = "String";
			}
			else
			{
			JSONObject required = (JSONObject) responsearray.get(0);
			name = (String)required.get("name");
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
}

private ShipVia getNameForShipvia(String id)
{
		ShipVia via = new ShipVia();
		try {
			if(id.equals(null)){
				via.setName("Ground");
				via.setProvider("Thompson");
			}
			else{
				JSONObject response = HttpClient.sendto(null, "GET", "ship_methods/"+ id +"?time=171114279788&token=64ebd05e550b23a15be09ccef57b27c6");
				JSONArray responsearray = (JSONArray) response.get("response");
				int i = responsearray.size();
				//logger.info("Response of Shipviaid:"+ response.toString());
				if(i == 0)
				{
					via.setName("Ground");
					via.setProvider("Thompson");
				}
				else
				{
				JSONObject required = (JSONObject) responsearray.get(0);
				via.setName(required.getString("name"));
				via.setProvider(required.getString("provider"));
			}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return via;
}
	
private CreateNewPO mapPO(JSONObject PO)
{
		CreateNewPO newpo = new CreateNewPO();
		newpo.setPurchase_order_id(PO.getString("purchase_order_id"));
		newpo.setDate(PO.getString("date"));
		newpo.setDate_due(PO.getString("date_due"));
		newpo.setShip_via(PO.getString("ship_via"));
		newpo.setTerms_id(PO.getString("terms_id"));
		newpo.setWarehouse_id(PO.getString("warehouse_id"));
		newpo.setVendor_id(PO.getString("vendor_id"));
		return newpo;
}
	
private CreateNewPO1 mapPO1(JSONObject poi,JSONObject PO)
{		
		CreateNewPO1 poiobj = new CreateNewPO1();
		poiobj.setPo_id(PO.getString("purchase_order_id"));
		poiobj.setAmount(poi.getString("amount"));
		poiobj.setAttr2(poi.getString("attr_2"));
		poiobj.setQty(poi.getString("qty"));
		poiobj.setSize(poi.getString("size"));
		poiobj.setStyle_number(poi.getString("style_number"));
		logger.debug("The Data of purchase orders :"+ poiobj.toString());
		return poiobj;
}

}