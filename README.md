### ePrint: an application to select and view company documents

The migration from Oracle to MySQL does not go well with hibernate (GORM).

I have done the following to make building and running the application a little easier:
* The Grails datasource has been set to 'none' to turn off Hibernate.
* 2 scripts will need to be run at the mysql command line prompt:
    * gw_rpts_def_ddl.sql
    * eprint_data.sql

CD to where the project was cloned then cd to the directory: `scripts`

The first script creates the DB: `mysql> source gw_rpts_def_ddl.sql`

The second script populates the table with data: `mysql> source eprint_data.sql`

At this point you should be able to execute: `grails run-app` at the command line.

The domain JSON data can be found in the reports.json file

The above assumes the following:
* A Unix or Linux development environment or emulated.
* git is installed and you have cloned the project from [github](https://github.com/pimpedoutgeek/eprintreport.git)
* JDK 1.7 (Oracle) is installed and in the execution path.
* [sdkman](http://sdkman.io/) is installed.
* CD to the directory where you have cloned ePrint
* Run sdk for the correct version of Grails in this case 2.5.0 (application.properties)
* Run: `sdk l grails` to verify the correct version of grails is installed.
* You should see several records under the display of 3 columns.
* Once a user has select a `parent row` the child rows should display from the data fetched runing the query `select_gw_rpts_sequence.sql`.