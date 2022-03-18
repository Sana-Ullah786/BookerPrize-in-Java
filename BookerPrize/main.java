// importing Required Modules
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
This Java File Contains Java Classes that depicts the Classes Presented on UML Diagram
*/

//Class Book
class Book {

  //Attributes of Book
  private String title;
  private String author;
  private String publisher;

  //Constructor for Book Class
  public Book(String title, String author, String publisher) {
    this.title = title;
    this.author = author;
    this.publisher = publisher;
  }

  //Getter Functions of Book Attributes

  //Get Title of the Book
  public String getTitle() {
    return title;
  }

  //Get Author name
  public String getAuthor() {
    return author;
  }

  //Get Publisher name
  public String getPublisher() {
    return publisher;
  }
}

//Class BookerPrize
class BookerPrize {

  //Attributes of Class BookerPrize
  private int year;
  private Book winner;
  private List<Book> shortList;
  private List<String> panel;
  private String chairPerson;

  //Constructor for BookerPrize
  public BookerPrize(
    int year,
    Book winner,
    List<Book> shortList,
    List<String> panel,
    String chairPerson
  ) {
    this.year = year;
    this.winner = winner;
    this.shortList = shortList;
    this.panel = panel;
    this.chairPerson = chairPerson;
  }

  //Getter for Attributes

  //Get the Year of Book was Published
  public int getYear() {
    return year;
  }

  //Book Winner Book for the Year
  public Book getWinner() {
    return winner;
  }

  //Get Shortlisted Books for the year
  public List<Book> getShortList() {
    return shortList;
  }

  //Get Panel that done the Selection Process
  public List<String> getPanel() {
    return panel;
  }

  //Get Chair Person of that Penal and year
  public String getChairPerson() {
    return chairPerson;
  }

  //To String Method That returns the Information of Winner Book
  public String toString() {
    return (
      winner.getAuthor().toUpperCase() +
      " " +
      winner.getTitle().toUpperCase() +
      " " +
      winner.getPublisher().toUpperCase()
    );
  }
}

//Main Class (Driver Code)
public class main {

  //Main Method
  public static void main(String args[]) {
    //List of BookerPrize Objects
    List<BookerPrize> allData = new ArrayList<BookerPrize>();
    int opt;
    Scanner inptObj = new Scanner(System.in);
    Scanner strObj = new Scanner(System.in);
    //Path of Text File (Data File)
    try {
      //the file to be opened for reading
      FileInputStream fis = new FileInputStream(
        "/home/jack-sparrow/Documents/Programming/Java/Summative2Download/Task1/booker-data.txt"
      );
      Scanner sc = new Scanner(fis); //file to be scanned
      //returns true if there is another line to read
      while (sc.hasNextLine()) {
        //Read Lines and Split them on Different Delimiters
        String[] tokens1 = spltOnBraces(sc.nextLine());
        String[] tokens2 = spltOnBraces(sc.nextLine());
        String[] tokens3 = spltOnBraces(sc.nextLine());
        //Create Book Object
        Book winner = new Book(tokens1[2], tokens1[1], tokens1[3]); //winner

        //List of ShortListed Books of a year
        List<Book> shortList = new ArrayList<Book>();
        //Create Book Object of ShortListed Books and add it to the List
        for (int i = 0; i < tokens2.length; i += 4) {
          if (tokens2[i].length() != 0) {
            Book obj1 = new Book(tokens2[i + 1], tokens2[i], tokens2[i + 2]);
            shortList.add(obj1);
          }
        }
        //Find Chair Person of the Book for a year
        String chairPerson = findChairPerson(tokens3);
        //Create a List for Panel
        List<String> panel = new ArrayList<String>();
        for (int i = 0; i < tokens3.length; i++) {
          if (
            tokens3[i].equalsIgnoreCase(chairPerson) ||
            tokens3[i].equalsIgnoreCase("chair") ||
            tokens3[i].length() == 0
          ) {
            continue;
          } else {
            panel.add(tokens3[i]);
          }
        }

        //Create BookerPrize Object and set All Attributes and add this object to the List
        BookerPrize bookerPrize = new BookerPrize(
          Integer.parseInt(tokens1[0]),
          winner,
          shortList,
          panel,
          chairPerson
        );
        allData.add(bookerPrize);
      }
      sc.close(); //closes the scanner
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Menu for user

    do {
      System.out.println(
        "\n---------------------- Booker\nprize menu\n----------------------\nList ................1\nSelect ..............2\nSearch ..............3\nExit.................0\n----------------------\n"
      );
      System.out.print("Enter choice:> ");
      opt = inptObj.nextInt();
      switch (opt) {
        // exitting the system
        case 0:
          {
            inptObj.close();
            strObj.close();
            System.exit(1);
            break;
          }
        // printing the list for user
        case 1:
          {
            displayData(allData);

            break;
          }
        // asking user to select
        case 2:
          {
            //Get Year from User and Find the Winner Book and all Other Information for that Year
            int input = 0;
            System.out.print("Enter Year = ");
            input = inptObj.nextInt();
            //If Year is a Valid Year
            if (checkYear(input)) {
              //Find winner Book
              BookerPrize winnerBook = findWinnerBook(allData, input);
              //Print information of the Winner Book
              printWinnerOfTheYear(winnerBook);
            } else {
              System.out.println(
                "Year = " +
                input +
                " is Out of Range\nKindly try again!!\nHint: (2000-2020)"
              );
            }

            break;
          }
        // searching a book
        case 3:
          {
            //Take input String
            String inp = "";
            System.out.print("Enter Word to Search = ");
            inp = strObj.nextLine();
            //Show Books that Contains that String
            showFoundBooks(allData, inp);
            break;
          }
        default:
          break;
      }
    } while (opt != 0);
  }

  //Function that Searches the Books Title That contains a provided String
  public static void showFoundBooks(List<BookerPrize> allData, String inp) {
    boolean flag = false;
    //Format for Tabular Form
    String format = "";
    //Print Heading of Table
    printHeadingForSearchedBooks();
    //Loop all Booker Prize Objects and Find first and last index of Provided String
    for (int i = 0; i < allData.size(); i++) {
      if (checkBook(allData.get(i).getWinner(), inp)) {
        int[] indeces = findIndex(
          allData.get(i).getWinner().getTitle().toLowerCase(),
          inp.toLowerCase()
        );
        //Make Provided String to upperCase in Book Name
        int startIndex = indeces[0];
        int endIndex = indeces[1];
        printPipe();
        format = "%-" + 45 + "s";
        System.out.printf(
          format,
          highLightText(
            allData.get(i).getWinner().getTitle(),
            startIndex,
            endIndex
          )
        );
        //Print Other Information of Books
        printPipe();
        format = "%-" + 33 + "s";
        System.out.printf(format, allData.get(i).getWinner().getAuthor());
        printPipe();
        format = "%-" + 18 + "s";
        //Print Status a Winner
        System.out.printf(format, "Winner");
        printPipe();
        format = "%-" + 5 + "d";
        System.out.printf(format, allData.get(i).getYear());
        printPipe();
        System.out.println("");
        printDashes(114);
        flag = true;
      }
      //Loop through all Books Object of a BookerPrize Object and Repeat Above Steps for Each
      for (int j = 0; j < allData.get(i).getShortList().size(); j++) {
        if (checkBook(allData.get(i).getShortList().get(j), inp)) {
          int[] indeces = findIndex(
            allData.get(i).getShortList().get(j).getTitle().toLowerCase(),
            inp.toLowerCase()
          );
          int startIndex = indeces[0];
          int endIndex = indeces[1];
          printPipe();
          format = "%-" + 45 + "s";
          System.out.printf(
            format,
            highLightText(
              allData.get(i).getShortList().get(j).getTitle(),
              startIndex,
              endIndex
            )
          );
          printPipe();
          format = "%-" + 33 + "s";
          System.out.printf(
            format,
            allData.get(i).getShortList().get(j).getAuthor()
          );
          //Print Status a ShortListed
          printPipe();
          format = "%-" + 18 + "s";
          //Print Status a ShortListed
          System.out.printf(format, "ShortListed");
          printPipe();
          format = "%-" + 5 + "d";
          System.out.printf(format, allData.get(i).getYear());
          printPipe();
          System.out.println("");
          printDashes(114);
          flag = true;
        }
      }
    }
    //If String not in any Book Name
    if (!flag) {
      System.out.println("NO Book Contains this Word = " + inp);
    }
  }

  //Check Book Title Contains That String
  public static boolean checkBook(Book book, String inp) {
    return book.getTitle().toLowerCase().contains(inp.toLowerCase());
  }

  //Find Portion of the provided string in Book Title
  public static int[] findIndex(String title, String inp) {
    int startIndex = title.indexOf(inp);
    int endIndex = startIndex + inp.length();
    int[] array = { startIndex, endIndex };
    return array;
  }

  //Highlight that Portion of The Book Title that Machtes the Provided String
  public static String highLightText(
    String title,
    int startIndex,
    int endIndex
  ) {
    char[] strArray = title.toCharArray();
    for (int i = startIndex; i < endIndex; i++) {
      strArray[i] = Character.toUpperCase(strArray[i]);
    }
    return String.valueOf(strArray);
  }

  // Heading Row for Searched Books
  public static void printHeadingForSearchedBooks() {
    String format = "";
    //Print Dashes Function
    printDashes(114);
    //Formatted Outputs
    printPipe();
    format = "%-" + 45 + "s";
    System.out.printf(format, "Book Title");
    printPipe();
    format = "%-" + 33 + "s";
    System.out.printf(format, "Author");
    printPipe();
    format = "%-" + 18 + "s";
    System.out.printf(format, "Status");
    printPipe();

    format = "%-" + 5 + "s";
    System.out.printf(format, "Year");
    printPipe();
    System.out.println("");
    printDashes(114);
  }

  //print Data for selected Year Option; and Helper Functions (Same as Other Print Functions)
  public static void printWinnerOfTheYear(BookerPrize winner) {
    printHeadingForWinner();
    String format = "";
    printPipe();
    format = "%-" + 22 + "s";
    System.out.format(format, winner.getWinner().getAuthor().toUpperCase());
    printPipe();
    format = "%-" + 33 + "s";
    System.out.format(format, winner.getWinner().getTitle().toUpperCase());
    printPipe();
    format = "%-" + 22 + "s";
    System.out.format(format, winner.getWinner().getPublisher().toUpperCase());
    printPipe();
    format = "%-" + 20 + "s";
    System.out.format(format, "");
    printPipe();
    format = "%-" + 20 + "s";
    System.out.format(format, "");
    printPipe();

    //Trim Lengths of Some String that alter the Table Format
    System.out.println("");
    for (int i = 0; i < winner.getShortList().size(); i++) {
      printPipe();
      format = "%-" + 22 + "s";
      System.out.format(format, winner.getShortList().get(i).getAuthor());
      printPipe();
      format = "%-" + 33 + "s";
      if (winner.getShortList().get(i).getTitle().length() > 33) {
        String str = winner.getShortList().get(i).getTitle().substring(0, 32);
        System.out.format(format, str);
        printPipe();
      } else {
        System.out.format(format, winner.getShortList().get(i).getTitle());
        printPipe();
      }

      format = "%-" + 22 + "s";
      System.out.format(format, winner.getShortList().get(i).getPublisher());
      printPipe();
      if (i == 2) {
        format = "%-" + 20 + "s";
        if (winner.getChairPerson().length() > 20) {
          String str = winner.getChairPerson().substring(0, 19);
          System.out.format(format, str);
          printPipe();
        } else {
          System.out.format(format, winner.getChairPerson());
          printPipe();
        }
      } else {
        format = "%-" + 20 + "s";
        System.out.format(format, "");
        printPipe();
      }
      if (i >= 0 && i <= 3) {
        format = "%-" + 20 + "s";
        if (winner.getPanel().get(i).length() > 20) {
          String str = winner.getPanel().get(i).substring(0, 19);
          System.out.format(format, str);
          printPipe();
        } else {
          System.out.format(format, winner.getPanel().get(i));
          printPipe();
        }
      } else {
        format = "%-" + 20 + "s";
        System.out.format(format, "");
        printPipe();
      }

      System.out.println("");
    }
    printDashes(133);
  }

  //Heading Row for Winner Book of a specific Year
  public static void printHeadingForWinner() {
    String format = "";
    printDashes(133);

    printPipe();
    format = "%-" + 22 + "s";
    System.out.printf(format, "Author");
    printPipe();
    format = "%-" + 33 + "s";
    System.out.printf(format, "Book Title");
    printPipe();
    format = "%-" + 22 + "s";
    System.out.printf(format, "Publisher");
    printPipe();

    format = "%-" + 20 + "s";
    System.out.printf(format, "Chair");
    printPipe();

    format = "%-" + 20 + "s";
    System.out.printf(format, "Panel");
    printPipe();
    System.out.println("");
    printDashes(133);
  }

  //Print AllData Function and Helper Functions (Displayed All Data of each BookerPrize Object)
  public static void displayData(List<BookerPrize> allData) {
    String format = "";
    printHeading();
    for (int i = 0; i < allData.size(); i++) {
      printPipe();
      System.out.print("" + allData.get(i).getYear());
      printPipe();
      format = "%-" + 33 + "s";

      System.out.format(format, allData.get(i).getWinner().getTitle());
      printPipe();
      format = "%-" + 18 + "s";
      System.out.format(format, allData.get(i).getWinner().getAuthor());
      printPipe();
      format = "%-" + 22 + "s";
      System.out.format(format, allData.get(i).getWinner().getPublisher());
      printPipe();
      System.out.println("");
    }
    printDashes(90);
  }

  //Heading for All Data List
  public static void printHeading() {
    printDashes(90);
    printPipe();
    System.out.print("Year");
    printPipe();
    String format = "%-" + 33 + "s";
    System.out.printf(format, "Title");
    printPipe();
    format = "%-" + 18 + "s";
    System.out.printf(format, "Author");
    printPipe();
    format = "%-" + 22 + "s";
    System.out.printf(format, "Publisher");
    printPipe();
    System.out.println("");
    printDashes(90);
  }

  //Dashes Function
  public static void printDashes(int n) {
    System.out.printf("%1s", "");
    for (int i = 0; i < n; i++) {
      System.out.print("-");
    }
    System.out.println("");
  }

  //PrintPipe
  public static void printPipe() {
    System.out.print(" | ");
  }

  //Spliting Lines
  public static String[] spltOnBraces(String s) {
    return s.split("[\\(||//)\\,\\:\\|]");
  }

  //Find Chair Person
  public static String findChairPerson(String[] tokens) {
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].equalsIgnoreCase("chair")) {
        return tokens[i - 1];
      }
    }
    return "";
  }

  //for Year Validation
  public static boolean checkYear(int year) {
    return year >= 2000 && year <= 2020;
  }

  //find Book with Year
  public static BookerPrize findWinnerBook(
    List<BookerPrize> allData,
    int year
  ) {
    if (year <= 2009) {
      return allData.get(year % 2000);
    } else {
      return allData.get(year % 2000);
    }
  }
}
