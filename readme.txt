Purpose of the app:
	
	The task description is presented in the TestJava.doc file.


Solution description:

	Before running the program:
		-the input files should be located in a folder called "input" in partition E:\\
		-the output folder should be named "output" and also should be already created in E:\\ 
	
	Classes description

		-Product -> here are defined the attributes of the product type and also it is overridden the method compareTo of
	    		interface Comparable (which will help later for sorting the data)
		-XML_parser -> it is used to parse the data from the input file
		-XML_creator -> it use XML_parser to get the needed data which is processed as it is required. Using the proccessed data,
				in the end it writes the output files
		-main Class -> firstly it uses a search method to get the valid input files. After that, for each input found it uses
			       XML_creator in order to get the output files