package com.bancolombia.AuthenticSoporte.Business;

import java.util.List;

import com.bancolombia.AuthenticSoporte.Entity.Monitoreo;
import com.bancolombia.AuthenticSoporte.Exception.BusinessException;

public interface IMonitoreoBusiness {
	
	public List<Monitoreo> generarInformeMonitoreo() throws BusinessException;

}
