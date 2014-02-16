package org.data.importer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.data.importer.entity.Fabricacion;
import org.data.importer.repository.FabricacionDao;
import org.data.importer.service.DataImportService;
import org.data.importer.service.ExcelBuilder;
import org.data.importer.service.FabricacionExcel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@RunWith(BlockJUnit4ClassRunner.class)
public class ExcelTest {

	private final static Logger LOG = LoggerFactory.getLogger(ExcelTest.class);

	@Test
	public void test1() throws InvalidFormatException, IOException {
		InputStream is = new FileInputStream("/Test/Fabricacion.xlsx");

		DataImportService service = new DataImportService();
		List<FabricacionExcel> list = service.map(is);
		LOG.info(list + "");
	}

	@Test
	public void test2() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"org/data/importer/config/spring-config.xml");

		Assert.assertNotNull(context);

		FabricacionDao fabricacionDao = (FabricacionDao) context
				.getBean("fabricacionDao");

		Assert.assertNotNull(fabricacionDao);

		Fabricacion fabricacion = fabricacionDao.findById(1L);

		Assert.assertNotNull(fabricacion);

		LOG.info("fabricacion {}, {}", fabricacion.getCodigo(),
				fabricacion.getModelo());

	}

	@Test
	public void test3() throws FileNotFoundException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"org/data/importer/config/spring-config.xml");

		Assert.assertNotNull(context);

		FabricacionDao fabricacionDao = (FabricacionDao) context
				.getBean("fabricacionDao");

		Assert.assertNotNull(fabricacionDao);

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
