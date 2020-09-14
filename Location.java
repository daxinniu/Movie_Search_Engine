package project6;

/**
 * This class restore filming location and funFact related to the movie.
 *
 * @author Daxin Niu
 * @version 9/29/2018
 */

public class Location {

    private String location;
    private String funFact;

    /**
     * Calls setLocation to set location and setFunFact to set funFact.
     * @param location
     * @param funFact
     * @throws IllegalArgumentException
     */
    public Location (String location, String funFact) throws IllegalArgumentException {
        setLocation(location);
        setFunFact(funFact);
    }

    /**
     * Returns location of this location object.
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Checks if location is null or empty, otherwise set location to this.location.
     * @param location
     * @throws IllegalArgumentException
     */
    private void setLocation(String location) throws IllegalArgumentException {
        if(location == null) {
            throw new IllegalArgumentException("Input can't be null");
        }
        else if(location.length() == 0) {
            throw new IllegalArgumentException("Input can't be empty");
        }
        else {
            this.location = location;
        }
    }

    /**
     * Returns funFact relate to this location object.
     * @return funFact
     */
    public String getFunFact() {
        return funFact;
    }

    /**
     * Set funFact to this.funFact.
     * @param funFact
     */
    private void setFunFact(String funFact) {
        this.funFact = funFact;
    }
}
