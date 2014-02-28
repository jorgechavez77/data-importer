package org.data.importer.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.data.importer.entity.Fabricacion;
import org.data.importer.service.ExcelLoad;
import org.data.importer.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet(name = "uploadFile", urlPatterns = "/uploadFile")
@MultipartConfig
public class UploadFileServlet extends HttpServlet {

	@Autowired
	private UploadFileService uploadFileService;

	private static final long serialVersionUID = 1L;

	private final static Logger LOG = LoggerFactory.getLogger(ExcelLoad.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(config.getServletContext());
		this.uploadFileService = (UploadFileService) context
				.getBean("uploadFileService");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Part filePart = req.getPart("file");
		InputStream input = filePart.getInputStream();

		LOG.info("upload file " + filePart);

		int tolalUploadedRows = uploadFileService.upload(input);

		List<Fabricacion> list = uploadFileService.findAll();

		int totalRows = list.size();

		req.setAttribute("list", list);
		LOG.info("totalRows: {}", totalRows);
		LOG.info("tolalUploadedRows: {}", tolalUploadedRows);

		req.setAttribute("totalRows", list.size());
		req.setAttribute("tolalUploadedRows", tolalUploadedRows);

		req.getRequestDispatcher("result.jsp").forward(req, resp);

	}

}
