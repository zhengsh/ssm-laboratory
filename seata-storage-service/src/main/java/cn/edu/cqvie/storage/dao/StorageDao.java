package cn.edu.cqvie.storage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {

    void decrease(@Param("productId") Long productId,
                  @Param("count") Integer count);
}
