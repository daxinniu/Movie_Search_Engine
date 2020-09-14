package project6;

import java.util.ArrayList;

/**
 * This class is used to store Actor's name information.
 *
 * @author Daxin Niu
 * @version 9/29/2018
 */
public class Movie implements Comparable <Movie> {
    private String title;
    private int year;
    private ArrayList<Location> locationList = new ArrayList<>();
    private String director;
    private String writer;
    private ArrayList<Actor> actorList = new ArrayList<>();

    /**
     * This constructor takes title and year as parameter and set movie title and year.
     * @param title
     * @param year
     * @throws IllegalArgumentException
     */
    public Movie(String title, int year) throws IllegalArgumentException {
        setTitle(title);
        setYear(year);
    }

    /**
     * This constructor takes title, year, director, writer, actor1, actor2
     * actor3 as input and set the title, year, director, writer and also
     * add elements to the actorList.
     * @param title
     * @param year
     * @param director
     * @param writer
     * @param actor1
     * @param actor2
     * @param actor3
     * @throws IllegalArgumentException
     */
    public Movie(String title, int year, String director, String writer,
                 Actor actor1, Actor actor2, Actor actor3) throws IllegalArgumentException {
        setTitle(title);
        setYear(year);
        setDirector(director);
        setWriter(writer);

        //make sure actor1 is not null and add actors into movie.
        if (actor1 == null){
            throw new IllegalArgumentException("Actor1 can't be null");
        }
        else{
            actorList.add(actor1);
        }
        actorList.add(actor2);
        actorList.add(actor3);
    }

    /**
     * This method returns actorList.
     * @return actorList
     */
    public ArrayList<Actor> getActorList() {
        return actorList;
    }

    /**
     * Return the title of this movie object.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Checks if the title is null or empty, otherwise set the title of
     * this movie object.
     * @param title
     * @throws IllegalArgumentException
     */
    private void setTitle(String title) throws IllegalArgumentException {
        if (title == null) {
            throw new IllegalArgumentException("Title can't be null");
        }
        else if (title.length() == 0) {
            throw new IllegalArgumentException("Title can't be empty");
        }
        else{
            this.title = title;
        }
    }

    /**
     * Return the year of this movie object.
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Check if the year is between 2020 and 1900, if yes then set the year
     * of this movie object.
     * @param year
     * @throws IllegalArgumentException
     */
    private void setYear(int year) throws IllegalArgumentException {
        if (year >= 2021 || year <= 1899) {
            throw new IllegalArgumentException("Invalid year input");
        }
        else {
            this.year = year;
        }

    }

    /**
     * Return the director of this movie object.
     * @return director
     */
    public String getDirector() {
        return director;
    }

    /**
     * This method set the director of this movie object.
     * @param director
     */
    private void setDirector(String director) {
        this.director = director;
    }

    /**
     * Return the writer in this movie object.
     * @return writer
     */
    public String getWriter() {
        return writer;
    }

    /**
     * Set the writer in this movie object.
     * @param writer
     */
    private void setWriter(String writer) {
        this.writer = writer;
    }

    /**
     * Return locationList of this movie object.
     * @return locationList
     */
    public ArrayList<Location> getLocationList() {
        return locationList;
    }

    /**
     * This method checks the location object parsed in
     * and adds location into the location list.
     * @param Loc
     * @throws IllegalArgumentException
     */
    public void addLocation (Location Loc) throws IllegalArgumentException {
        if (Loc == null) {
            throw new IllegalArgumentException("Location can't be null");
        }
        else {
            locationList.add(Loc);
        }
    }

    /**
     * Overrides the compareTo method. Currently used to compare year and title
     * in movie objects.
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public int compareTo(Movie o) throws IllegalArgumentException {
        if (o == null) {
            throw new IllegalArgumentException("Movie can't be null");
        }
        if (!(o instanceof Movie)){
            throw new IllegalArgumentException("This is not a movie object");
        }

        //Compare year first, if equal, compare title
        Movie other = (Movie) o;
        if (other.getYear() < this.year) {
            return 1;
        }
        else if (other.getYear() > this.year){
            return -1;
        }
        else if (other.getYear() == this.year) {
            if (this.title.compareToIgnoreCase(other.getTitle()) > 0) {
                return 1;
            }
            else if (this.title.compareToIgnoreCase(other.getTitle()) == 0) {
                return 0;
            }
            else {
                return -1;
            }
        }
        return 0;

    }

    /**
     * Overrides the equals method and check whether the searched data is contained
     * in this object or not.
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Movie)) {
            return false;
        }

        //check if year and movie title are the same.
        Movie other = (Movie) obj;
        if (this.year == other.getYear() && this.title.equalsIgnoreCase(other.getTitle())){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Overrides the toString() method and print out information in
     * a specific format.
     * @return result
     * */
    @Override
    public String toString() {
        String outDirector = "";
        String outWriter = "";
        if (this.director != null) {
            outDirector = this.director;
        }
        if (this.writer != null) {
            outWriter = this.writer;
        }

        //formatting the output strings.
        String result = "";
        result += String.format("%s (%d)\n", this.title, this.year);
        result += "------------------------------------\n";
        result += "director            :" + outDirector + "\n";
        result += "writer              :" + outWriter + "\n";

        //Temporary string containing actor name.
        String tmp = "";

        //Temporary string containing location name.
        String locTmp = "";

        //Loop through actorList and add in information to tmp.
        for (Actor c : this.actorList) {
            if (!(c == null)) {
                tmp += c.getActorName() + ", ";
            }
        }
        result += "starring            :" + tmp + "\n";
        result += "filmed on location at:\n";

        //Loop through locationList and add in information to locTmp.
        for (Location c : this.locationList) {
            if (!((c == null))) {
                locTmp += "    " + c.getLocation();
                if (c.getFunFact()!= null &&!(c.getFunFact().isEmpty())) {
                    locTmp += " (" + c.getFunFact() + ")\n";
                } else {
                    locTmp += "\n";
                }
            }
        }
        result += locTmp;
        return result;
    }
}
