package umc.study.service.MemberService;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberQueryService {
    void CheckFlag(Integer flag);

    Optional<Member> findMember(Long id);

    Optional<MemberMission> findMemberMissionByMemberAndMission(Long memberId, Long missionId);

    Page<Review> getMyReviewList(Long memberId, Integer page);

    Page<MemberMission> getMyChallengingList(Long memberId, Integer page);
}
