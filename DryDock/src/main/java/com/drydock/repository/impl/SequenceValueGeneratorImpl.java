package com.drydock.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.drydock.repository.SequenceValueGenerator;

@Component
public class SequenceValueGeneratorImpl extends JdbcDaoSupport  implements SequenceValueGenerator{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public Long getNextTransactionNumberFromSequence(String seqName) {

		//JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return (Long) jdbcTemplate.execute(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						return connection.prepareStatement("select NEXT VALUE FOR "+seqName);
					}
				},
				new PreparedStatementCallback() {
					public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						ResultSet rs = ps.executeQuery();
						Long value = null;
						while(rs.next()) {
							value = rs.getLong(1);
						}
						return value;
					}
				});
	}

}
