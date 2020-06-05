
package XmlProcessor;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParser{
    
    List<Product> products = new ArrayList<Product>();
    List<String> suppliers = new ArrayList<String>();
    File inputFile;
    int nrOfProducts = 0;
    
    XmlParser(File inputFile){
        
        this.inputFile = inputFile;
    }
    
    //used to parse the products from the input file
    public void parser(){
    
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("order");

            
            int product_index = 0;
            for (int temp = 0; temp < nList.getLength(); temp++) {
                
                Node nNode = nList.item(temp);
            
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eFirstElement = (Element) nNode;
                    NodeList nList1 = eFirstElement.getElementsByTagName("product");
                    nrOfProducts += nList1.getLength();
                                        
                    
                    for (int j = 0; j < nList1.getLength(); j++){
                        
                        Node nSubNode = nList1.item(j);
                        if (nSubNode.getNodeType() == Node.ELEMENT_NODE){
                            
                            products.add(product_index, new Product());                            
                            Element eElement = (Element) nSubNode;
                            
                            products.get(product_index).ID = eFirstElement.getAttribute("ID");
                            products.get(product_index).timestamp = eFirstElement.getAttribute("created");
                            
                            products.get(product_index).description = eElement
                                                        .getElementsByTagName("description")
                                                        .item(0)
                                                        .getTextContent();

                            products.get(product_index).gtin = eElement
                                                        .getElementsByTagName("gtin")
                                                        .item(0)
                                                        .getTextContent();

                            products.get(product_index).price = eElement
                                                        .getElementsByTagName("price")
                                                        .item(0)
                                                        .getTextContent();                            

                            products.get(product_index).supplier = eElement
                                                        .getElementsByTagName("supplier")
                                                        .item(0)
                                                        .getTextContent();                            
                            
                            if(!suppliers.contains(products.get(product_index).supplier)){
                                
                                suppliers.add(suppliers.size(), products.get(product_index).supplier);
                            }
                            product_index++;
                        } 
                   
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }     
    }
       
}
