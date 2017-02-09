## Importing the project into Eclipse

This `populationbuilder` is the parent maven project. Follow below instructions to import this project and all the child projects into Eclipse

### Prerequisits
* JAVA 8 (https://java.com/en/download/)
* Maven (https://maven.apache.org/download.cgi)

### Importing steps
   1. File > Import...
   2. In Import console, select Maven > Existing Maven Projects > Next
   3. In Import Maven Project console, set `populationbuilder` as the Root Directory. This will show available projects
   4. Select `populationbuilder` and its child projects
   5. Click Finish
  
This will automatically create the required Eclipse configuration files (.project, .classpath, .settings) and import all the projects under `populationbuilder`.

When checking in any changes to the repository do not push .project, .classpath and .settings. Otherwise there will be conflicts when using with different Eclipse versions.

There are 5 maven projects that needs to be built. They are all subprojects of `populationbuilder` project. To build all the 5 projects at once change directory to `buildpopulation/populationbuilder` and execute below command.

        > mvn install