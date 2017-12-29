package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

/**
 * Created by 刘亮 on 2017/11/20.
 */
@Data
@TableName("attachment")
@Accessors(chain = true)
public class Attachment  {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @TableField("attachment_oid")
    private String attachmentOid;
    @TableField("file_name")
    private  String fileName;
    @TableField("file_url")
    private String fileUrl;
    private String link;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long size;
    @TableField("version_number")
    private Integer versionNumber;
    @TableField("is_enabled")
    protected Boolean isEnabled;
    @TableField(value = "is_deleted")
    protected Boolean isDeleted;
    @TableField("created_date")
    protected ZonedDateTime createdDate;
    @TableField("created_by")
    protected Long createdBy;
    @TableField("last_updated_date")
    protected ZonedDateTime lastUpdatedDate;
    @TableField("last_updated_by")
    protected Long lastUpdatedBy;

}
