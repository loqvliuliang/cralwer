package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.cloudhelios.atlantis.domain.DomainObject;
import lombok.Data;

/**
 * Created by 刘亮 on 2017/11/16.
 */
public class ExpenseDomainObject extends DomainObject {
    @TableField(strategy = FieldStrategy.NOT_NULL,fill = FieldFill.INSERT_UPDATE)
    private Integer versionNumber;

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }
}
