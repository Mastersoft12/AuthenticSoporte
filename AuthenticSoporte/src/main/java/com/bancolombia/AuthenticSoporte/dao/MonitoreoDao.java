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
	

	
	public List<Monitoreo> obtenerMonitoreoAprobado() throws BusinessException{
		List<Monitoreo> monitoreo = new ArrayList<>();
		Supplier<String> MonitoreoSupplier = UtilidadMonitoreoQuery::queryMonitoreo;
		try {
			logger.info("Inicio ejecucion del query de monitoreo Aprobadas...");
			 monitoreo = jdbcTemplateConexion.obtenerJdbcTemplate().query(MonitoreoSupplier.get(), new MonitoreoMapper());
		} catch (DataAccessException | SQLException e) {
	         logger.error("Erro al accesar a los datos de la base de datos: "+e);
	         throw new BusinessException("Error en el proceso de negocio "+ e);
		} catch (IllegalArgumentException e) {
	         logger.error("Erro, Indice fuera de los límites: "+e);
	        throw new BusinessException("Error en el proceso de negocio ");
		} catch (NamingException e) {
			  logger.error("Error en la acción solicitada: "+e);
	        throw new BusinessException("Error en el proceso de negocio ");
		} 
		return monitoreo;
	}
	
	public List<Monitoreo> obtenerMonitoreoRechazado() throws BusinessException{
		List<Monitoreo> monitoreo = new ArrayList<>();
		Supplier<String> MonitoreoSupplier = UtilidadMonitoreoQuery::queryMonitoreoRechazada;
		try {
			 monitoreo = jdbcTemplateConexion.obtenerJdbcTemplate().query(MonitoreoSupplier.get(), new MonitoreoMapper());
		} catch (DataAccessException | SQLException e) {
	         logger.error("Erro al accesar a los datos de la base de datos: "+e);
	         throw new BusinessException("Error en el proceso de negocio "+ e);
		} catch (IllegalArgumentException e) {
	         logger.error("Erro, Indice fuera de los límites: "+e);
	        throw new BusinessException("Error en el proceso de negocio ");
		} catch (NamingException e) {
			  logger.error("Error en la acción solicitada: "+e);
	        throw new BusinessException("Error en el proceso de negocio ");
		} 
		return monitoreo;
	}
	
	


}
