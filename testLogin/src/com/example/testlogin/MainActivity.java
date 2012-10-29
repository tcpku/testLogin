package com.example.testlogin;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import com.google.android.maps.*;

import android.location.*;

public class MainActivity extends Activity {
	
    
    
    LocationManager locationManager;
    Document doc;
    
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        LocationListener locationListener=new LocationListener(){
			public void onLocationChanged(Location location) {
				if(location!=null)
				{
					Log.i("superMap","locationchanged");
				}
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,locationListener);
        Button logButton = (Button) findViewById(R.id.button1);
        
        
        Button canButton = (Button) findViewById(R.id.button2);
        canButton.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		jumpToLayout1();
        	}
        });
        logButton.setOnClickListener(new Button.OnClickListener()
		{
	        public void onClick(View v)
	        {
	        	EditText userName= (EditText) findViewById(R.id.editText1);
	            EditText passWord= (EditText) findViewById(R.id.editText2);
	            final String uName= userName.getText().toString();
	            final String pWord= passWord.getText().toString();
	        	
	            setContentView(R.layout.mylayout);
	        	
	            //TextView mAddress= (TextView) findViewById(R.id.address);
	        	Button outButton = (Button) findViewById(R.id.button2);
	            outButton.setOnClickListener(new Button.OnClickListener()
	            {
	            	public void onClick(View v)
	            	{
	            		jumpToLayout1();
	            	}
	            });
	            Button getAddress = (Button) findViewById(R.id.button1);
	            getAddress.setOnClickListener(new Button.OnClickListener()
	            {
	            	public void onClick(View v)
	            	{
	            		Calendar c=Calendar.getInstance();
	            		int mYear=c.get(Calendar.YEAR);
	            		int mMonth=c.get(Calendar.MONTH);
	            		int mDay=c.get(Calendar.DAY_OF_MONTH);
	            		int mHour=c.get(Calendar.HOUR_OF_DAY);
	            		int mMinute=c.get(Calendar.MINUTE);
	            		
	            		TextView mLatitude=(TextView) findViewById(R.id.latitude);
	                    TextView mLongtitude=(TextView) findViewById(R.id.longtitude);
	                    TextView mAddress= (TextView) findViewById(R.id.address);
	                    TextView time=(TextView) findViewById(R.id.time);
	                    Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	            		GeoPoint gp=getGeoByLocation(location);
	            		Address mAddr=getAddressByGeoPoint(MainActivity.this,gp);
	                    double latitude=location.getLatitude();
	            		double longtitude=location.getLongitude();
	            		mLatitude.setText("latitude:"+latitude);
	            		mLongtitude.setText("longtitude:"+longtitude);
	            		mAddress.setText("Address:"+mAddr.getCountryName()+","+mAddr.getLocality());
	            		time.setText("time:"+mYear+"/"+mMonth+"/"+mDay+"/"+mHour+"/"+mMinute);
	            	
         		        
	            		//readDoc();
	            		try {
	            		       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            		       DocumentBuilder builder = factory.newDocumentBuilder();
	            		       doc = builder.newDocument();
	            		      }
	            		      catch (FactoryConfigurationError fce){
	            			  System.err.println("Could not create DocumentBuilderFactory");
	            		      }
	            		      catch (ParserConfigurationException pce) { 
	            		          System.out.println("Could not locate a JAXP parser"); 
	            		      }
	            		      /*catch (SAXException se) {
	            		          System.out.println("XML file is not well-formed.");
	            		      }
	            		      catch (IOException ioe) { 
	            		          System.out.println(
	            		          "Due to an IOException, the parser could not read the XML file"
	            		          ); 
	            		      }*/
	            		Element root= doc.getDocumentElement();
	            		
	            		 
	            		
	            		 Element user = doc.createElement("user");
	            		 Element addr = doc.createElement("address");

	            		 user.appendChild(addr);
	            	     root.appendChild(user);
	            				   
	            				  
	            			 /*user.setAttribute("password", "1");
	            			 addr.setAttribute("time", "12:00");
	            		    addr.setAttribute("latitude", "12");
	            		    addr.setAttribute("longtitude", "13");
	            		 
	            		//addDoc();*/
	            		//doc2XmlFile(doc, "history2.xml");
	            	}
	            });
	        }
		});
        
    }
    public void jumpToLayout1()
    {
    	setContentView(R.layout.activity_main);
        Button logButton = (Button) findViewById(R.id.button1);
        
        
        Button canButton = (Button) findViewById(R.id.button2);
        canButton.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		jumpToLayout1();
        	}
        });
        logButton.setOnClickListener(new Button.OnClickListener()
		{
	        public void onClick(View v)
	        {
	        	EditText userName= (EditText) findViewById(R.id.editText1);
	            EditText passWord= (EditText) findViewById(R.id.editText2);
	        	
	            final String uName= userName.getText().toString();
	            final String pWord= passWord.getText().toString();
	        	
	            setContentView(R.layout.mylayout);
	        	
	            
	        	Button outButton = (Button) findViewById(R.id.button2);
	            outButton.setOnClickListener(new Button.OnClickListener()
	            {
	            	public void onClick(View v)
	            	{
	            		jumpToLayout1();
	            	}
	            });
	            Button getAddress = (Button) findViewById(R.id.button1);
	            getAddress.setOnClickListener(new Button.OnClickListener()
	            {
	            	public void onClick(View v)
	            	{
	            		Calendar c=Calendar.getInstance();
	            		int mYear=c.get(Calendar.YEAR);
	            		int mMonth=c.get(Calendar.MONTH);
	            		int mDay=c.get(Calendar.DAY_OF_MONTH);
	            		int mHour=c.get(Calendar.HOUR_OF_DAY);
	            		int mMinute=c.get(Calendar.MINUTE);
	            		
	            		TextView mLatitude=(TextView) findViewById(R.id.latitude);
	                    TextView mLongtitude=(TextView) findViewById(R.id.longtitude);
	                    TextView mAddress= (TextView) findViewById(R.id.address);
	                    TextView time=(TextView) findViewById(R.id.time);
	                    Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	            		GeoPoint gp=getGeoByLocation(location);
	            		Address mAddr=getAddressByGeoPoint(MainActivity.this,gp);
	                    double latitude=location.getLatitude();
	            		double longtitude=location.getLongitude();
	            		mLatitude.setText("latitude:"+latitude);
	            		mLongtitude.setText("longtitude:"+longtitude);
	            		mAddress.setText("Address:"+mAddr.getCountryName()+","+mAddr.getLocality());
	            		time.setText("time:"+mYear+"/"+mMonth+"/"+mDay+"/"+mHour+"/"+mMinute);
	            		/*Document doc;
	            		WriteFile writeFile=new WriteFile();
	            		doc=writeFile.readDoc();
	            		doc=writeFile.addDoc(doc,uName, pWord, mAddress.getText().toString(), 
	            				time.getText().toString(), mLatitude.getText().toString(), mLongtitude.getText().toString());
	            		writeFile.doc2XmlFile(doc, "history.xml");*/
	            	}
	            });
	        }
		});
        
    	
    }
    

    public GeoPoint getGeoByLocation(Location location)
    {
    	GeoPoint gp=null;
    	try
    	{
    		if(location!=null)
    		{
    			double geoLatitude=location.getLatitude()*1E6;
    			//Log.i(TAG,geoLatitude+"");
    			double geoLongtitude=location.getLongitude()*1E6;
    			//Log.i(TAG,geoLongtitude+"");
    			gp=new GeoPoint((int) geoLatitude,(int) geoLongtitude);
    			   
    		}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return gp;
    }
    public Address getAddressByGeoPoint(Context context,GeoPoint gp)
    {
    	Address address=null;
    	//Log.i(TAG,(gp=null)+"");
    	try
    	{
    		if(gp!=null)
    		{
    			Geocoder gc=new Geocoder(context,Locale.US);
    			double geoLatitude=(int) gp.getLatitudeE6()/1E6;
    			double geoLongtitude=(int) gp.getLongitudeE6()/1E6;
    			List<Address>
lstAddress=gc.getFromLocation(geoLatitude,geoLongtitude,1);
    			if(lstAddress.size()>0)
    			{
    				address=lstAddress.get(0);
    			}
    
    		}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return address;
    }
    /*public void readDoc() {
	      try {
	       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	       DocumentBuilder builder = factory.newDocumentBuilder();
	       doc = builder.parse( new File("history.xml") );
	      }
	      catch (FactoryConfigurationError fce){
		  System.err.println("Could not create DocumentBuilderFactory");
	      }
	      catch (ParserConfigurationException pce) { 
	          System.out.println("Could not locate a JAXP parser"); 
	      }
	      catch (SAXException se) {
	          System.out.println("XML file is not well-formed.");
	      }
	      catch (IOException ioe) { 
	          System.out.println(
	          "Due to an IOException, the parser could not read the XML file"
	          ); 
	      }
	    }*/
	//public void addDoc(){
		//Element root = doc.getDocumentElement();
		//Element user = doc.createElement("user");
		//Element addr = doc.createElement("address");

		  // user.appendChild(addr);
		  // root.appendChild(user);
		   
		  
		   //user.setAttribute("password", "1");
		   //addr.setAttribute("time", "12:00");
		   //addr.setAttribute("latitude", "12");
		   //addr.setAttribute("longtitude", "13");
		
	//}
	public void removeDoc(){
		Element root=doc.getDocumentElement();
		doc.removeChild(root);
		Element root1=doc.createElement("recipe");
		doc.appendChild(root1);
	}






   public boolean doc2XmlFile(Document document, String filename) {
	   boolean flag = true;
	   try {
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();
	   
	    // transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
	    DOMSource source = new DOMSource(document);
	    StreamResult result = new StreamResult(new File(filename));
	    transformer.transform(source, result);
	   } catch (Exception ex) {
	    flag = false;
	    ex.printStackTrace();
	   }
	   return flag;
	}
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
