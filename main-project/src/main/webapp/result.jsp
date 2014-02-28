<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spreadsheet result</title>
</head>
<body>
	<p>There have been uploaded ${tolalUploadedRows} rows, in total there are ${totalRows} rows.<p>

	<form action="uploadFile" method="post" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="Upload">
	</form>
<br/>
	<table border="1">
		<thead>
			<tr>
				<td>Numero de Serie</td>
				<td>Codigo</td>
				<td>Modelo</td>
				<td>Fecha de fabricacion</td>
				<td>proveedor</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="f" items="${list}">
				<tr>
					<td>${f.numeroSerie}</td>
					<td>${f.codigo}</td>
					<td>${f.modelo}</td>
					<td>${f.fechaFabricacion}</td>
					<td>${f.proveedor}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>