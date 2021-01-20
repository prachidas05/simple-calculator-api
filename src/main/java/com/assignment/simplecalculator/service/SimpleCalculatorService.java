package com.assignment.simplecalculator.service;


import com.assignment.simplecalculator.exception.DivideByZeroError;

import java.util.List;

/**
 * Simple Calculator service interface.
 *
 * @author Prachi Das
 */
public interface SimpleCalculatorService {

    /**
     * This method performs simple mathematical Operation Add.
     * @param a  &  b
     * @return Double instance
     */
    Double add(final Double a, final  Double b);

    /**
     * This method performs simple mathematical Operation Subtract.
     * @param  a &  b
     * @return Double instance
     */
    Double subtract(final Double a, final  Double b);

    /**
     * This method performs simple mathematical Operation Multiply.
     * @param  a &  b
     * @return Double instance
     */
    Double multiply(final Double a, final  Double b);

    /**
     * This method performs simple mathematical Operation Multiply.
     * @param  a & integer b
     * @return Double instance
     */
    Double divide(final Double a, final  Double b) throws DivideByZeroError;

    /**
     * This method gets the history of all calculations
     * @return List of String
     */
    List<String> getHistory();

}
