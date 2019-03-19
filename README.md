Cytoscape with custom REST API
======
**CytoscapeCustom** is a Cytoscape app. Cytoscape only only data to be imported from selected external data sources.
This is a project to add functionality to import and visualize network data from custom REST APIs.

This is a work in progress.

For this to work it needs a REST API in a certain format to provide network data. XML format for data output is in REST_data_format folder.



It adds additional menus to Cytoscape to search, import and view custom network data(in XGGML format).


## Instruction to build the application
* Import src, lib, app-manifest and build.bat to your local folder. Run build.bat. This should generate the * app file named CustomApp.jar.


### Third party libraries
* http://chianti.ucsd.edu/cytoscape-3.1.0/cytoscape-swing-app-api-3.1.0.jar


## Version
* Version 1.0

## How to install the app in Cytoscape
* Open Cytoscape > Apps > App Manager. Click on "Install Apps" tab. Click on "Install from File" and import * CustomApp.jar. This should create a new link under File named "Custom Extension".

## Contact
#### Arun
* e-mail: arunqkumar@gmail.com
