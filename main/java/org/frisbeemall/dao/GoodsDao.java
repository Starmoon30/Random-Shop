package org.frisbeemall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.frisbeemall.domain.Goods;

import java.util.List;

@Mapper
public interface GoodsDao {
    @Select("SELECT * FROM Goods")
    List<Goods> selectAll();

    @Select("SELECT * FROM Goods WHERE GID = #{gid}")
    Goods selectById(@Param("gid") int gid);

    @Insert("INSERT INTO Goods (GName, GDesc, GValue, GPic) VALUES (#{gname}, #{gdesc}, #{gvalue}, #{gpic})")
    int insert(Goods goods);

    @Update("UPDATE Goods SET GName = #{gname}, GDesc = #{gdesc}, GValue = #{gvalue}, GPic = #{gpic} WHERE GID = #{gid}")
    int updateById(Goods goods);

    @Delete("DELETE FROM Goods WHERE GID = #{gid}")
    int deleteById(@Param("gid") int gid);
}