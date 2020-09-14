package project6;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is the main class of project2, it divides up into three parts.
 * Part I checks whether we have an correct program argument.
 * Part II processes the CSV file and stores the information in a MovieList.
 * Part III asks for user input and search the information in our MovieList,
 * then print out the information.
 *
 * @author Daxin Niu
 * @version 10/2/2018
 */
public class SFMovieData {
    public static void main(String[] args) {

        //Part I: File validating.
        //Make sure there is an command line argument.
        if (args == null) {
            System.err.println("Error: program argument can't be null.\n");
            System.exit(1);
        }
        else if (args.length == 0) {
            System.err.println("Error: program argument can't be empty.\n");
            System.exit(1);
        }

        //Make sure the file exist and readable.
        File movieFile = new File (args [0]);
        if (!movieFile.exists()) {
            System.err.println("Error: file " + movieFile.getAbsolutePath() + " doesn't exist.\n");
            System.exit(1);
        }
        if (!movieFile.canRead()) {
            System.err.println("Error: file " + movieFile.getAbsolutePath() +
                    " can't be opened for reading.\n");
            System.exit(1);
        }

        //Try to open the file.
        Scanner inMovie = null;
        try {
            inMovie = new Scanner(movieFile);
        }
        catch (FileNotFoundException e) {
            System.err.println("Error: file " + movieFile.getAbsolutePath() +
                    " cannot be opened for reading.\n");
            System.exit(1);
        }

        //Part II: File processing.
        MovieList movieList = new MovieList();

        //while loop to grab even line of data in the CSV file.
        while (inMovie.hasNextLine()){
            try{
                //Process each line in to an ArrayList.
                ArrayList<String> processLine = splitCSVLine(inMovie.nextLine());

                //Ignores broken information.
                if (processLine.size() != 11) {
                    continue;
                }
                //0:title, 1:year, 2:location 3:fun fact, 6:director, 7:writer
                //8 9 10 actor.

                String title = processLine.get(0);
                int year = Integer.parseInt(processLine.get(1));
                String director = processLine.get(6);
                String writer = processLine.get(7);
                //Actor 1 cannot be null (Specified in movie class).
                Actor actor1 = new Actor(processLine.get(8));
                Actor actor2 = null;
                Actor actor3 = null;

                //check if actor2 and actor3 exist
                if (processLine.get(9).length() != 0) {
                    actor2 = new Actor(processLine.get(9));
                }

                if (processLine.get(10).length() != 0) {
                    actor3 = new Actor(processLine.get(10));
                }

                //Construct a movie object.
                Movie complexMovie = new Movie(title, year, director, writer, actor1, actor2, actor3);

                if (!(processLine.get(2).length() == 0)) {
                    Location location = new Location(processLine.get(2), processLine.get(3));
                    complexMovie.addLocation(location);
                }
                //check if location is already in movieList.
                if (!movieList.contains(complexMovie)) {
                    movieList.add(complexMovie);
                }
                else{
                    for(Movie c: movieList) {
                        if (c.equals(complexMovie)){
                            if (!(processLine.get(2).length() == 0)) {
                                Location location = new Location(processLine.get(2), processLine.get(3));
                                //add location to existing movie object in movieList.
                                c.addLocation(location);
                            }
                        }
                    }
                }


            }catch (IllegalArgumentException e ){

            }catch (NoSuchElementException e){

            }
        }

        //Part III: Ask for user input and search for data.
        //formatting questions
        System.out.println("Search the database by matching keywords to titles or actor names.");
        System.out.println("    To search for matching titles, enter");
        System.out.println("        title KEYWORD");
        System.out.println("    To search for matching actor names, enter");
        System.out.println("        actor KEYWORD");
        System.out.println("    To finish the program, enter");
        System.out.println("        quit");
        System.out.println("");
        System.out.println("");

        //interactive program
        Scanner userInput = new Scanner(System.in);
        String userValue = "";
        String searchValue = "";
        do{
            System.out.println("Enter your search query:");
            System.out.println("");
            userValue = userInput.nextLine();
            System.out.println("");
            //check if the first part is actor or title
            if (userValue.split(" ")[0].equalsIgnoreCase("title")){
                //Process the input and get rid of "title".
                searchValue = userValue.replace(userValue.split(" ")[0] + " ", "");
                MovieList temp = movieList.getMatchingTitles(searchValue);
                if(temp==null) {
                    System.out.println("No matches found. Try again.");
                    System.out.println("");
                    continue;
                }

                for (Movie c : temp) {
                    System.out.println(c.toString());
                }
                if(temp.isEmpty()) {
                    System.out.println("No matches found. Try again.");
                    System.out.println("");
                }
            }
            else if (userValue.split(" ")[0].equalsIgnoreCase("actor")){
                //Process input and get rid of "actor".
                searchValue = userValue.replace(userValue.split(" ")[0] + " ", "");
                MovieList temp = movieList.getMatchingActor(searchValue);

                if(temp==null) {
                    System.out.println("No matches found. Try again.");
                    System.out.println("");
                    continue;
                }

                for (Movie c : temp) {
                    System.out.println(c.toString());
                }
                if(temp.isEmpty()) {
                    System.out.println("No matches found. Try again.");
                    System.out.println("");
                }
            }
            else {
                if(userValue.equalsIgnoreCase("quit")){
                    continue;
                }
                System.out.println("This is not a valid query. Try again.");
                System.out.println("");
            }
            //loop until user enters "quit".
        }while (!(userValue.equalsIgnoreCase("quit")));
    }


    /**
     * Splits the given line of a CSV file according to commas and double quotes
     * (double quotes are used to surround multi-word entries so that they may contain commas)
     * @author Joanna Klukowska
     * @param textLine  a line of text to be passed
     * @return an ArrayList object containing all individual entries found on that line
     */
    public static ArrayList<String> splitCSVLine(String textLine){
        if (textLine == null ) return null;

        ArrayList<String> entries = new ArrayList<String>();
        int lineLength = textLine.length();
        StringBuffer nextWord = new StringBuffer();
        char nextChar;
        boolean insideQuotes = false;
        boolean insideEntry= false;

        // iterate over all characters in the textLine
        for (int i = 0; i < lineLength; i++) {
            nextChar = textLine.charAt(i);

            // handle smart quotes as well as regular quotes
            if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {

                // change insideQuotes flag when nextChar is a quote
                if (insideQuotes) {
                    insideQuotes = false;
                    insideEntry = false;
                }else {
                    insideQuotes = true;
                    insideEntry = true;
                }
            } else if (Character.isWhitespace(nextChar)) {
                if ( insideQuotes || insideEntry ) {
                    // add it to the current entry
                    nextWord.append( nextChar );
                }else { // skip all spaces between entries
                    continue;
                }
            } else if ( nextChar == ',') {
                if (insideQuotes){ // comma inside an entry
                    nextWord.append(nextChar);
                } else { // end of entry found
                    insideEntry = false;
                    entries.add(nextWord.toString());
                    nextWord = new StringBuffer();
                }
            } else {
                // add all other characters to the nextWord
                nextWord.append(nextChar);
                insideEntry = true;
            }

        }
        // add the last word ( assuming not empty )
        // trim the white space before adding to the list
        if (!nextWord.toString().equals("")) {
            entries.add(nextWord.toString().trim());
        }

        return entries;
    }

}
