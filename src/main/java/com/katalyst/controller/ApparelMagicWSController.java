package com.katalyst.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katalyst.model.MapShipment;
import com.katalyst.model.NewOrder;
import com.katalyst.model.Shipment;
import com.katalyst.service.ApparelMagicWSService;
import com.katalyst.util.HttpClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RestController
public class ApparelMagicWSController {
	private static final Logger logger = LoggerFactory.getLogger(ApparelMagicWSController.class);
	
	@Autowired
	ApparelMagicWSService apparelMagicWsService;

	@RequestMapping(value="/test")
	public String info()
	{
		logger.debug("This is a test for logger creation");
		return "This app is Apparel Magic Client";
		
	}
	
	//This method is for getting all shipments
	@RequestMapping(value="/Shipments")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Shipment> Shipments()
	{
		return apparelMagicWsService.getShipments();
		
	}
	
	//This method is for getting shipments by individual ID
	@RequestMapping(value="/Shipments/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Shipment shipment(@PathVariable("id") long id){
		return apparelMagicWsService.getShipment(id);
	}

	//This method is for POST the new shipment method to the URL
	@RequestMapping(value="/newShipment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String shipment(@RequestBody MapShipment request){
		return apparelMagicWsService.createNewShipment(request);
	}
	@RequestMapping(value="/newOrder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String neworder(@RequestBody NewOrder request){
		return apparelMagicWsService.createNewOrder(request);
	}
	
	//This method is for getting all PO's
		@RequestMapping(value="/Purchase_order")
		@Produces(MediaType.TEXT_PLAIN)
		public String PurchaseOrders()
		{
			return apparelMagicWsService.getPO();
			
		}
	
}
