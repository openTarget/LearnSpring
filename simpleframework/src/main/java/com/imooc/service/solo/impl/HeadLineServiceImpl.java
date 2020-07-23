package com.imooc.service.solo.impl;

import com.imooc.entity.bo.HeadLine;
import com.imooc.entity.dto.Result;
import com.imooc.service.solo.HeadLineService;

import java.util.List;

/**
 * 〈一句话功能简述>
 * 〈〉
 *
 * @Author lhn
 * @Create 2020/7/16
 * @Since 1.0.0
 */
public class HeadLineServiceImpl implements HeadLineService {

    @Override
    public Result <Boolean> addHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result <Boolean> removeHeadLine(int headLineId) {
        return null;
    }

    @Override
    public Result <Boolean> modifyHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result <HeadLine> queryHeadLineById(int headLineId) {
        return null;
    }

    @Override
    public Result <List <HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize) {
        return null;
    }
}