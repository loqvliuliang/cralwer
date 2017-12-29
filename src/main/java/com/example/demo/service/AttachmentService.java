package com.example.demo.service;

import com.cloudhelios.atlantis.service.BaseService;
import com.example.demo.domain.Attachment;
import com.example.demo.domain.Good;
import com.example.demo.enums.AttachmentType;
import com.example.demo.exception.BizException;
import com.example.demo.mapper.AttachmentMapper;
import com.example.demo.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by 刘亮 on 2017/12/27.
 */
@Service
public class AttachmentService extends BaseService<AttachmentMapper,Attachment> {
    @Value("${attachment.good.path:}")
    private String path;

    @Value("${attachment.good.prefix:}")
    private String prefix;

    private final GoodService goodService;

    private final AttachmentMapper attachmentMapper;

    public AttachmentService(GoodService goodService, AttachmentMapper attachmentMapper) {
        this.goodService = goodService;
        this.attachmentMapper = attachmentMapper;
    }

    //批量上传附件
    public List<Attachment> uploadBath(List<MultipartFile> files, String attachmentsType, Long objectId, Long userId, Good good){

        AttachmentType attachmentType = null;
        if(attachmentsType.equals(AttachmentType.GOOD.toString())){
            attachmentType = AttachmentType.GOOD;
        }else {
            throw new BizException(ResponseCode.ATTACHMENT_TYPE_NOT_NULL);
        }

        List<Attachment> attachments = new ArrayList<Attachment>();
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        List<String> fullNames = new ArrayList<>();
        for (int i = 0; i < files.size(); ++i) {

            file = files.get(i);
            if (!file.isEmpty()) {

                byte[] bytes = new byte[0];
                try {
                    bytes = file.getBytes();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String fileOriginalFilename = file.getOriginalFilename();
                    String fileSuffix= fileOriginalFilename.substring(fileOriginalFilename.lastIndexOf("."));
                    String fileName = fileOriginalFilename.substring(0,fileOriginalFilename.indexOf("."));
                    String fullName = fileName+ UUID.randomUUID()+fileSuffix;
                    fullNames.add(fullName);
                    Attachment attachment = new Attachment();
                    attachment.setAttachmentOid(UUID.randomUUID().toString())
                            .setFileName(file.getOriginalFilename())
                            .setSize(file.getSize());
                    String fileUrl = getFileUrl(attachment, attachmentType);
                    attachment.setFileUrl(fileUrl).setLink(fileUrl);
                    attachment.setVersionNumber(1);
                    //oauth获取当前操作用户（待完善）
                    attachment.setCreatedBy(userId);
                    attachment.setLastUpdatedBy(userId);
                    attachment.setCreatedDate(ZonedDateTime.now());
                    attachment.setLastUpdatedDate(ZonedDateTime.now());
                    attachment.setIsEnabled(true);
                    attachment.setIsDeleted(false);
                    this.insert(attachment);
                    attachments.add(attachment);

                    good.setGood_img(fileUrl);
                    good.setGood_com(0);
                    good.setGood_num(255);
                    goodService.BatchInsertOrUpdate(Arrays.asList(good));
                    String filePath = fileUrl.substring(this.prefix.length(),fileUrl.lastIndexOf("/")+1);
                    String fileLink = fileUrl.substring(this.prefix.length());
                    //路径不存在，创建路径
                    if (!(new File(filePath).isDirectory())) {
                        new File(filePath).mkdirs();
                    }
                try {
                    stream = new BufferedOutputStream(new FileOutputStream(fileLink));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    throw new BizException(ResponseCode.BUDGET_JOURNAL_HEAD_UPLOAD_ERR);
                }
            } else {
                throw new BizException(ResponseCode.BUDGET_JOURNAL_HEAD_FILE_IS_NULL);
            }
        }

        /*如果是修改，将这些附件oid直接保存到对应的表里
        * 如果事新增，则不管
        * */
//        if(objectId !=null){
//            List<String> attachmentOids = new ArrayList<>();
//            attachments.forEach(
//                    attachment->{
//                        attachmentOids.add(attachment.getAttachmentOid());
//                    }
//            );
//            switch (attachmentType){
//                case GOOD:
//                {
//                    BudgetJournalHeader header = new BudgetJournalHeader();
//                    BudgetJournalHeaderAdapter.AttachmentOidToJsonString(attachmentOids,header);
//                    header.setId(objectId);
//                    budgetJournalHeaderMapper.updateById(header);
//                }
//            }
//        }
        return attachments;
//        return StringUtils.join(fullNames,",");
    }

    private String getFileUrl(Attachment attachment, AttachmentType attachmentType){
        String fileUrl =path;
        switch (attachmentType){
            case GOOD:
                fileUrl =this.prefix + this.path+attachment.getAttachmentOid()+"-"+attachment.getFileName();
        }
        return fileUrl;
    }





}
