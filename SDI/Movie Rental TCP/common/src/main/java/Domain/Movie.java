package Domain;

public class Movie extends Entity<Long> {
    private String name;
    private String description;
    private int price;
    private int rating;

    public Movie(){

    }

    public Movie(String name, String description, int price, int rating)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }

    /**
     * Returns the name of the movie.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the movie
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the price of the movie.
     * @return Integer
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the rating of the movie.
     * @return Integer
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the name for the movie.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description for the movie
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the price for the movie.
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Sets the rating for the movie.
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price + '\'' +
                ", rating=" + rating +
                "} " + super.toString();
    }
}
