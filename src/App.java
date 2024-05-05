import Controller.Controller;
import Model.Model;
import View.View;
/**
 * Filename: App.java
 * Short description: Application class for program
 * IST 242 Assignment:GUI Programming Project
 *
 * @author Chad Rousseau, Christopher Rusnak, Tyler Mascherino
 * @version 05/3/2024
 */
public class App {
    public static void main(String[] args) {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

    }
}
