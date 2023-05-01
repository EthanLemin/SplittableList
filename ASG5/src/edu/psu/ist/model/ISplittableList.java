package edu.psu.ist.model;

import java.util.List;
import java.util.NoSuchElementException;

public interface ISplittableList<T> {

    /**
     * Gets Left List
     * @return list
     */
    List<T> getLeftList();

    /**
     * Gets Left List
     * @return list
     */
    List<T> getRightList();

    /**
     * Adds entries to the beginning of the right side of list
     * @param e the entry to add
     */
    void addToRightAtFront(T e);

    /**
     * Removes and returns the first entry from the right side list. The method
     * requires that the right side list must be a non-empty when called
     * @throws NoSuchElementException if one attempts to remove from an empty
     * right list
     */
    T removeFromTheRightAtFront();

    /**
     * Moves all entries from the left list and prepends them to the entries
     * in the right list. This method may be called at any time.
     */
    void moveToVeryBeginning();

    /**
     * Moves the position of splittable list's "handle" into an empty right list
     */
    void moveForward();

    /**
     * Returns the length of the left list. This method may be called at any time.
     * @return returns the length of the left list.
     */
    int leftLength();

    /**
     * Returns the length of the right list. This method may be called at any time.
     * @return returns the length of the right list.
     */
    int rightLength();

    /**
     * Clears the contents out of both the left and right sides of this splittable list.
     */
    void clear();
}
