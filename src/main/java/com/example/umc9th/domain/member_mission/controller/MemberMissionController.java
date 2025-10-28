package com.example.umc9th.domain.member_mission.controller;

import com.example.umc9th.domain.member_mission.dto.MemberMissionResponse;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.member_mission.service.MemberMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member_missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @GetMapping
    public ResponseEntity<List<MemberMissionResponse>> getByState(@RequestParam(required = false) String state) {
        List<MemberMission> missions = memberMissionService.getMemberMissionsByState(state);
        List<MemberMissionResponse> body = missions.stream()
                .map(MemberMissionResponse::from)
                .toList();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/count")
    public Integer countMissions(@RequestParam(required = true) String region, @RequestParam(required = true) Long memberId) {
        System.out.println("region = " + region);
        Integer count = memberMissionService.countMemberMissionsByRegionAndMember(region, memberId);
        return count;

    }
}

