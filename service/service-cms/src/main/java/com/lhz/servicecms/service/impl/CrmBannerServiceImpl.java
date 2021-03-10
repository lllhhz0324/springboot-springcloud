package com.lhz.servicecms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhz.servicecms.entity.CrmBanner;
import com.lhz.servicecms.mapper.CrmBannerMapper;
import com.lhz.servicecms.service.CrmBannerService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author: lhz
 * @Description: banner 服务实现类
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    /**
     * @author: lhz
     * @Description: 查询所有banner
     */
    @Override
    public List<CrmBanner> selectAllBanner() {

        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}
