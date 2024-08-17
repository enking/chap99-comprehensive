import java.io.*;
import java.util.ArrayList;

public class MovieRepository {

    private final ArrayList<Movie> movieList = new ArrayList<>();
    private static final String FILE_PATH = "movieDB.dat"; // 루트 디렉토리에 저장

    public MovieRepository() {
        File file = new File(FILE_PATH);

        // 파일이 존재하지 않으면 새 파일 생성
        if (!file.exists()) {
            try {
                file.createNewFile(); // 파일 생성
                System.out.println("새로운 데이터 파일을 생성했습니다: " + FILE_PATH);

                // 초기 영화 데이터 추가
                ArrayList<Movie> movies = new ArrayList<>();
                movies.add(new Movie(1, "Inception", "Christopher Nolan", 148, new String[]{"Sci-Fi", "Thriller"}));
                movies.add(new Movie(2, "The Godfather", "Francis Ford Coppola", 175, new String[]{"Crime", "Drama"}));
                movies.add(new Movie(3, "The Dark Knight", "Christopher Nolan", 152, new String[]{"Action", "Crime"}));
                movies.add(new Movie(4, "신세계", "박훈정", 152, new String[]{"Crime"}));

                saveMovies(file, movies);
            } catch (IOException e) {
                throw new RuntimeException("데이터 파일을 생성할 수 없습니다.", e);
            }
        }

        loadMovies(file);
    }

    private void loadMovies(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while(true) {
                movieList.add((Movie)ois.readObject());
            }
        } catch(EOFException e) {
            System.out.println("영화 정보를 모두 로딩하였습니다.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveMovies(File file, ArrayList<Movie> movies) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Movie movie : movies) {
                oos.writeObject(movie);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Movie> selectAllMovies() {
        return movieList;
    }

    public Movie selectMovieById(int id) {
        for(Movie movie : movieList) {
            if(movie.getId() == id) {
                return movie;
            }
        }

        return null;
    }

    public int insertMovie(Movie movie) {
        movieList.add(movie);
        saveMovies(new File(FILE_PATH), movieList);
        return 1;
    }

    public int deleteMovie(int id) {
        Movie selectedMovie = selectMovieById(id);

        if(selectedMovie != null) {
            movieList.remove(selectedMovie);
            saveMovies(new File(FILE_PATH), movieList);
            return 1;
        }

        return 0;
    }

    public int selectLastMovieId() {
        return movieList.get(movieList.size() - 1).getId();
    }

    public int updateMovie(Movie movie) {
        Movie selectedMovie = selectMovieById(movie.getId());

        if(selectedMovie != null) {
            selectedMovie.setTitle(movie.getTitle());
            selectedMovie.setDirector(movie.getDirector());
            selectedMovie.setGenres(movie.getGenres());
            selectedMovie.setRunningTime(movie.getRunningTime());
            saveMovies(new File(FILE_PATH), movieList);
            return 1;
        }

        return 0;
    }

    public ArrayList<Movie> selectRecommendedMovies() {
        ArrayList<Movie> recommendedMovies = new ArrayList<>();
        for(Movie movie : movieList) {
            if(movie.getGenres().length > 1) { // 예시: 장르가 2개 이상인 영화를 추천
                recommendedMovies.add(movie);
            }
        }
        return recommendedMovies;
    }
}
