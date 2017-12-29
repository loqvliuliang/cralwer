package com.example.demo.controller;

import com.example.demo.domain.Attachment;
import com.example.demo.domain.Good;
import com.example.demo.service.AttachmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 刘亮 on 2017/12/27.
 */
@RestController
@RequestMapping("/api/attachment")
public class AttachmentController  {

    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }


    @RequestMapping(value = "/upload/batch")
    public ResponseEntity<List<Attachment>> batchUpload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> files = multipartHttpServletRequest.getFiles("file");
        String attachmentType = multipartHttpServletRequest.getParameter("attachmentType");
        Long userId = Long.parseLong(multipartHttpServletRequest.getParameter("userId"));
        Long objectId = multipartHttpServletRequest.getParameter("objectId") ==null?null:Long.parseLong(multipartHttpServletRequest.getParameter("objectId"));

        return ResponseEntity.ok(attachmentService.uploadBath(files,attachmentType,objectId,userId,null));
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<List<Attachment>> upload(@RequestParam String attachmentType,
                                                   @RequestParam Long userId,
                                                   @RequestParam("file") MultipartFile[] uploadfiles,
                                                   @RequestParam("good_name") String good_name,
                                                   @RequestParam("good_price") String good_price,
                                                   @RequestParam("good_desc") String good_desc
                                                   ){
        Good good = new Good();
        good.setGood_desc(good_desc);
        good.setGood_name(good_name);
        good.setGood_price(good_price);
        return ResponseEntity.ok(attachmentService.uploadBath(Arrays.asList(uploadfiles),attachmentType,null,userId,good));
    }

}
