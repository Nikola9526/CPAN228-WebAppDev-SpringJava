package com.humber.Week4JDBCApp.repositories;
// importing Model Package Dish
import com.humber.Week4JDBCApp.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository // need to add to make class a BEAN // or @Autowire will not work
public class DishRepository {
    @Autowired

    JdbcTemplate template; // inject this by adding @Autowire
    // save a dish

    // get all dishes

    public  List <Dish> getDishes (){
        String sql = "SELECT * FROM dishes";

        RowMapper<Dish> mapper = new RowMapper<Dish>() {

            @Override
            public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
                return null;
            }
        };

         template.query(sql, mapper);
    }


}// class
//repo done next service