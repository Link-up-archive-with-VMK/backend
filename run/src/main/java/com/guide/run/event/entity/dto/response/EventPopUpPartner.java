package com.guide.run.event.entity.dto.response;

import com.guide.run.user.entity.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventPopUpPartner {

	private String partnerName;
	private String partnerRecord;
	private UserType partnerType;
}
