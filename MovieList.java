package project6;

/**
 * This class is used to store Movie objects.
 *
 * @author Daxin Niu
 * @version 9/29/2018
 */

public class MovieList extends BST<Movie> {

    /**
     * Call the constructor in ArrayList to from an empty Movie list
     */
    public MovieList () {
        super (0);
    }

    /**
     * This method checks whether the keyword matches with any movie title,
     * if yes, add it to a new movie list.
     * @param keyword
     * @return result
     */
    public MovieList getMatchingTitles ( String keyword ) {
        if (this == null || this.size() == 0) {return null;}
        if (keyword == null || keyword.length() == 0){
            return null;
        }
        MovieList result = new MovieList();
        //Loop through this list and check if the movie object contains the keyword.
        for (Movie c : this) {
            if (c.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(c);
            }
        }

        if(result.isEmpty()) {
            return null;
        }else {
            return result;
        }
    }

    /**
     * This method checks the keyword and see if the actor is in the movie
     * object in this movie list.
     * @param keyword
     * @return
     */
    public MovieList getMatchingActor ( String keyword ) {
        if (this.isEmpty()){ return null;}
        if (keyword == null || keyword.isEmpty()) {return null;}
        MovieList result = new MovieList ();
        //loop going through each movie in this list.
        for (Movie c : this) {
            //loop to check the actors in each movie.
            for ( Actor a : c.getActorList()) {
                if (a != null){
                    if (a.getActorName().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(c);
                    }
                }
            }
        }

        if(result.isEmpty()) {
            return null;
        }else {
            return result;
        }
    }

    /**
     * Overrides the toString method and make them in to the correct
     * output format.
     * @return
     */
    @Override
    public String toString() {
        String result = "";
        for (Movie c: this) {
            result += c.getTitle() + "; ";
        }
        return result;
    }
}
