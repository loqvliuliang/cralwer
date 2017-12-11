package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 刘亮 on 2017/11/16.
 */
@Data
@Builder
@TableName("exp_form_assign_company")
public class ExpenseFormAssignCompany extends ExpenseDomainObject {
    @NotNull
    @TableField("form_oid")
    private String formOid;
    @NotNull
    @TableField("company_id")
    private Long companyId;
}
