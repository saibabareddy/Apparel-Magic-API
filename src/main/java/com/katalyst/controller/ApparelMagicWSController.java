package com.katalyst.controller;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.katalyst.service.ApparelMagicWSService;
import net.sf.json.JSONObject;

@RestController
public class ApparelMagicWSController {
private static final Logger logger = LoggerFactory.getLogger(ApparelMagicWSController.class);
	
@Autowired
ApparelMagicWSService apparelMagicWsService;

//Test
@RequestMapping(value="/test")
public String info()
{
	logger.debug("This is a test for logger creation");
	return "This app is Apparel Magic Client";		
}

//Business class for posting Purchase_order to the SKUVault
@RequestMapping(value="/Purchase_order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
public ArrayList<JSONObject> PurchaseOrders(@RequestBody String _date)
{
	return apparelMagicWsService.SyncPOusingDate(_date);			
}
	
}
