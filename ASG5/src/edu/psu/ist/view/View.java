package edu.psu.ist.view;

import javax.swing.*;

public class View extends JFrame {

    private final SplitList form;

    /**
     * Constructor for the View Class
     * View constructor sets specific settings
     */
    public View() {
        this.form = new SplitList();
        JPanel content = form.getMainPanel();

        this.setContentPane(content);
        this.pack();
        this.setTitle("Splittable Madness");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 275);
        this.setLocationRelativeTo(null);
    }

    /**
     * SplitList method returns form to access swing objects
     * @return form
     */
    public SplitList form() {return form;}

}
