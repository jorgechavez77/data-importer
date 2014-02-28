package org.data.importer.service;

import java.io.InputStream;
import java.util.List;

import org.data.importer.entity.Fabricacion;

public interface UploadFileService {

	int upload(InputStream input);

	List<Fabricacion> findAll();
	
}
