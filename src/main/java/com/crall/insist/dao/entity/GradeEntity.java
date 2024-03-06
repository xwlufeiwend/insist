package com.crall.insist.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "grade")
public class GradeEntity {
    private Integer id;
    private Integer studentId;

    @TableField
    private String subject;
    private Integer grade;
}
