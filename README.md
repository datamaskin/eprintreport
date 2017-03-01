### Details on the CompassReports Grails SSB
* Grails sdk 2.5
* JDK 1.7
* Browsers tested: Chrome, FF on Mac OSX, Windows 10 and Ubuntu 16
* Tech stack mix of JS libraries and Grails plugins:
    * application.js
    * download.js (should replace with FileSave.js)
    * FileSaver.js
    * Grails reverse engineering plugin
    * Grails datatables plugin
    * Apache Commons POI/HSSF and TempDir API
* UI tech stack:
    * application.css
    * eprintreport.css
    * html5
* Heavy use of JS libraries and JQuery ajax functions to call Grails controller actions rendering servlet response text
most cases
* Grails Groovy uses Java SQL and not Oracle SQL therefor leading to package access permissions exceptions when attempting to render as JSON.
* TODO - should look into to upgrading to Grails 3.x to take advantages of Spring Boot features and the new fat jar
capabilities
