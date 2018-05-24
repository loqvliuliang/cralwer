//package com.example.demo.utils;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.shunicom.marketing.report.util.HttpsClientUtil;
//@RestController
//@RequestMapping(value = "/excel")
//public class ExcelController {
//
//	@RequestMapping(value="exporList")
//	public void exporList(HttpServletRequest request,HttpServletResponse response){
//		try{
//			request.setCharacterEncoding("utf-8");
//			response.setCharacterEncoding("utf-8");
//			response.setContentType("application/vnd.ms-excel");
//			OutputStream out = response.getOutputStream();
//			response.setHeader("Content-Disposition", "attachment; filename=TestExcel.xls ");
//			//创建workbook工作薄
//			Workbook workbook = new HSSFWorkbook();
//			//创建工作表
//			Sheet sheet = workbook.createSheet("你我贷注册信息");
//			//设置单元格样式
//			HSSFCellStyle hssfCellStyle = (HSSFCellStyle) workbook.createCellStyle();
//			hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中显示
//			hssfCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//纵向居中
//			//创建行
//			Row row = sheet.createRow(0);
//			//创建单元格
//		    Cell cell = row.createCell(0);
//		    //设置第一行第一格的值
//		    cell.setCellValue("姓名");
//		    //设置单元格的文本居中显示
//		    cell.setCellStyle(hssfCellStyle);
//		    //创建单元格
//		    Cell cell1 = row.createCell(1);
//		    //设置第一行第一格的值
//		    cell1.setCellValue("性别");
//		    cell1.setCellStyle(hssfCellStyle);
//		    //创建单元格
//		    Cell cell2 = row.createCell(2);
//		    //设置第一行第一格的值
//		    cell2.setCellValue("年龄");
//		    cell2.setCellStyle(hssfCellStyle);
//		    //创建单元格
//		    Cell cell3 = row.createCell(3);
//		    //设置第一行第一格的值
//		    cell3.setCellValue("家庭住址");
//		    cell3.setCellStyle(hssfCellStyle);
//		    //调用你我贷数据写入Excel中
//
//
//
//
//
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//		/*Map<String, String> paramMaps = new HashMap<String, String>();
//		paramMaps.put("accessCode", "7af7f873-e262-4fa3-bf62-fcae1050d2f0");
//		paramMaps.put("jsonParam", "{\"appId\":\"APItest\",\"appKey\":\"0f2bea71c4fa657986b68852fc224b06\"}");
//		try {
//			System.out.println(HttpsClientUtil.sendPost("http://api.niwodai.org/interface/callHttpInterfaces.do", paramMaps, "UTF-8"));
//		} catch (KeyManagementException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}*/
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("accessCode", "015ee647-26d2-41e7-914b-2c100120f37f");
//		paramMap.put("jsonParam", "{\"nwd_ext_aid\":\"5020160023536001\",\"sourceId\":\"\",\"startTime\":\"20180313000000\",\"endTime\":\"20180412235959\",\"phone\":\"\",\"pageNo\":10,\"token\":\"bd84c279-4b32-4eb0-bc37-6f1e268ea5c0\"}");
//		try {
//			System.out.println(HttpsClientUtil.sendPost("http://api.niwodai.org/interface/callHttpInterfaces.do", paramMap, "UTF-8"));
//		} catch (KeyManagementException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void pickFormation(){
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("accessCode", "015ee647-26d2-41e7-914b-2c100120f37f");
//		paramMap.put("jsonParam", "{\"nwd_ext_aid\":\"5020160023536001\",\"sourceId\":\"\",\"startTime\":\"20180313000000\",\"endTime\":\"20180412235959\",\"phone\":\"\",\"pageNo\":10,\"token\":\"bd84c279-4b32-4eb0-bc37-6f1e268ea5c0\"}");
//		try {
//			System.out.println(HttpsClientUtil.sendPost("http://api.niwodai.org/interface/callHttpInterfaces.do", paramMap, "UTF-8"));
//		} catch (KeyManagementException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
