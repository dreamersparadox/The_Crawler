import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;


public class ListLinks 
{
	
	static ArrayList<String> ulinks = new ArrayList<String>();	
	static ArrayList<String> vlinks = new ArrayList<String>();
	
    public static void main(String[] args) throws IOException 
    {
        @SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
        System.out.println("Enter the url - ");
        String url = s.nextLine();
        get(url);
        
        System.out.println("Enter the filename:");
        String fname = s.nextLine();
        
        for(int i = 0; i<ulinks.size(); i++)
        {
        	try 
        	{
        		get(ulinks.get(i));
        	}
        	
        	catch (Exception e)
        	{
        		continue;
        	}
        	
        } 
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fname+".txt")));

        for(int i = 0; i<vlinks.size(); i++)
        {
        	writer.write(vlinks.get(i));
        	writer.newLine();
        }
        writer.close();
    }
    
    private static void get(String url) throws IOException
    {
    	vlinks.add(url);

    	System.out.println("Fetching links from: "+url);
    	
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        CharSequence seq = "https://www.hyundai.com";
        for (Element link : links) 
        {
        	if(link.attr("abs:href").contains(seq))
        		ulinks.add(link.attr("abs:href"));
        }
               
        for(int i = 0; i<ulinks.size(); i++)
        	ulinks.set(i, ulinks.get(i).replaceAll("/$", ""));
        
        // Create a new LinkedHashSet 
        Set set = new LinkedHashSet<>(); 
        // Add the elements to set 
        set.addAll(ulinks); 
        // Clear the list 
        ulinks.clear(); 
        // add the elements of set 
        // with no duplicates to the list 
        ulinks.addAll(set); 
        
           
    }
    
   
}