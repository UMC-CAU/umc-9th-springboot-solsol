package com.example.umc9th.domain.member_mission.controller;

import com.example.umc9th.domain.member_mission.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.member_mission.dto.MemberMissionResponse;
import com.example.umc9th.domain.member_mission.dto.MemberMissionResDTO;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.member_mission.service.MemberMissionQueryService;
import com.example.umc9th.domain.member_mission.service.MemberMissionService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member_missions")
@Validated
public class MemberMissionController {

    private final MemberMissionService memberMissionService;
    private final MemberMissionQueryService memberMissionQueryService;

    @GetMapping
    public ApiResponse<List<MemberMissionResponse>> getByState(@RequestParam(required = false) String state) {
        List<MemberMission> missions = memberMissionService.getMemberMissionsByState(state);
        List<MemberMissionResponse> body = missions.stream()
                .map(MemberMissionResponse::from)
                .toList();
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, body);
    }

    @GetMapping("/count")
    public ApiResponse<Integer> countMissions(@RequestParam(required = true) String region,
                                 @RequestParam(required = true) Long memberId) {
        System.out.println("region = " + region);
        Integer count = memberMissionService.countMemberMissionsByRegionAndMember(region, memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, count);
    }

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<Long> challengeMission(@PathVariable Long missionId,
                                              @RequestParam Long memberId) {
        Long memberMissionId = memberMissionService.challengeMission(missionId, memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberMissionId);
    }

    @Operation(
            summary = "내가 진행중인 미션 목록 조회 API",
            description = "특정 회원이 진행중인 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/progressing")
    public ApiResponse<MemberMissionResDTO.MyProgressingMissionListDTO> getMyProgressingMissions(
            @RequestParam Long memberId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ){
        MemberMissionResDTO.MyProgressingMissionListDTO missions = memberMissionQueryService.findMyProgressingMissions(memberId, page - 1);
        MemberMissionSuccessCode code = MemberMissionSuccessCode.MEMBER_MISSION_RETRIEVED;
        return ApiResponse.onSuccess(code, missions);
    }
}
