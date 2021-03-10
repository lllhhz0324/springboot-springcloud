package com.lhz.servicecms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.servicecms.entity.CrmBanner;

import java.util.List;

/**
 * @author: lhz
 * @Description: banner 服务接口
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * @author: lhz
     * @Description: 查询所有banner
     */
    List<CrmBanner> selectAllBanner();
}
