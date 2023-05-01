package edu.psu.ist.model;

import java.util.*;

public class UtilListImpl<E> implements ISplittableList<E> {

    private List<E> left = new ArrayList<>();
    private List<E> right = new LinkedList<>();

    /**
     * Gets Left List
     * @return list
     */
    public List<E> getLeftList() {return left;}

    /**
     * Gets Right list
     * @return list
     */
    public List<E> getRightList() {return right;}

    /**
     * Adds entries to the beginning of the right side of list
     * @param e the entry to add
     */
    @Override
    public void addToRightAtFront(E e) {  // O(1)
        right.add(0, e);          // O(1)
    }

    /**
     * Removes and returns the first entry from the right side list. The method
     * requires that the right side list must be a non-empty when called
     * @throws NoSuchElementException if one attempts to remove from an empty
     *                                right list
     */
    @Override
    public E removeFromTheRightAtFront() { // O(1)
        if(right.isEmpty()) {              // O(1)
            throw new NoSuchElementException("No Element in Right List to Remove..."); // O(1)
        }
        return right.remove(0);      // O(1)
    }

    /**
     * Moves all entries from the left list and prepends them to the entries
     * in the right list. This method may be called at any time.
     */
    @Override
    public void moveToVeryBeginning() { // O(1)
        right.addAll(0, left);    // O(1)
        left.clear();                   // O(1)
    }

    /**
     * Moves the position of splittable list's "handle" into an empty right list
     * @throws IllegalStateException if one tries to move forward into an empty right list
     */
    @Override
    public void moveForward() {  // O(n)
        if(right.isEmpty()) {    // O(1)
            throw new IllegalStateException("No Elements in Right List to Move Forward..."); // O(1)
        }
        left.add(right.get(0));  // O(1) + O(n) = O(n)
        right.remove(0);   // O(1)
    }

    /**
     * Returns the length of the left list. This method may be called at any time.
     *
     * @return returns the length of the left list.
     */
    @Override
    public int leftLength() {  // O(1)
        return left.size();    // O(1)
    }

    /**
     * Returns the length of the right list. This method may be called at any time.
     *
     * @return returns the length of the right list.
     */
    @Override
    public int rightLength() {  // O(1)
        return right.size();    // O(1)
    }

    /**
     * Clears the contents out of both the left and right sides of this splittable list.
     */
    @Override
    public void clear() {  // O(1)
        left.clear();      // O(1)
        right.clear();     // O(1)
    }

    /**
     * ToString method for UtilListImpl
     * @return string with splittable list
     */
    @Override
    public String toString() {  // O(n)
        String str = "";
        if(left.isEmpty()) {
            str += "<>";
        } else {
            for(int i = 0; i < left.size(); i++) { // O(n)
                if (i == 0 && (i + 1) != left.size()) {
                    str += "<" + left.get(i) + ", ";
                } else if(i == 0 && (i + 1) == left.size()) {
                    str += "<" + left.get(i) + ">";
                } else if((i + 1) == left.size()) {
                    str += left.get(i) + ">";
                } else {
                    str += left.get(i) + ", ";
                }
            }
        }
        if(right.isEmpty()) {
            str += "|<>";
        } else {
            for(int i = 0; i < right.size(); i++) { // O(n)
                if (i == 0 && (i + 1) != right.size()) {
                    str += "|<" + right.get(i) + ", ";
                } else if(i == 0 && (i + 1) == right.size()) {
                    str += "|<" + right.get(i) + ">";
                } else if((i + 1) == right.size()) {
                    str += right.get(i) + ">";
                } else {
                    str += right.get(i) + ", ";
                }
            }
        }
        return str;
    }

}
