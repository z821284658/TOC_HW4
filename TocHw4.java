/** Author: 許成家
 * 	Student ID: F74006365
 */
/** Program: 
 * This program is for parse data from the web and the find out 
 * “which road in a city has house trading records spread in #max_distinct_month”. 
 * The result would print out the roads name and their cities with their highest sale price and lowest sale price.
 * 
 * Your input should be giving one extra argument "URl".
 * 2014.6.25
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TocHw4 {

    public static void main(String[] args) throws JSONException, IOException, InterruptedException {
        	getPageData(args[0]);
    }
    
    
    public static void getPageData(String URL){
		final class road_info {
			String road_name;
			ArrayList<String> year_month_count = new ArrayList<String>();
			int count;
			int max_price;
			int min_price;
			public road_info(String Road_name, int Count, int Max_price, int Min_price) { 
				count = Count;
				max_price = Max_price;
				min_price = Min_price;
				road_name = Road_name;
			}
		}
		
		int max_count = 0;
		ArrayList<road_info> all_road = new ArrayList<road_info>();
		boolean check_exist = false;
        boolean road_check = false;
        Pattern pattern = Pattern.compile(".*路|.*街|.*大道");
        Pattern pattern2 = Pattern.compile(".*巷");
        
        URL u = null;
        InputStream in = null;
        InputStreamReader r = null;
        BufferedReader br = null;
        
        try {
           u = new URL(URL);
           in = u.openStream();
           r = new InputStreamReader(in, "UTF-8");
           br = new BufferedReader(r);
           String tempstr = null;
           while ((tempstr = br.readLine()) != null) {
        	   road_check = false;
        	   check_exist = false;
        	   if(tempstr.length() > 1) {
        		   JSONObject jsonObjectJackyFromString = new JSONObject(tempstr);
        		   Matcher matcher = pattern.matcher(jsonObjectJackyFromString.get("土地區段位置或建物區門牌").toString());
        		   while (matcher.find()) {
        			   if(all_road.isEmpty()){
        				   road_info current_road2 = new road_info(matcher.group(), 1, Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString()), Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString()));
        				   current_road2.year_month_count.add(jsonObjectJackyFromString.get("交易年月").toString());
        				   all_road.add(current_road2);
        			   } else {
        				   for (road_info current_road:all_road) {
        					   if(current_road.road_name.equals(matcher.group())) {
        						   if(!current_road.year_month_count.contains(jsonObjectJackyFromString.get("交易年月").toString())) {
        		            			current_road.year_month_count.add(jsonObjectJackyFromString.get("交易年月").toString());
        		            			current_road.count++;
        		            			if(max_count < current_road.count) {
        		            				max_count = current_road.count;
        		            			}
        		            	}
        		            	if(current_road.max_price < Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString())) {
        		            			current_road.max_price = Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString());
        		            	}
        		            	if(current_road.min_price > Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString())) {
        		            			current_road.min_price = Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString());
        		            	}
        		            	check_exist = true;
        		            	break;
        					   	}
        				   	}
        				   	if(!check_exist) {
        				   		road_info current_road2 = new road_info(matcher.group(), 1, Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString()), Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString()));
        				   		current_road2.year_month_count.add(jsonObjectJackyFromString.get("交易年月").toString());
        				   		all_road.add(current_road2);
        				   		break;
        				   	}
        			   	}
        			   	road_check = true;
        	        }
        		   if(!road_check) {
        			   Matcher matcher2 = pattern2.matcher(jsonObjectJackyFromString.get("土地區段位置或建物區門牌").toString());
        			   while (matcher2.find()) {
        				   if(all_road.isEmpty()){
            				   road_info current_road2 = new road_info(matcher2.group(), 1, Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString()), Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString()));
            				   current_road2.year_month_count.add(jsonObjectJackyFromString.get("交易年月").toString());
            				   all_road.add(current_road2);
            			   } else {
            				   for (road_info current_road:all_road) {
            					   if(current_road.road_name.equals(matcher2.group())) {
            						   if(!current_road.year_month_count.contains(jsonObjectJackyFromString.get("交易年月").toString())) {
            		            			current_road.year_month_count.add(jsonObjectJackyFromString.get("交易年月").toString());
            		            			current_road.count++;
            		            			if(max_count < current_road.count) {
            		            				max_count = current_road.count;
            		            			}
            		            	}
            		            	if(current_road.max_price < Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString())) {
            		            			current_road.max_price = Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString());
            		            	}
            		            	if(current_road.min_price > Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString())) {
            		            			current_road.min_price = Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString());
            		            	}
            		            	check_exist = true;
            		            	break;
            					   	}
            				   	}
            				   	if(!check_exist) {
            				   		road_info current_road2 = new road_info(matcher2.group(), 1, Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString()), Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString()));
            				   		current_road2.year_month_count.add(jsonObjectJackyFromString.get("交易年月").toString());
            				   		all_road.add(current_road2);
            				   		break;
            				   	}
            			   	}
            	       	}
        		   	}
        	    }
           }
           for (road_info current_road:all_road) {
			   if(max_count == current_road.count) {
				   System.out.println(current_road.road_name + ", 最高成交價:" + current_road.max_price + ", 最低成交價:" + current_road.min_price);
   			   }
			   
		   }
           
        } catch (Exception e) {
           e.getStackTrace();
           System.out.println(e.getMessage());
        } finally {
           try {
              u = null;
              in.close();
              r.close();
              br.close();
           } catch (Exception e) {
           }

        }  
    }
        
}
