//package com.example.demo.controller;
//
//import lombok.Data;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Created by 刘亮 on 2018/4/19.
// */
//@RestController
//@RequestMapping("/api/exp")
//public class ExportController {
//
//    @PostMapping("/export")
//    public ResponseEntity<byte[]> budgetBalanceResultExport(@RequestBody @Valid ExportDTO exportDTO) throws Exception {
//        String excelVersion = exportDTO.getExcelVersion();
//        String fileName;
//        if("2003".equals(excelVersion)){
//            fileName = new String("数据导出.xls".getBytes(), Charset.forName("iso-8859-1"));
//        } else if ("2007".equals(excelVersion)){
//            fileName = new String("数据导出.xlsx".getBytes(), Charset.forName("iso-8859-1"));
//        } else {
//            throw new RuntimeException("只支持2003，2007版本");
//        }
//
//        List<BudgetBalanceQueryResultLineDTO> resultList = balanceQueryResultService.budgetBalanceResultExport(exportDTO.getConditionId());
//        byte[] bytes = balanceQueryResultService.exportResult(resultList,exportDTO);
//        return null;
//    }
//
//
//    public byte[] exportResult(List<BudgetBalanceQueryResultLineDTO> resultList, BudgetBalanceQueryExportDTO exportDTO) {
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        Workbook workBook;
//        byte[] bytes;
//        if ("2003".equals(exportDTO.getExcelVersion())) {
//            workBook = new HSSFWorkbook();
//        } else {
//            workBook = new XSSFWorkbook();
//        }
//        createExcel(workBook, resultList,exportDTO);
//        try {
//            workBook.write(bos);
//            bos.flush();
//            queryObject.remove();
//            bytes = bos.toByteArray();
//        } catch (IOException e) {
//            throw new BizException(RespCode.READ_FILE_FAILED);
//        } finally {
//            try {
//                bos.close();
//                workBook.close();
//            } catch (IOException e) {
//                throw new BizException(RespCode.READ_FILE_FAILED);
//            }
//        }
//        queryObject.remove();
//        return bytes;
//    }
//
//
//    @Data
//    public class ExportDTO {
//        @NotNull
//        private String excelVersion;//excel版本 2003/2007
//
//        private HashMap<Integer, String> columnFiledMap;//列名
//    }
//}
//
//
