package org.data.importer.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DateUtil;
import org.data.importer.annotation.ExcelAttribute;
import org.data.importer.entity.Fabricacion;

public class ExcelBuilder {

	public List<FabricacionExcel> build(List<Object[]> list) {
		Class<FabricacionExcel> clazz = FabricacionExcel.class;

		List<FabricacionExcel> resultList = new ArrayList<>();

		for (Object[] values : list) {
			try {
				FabricacionExcel fabricacionExcel = clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();

				for (Field field : fields) {
					field.setAccessible(true);
					ExcelAttribute attribute = field
							.getAnnotation(ExcelAttribute.class);
					if (attribute != null) {
						int rowOrder = attribute.rowOrder();
						field.set(fabricacionExcel, values[rowOrder]);
					}
				}
				resultList.add(fabricacionExcel);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	public Fabricacion parseFabricacion(FabricacionExcel excel) {
		Fabricacion fabricacion = new Fabricacion();

		fabricacion.setCodigo(excel.getCodigo().longValue());
		fabricacion.setFechaFabricacion(DateUtil.getJavaDate(excel.getFechaFabricacion()
				.longValue()));
		fabricacion.setModelo(excel.getModelo());
		fabricacion.setNumeroSerie(excel.getNumeroSerie());
		fabricacion.setProveedor(excel.getProveedor());

		return fabricacion;
	}

	public List<Fabricacion> parseFabricacion(List<FabricacionExcel> excelList) {
		List<Fabricacion> list = new ArrayList<>();
		for (FabricacionExcel excel : excelList) {
			list.add(parseFabricacion(excel));
		}

		return list;
	}

}
