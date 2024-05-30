package com.humber.Week4JDBCApp.repositories;
// importing Model Package Dish
import com.humber.Week4JDBCApp.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper; // rowmapper from spring frame work
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository //need to add to make class a BEAN //or @Autowire will not work
public class DishRepository {
    @Autowired

    JdbcTemplate template; // inject this by adding @Autowire

    //save a dish
    public int save(Dish dish) { //
        String sql = "INSERT INTO dish (name, category, price) VALUES (?, ?, ?)";
       return  template.update(sql, dish.getName(), dish.getCategory(), dish.getPrice() ); // to make changes
        // return int
    }

    /*get all dishes //data is at DB */
    public  List <Dish> getDishes (){
        String sql = "SELECT * FROM dish";

        RowMapper<Dish> mapper = new RowMapper<Dish>() {

            @Override
            public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
                Dish mydish = new Dish();
                // mapRow takes out data from table
                mydish.setId(rs.getInt(1)); //or this mydish.setId(rs.getInt("id"));
                mydish.setName(rs.getString(2));
                mydish.setCategory(rs.getString(3));
                mydish.setPrice(rs.getDouble(4));

                return mydish;
            }
        };

         List <Dish> dishes = template.query(sql, mapper); //do not have to add List<Dish> just return template.query(sql, mapper)
         return dishes;
         //template will hold list of dishes
    }


}// class
//repo done next service