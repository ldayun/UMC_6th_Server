package umc.study.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.domain.Member;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.ReviewRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryServiceImpl implements MemberQueryService{

    @Override
    public void CheckFlag(Integer flag) {

    }

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<MemberMission> findMemberMissionByMemberAndMission(Long memberId, Long missionId) {
        Optional<Member> member = findMember(memberId);
        Optional<Mission> mission = missionRepository.findById(missionId);

        return memberMissionRepository.findMemberMissionByMemberAndMission(member.get(), mission.get());
    }

    @Override
    public Page<Review> getMyReviewList(Long memberId, Integer page) {
        Member member = findMember(memberId).get();
        Page<Review> StorePage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return StorePage;
    }

    @Override
    public Page<MemberMission> getMyChallengingList(Long memberId, Integer page) {
        Member member = findMember(memberId).get();
        Page<MemberMission> StorePage = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, PageRequest.of(page, 10));
        return StorePage;
    }

}