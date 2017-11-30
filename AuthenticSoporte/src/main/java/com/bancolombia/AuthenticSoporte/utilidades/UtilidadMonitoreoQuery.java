package com.bancolombia.AuthenticSoporte.utilidades;

public class UtilidadMonitoreoQuery {
	
	  private UtilidadMonitoreoQuery() {
		    throw new IllegalStateException("Utility class");
	  }
	
	
	public static final String queryMonitoreo(){
		StringBuilder query = new StringBuilder(); 
		query.append(" SELECT 'Aprobadas' AS DESCRIPCION,COUNT (*) AS CANTIDAD,");
		query.append(" AVG (EXTRACT (SECOND FROM (EML.EML_RESPONSE_TIME - LOG.TRL_SYSTEM_TIMESTAMP))) AS PROMEDIO");
		query.append(" FROM bcolombia_owner.TRANSACTION_LOG LOG, bcolombia_owner.ENDPOINT_MESSAGE_LOG EML");
		query.append(" WHERE LOG.TRL_MESSAGE_UID = EML.EML_MESSAGE_UID");
		query.append(" AND LOG.TRL_SYSTEM_TIMESTAMP BETWEEN TRUNC(SYSDATE)");
		query.append(" AND SYSDATE");
		query.append(" AND LOG.TRL_ORIGIN_RESULT_CODE = '00'");
		query.append(" AND EML.EML_REQ_CONN_URI LIKE '%/Redeban/%'");
		query.append(" GROUP BY 'Aprobadas'");
		return query.toString();
	}
	
	public static final String queryMonitoreoRechazada(){
		StringBuilder query = new StringBuilder(); 
		query.append("SELECT 'Rechazadas' AS DESCRIPCION,COUNT (*) AS CANTIDAD,");
		query.append(" AVG (EXTRACT (SECOND FROM (EML.EML_RESPONSE_TIME - LOG.TRL_SYSTEM_TIMESTAMP))) AS PROMEDIO");
		query.append(" FROM bcolombia_owner.TRANSACTION_LOG LOG, bcolombia_owner.ENDPOINT_MESSAGE_LOG EML");
		query.append(" WHERE LOG.TRL_MESSAGE_UID = EML.EML_MESSAGE_UID");
		query.append(" AND LOG.TRL_SYSTEM_TIMESTAMP BETWEEN TRUNC(SYSDATE)");
		query.append(" AND SYSDATE ");
		query.append(" AND LOG.TRL_ORIGIN_RESULT_CODE <> '00'");
		query.append(" AND EML.EML_REQ_CONN_URI LIKE '%/Redeban/%'");
		query.append(" GROUP BY 'Rechazadas'");
		return query.toString();
	}

}
