package com.bancolombia.AuthenticSoporte.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.bancolombia.AuthenticSoporte.bean.IDatasource;


@Component("jdbcTemplateConexion")
public class JdbcTemplateConexion {
	
	@Autowired
	@Qualifier("dataSourceO")
	IDatasource dataSource;
	
	public JdbcTemplate obtenerJdbcTemplate() throws IllegalArgumentException, NamingException, SQLException{
		return new JdbcTemplate(dataSource.obtenerDatasource());
	}

}
