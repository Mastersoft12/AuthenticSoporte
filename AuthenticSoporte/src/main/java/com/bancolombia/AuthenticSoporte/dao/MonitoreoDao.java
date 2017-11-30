package com.bancolombia.AuthenticSoporte.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String MENSAJEERRORGENERICO = "Error en el proceso de negocio: ";
	
	public static final String MENSAJEERRORBD = "Erro al accesar a los datos de la base de datos: ";

	public static final String MENSAJEERRORINDICE = "Erro, Indice fuera de los límites: ";
	
	public static final String MENSAJEERRORACCION = "Error en la acción solicitada: ";
	
	public List<Monitoreo> obtenerMonitoreoAprobado() throws BusinessException{
		List<Monitoreo> monitoreo = new ArrayList<>();
		Supplier<String> monitoreoSupplier = UtilidadMonitoreoQuery::queryMonitoreo;
		try {
			 monitoreo = jdbcTemplateConexion.obtenerJdbcTemplate().query(monitoreoSupplier.get(), new MonitoreoMapper());
		} catch (DataAccessException | SQLException e) {
	         logger.error(MENSAJEERRORBD + e);
	         throw new BusinessException(MENSAJEERRORGENERICO+ e);
		} catch (IllegalArgumentException e) {
	         logger.error(MENSAJEERRORINDICE + e);
	        throw new BusinessException(MENSAJEERRORGENERICO);
		} catch (NamingException e) {
			  logger.error(MENSAJEERRORACCION + e);
	        throw new BusinessException(MENSAJEERRORGENERICO);
		} 
		return monitoreo;
	}
	
	public List<Monitoreo> obtenerMonitoreoRechazado() throws BusinessException{
		List<Monitoreo> monitoreo = new ArrayList<>();
		Supplier<String> monitoreoSupplier = UtilidadMonitoreoQuery::queryMonitoreoRechazada;
		try {
			 monitoreo = jdbcTemplateConexion.obtenerJdbcTemplate().query(monitoreoSupplier.get(), new MonitoreoMapper());
		} catch (DataAccessException | SQLException e) {
	         logger.error(MENSAJEERRORBD + e);
	         throw new BusinessException(MENSAJEERRORGENERICO+ e);
		} catch (IllegalArgumentException e) {
	         logger.error(MENSAJEERRORINDICE + e);
	        throw new BusinessException(MENSAJEERRORGENERICO);
		} catch (NamingException e) {
			  logger.error(MENSAJEERRORACCION + e);
	        throw new BusinessException(MENSAJEERRORGENERICO);
		} 
		return monitoreo;
	}
	
	


}
