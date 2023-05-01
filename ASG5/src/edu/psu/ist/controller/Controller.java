package edu.psu.ist.controller;

import edu.psu.ist.model.*;
import edu.psu.ist.view.SplitList;
import edu.psu.ist.view.View;

import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * Controller class combines view and model classes together
 * This class controls all functions of the GUI
 * which include button presses, text entries, etc.
 */
public class Controller<T> {

    private View view;
    private ISplittableList listModel;
    private final File OUTPUT_FILE = new File("contents.txt");

    public Controller(ISplittableList model, View view) {
        this.view = view;
        this.listModel = model;
        SplitList viewForm = view.form();

        /**
        This section of code sets the empty JLabels with default information
        about the splittable list
         */
        viewForm.getSplitListLabel().setText(listModel.toString());
        viewForm.getLeftLengthLabel().setText(String.format(
                "%s %d", "Left Length:", listModel.rightLength()));
        viewForm.getRightLengthLabel().setText(String.format(
                "%s %s", "Right Length:", listModel.rightLength()));
        viewForm.getLastRemovedLabel().setText(String.format(
                "%s %s", "Last Removed:", "N/A"));

        /**
         * <b>Add To Right at Front ActionListener</b>
         * Grabs the text from the Add to Right at Front text field
         * Verifies that the text field is not empty; if so, displays an error
         * Calls the addToRightAtFront method to add the text to the right list in front of line
         * Updates JLabels with correct information
         */
        viewForm.getAddToRightAtFrontButton().addActionListener(e -> {
            String text = viewForm.getAddToRightAtFrontTextField().getText();
            if(checkAddToRightAtFrontTextField(text)) {
                JOptionPane.showMessageDialog(view, "Text Field is Blank...");
            } else {
                listModel.addToRightAtFront(text);
                viewForm.getAddToRightAtFrontTextField().setText("");
                viewForm.getSplitListLabel().setText(listModel.toString());
                viewForm.getLeftLengthLabel().setText(String.format(
                        "%s %d", "Left Length:", listModel.leftLength()));
                viewForm.getRightLengthLabel().setText(String.format(
                        "%s %s", "Right Length:", listModel.rightLength()));
            }
        });

        /**
         * <b>Remove from Right at Front ActionListener</b>
         * Calls the removeFrontTheRightAtFront method that removes the object right of the line
         * If the method throws an error, an error message is displayed
         * Updates JLabels with correct information
         */
        viewForm.getRemoveFromRightAtFrontButton().addActionListener(e -> {
            try {
                Object x = listModel.removeFromTheRightAtFront();
                viewForm.getSplitListLabel().setText(listModel.toString());
                viewForm.getLeftLengthLabel().setText(String.format(
                        "%s %d", "Left Length:", listModel.leftLength()));
                viewForm.getRightLengthLabel().setText(String.format(
                        "%s %s", "Right Length:", listModel.rightLength()));
                viewForm.getLastRemovedLabel().setText(String.format(
                        "%s %s", "Last Removed:", x));
            } catch(NoSuchElementException nse) {
                JOptionPane.showMessageDialog(view, nse.getMessage());
            }
        });

        /**
         * <b>Move Forward ActionListener</b>
         * Calls the moveForward method that moves the object infront of the line to the left list
         * If the method throws an error, an error message is displayed
         * Updates JLabels with correct information
         */
        viewForm.getMoveForwardButton().addActionListener(e -> {
            try {
                listModel.moveForward();
                viewForm.getSplitListLabel().setText(listModel.toString());
                viewForm.getLeftLengthLabel().setText(String.format(
                        "%s %d", "Left Length:", listModel.leftLength()));
                viewForm.getRightLengthLabel().setText(String.format(
                        "%s %s", "Right Length:", listModel.rightLength()));
            } catch(IllegalStateException ise) {
                JOptionPane.showMessageDialog(view, ise.getMessage());
            }
        });

        /**
         * <b>Move to Beginning ActionListener</b>
         * Calls the moveToBeginning method that moves all entities in left list to right
         * Updates JLabels with correct information
         */
        viewForm.getMoveToBeginningButton().addActionListener(e -> {
            listModel.moveToVeryBeginning();
            viewForm.getSplitListLabel().setText(listModel.toString());
            viewForm.getLeftLengthLabel().setText(String.format(
                    "%s %d", "Left Length:", listModel.leftLength()));
            viewForm.getRightLengthLabel().setText(String.format(
                    "%s %s", "Right Length:", listModel.rightLength()));
        });

        /**
         * <b>Clear ActionListener</b>
         * Calls the clear method that clears the splittableList
         * Updates JLabels with correct information
         */
        viewForm.getClearButton().addActionListener(e -> {
            listModel.clear();
            viewForm.getSplitListLabel().setText(listModel.toString());
            viewForm.getLeftLengthLabel().setText(String.format(
                    "%s %d", "Left Length:", listModel.rightLength()));
            viewForm.getRightLengthLabel().setText(String.format(
                    "%s %s", "Right Length:", listModel.rightLength()));
            viewForm.getLastRemovedLabel().setText(String.format(
                    "%s %s", "Last Removed:", "N/A"));
        });

        /**
         *  <b>Save ActionListener</b>
         *  Calls the save method
         */
        viewForm.getSaveButton().addActionListener(e -> {
            save(view);
            JOptionPane.showMessageDialog(view, "Save Complete!");
        });

        /**
         *  <b>Clear + Load ActionListener</b>
         * Calls the load method
         * Updates JLabels with correct information
         */
        viewForm.getClearLoadButton().addActionListener(e -> {
            load(view);
            viewForm.getSplitListLabel().setText(listModel.toString());
            viewForm.getLeftLengthLabel().setText(String.format(
                    "%s %d", "Left Length:", listModel.rightLength()));
            viewForm.getRightLengthLabel().setText(String.format(
                    "%s %s", "Right Length:", listModel.rightLength()));
            viewForm.getLastRemovedLabel().setText(String.format(
                    "%s %s", "Last Removed:", "N/A"));
        });

    }

    /**
     * Method verifies that the text field is not blank
     * @param text String containing text from text field
     * @return True if blank, false if filled
     */
    private static boolean checkAddToRightAtFrontTextField(String text) {return text.isBlank();}

    /**
     * <b>Save Method</b>
     * This method saves the current splittable list to a file
     * Method takes the current splittable list and creates a temporary list to manipulate
     * Stores both left and right lengths to use for reference
     * If left array isn't empty, moves all objects forward
     * Stores the new right length to filter through list
     * Removes each element of the right list and pushes it on a stack
     * Two for loops go through the stack and prints to the file, separates left from right with space
     * If there is an error while saving, it will catch and display an error message
     * @param view View object to add error message if file not found
     */
    private void save(View view) {
        try(PrintWriter wr = new PrintWriter(OUTPUT_FILE)) {
            List left = listModel.getLeftList();
            List right = listModel.getRightList();
            int leftLength = left.size();
            int rightLength = right.size();
            Stack stack = new Stack();
            for(int i = 0; i < left.size(); i++) {
                Object obj = left.get(i);
                stack.push(obj);
            }
            for(int i = 0; i < right.size(); i++) {
                Object obj = right.get(i);
                stack.push(obj);
            }
            for(int i = 0; i < leftLength; i++) {
                wr.println(stack.pop());
            }
            wr.println();
            for(int i = 0; i < rightLength; i++) {
                wr.println(stack.pop());
            }
        } catch(FileNotFoundException fne) {
            JOptionPane.showMessageDialog(view, "Error While Saving...");
        }
    }

    /**
     * <b>Load Method</b>
     * Takes current splittable list and clears it
     * Scans through file and adds each element to stack
     * If the scanner hits a white space, it will increment after each line until the end (To find left length)
     * For each element in the stack, it pops the top and calls the command addToRightAtFront with that
     *      element as parameter
     * Another for loop with iterate x number of times for left length and calls moveForward method
     * If there is an error, displays error message and reinitializes the splittable list
     * @param view View object to add error message if file not found
     */
    private void load(View view) {
        try(Scanner scnr = new Scanner(OUTPUT_FILE)) {
            listModel.clear();
            Stack stack = new Stack();
            int leftLength = 0;
            boolean hitWhitespace = false;
            while(scnr.hasNext()) {
                String str = scnr.nextLine();
                if(!str.isBlank()) {
                    if(hitWhitespace) {
                        leftLength++;
                    }
                    stack.push(str);
                } else {
                    hitWhitespace = true;
                }
            }
            for(Object obj : stack) {
                listModel.addToRightAtFront(obj);
            }
            for(int i = 0; i < leftLength; i++) {
                listModel.moveForward();
            }
        } catch(FileNotFoundException fne) {
            JOptionPane.showMessageDialog(view, "Error While Loading...");
            listModel = new UtilListImpl();
        }
    }
}
