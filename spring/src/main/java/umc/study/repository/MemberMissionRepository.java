package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Optional<MemberMission> findMemberMissionByMemberAndMission(Member member, Mission mission);
    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, PageRequest pageRequest);
}
