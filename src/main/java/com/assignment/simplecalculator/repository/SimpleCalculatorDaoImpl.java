package com.assignment.simplecalculator.repository;


import com.assignment.simplecalculator.model.CalculatorHistoryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


/**
 * Data Access Object(DAO) class for <em>Operation</em>.
 *
 * @author Prachi Das
 */
@Repository
public class SimpleCalculatorDaoImpl implements SimpleCalculatorDao{


    /**
     * the session factory instance provided by Hibernate
     */
    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * Retrieves all performed operations in the calculator app
     * {@link CalculatorHistoryEntity}.
     *
     * @return {@code Operation} instance with operation history
     */
    public List<CalculatorHistoryEntity> getOperationHistory(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CalculatorHistoryEntity");
        return query.getResultList();
    }


    /**
     * Saves an expression or a single calculation
     *
     * @param entity {@link CalculatorHistoryEntity} instance
     */
    public void saveOrUpdateAll(CalculatorHistoryEntity entity){
        sessionFactory.getCurrentSession().save(entity);
    }



}
