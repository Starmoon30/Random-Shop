package org.frisbeemall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.frisbeemall.domain.Shelf;

import java.util.List;

@Mapper
public interface ShelfDao {
    @Select("SELECT * FROM Shelf")
    List<Shelf> selectAll();

    @Select("SELECT * FROM Shelf WHERE ShID = #{shid}")
    Shelf selectById(@Param("shid") int shid);

    @Insert("INSERT INTO Shelf (GID, ShState) VALUES (#{gid}, #{shstate})")
    int insert(Shelf shelf);

    @Update("UPDATE Shelf SET ShState = #{shstate} WHERE ShID = #{shid}")
    int updateById(Shelf shelf);
}