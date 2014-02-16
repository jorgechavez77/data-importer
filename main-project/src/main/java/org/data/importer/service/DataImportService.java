package org.data.importer.service;

import java.io.InputStream;
import java.util.List;

public class DataImportService {

	public List<FabricacionExcel> map(InputStream input) {
		ExcelLoad excelLoad = new ExcelLoad();
		List<Object[]> list = excelLoad.load(input);
		ExcelBuilder builder = new ExcelBuilder();
		List<FabricacionExcel> resultList = builder.build(list);

		return resultList;
	}

}
