package com.guide.run.event.entity.dto.response.search;

import com.guide.run.event.entity.type.EventRecruitStatus;
import com.guide.run.event.entity.type.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchAllEvent {
    private Long eventId;
    private EventType eventType;
    private String name;
    private String endDate;
    private EventRecruitStatus recruitStatus;
}
