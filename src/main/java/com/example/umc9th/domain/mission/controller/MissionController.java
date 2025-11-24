package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.dto.MissionResponse;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Validated
public class MissionController {

    private final MissionService missionService;
    private final MissionQueryService missionQueryService;

    @GetMapping
    public ApiResponse<List<MissionResponse>> getMissions(@RequestParam String region) {
        List<Mission> missions = missionService.getMissionsByRegion(region);
        List<MissionResponse> body = missions.stream()
                .map(m -> new MissionResponse(
                        m.getId(),
                        m.getName(),
                        m.getDeadline(),
                        m.getMinCost(),
                        m.getStore().getName()
                ))
                .collect(Collectors.toList());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, body);
    }

    @Operation(
            summary = "특정 가게의 미션 목록 조회 API",
            description = "특정 가게의 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/store")
    public ApiResponse<MissionResDTO.StoreMissionListDTO> getStoreMissions(
            @RequestParam Long storeId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ){
        MissionResDTO.StoreMissionListDTO missions = missionQueryService.findStoreMissions(storeId, page - 1);
        MissionSuccessCode code = MissionSuccessCode.MISSION_RETRIEVED;
        return ApiResponse.onSuccess(code, missions);
    }
}
