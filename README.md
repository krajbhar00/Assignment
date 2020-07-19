# Assignment

I have used the page object design model to automate this, for automation i have used selenium, java as programming language,and testng to prioritizing the test case and methods. Here i am generating two type of report one is testng emailable report(default) and other is extend report.



# Created 4 class 
  1. BaseClassTest:- I have used @BeforeMethod and @AfterMethod testng annotation. So that these two methods will execute before and after every test method.
  2. ExtentReport:- ExtentReport listener to create extent report.
  3. PageClass:- To store the all the webelement at one place and by using PageFactory.initElements(driver, ClassName.this) initialized them so that user can            perfrom action on webelement.
  4. TestClass:- Defined all the test case with @Test annotation.




# Steps to setup project in your local system
  1. Import the project in your local system
  2. Upzip the downloaded file
  3. Import the project in eclipse
  4. Run testng.xml file
  5. logs will generate in HTMLlog.html file 
  6. Extent report is generating in-side test-output folder with file name Extent.html
  7. Screenshot are generating in screenshot folder
 
