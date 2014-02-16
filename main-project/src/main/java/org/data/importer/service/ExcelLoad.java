package org.data.importer.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLoad {

	public List<Object[]> load(InputStream input) {
		List<Object[]> list = new ArrayList<>();
		try {
			Workbook workbook = WorkbookFactory.create(input);
			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> itRow = sheet.iterator();

			itRow.next();

			while (itRow.hasNext()) {
				Row row = itRow.next();

				Iterator<Cell> itCell = row.iterator();
				List<Object> values = new ArrayList<>();
				while (itCell.hasNext()) {
					Cell cell = itCell.next();

					Object val = null;
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						val = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						val = cell.getNumericCellValue();
						break;
					default:
						val = null;
					}
					values.add(val);
				}
				list.add(values.toArray(new Object[0]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
