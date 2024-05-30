package umc.study.web.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Mission;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.validation.annotation.CheckPage;
import umc.study.domain.Review;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReviewDTO request,
                                                                            @PathVariable(name = "storeId") Long storeId,
                                                                            @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }

    @PostMapping("/{storeId}/missions")
    public ApiResponse<StoreResponseDTO.CreateMissionResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.MissionDTO request,
                                                                             @ExistStore @PathVariable(name = "storeId") Long storeId){
        Mission mission = storeCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateMissionResultDTO(mission));
    }

    // 리뷰 조회
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{storeId}/reviews")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @CheckPage @RequestParam(name = "page") Integer page){
        Page<Review> reviews = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviews));
    }

    // 미션 목록 조회
    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{storeId}/missions")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponseDTO.MissionPreViewListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId, @CheckPage @RequestParam(name = "page") Integer page){
        Page<Mission> missions = storeQueryService.getMissionList(storeId, page);
        return ApiResponse.onSuccess(MissionConverter.missionPreViewListDTO(missions));
    }
}