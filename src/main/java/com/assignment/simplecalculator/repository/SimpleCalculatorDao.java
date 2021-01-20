package com.assignment.simplecalculator.repository;


import com.assignment.simplecalculator.model.CalculatorHistoryEntity;

import java.util.List;


/**
 * Data Access Object(DAO) class for <em>Operation</em>.
 *
 * @author Prachi Das
 */
public interface SimpleCalculatorDao {

    /**
     * Retrieves all performed operations in the calculator app
     * {@link CalculatorHistoryEntity}.
     *
     * @return {@code Operation} instance with operation history
     */
    List<CalculatorHistoryEntity> getOperationHistory();


    /**
     * Saves an expression or a single calculation
     *
     * @param entity  {@link CalculatorHistoryEntity} instance
     */
    void saveOrUpdateAll(CalculatorHistoryEntity entity);



}
