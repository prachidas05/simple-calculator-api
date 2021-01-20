package com.assignment.simplecalculator.service;


import com.assignment.simplecalculator.exception.DivideByZeroError;
import com.assignment.simplecalculator.model.CalculatorHistoryEntity;
import com.assignment.simplecalculator.model.CalculatorResponse;
import com.assignment.simplecalculator.repository.SimpleCalculatorDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple Calculator service implementation.
 *
 * @author Prachi Das
 */
@Service
public class SimpleCalculatorServiceImpl implements SimpleCalculatorService {

    @Autowired
    private SimpleCalculatorDao simpleCalculatorDao;

    /**
     * This method performs simple mathematical Operation Add.
     *
     * @param a &  b
     * @return Double instance
     */
    @Override
    public Double add(final Double a, final Double b) {
        Double result = a + b;
        String expression = getExpression(a, b, "+", result);
        CalculatorHistoryEntity calculatorHistoryEntity = new CalculatorHistoryEntity();
        calculatorHistoryEntity.setExpression(expression);
        simpleCalculatorDao.saveOrUpdateAll(calculatorHistoryEntity);
        return result;
    }

    ;

    /**
     * This method performs simple mathematical Operation Subtract.
     *
     * @param a &  b
     * @return Double instance
     */
    @Override
    public Double subtract(final Double a, final Double b) {
        Double result = a - b;
        String expression = getExpression(a, b, "-", result);
        CalculatorHistoryEntity calculatorHistoryEntity = new CalculatorHistoryEntity();
        calculatorHistoryEntity.setExpression(expression);
        simpleCalculatorDao.saveOrUpdateAll(calculatorHistoryEntity);
        return result;
    }

    /**
     * This method performs simple mathematical Operation Multiply.
     *
     * @param a &  b
     * @return Double instance
     */
    @Override
    public Double multiply(final Double a, final Double b) {
        Double result = a * b;
        String expression = getExpression(a, b, "*", result);
        CalculatorHistoryEntity calculatorHistoryEntity = new CalculatorHistoryEntity();
        calculatorHistoryEntity.setExpression(expression);
        simpleCalculatorDao.saveOrUpdateAll(calculatorHistoryEntity);
        return result;
    }

    /**
     * This method performs simple mathematical Operation Multiply.
     *
     * @param a & integer b
     * @return Double instance
     */
    @Override
    public Double divide(final Double a, final Double b) throws DivideByZeroError {
        if (b == 0) {
            throw new DivideByZeroError();
        }
        Double result = a / b;
        String expression = getExpression(a, b, "/", result);
        CalculatorHistoryEntity calculatorHistoryEntity = new CalculatorHistoryEntity();
        calculatorHistoryEntity.setExpression(expression);
        simpleCalculatorDao.saveOrUpdateAll(calculatorHistoryEntity);
        return result;
    }

    private String getExpression(Double a, Double b, String operator, Double result) {
        StringBuilder builder = new StringBuilder();
        return builder.append(a).append(operator).append(b).append("=").append(result).toString();
    }

    /**
     * This method gets the history of all calculations
     *
     * @return List of String
     */
    @Override
    public List<String> getHistory() {
        List<CalculatorHistoryEntity> entities = simpleCalculatorDao.getOperationHistory();
        return entities.stream().map(e -> e.getExpression()).collect(Collectors.toList());
    }


}
