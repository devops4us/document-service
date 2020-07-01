package com.ibm.documents;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class DocumentService {

	private static Logger logger= Logger.getLogger(DocumentService.class.getName());
	private static DateFormat fmt= new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
	
	@Path("/status")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String status () 
	{		
		String host="";
		try {
			host=InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			host="unknown host";
			logger.log(Level.SEVERE, "unable to resolve localhost", e);
		}		
		String infoMsg=String.format("OK - server=%s, time=%s",fmt.format(new Date()), host);
		if(logger.isLoggable(Level.FINE))
			logger.fine(infoMsg);		
		return infoMsg;
	}
	
}
