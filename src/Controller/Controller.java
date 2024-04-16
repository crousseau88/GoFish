package Controller;

import Model.Model;
import View.View;
import Model.Player;
import View.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;

    public Controller(View v, Model m) {
        model = m;
        view = v;
        model.startGame();

    }



}
