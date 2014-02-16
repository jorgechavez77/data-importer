package org.data.importer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.data.importer.entity.Fabricacion;
import org.data.importer.repository.FabricacionDao;
import org.data.importer.service.DataImportService;
import org.data.importer.service.ExcelBuilder;
import org.data.importer.service.ExcelLoad;
import org.data.importer.service.FabricacionExcel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "/org/data/importer/config/spring-config.xml")
@TransactionConfiguration(defaultRollback = false)
public class SpringTest extends AbstractTransactionalJUnit4SpringContextTests {

	private final static Logger LOG = LoggerFactory.getLogger(ExcelLoad.class);

	@Autowired
	private FabricacionDao fabricacionDao;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void test() throws FileNotFoundException {
		LOG.info("Test");
		InputStream is = new FileInputStream("/Test/Fabricacion.xlsx");

		DataImportService service = new DataImportService();
		List<FabricacionExcel> excelList = service.map(is);

		ExcelBuilder builder = new ExcelBuilder();
		List<Fabricacion> list = builder.parseFabricacion(excelList);

		for (Fabricacion fabricacion : list) {
			fabricacionDao.save(fabricacion);
		}

	}

}
