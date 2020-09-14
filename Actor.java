package project6;

/**
 * This class is used to store Actor's name information.
 *
 * @author Daxin Niu
 * @version 9/29/2018
 */

public class Actor {

    private String actorName;

    /**
     * Call the setActorName method to set actorName.
     * @param name
     * @throws IllegalArgumentException
     */
    public Actor(String name) throws IllegalArgumentException {
        setActorName(name);
    }

    /**
     * Returns actorName when this method is called.
     * @return actorName
     */
    public String getActorName() {
        return actorName;
    }

    /**
     * This method checks whether the input is null or empty and
     * throws IllegalArgumentException, otherwise set name to actorName.
     * @param name
     * @throws IllegalArgumentException
     */
    private void setActorName(String name) throws IllegalArgumentException {

        //validate name length or if it is null
        if(name == null) {
            throw new IllegalArgumentException("Input can't be null");
        }
        else if(name.length() == 0) {
            throw new IllegalArgumentException("Input can't be empty");
        }
        else {
            this.actorName = name;
        }
    }
}
