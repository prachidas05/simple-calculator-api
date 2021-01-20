
package com.assignment.simplecalculator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @author Prachi Das
 *
 */
@Getter
@Setter
@Entity
@Table(name = "CALCULATOR_HISTORY")
public class CalculatorHistoryEntity implements Serializable {

	@Id
	@Column(name = "EXPRESSION")
	private String expression;

}
