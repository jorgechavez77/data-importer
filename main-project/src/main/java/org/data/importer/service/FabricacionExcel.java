package org.data.importer.service;

import org.data.importer.annotation.ExcelAttribute;
import org.data.importer.annotation.ExcelClass;

@ExcelClass(name = "fabricacion")
public class FabricacionExcel {

	@ExcelAttribute(rowOrder = 0)
	private String numeroSerie;

	@ExcelAttribute(rowOrder = 1)
	private Double codigo;

	@ExcelAttribute(rowOrder = 2)
	private String modelo;

	@ExcelAttribute(rowOrder = 3)
	private Double fechaFabricacion;

	@ExcelAttribute(rowOrder = 4)
	private String proveedor;

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Double getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(Double fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public Double getCodigo() {
		return codigo;
	}

	public void setCodigo(Double codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "{numeroSerie: " + numeroSerie + ", codigo: " + codigo
				+ ", modelo: " + modelo + ", fechaFabricacion: "
				+ fechaFabricacion + ", proveedor: " + proveedor + "}";
	}

}
