package spring.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import spring.mybatis.entity.KV;
import spring.mybatis.entity.KVExample;

@Mapper
public interface KVMapper {
    long countByExample(KVExample example);

    int deleteByExample(KVExample example);

    int deleteByPrimaryKey(String key);

    int insert(KV record);

    int insertSelective(KV record);

    List<KV> selectByExampleWithBLOBs(KVExample example);

    List<KV> selectByExample(KVExample example);

    KV selectByPrimaryKey(String key);

    int updateByExampleSelective(@Param("record") KV record, @Param("example") KVExample example);

    int updateByExampleWithBLOBs(@Param("record") KV record, @Param("example") KVExample example);

    int updateByExample(@Param("record") KV record, @Param("example") KVExample example);

    int updateByPrimaryKeySelective(KV record);

    int updateByPrimaryKeyWithBLOBs(KV record);
}