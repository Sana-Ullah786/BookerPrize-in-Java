**Booker Prize**  
Create a NetBeans 8 project for this task, named main.java.  
You are required to write a Java 8 program that opens and reads a delimited data file that is located relative to the NetBeans project root folder. The delimited data file contains information about Booker prize winning and shortlisted novels. The data file is called booker-data.txt. The data file must not be altered and should be considered as a read-only data file.  
The data file is delimited in a consistent structure and contains entries relating to 20 years of Booker prize winners and shortlisted nominees. Each entry contains a series of data fields representing the following information: the year of the prize, the winning author, the winning book title, the publisher of the winning book, the authors and book titles of shortlisted nominees, the panel members who were responsible for deciding the winner, and an indication of which panel member acted as chairperson.  
You are required to implement Java classes to represent the Booker prize winning information with respect to this data set. The program should parse the data file and create and store a collection of objects for each entity. Figure 3 provides a partial UML class representation of the classes that you will need to implement. It illustrates a core class to represent the Booker prize information alongside a utility class to represent the basic details of a book. The class model indicates the data members and accessor (i.e., getter) methods that map to those data members, and a toString() method for the BookerPrize class. It is left to you to determine how the objects should be initialised.  

**UML Diagram**  
<img width="656" alt="image" src="https://user-images.githubusercontent.com/91070350/159005408-95f37657-5732-4053-beff-8bac8aaf8c15.png">  

Once all the objects are loaded into the collection, the program should present the User with a console-based menu to interact with the data set. This menu should loop until the User enters a character to exit the menu (e.g., zero as illustrated below). In addition to an exit option, the menu should offer three other options: list, select and search.  
On starting the program, the following menu should be displayed to the console:  

<img width="318" alt="image" src="https://user-images.githubusercontent.com/91070350/159005699-887f5a5c-bb1e-4e27-b2fe-b303e4230b20.png">  

The User can simply exit the program by entering zero. The three other menu options allow the User to inspect the information in the data set (note again that this program is entirely read-only and there is no requirement to add, update or delete any part of the data set).  
Note that console output should be neatly formatted, and some consideration will be given to formatting when the program is assessed. In particular, when the option to view the details of the Booker prize winner and shortlist for a given year is selected (i.e., the ‘select’ menu option), it must result in the invocation of the toString() method for that particular BookerPrize object. You are required to utilise a StringBuilder object when implementing the toString() method for the BookerPrize class.  

**Console interaction examples**  
 **Option 1: list Booker prize winners**  
On selecting option 1, the User should be presented with a neatly formatted listing of the winning nominees for the Booker prize for the 20 years pertaining to the data set. The data displayed should be ordered by the year of prize, and include the book title, the name of the author, and publisher of the book. To select a single Booker prize to view its full details, the User should be prompted to enter the year of publication (i.e., option 2).  
<img width="748" alt="Screenshot 2022-03-18 at 5 16 40 PM" src="https://user-images.githubusercontent.com/91070350/159005786-9116eee5-4f2f-49df-ae23-ffe432bc50c7.png">


 **Option 2: select year**
On selecting option 2, the User should be prompted to enter the year of Booker prize (that was displayed when option 1 was chosen). If an incorrect year is entered, an appropriate message should be displayed so the User tries again. On entering a valid year, all the details of that Booker prize should be displayed, as illustrated below. Note that the winning book information must appear at the top with the shortlisted books below and the winning book information must be in upper case. This should be achieved by invocation of the relevant BookerPrize object’s toString() method. Console output should be neatly formatted.  
<img width="976" alt="Screenshot 2022-03-18 at 5 18 21 PM" src="https://user-images.githubusercontent.com/91070350/159005866-a030509e-a396-49f2-94aa-b679b892203c.png">

**Option 3: search book titles**
On selecting option 3, the User should be prompted to enter a ‘search string’ to match against the book titles of all the entries (both winning and shortlisted books). All searches should be case insensitive. The program should return a listing of any books that match the search string, with the section that matches in upper-case. The returned matches should also display if the book was a prize winner or shortlisted, and the year of publication.  
<img width="831" alt="Screenshot 2022-03-18 at 5 19 39 PM" src="https://user-images.githubusercontent.com/91070350/159005947-466c2575-0db2-4691-b243-66b6340d1e96.png">



**How To Run**  
1)Open the main.java file in any text editor (particularly VS Code or netbeans)  
2)Download the booker-data.txt file in your computer  
3)Change the file path of your booker-data.txt file in the file opener in Main class  
4) Run the file  
5) The user is prompted to choose among 4 options  
6) You can search, select and retrieve any information from the booker prize information  


