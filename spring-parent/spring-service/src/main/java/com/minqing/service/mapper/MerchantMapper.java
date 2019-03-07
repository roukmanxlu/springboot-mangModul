package com.minqing.service.mapper;

import com.minqing.service.entity.Merchant;
import java.util.List;

public interface MerchantMapper {
    int deleteByPrimaryKey(int id);

    int insert(Merchant record);

    Merchant selectByPrimaryKey(int id);

    List<Merchant> selectAll();

    int updateByPrimaryKey(Merchant record);
}