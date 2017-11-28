package com.bancolombia.AuthenticSoporte.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bancolombia.AuthenticSoporte.Entity.Monitoreo;

public class MonitoreoMapper implements RowMapper<Monitoreo> {

	@Override
	public Monitoreo mapRow(ResultSet arg0, int arg1) throws SQLException {
		Monitoreo monitoreo = new Monitoreo();
		monitoreo.setPromedio(arg0.getDouble("PROMEDIO"));
		monitoreo.setDescripcion(arg0.getString("DESCRIPCION"));
		monitoreo.setCantidad(arg0.getInt("CANTIDAD"));
		return monitoreo;
	}

}
