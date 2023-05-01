package edu.psu.ist.view;

import javax.swing.*;

public class SplitList{
    private JPanel mainPanel;
    private JLabel splitListLabel;
    private JButton addToRightAtFrontButton;
    private JTextField addToRightAtFrontTextField;
    private JButton removeFromRightAtFrontButton;
    private JButton moveForwardButton;
    private JButton moveToBeginningButton;
    private JButton clearButton;
    private JLabel lastRemovedLabel;
    private JLabel leftLengthLabel;
    private JLabel rightLengthLabel;
    private JButton saveButton;
    private JButton clearLoadButton;

    /**
     The methods below simply return certain swing objects to access in the Controller class
     **/
    public JPanel getMainPanel() {return mainPanel;}
    public JButton getAddToRightAtFrontButton() { return addToRightAtFrontButton; }
    public JButton getRemoveFromRightAtFrontButton() { return removeFromRightAtFrontButton; }
    public JButton getMoveForwardButton() { return moveForwardButton; }
    public JButton getMoveToBeginningButton() { return moveToBeginningButton; }
    public JButton getClearButton() { return clearButton; }
    public JButton getSaveButton() { return saveButton; }
    public JButton getClearLoadButton() { return clearLoadButton; }
    public JTextField getAddToRightAtFrontTextField() { return addToRightAtFrontTextField; }
    public JLabel getSplitListLabel() { return splitListLabel; }
    public JLabel getLastRemovedLabel() { return lastRemovedLabel; }
    public JLabel getLeftLengthLabel() { return leftLengthLabel; }
    public JLabel getRightLengthLabel() { return rightLengthLabel; }
}
