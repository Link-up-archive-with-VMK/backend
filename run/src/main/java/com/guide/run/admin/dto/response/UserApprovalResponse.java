package com.guide.run.admin.dto.response;

import com.guide.run.user.entity.type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApprovalResponse {
    private String userId;
    private Boolean isApprove;
    private String recordDegree;
}
