import Controller.Controller;
import Model.Model;
import View.View;

public class App {
    public static void main(String[] args) {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

    }
}
//TODO add comments / javadoc to code
//TODO fix GUI updates within game panel so accurate information is displayed
//TODO test logic