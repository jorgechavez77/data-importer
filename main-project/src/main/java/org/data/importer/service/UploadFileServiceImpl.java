package org.data.importer.service;

import java.io.InputStream;
import java.util.List;

import org.data.importer.entity.Fabricacion;
import org.data.importer.repository.FabricacionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("uploadFileService")
public class UploadFileServiceImpl implements UploadFileService {

	@Autowired
	private FabricacionDao fabricacionDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void upload(InputStream input) {
		DataImportService service = new DataImportService();
		List<FabricacionExcel> excelList = service.map(input);

		ExcelBuilder builder = new ExcelBuilder();
		List<Fabricacion> list = builder.parseFabricacion(excelList);

		for (Fabricacion fabricacion : list) {
			fabricacionDao.save(fabricacion);
		}
	}

}
