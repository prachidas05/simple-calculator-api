package com.assignment.simplecalculator.service;

import com.assignment.simplecalculator.exception.DivideByZeroError;
import com.assignment.simplecalculator.model.CalculatorHistoryEntity;
import com.assignment.simplecalculator.repository.SimpleCalculatorDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SimpleCalculatorServiceImplTest {

    @InjectMocks
    private final SimpleCalculatorService simpleCalculatorService = new SimpleCalculatorServiceImpl();

    @Mock
    private SimpleCalculatorDaoImpl simpleCalculatorDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSum() {
        assertEquals(2.0,simpleCalculatorService.add(1.0,1.0));
        doNothing().when(simpleCalculatorDao).saveOrUpdateAll(Mockito.any(CalculatorHistoryEntity.class));
        verify(simpleCalculatorDao, Mockito.times(1)).saveOrUpdateAll(Mockito.any(CalculatorHistoryEntity.class));
    }

    @Test
    public void testDifference() {
        assertEquals(0.0,simpleCalculatorService.subtract(1.0,1.0));
        doNothing().when(simpleCalculatorDao).saveOrUpdateAll(Mockito.any(CalculatorHistoryEntity.class));
        verify(simpleCalculatorDao, Mockito.times(1)).saveOrUpdateAll(Mockito.any(CalculatorHistoryEntity.class));
    }

    @Test
    public void testMultiplication() {
        assertEquals(1.0,simpleCalculatorService.multiply(1.0,1.0));
        doNothing().when(simpleCalculatorDao).saveOrUpdateAll(Mockito.any(CalculatorHistoryEntity.class));
        verify(simpleCalculatorDao, Mockito.times(1)).saveOrUpdateAll(Mockito.any(CalculatorHistoryEntity.class));
    }

    @Test
    public void testDivision() throws DivideByZeroError {
        assertEquals(2.0,simpleCalculatorService.divide(4.0,2.0));
        doNothing().when(simpleCalculatorDao).saveOrUpdateAll(Mockito.any(CalculatorHistoryEntity.class));
        verify(simpleCalculatorDao, Mockito.times(1)).saveOrUpdateAll(Mockito.any(CalculatorHistoryEntity.class));
    }

    @Test
    public void testGetHistory()  {
        when(simpleCalculatorDao.getOperationHistory())
                .thenReturn(mockHistoryData());
        final List<String> list = simpleCalculatorService.getHistory();
        assertNotNull(list);
        assertEquals(2,
                list.size());
    }

    private List<CalculatorHistoryEntity> mockHistoryData() {
        final List<CalculatorHistoryEntity> mockedData = new ArrayList<>();
        CalculatorHistoryEntity entity1 = new CalculatorHistoryEntity();
        CalculatorHistoryEntity entity2 = new CalculatorHistoryEntity();
        entity1.setExpression("2.0+5.0=7.0");entity2.setExpression("2.0*3.0=6.0");
        mockedData.add(entity1);
        mockedData.add(entity2);
        return mockedData;

    }
}