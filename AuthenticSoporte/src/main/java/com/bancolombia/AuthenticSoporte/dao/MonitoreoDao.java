package com.bancolombia.AuthenticSoporte.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.bancolombia.AuthenticSoporte.Entity.Monitoreo;
import com.bancolombia.AuthenticSoporte.Exception.BusinessException;
import com.bancolombia.AuthenticSoporte.bean.IDatasource;
import com.bancolombia.AuthenticSoporte.mapper.MonitoreoMapper;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


@Component("monitoreoDao")
public class MonitoreoDao implements IMonitoreoDao {
	
	@Autowired
	@Qualifier("dataSourceO")
	private IDatasource dataSource;
	
	@Autowired
	@Qualifier("jdbcTemplateConexion")
	private JdbcTemplateConexion jdbcTemplateConexion;
	

	
	public List<Monitoreo> obtenerMonitoreo() throws BusinessException{
		String sql = "";
		List<Monitoreo> monitoreo = null;
		sql = queryMonitoreo();		
		try {
			 monitoreo = jdbcTemplateConexion.obtenerJdbcTemplate().query(sql, new MonitoreoMapper());
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
	
	public String queryMonitoreo(){
		StringBuilder query = new StringBuilder(); 
		query.append(" SELECT 'Aprobadas' AS DESCRIPCION,COUNT (*) AS CANTIDAD,");
		query.append(" AVG (EXTRACT (SECOND FROM (EML.EML_RESPONSE_TIME - LOG.TRL_SYSTEM_TIMESTAMP))) AS PROMEDIO");
		query.append(" FROM bcolombia_owner.TRANSACTION_LOG LOG, bcolombia_owner.ENDPOINT_MESSAGE_LOG EML");
		query.append(" WHERE LOG.TRL_MESSAGE_UID = EML.EML_MESSAGE_UID");
		query.append(" AND LOG.TRL_SYSTEM_TIMESTAMP BETWEEN TO_DATE ('2017-11-23 000000','yyyy/mm/dd HH24MISS')");
		query.append(" AND TO_DATE ('2017-11-23 235959','yyyy/mm/dd HH24MISS')");
		query.append(" AND LOG.TRL_ORIGIN_RESULT_CODE = '00'");
		query.append(" AND EML.EML_REQ_CONN_URI LIKE '%/Redeban/%'");
		query.append(" GROUP BY 'Aprobadas'");
		return query.toString();
	}


}
