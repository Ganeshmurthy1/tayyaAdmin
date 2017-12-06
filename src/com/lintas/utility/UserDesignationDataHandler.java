package com.lintas.utility;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.lintas.admin.model.UserDesignation;

public class UserDesignationDataHandler  extends DefaultHandler {
	
	private List<UserDesignation> userdesignationlist = new ArrayList<UserDesignation>();
	private UserDesignation userdesignation;
	 private String temp;
	 public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserDesignationDataHandler.class);

	  /*
      * When the parser encounters plain text (not XML elements),
      * it calls(this method, which accumulates them in a string buffer
      */
	   public void characters(char[] buffer, int start, int length) {
           temp = new String(buffer, start, length);
    }
	   
	   /*
        * Every time the parser encounters the beginning of a new element,
        * it calls this method, which resets the string buffer
        */ 
	   public void startElement(String uri, String localName,
               String qName, Attributes attributes) throws SAXException {
     log.info("startElement --- uri" + uri);
     log.info("startElement --- qName::::" + qName);
     log.info("startElement --- localName" + localName);
     log.info("startElement --- attributes" + attributes.getLocalName(1));
        temp = "";
        if (qName.equalsIgnoreCase("UserDesignation")) {
        	userdesignation = new UserDesignation();
        	 userdesignationlist.add(userdesignation);
            log.info("userdesignation created.");    
        }
 }
	   /*
        * When the parser encounters the end of an element, it calls this method
        */
       public void endElement(String uri, String localName, String qName)
                     throws SAXException {

             if (qName.equalsIgnoreCase("Designation")) {
            	 userdesignation.setDesignation(temp);
              } else if (qName.equalsIgnoreCase("Description")) {
            	  userdesignation.setDescription(temp);
              } 
            
       }

       public List<UserDesignation> getUserDesignationData() {
              log.info("UserDesignation getUserDesignationData.");
              return userdesignationlist;
       }
	   
	   
}
