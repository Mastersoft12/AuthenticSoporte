package com.bancolombia.AuthenticSoporte.Entity;

import java.io.Serializable;

public class Monitoreo implements Serializable {

	private static final long serialVersionUID = 2294212492516746788L;

	private double promedio;
	
	private String Descripcion;
	
	private int cantidad;
	
	public Monitoreo(){		
		
	}

	public Monitoreo(double promedio, String descripcion, int cantidad) {
		this.promedio = promedio;
		Descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public double getPromedio() {
		return promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
		
	
}
