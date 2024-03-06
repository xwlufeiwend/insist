package com.crall.insist;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crall.insist.dao.entity.GradeEntity;
import com.crall.insist.dao.mapper.GradeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMybatisPlus {

    @Autowired
    private GradeMapper gradeMapper;

    @Test
    public void test(){
        LambdaQueryWrapper<GradeEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(GradeEntity::getSubject, "math");
        List<GradeEntity> gradeEntities = gradeMapper.selectList(wrapper);
        gradeEntities.forEach(System.out::println);
    }
}
