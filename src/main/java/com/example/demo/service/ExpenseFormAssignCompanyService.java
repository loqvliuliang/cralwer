package com.example.demo.service;

import com.cloudhelios.atlantis.service.BaseService;
import com.example.demo.domain.ExpenseFormAssignCompany;
import com.example.demo.exception.BizException;
import com.example.demo.mapper.ExpenseFormAssignCompanyMapper;
import com.sun.tools.javac.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.ZonedDateTime;

/**
 * Created by 刘亮 on 2017/11/16.
 */
@Service
public class ExpenseFormAssignCompanyService extends BaseService<ExpenseFormAssignCompanyMapper,ExpenseFormAssignCompany> {
    private final ExpenseFormAssignCompanyMapper expenseFormAssignCompanyMapper;

    public ExpenseFormAssignCompanyService(ExpenseFormAssignCompanyMapper expenseFormAssignCompanyMapper) {
        this.expenseFormAssignCompanyMapper = expenseFormAssignCompanyMapper;
    }

    //批量新建或修改表单分配公司
    public List<ExpenseFormAssignCompany> insertOrUpdateBatch(List<ExpenseFormAssignCompany> list){

        if(CollectionUtils.isEmpty(list)){
//            throw new BizException();
        }
        list.forEach(expenseFormAssignCompany -> {
            if(expenseFormAssignCompany.getId()==null){//新增
                expenseFormAssignCompany.setLastUpdatedBy(1L);
                expenseFormAssignCompany.setCreatedBy(1L);
                expenseFormAssignCompany.setCreatedDate(ZonedDateTime.now());
                expenseFormAssignCompany.setLastUpdatedDate(ZonedDateTime.now());
                this.insert(expenseFormAssignCompany);
            }else {//更新
                expenseFormAssignCompany.setLastUpdatedDate(ZonedDateTime.now());
                expenseFormAssignCompany.setLastUpdatedBy(1L);
                this.updateById(expenseFormAssignCompany);
            }
        });
        return list;
    }



    //根据id批量删除表单分配公司
    public Boolean deleteBatchById(List<Long> ids){
        return this.deleteBatchIds(ids);
    }


    //查询当前表单下已分配的公司
//    public List<Company>









}
