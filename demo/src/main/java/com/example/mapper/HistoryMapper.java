package com.example.mapper;

import com.example.domain.History;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 小假
* @description 针对表【History】的数据库操作Mapper
* @createDate 2024-11-28 09:52:45
* @Entity com.example.domain.History
*/
@Mapper
public interface HistoryMapper extends BaseMapper<History> {

}




