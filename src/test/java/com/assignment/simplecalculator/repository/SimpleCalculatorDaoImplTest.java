package com.assignment.simplecalculator.repository;

import com.assignment.simplecalculator.model.CalculatorHistoryEntity;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class SimpleCalculatorDaoImplTest {

    @Autowired
    private SimpleCalculatorDao simpleCalculatorDao;

    @Autowired private  DataSource dataSource;

    private IDatabaseConnection dbConn;

    @BeforeEach
    public void setUp() throws Exception {
        dbConn = new DatabaseDataSourceConnection(dataSource);
    }


    @AfterEach
    public void cleanup() throws SQLException {
        dbConn.close();
    }

    /**
     * Reads file and returns {@link InputStream} instance.
     *
     * @param fileName name of the file
     * @return {@code InputStream} instance
     */
    protected InputStream readFile(final String fileName) {
        final ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }


    @Test
    public void testSaveOrUpdate() throws DataSetException, DatabaseUnitException, SQLException {
        final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        DatabaseOperation.CLEAN_INSERT.execute(dbConn,
                builder.build(readFile("calculator-history-test-data.xml")));
        String exp = "2.0*3.0=6.0";
        CalculatorHistoryEntity calculatorHistoryEntity = new CalculatorHistoryEntity();
        calculatorHistoryEntity.setExpression(exp);
        simpleCalculatorDao.saveOrUpdateAll(calculatorHistoryEntity);
        final CalculatorHistoryEntity entity = simpleCalculatorDao.getOperationHistory().get(0);
        assertNotNull(entity);
    }
}