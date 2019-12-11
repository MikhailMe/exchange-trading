package com.kspt.exchangetrading.repositories.treasury;

import com.kspt.exchangetrading.models.treasury.Asset;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends CommonRepository<Asset> {

    List<Asset> findByClientId(@NotNull final Long clientId);

    Asset findByIdAndClientId(@NotNull final Long id, @NotNull final Long clientId);
}
