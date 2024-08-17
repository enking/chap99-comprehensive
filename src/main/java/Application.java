import java.util.Scanner;

public class Application {

    private static final MovieService movieService = new MovieService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("===== 영화 관리 프로그램 =====");
            System.out.println("1. 모든 영화 정보 보기");
            System.out.println("2. 영화 찾기");
            System.out.println("3. 영화 등록");
            System.out.println("4. 영화 정보 수정");
            System.out.println("5. 영화 삭제");
            System.out.println("6. 영화 추천 받기");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 선택 : ");
            int choice = scanner.nextInt();

            switch(choice) {
                case 1: movieService.findAllMovies(); break;
                case 2: movieService.findMovieById(chooseId()); break;
                case 3: movieService.registerMovie(signUp()); break;
                case 4:
                    Movie selected = movieService.findMovieForModify(chooseId());
                    if (selected == null) continue;
                    movieService.modifyMovie(reform(selected));
                    break;
                case 5: movieService.removeMovie(chooseId()); break;
                case 6: movieService.recommendMovie(); break;
                case 9:
                    System.out.println("영화 관리 프로그램을 종료합니다."); return;
                default:
                    System.out.println("번호를 잘 못 입력했습니다.");
            }

        }

    }

    private static int chooseId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("영화 ID 입력 : ");
        return scanner.nextInt();
    }

    private static Movie reform(Movie selected) {
        Movie modifiedMovie = selected;
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("==== 수정 서브 메뉴 ====");
            System.out.println("1. 제목");
            System.out.println("2. 감독");
            System.out.println("3. 장르");
            System.out.println("4. 상영 시간");
            System.out.println("5. 메인 메뉴로 돌아가기");
            System.out.print("내용을 선택하세요: ");
            int chooseNo = sc.nextInt();
            sc.nextLine();
            switch(chooseNo) {
                case 1:
                    System.out.print("수정 할 제목을 입력하세요: ");
                    modifiedMovie.setTitle(sc.nextLine());
                    break;
                case 2:
                    System.out.print("수정 할 감독을 입력하세요: ");
                    modifiedMovie.setDirector(sc.nextLine());
                    break;
                case 3:
                    modifiedMovie.setGenres(resetGenres());
                    break;
                case 4:
                    System.out.print("수정 할 상영 시간을 입력하세요(분 단위): ");
                    modifiedMovie.setRunningTime(sc.nextInt());
                    break;
                case 5:
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    return selected;
                default:
                    System.out.println("번호를 다시 제대로 입력해 주세요: ");
            }

            return modifiedMovie;
        }
    }

    private static String[] resetGenres() {
        Scanner sc = new Scanner(System.in);

        System.out.print("수정 할 장르 개수를 입력하세요(숫자로 1 이상): ");
        int length = sc.nextInt();
        sc.nextLine();

        String[] genres = new String[length];
        for (int i = 0; i < genres.length; i++) {
            System.out.print((i + 1) + "번째 장르를 입력하세요: ");
            String input = sc.nextLine();
            genres[i] = input;
        }

        return genres;
    }

    private static Movie signUp() {
        Movie newMovie = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("제목을 입력하세요: ");
        String title = sc.nextLine();

        System.out.print("감독을 입력하세요: ");
        String director = sc.nextLine();

        System.out.print("상영 시간을 입력하세요(분 단위): ");
        int duration = sc.nextInt();

        System.out.print("입력 할 장르 개수를 입력하세요(숫자로 1 이상): ");
        int length = sc.nextInt();
        sc.nextLine();          // 버퍼의 개행문자 처리용

        String[] genres = new String[length];
        for (int i = 0; i < genres.length; i++) {
            System.out.print((i + 1) + "번째 장르를 입력하세요: ");
            String input = sc.nextLine();
            genres[i] = input;
        }

        newMovie = new Movie(title, director, duration, genres);

        return newMovie;
    }
}
