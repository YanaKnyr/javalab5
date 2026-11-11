public class Main {
    public static void main(String[] args) {
        BookView view = new BookView();
        BookController controller = new BookController(view);
        controller.run();
    }
}
