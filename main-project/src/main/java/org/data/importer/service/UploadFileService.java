package org.data.importer.service;

import java.io.InputStream;
import java.util.List;

import org.data.importer.entity.Fabricacion;

public interface UploadFileService {

	void upload(InputStream input);

	List<Fabricacion> findAll();
	
}
