package com.assignment.simplecalculator.exception;

/**
 * @author Prachi Das
 */
public class DivideByZeroError extends Exception {
    public DivideByZeroError() {
        super("Cannot be divided by 0");
    }
}
