package org.data.importer.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.data.importer.service.ExcelLoad;
import org.data.importer.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@WebServlet(name = "uploadFile", urlPatterns = "/uploadFile")
@MultipartConfig
public class UploadFileServlet extends HttpServlet {

	@Autowired
	private UploadFileService uploadFileService;

	private static final long serialVersionUID = 1L;

	private final static Logger LOG = LoggerFactory.getLogger(ExcelLoad.class);

	/*
	 * http://docs.oracle.com/javaee/6/tutorial/doc/glraq.html
	 * Pending the Spring configuration with the web app
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Part filePart = req.getPart("file");
		InputStream input = filePart.getInputStream();

		LOG.info("upload file " + filePart);

		uploadFileService.upload(input);

	}

}
