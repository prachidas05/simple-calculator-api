package com.assignment.simplecalculator.controller;


import com.assignment.simplecalculator.exception.DivideByZeroError;
import com.assignment.simplecalculator.model.CalculatorResponse;
import com.assignment.simplecalculator.service.SimpleCalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * This is controller class for simple calculator
 *
 * @author Prachi Das
 */
@RestController
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8000")
public class SimpleCalculatorController {

  private final SimpleCalculatorService simpleCalculatorService;

  /**
   * Perform simple arithmetic calculation
   *
   * @param firstNum
   * @param secondNum
   * @param operation
   * @throws DivideByZeroError
   */

  @GetMapping(value = "/calculator", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CalculatorResponse> calculate(@RequestParam("firstNum") final Double firstNum, @RequestParam("secondNum") final Double secondNum, @RequestParam("operation") final String operation) throws DivideByZeroError {
    Double output = 0D;
    if (firstNum != null && secondNum != null) {
      output = getResult(firstNum, secondNum, operation, output);
    }
    CalculatorResponse response = new CalculatorResponse();
    response.setResult(output.toString().endsWith(".0") ? String.valueOf(output.longValue()) : output.toString());
    final List<String> calculationHistory = simpleCalculatorService.getHistory();
    response.setHistory(calculationHistory);
    return new ResponseEntity(response, HttpStatus.OK);
  }

  private Double getResult(final Double firstNum, final Double secondNum, final String operation, Double output) throws DivideByZeroError {
    switch (operation) {
      case "add":
        output = simpleCalculatorService.add(firstNum, secondNum);
        break;
      case "subtract":
        output = simpleCalculatorService.subtract(firstNum, secondNum);
        break;
      case "multiply":
        output = simpleCalculatorService.multiply(firstNum, secondNum);
        break;
      case "division":
        output = simpleCalculatorService.divide(firstNum, secondNum);
        break;
    }
    return output;
  }

  /**
   * Perform simple arithmetic calculation
   *
   * @param firstNum
   * @param secondNum
   * @param operation
   * @throws DivideByZeroError
   */
  @GetMapping(value = "/calculator/history", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CalculatorResponse> getHistory() {
    CalculatorResponse response = new CalculatorResponse();
    final List<String> calculationHistory = simpleCalculatorService.getHistory();
    response.setHistory(calculationHistory);
    return new ResponseEntity(response, HttpStatus.OK);
  }

  @ExceptionHandler(DivideByZeroError.class)
  public ResponseEntity<String> handleException(DivideByZeroError e) {
    return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
