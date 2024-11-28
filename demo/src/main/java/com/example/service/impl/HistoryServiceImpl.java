package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.History;
import com.example.service.HistoryService;
import com.example.mapper.HistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 小假
* @description 针对表【History】的数据库操作Service实现
* @createDate 2024-11-28 09:52:45
*/
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History>
    implements HistoryService{

}




