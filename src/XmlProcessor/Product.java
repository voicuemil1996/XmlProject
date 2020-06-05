/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XmlProcessor;



public class Product implements Comparable{
    
    String ID = new String();
    String description = new String();
    String gtin = new String();
    String price = new String();
    String supplier = new String();
    String timestamp = new String();

    @Override
    public int compareTo(Object t) {
        Product p = (Product)t;
        if(timestamp == p.timestamp){
            
            return (-1)* Double.valueOf(price).compareTo(Double.valueOf(p.price));
        }else{

            return (-1) * timestamp.compareTo(p.timestamp);
        }
    }
   
}
