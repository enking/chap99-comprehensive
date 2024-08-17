import java.util.ArrayList;

public class MovieService {

    private final MovieRepository movieRepository = new MovieRepository();

    public void findAllMovies() {
        ArrayList<Movie> findMovies = movieRepository.selectAllMovies();

        for(Movie movie : findMovies) {
            System.out.println("movie = " + movie);
        }
    }

    public void findMovieById(int id) {
        Movie selectedMovie = movieRepository.selectMovieById(id);

        if(selectedMovie != null) {
            System.out.println(selectedMovie);
        } else {
            System.out.println("해당 ID를 가진 영화는 없습니다.");
        }
    }

    public void registerMovie(Movie movie) {

        int lastMovieId = movieRepository.selectLastMovieId();
        movie.setId(lastMovieId + 1);

        int result = movieRepository.insertMovie(movie);

        if(result == 1)
            System.out.println(movie.getTitle() + " 영화 등록이 완료 되었습니다.");
    }

    public void removeMovie(int id) {
        int result = movieRepository.deleteMovie(id);

        if(result == 1) {
            System.out.println("영화 삭제가 완료 되었습니다.");
        } else {
            System.out.println("입력하신 영화 ID에 해당하는 영화가 없습니다.");
        }
    }

    public Movie findMovieForModify(int id) {

        Movie selectedMovie = movieRepository.selectMovieById(id);

        if(selectedMovie != null) {
            Movie newInstance = new Movie();
            newInstance.setId(selectedMovie.getId());
            newInstance.setTitle(selectedMovie.getTitle());
            newInstance.setDirector(selectedMovie.getDirector());
            newInstance.setRunningTime(selectedMovie.getRunningTime());
            newInstance.setGenres(selectedMovie.getGenres().clone());
            return newInstance;
        }

        System.out.println("입력하신 영화 ID에 해당하는 영화가 없습니다.");
        return null;
    }

    public void modifyMovie(Movie movie) {
        int result = movieRepository.updateMovie(movie);

        if(result == 1) {
            System.out.println("영화 정보 수정이 완료 되었습니다.");
        } else {
            System.out.println("입력하신 영화 ID에 해당하는 영화가 없습니다.");
        }
    }

    public void recommendMovie() {
        ArrayList<Movie> recommendedMovies = movieRepository.selectRecommendedMovies();

        System.out.println("===== 추천 영화 =====");
        for (Movie movie : recommendedMovies) {
            System.out.println(movie);
        }
    }
}
