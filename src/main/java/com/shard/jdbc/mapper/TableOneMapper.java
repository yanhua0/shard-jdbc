package com.shard.jdbc.mapper;

import com.shard.jdbc.entity.TableOne;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TableOneMapper {

    TableOne selectOneByPhone(@Param("phone") String phone) ;

    int deleteByPrimaryKey(Integer id);

    int insert(TableOne record);

    int insertSelective(TableOne record);



    List<TableOne> table(List<String> phone);

    TableOne selectByPrimaryKey(Integer id);


    int selectCount();
}