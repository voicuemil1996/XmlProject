
package pss_technical_exercise;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlCreator{
    
    File inputFile;
    XmlParser parsedObject;
    String orderDigits;
    File outputFolder;
    File outputFile;
    
    public XmlCreator(File inputFile){
        //instantiating the parsedObject
        this.inputFile = inputFile; 
        parsedObject = new XmlParser(inputFile);
        
        //taking the order digits
        String[] splitedName = inputFile.getName().split("\\.");
        orderDigits = splitedName[0].substring(splitedName[0].length() - 2);
    }
    
    void XML_create(){
        
        parsedObject.parser();        
        
        //creating output folder in accordance with the input file
        outputFolder = new File("E:\\output\\order" + orderDigits);
        System.out.println(outputFolder.mkdir());                
        
        
        for(String supplier : parsedObject.suppliers){
            
            outputFile = new File(outputFolder.getAbsolutePath() + File.separator + 
                                    supplier + orderDigits + ".xml");
            
            //creating output file for current supplier
            try{
            
                outputFile.createNewFile();
            }catch(Exception e){
                
                e.printStackTrace();
            }
            //filtering and sorting the data
            List<Product> filteredProducts = parsedObject.products.stream()
                                                        .filter(p -> p.supplier.contains(supplier))
                                                        .sorted()
                                                        .collect(Collectors.toList());
                                        
            
            //writing the filtered products in XML_outputs 
            try {
                
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();            
                Document doc = dBuilder.newDocument();
                
                // root element
                Element rootElement = doc.createElement("products");
                doc.appendChild(rootElement);
                
                for(Product productObj : filteredProducts){
        
                    // product element
                    Element product = doc.createElement("product");
                    rootElement.appendChild(product);

                    // description element
                    Element description = doc.createElement("description");
                    description.appendChild(doc.createTextNode(productObj.description));
                    product.appendChild(description);
                    
                    // gtin element
                    Element gtin = doc.createElement("gtin");
                    gtin.appendChild(doc.createTextNode(productObj.gtin));
                    product.appendChild(gtin);
                    
                    // price element
                    Element price = doc.createElement("price");
                    Attr attrCurrency = doc.createAttribute("currency");
                    attrCurrency.setValue("USD");
                    price.setAttributeNode(attrCurrency);
                    price.appendChild(doc.createTextNode(productObj.price));
                    product.appendChild(price);

                    // orderid element
                    Element orderid = doc.createElement("orderid");
                    orderid.appendChild(doc.createTextNode(productObj.ID));
                    product.appendChild(orderid);
                }    
                    
                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(outputFile);
                transformer.transform(source, result);
         
                
            } catch (Exception e) {
                    e.printStackTrace();
            }           
            
        }
    }
}