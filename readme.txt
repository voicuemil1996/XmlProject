Purpose of the app:
	
	It takes some input xml files with a specific elements and depending of the number of Suppliers founded in the input it creates 
	as many outputs that contains the corresponding characteristics (also founded in the input file).


Solution description:

	Before running the program:
		-the input files should be located in a folder called "input" in partition E:\\
		-the output folder should be named "output" and also should be already created in E:\\ 
	
	Classes description

		-Product -> here are defined the attributes of the product type and also it is overridden the method compareTo of
	    		interface Comparable (which will help later for sorting the data)
		-XmlParser -> it is used to parse the data from the input file and store it in a Product list
		-XmlCreator -> it use XmlParser to get the needed data which is processed as it is required. Using the proccessed data,
				in the end it writes the output files
		-XmlProcessor -> firstly it uses a search method to get the valid input files. After that, for each input found it uses
			       XmlCreator in order to get the output files