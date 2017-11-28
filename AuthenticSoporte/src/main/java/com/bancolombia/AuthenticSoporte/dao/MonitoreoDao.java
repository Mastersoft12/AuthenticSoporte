package com.bancolombia.AuthenticSoporte.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.bancolombia.AuthenticSoporte.Entity.Monitoreo;
import com.bancolombia.AuthenticSoporte.Exception.BusinessException;
import com.bancolombia.AuthenticSoporte.bean.IDatasource;
import com.bancolombia.AuthenticSoporte.mapper.MonitoreoMapper;
import com.bancolombia.AuthenticSoporte.utilidades.UtilidadMonitoreoQuery;


@Component("monitoreoDao")
public class MonitoreoDao implements IMonitoreoDao {
	
	@Autowired
	@Qualifier("dataSourceO")
	private IDatasource dataSource;
	
	@Autowired
	@Qualifier("jdbcTemplateConexion")
	private JdbcTemplateConexion jdbcTemplateConexion;
	

	
	public List<Monitoreo> obtenerMonitoreoAprobado() throws BusinessException{
		List<Monitoreo> monitoreo = null;
		Supplier<String> MonitoreoSupplier = UtilidadMonitoreoQuery::queryMonitoreo;
		try {
			 monitoreo = jdbcTemplateConexion.obtenerJdbcTemplate().query(MonitoreoSupplier.get(), new MonitoreoMapper());
		} catch (DataAccessException e) {
	    //    logger.info("Erro al accesar a la base de datos: "+e);
		} catch (IllegalArgumentException e) {
	     //   logger.info("Argumento ilegal: "+e);
	        throw new BusinessException("Error en el proceso de negocio ");
		} catch (NamingException e) {
	      //  logger.error("error NamingException: "+e);
	        throw new BusinessException("Error en el proceso de negocio ");
		} catch (SQLException e) {
	     //   logger.error("Error en la base de datos: "+e);
	        throw new BusinessException("Error en el proceso de negocio ");

		}
		return monitoreo;
	}
	
	public List<Monitoreo> obtenerMonitoreoRechazado() throws BusinessException{
		List<Monitoreo> monitoreo = null;
		Supplier<String> MonitoreoSupplier = UtilidadMonitoreoQuery::queryMonitoreoRechazada;
		try {
			 monitoreo = jdbcTemplateConexion.obtenerJdbcTemplate().query(MonitoreoSupplier.get(), new MonitoreoMapper());
		} catch (DataAccessException e) {
	    //    logger.info("Erro al accesar a la base de datos: "+e);
		} catch (IllegalArgumentException e) {
	     //   logger.info("Argumento ilegal: "+e);
	        throw new BusinessException("Error en el proceso de negocio ");
		} catch (NamingException e) {
	      //  logger.error("error NamingException: "+e);
	        throw new BusinessException("Error en el proceso de negocio ");
		} catch (SQLException e) {
	     //   logger.error("Error en la base de datos: "+e);
	        throw new BusinessException("Error en el proceso de negocio ");

		}
		return monitoreo;
	}
	
	


}
