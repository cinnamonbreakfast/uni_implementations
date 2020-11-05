package Domain;

import java.util.Objects;

public class Rental extends Entity<Long> {
    private Long client;
    private Long movie;

    public Rental(Long client, Long movie) {
        this.client = client;
        this.movie = movie;
    }

    public Long getClient() {
        return client;
    }

    public Long getMovie() {
        return movie;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public void setMovie(Long movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return client.equals(rental.client) &&
                movie.equals(rental.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, movie);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "client=" + client +
                ", movie=" + movie +
                '}';
    }
}
