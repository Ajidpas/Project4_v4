/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import static model.dao.EntityCreator.CONNECTION_POOL;
import model.entity.DBEntity;
import model.entity.Meal;

/**
 *
 * @author Sasha
 */
public class MealCreator extends EntityCreator {

    /**
     * Constructor
     */
    public MealCreator() {
        super(MEAL_TABLE, MEAL_ID);
    }

    /**
     * Create new meal
     * @param meal meal migth be updated into the data base
     * @return true if updating is successfull and false otherwise
     * @throws java.sql.SQLException
     * @throws model.dao.ServerOverloadedException
     */
    public boolean insertMeal(Meal meal) throws SQLException, 
            ServerOverloadedException {
        boolean flag = false;
        WrapperConnectionProxy wrapperConnection = null;
        try {
            wrapperConnection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = wrapperConnection.
                    prepareStatement(SQL_FOR_INSERTING_ENTITY);
            ps.setString(1, meal.getTypeString());
            ps.setString(2, meal.getName());
            ps.setString(3, meal.getDescription());
            ps.setBigDecimal(4, meal.getPrice());
            ps.executeUpdate();
            flag = true;
        } finally {
            if (wrapperConnection != null) {
                wrapperConnection.close();
            }
        }
        return flag;
    }
    
    /**
     * Get one meal by result set
     * @param rs result set of sql query
     * @return DBEntity object
     * @throws SQLException
     */
    @Override
    protected DBEntity getEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("meal_id");
        String type = rs.getString("meal_type");
        String name = rs.getString("meal_name");
        String description = rs.getString("meal_description");
        BigDecimal price = rs.getBigDecimal("meal_price");
        Meal newMeal = new Meal(price, type, name, description, 10); // hard code
        newMeal.setId(id);
        return newMeal;
    }
    
}
