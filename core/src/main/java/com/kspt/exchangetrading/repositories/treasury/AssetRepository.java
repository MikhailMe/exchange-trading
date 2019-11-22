package com.kspt.exchangetrading.repositories.treasury;

import com.kspt.exchangetrading.models.treasury.Asset;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends CommonRepository<Asset> {
}
