package com.minqing.service.mapper;

import com.minqing.service.entity.Merchant;
import java.util.List;

public interface MerchantMapper {
    int deleteByPrimaryKey(int id);

    int insert(Merchant record);

    Merchant selectByPrimaryKey(int id);

    List<Merchant> selectAll();
    
    List<Merchant> selectMerchant(String accountNo);

    int updateByPrimaryKey(Merchant record);
}