package umc.study.service.StoreService;

import umc.study.domain.Mission;
import umc.study.domain.Store;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    Optional<Mission> findMission(Long id);
}
