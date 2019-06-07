package com.drydock.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.CurrencyMaster;

public class CurrencyMasterMapper  implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		CurrencyMaster currencyMaster = new CurrencyMaster();
		currencyMaster.setId(rs.getLong("id"));
		currencyMaster.setCurrencyCode(rs.getString("currency_code"));
		currencyMaster.setCurrencyDescription(rs.getString("currency_description"));		 
		currencyMaster.setCreateid(rs.getLong("create_id"));
		currencyMaster.setCreatedate(rs.getDate("create_date"));
		currencyMaster.setUpdateid(rs.getLong("update_id"));
		currencyMaster.setUpdatedate(rs.getDate("update_date"));
		currencyMaster.setIsactive(rs.getInt("is_active"));
		 
		 return currencyMaster;
	}

}
