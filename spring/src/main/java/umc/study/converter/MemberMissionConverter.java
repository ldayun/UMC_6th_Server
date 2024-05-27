package umc.study.converter;

import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberResponseDTO;

public class MemberMissionConverter {

    public static MemberResponseDTO.MemberMissionResultDTO toCreateMemberMissionResultDTO(MemberMission memberMission){
        return MemberResponseDTO.MemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .build();
    }
}
