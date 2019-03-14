package application.dao;

import application.model.BasicKV;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface SKVDao {
    @Select("select * from SKV where skey=#{key}")
    @Results({
            @Result(property = "key", column = "skey"),
            @Result(property = "value", column = "svalue")
    })
    BasicKV selectKV(String key);
}
