import java.io.Serializable;
import java.util.Arrays;

public class Movie implements Serializable {

    private int id;
    private String title;
    private String director;
    private int runningTime;
    private String[] genres;

    public Movie() {
    }

    public Movie(String title, String director, int runningTime, String[] genres) {
        this.title = title;
        this.director = director;
        this.runningTime = runningTime;
        this.genres = genres;
    }

    public Movie(int id, String title, String director, int runningTime, String[] genres) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.runningTime = runningTime;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", runningTime=" + runningTime +
                ", genres=" + Arrays.toString(genres) +
                '}';
    }
}
