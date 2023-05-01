package edu.psu.ist;

import edu.psu.ist.controller.Controller;
import edu.psu.ist.model.*;
import edu.psu.ist.view.View;

/**
 * App class puts everything together in the main method
 * This is how the actual application can run
 */
public class App {

    public static void main(String[] args) {
        ISplittableList<String> model = new UtilListImpl<>();
        View view = new View();
        Controller controller = new Controller(model, view);
        view.setVisible(true);
    }
}
