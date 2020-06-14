package com.baka.Main.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductJdbcRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;


    static class ProductRowMapper implements RowMapper<ProductEntity> {
        @Override
        public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductEntity product = new ProductEntity();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setLicenseCost(rs.getBigDecimal("license_cost"));
            product.setDataAmount(rs.getInt("data_amountGB"));
            product.setVMram(rs.getInt("vm_ram_amountGB"));
            product.setSeessionCount(rs.getInt("session_count"));
            return product;
        }
    }

    public List<ProductEntity> findAll() {
        return jdbcTemplate.query("select * from product", new ProductRowMapper());
    }

}
