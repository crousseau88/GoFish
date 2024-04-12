package Controller;

import Model.Model;
import View.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(View v, Model m) {
        model = m;
        view = v;

        model.startGame();



    }
}
