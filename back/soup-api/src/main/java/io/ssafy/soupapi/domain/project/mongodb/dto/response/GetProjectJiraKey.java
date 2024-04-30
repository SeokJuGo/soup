package io.ssafy.soupapi.domain.project.mongodb.dto.response;

import io.ssafy.soupapi.domain.project.mongodb.entity.Info;
import io.ssafy.soupapi.global.util.StringParserUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "프로젝트 키 정보")
public record GetProjectJiraKey(
        @Schema(description = "프로젝트 jira host")
        String jiraHost,
        @Schema(description =  "프로젝트 jira project key")
        String jiraProjectKey,
        @Schema(description = "프로젝트 jira username")
        String jiraUsername,
        @Schema(description = "프로젝트 jira key")
        String jiraKey
) {
    @Builder
    public GetProjectJiraKey {
        jiraHost = StringParserUtil.parseNullToEmpty(jiraHost);
        jiraProjectKey = StringParserUtil.parseNullToEmpty(jiraProjectKey);
        jiraUsername = StringParserUtil.parseNullToEmpty(jiraUsername);
        jiraKey = StringParserUtil.parseNullToEmpty(jiraKey);
    }

    public static GetProjectJiraKey toProjectInfoDto(Info info) {
        return GetProjectJiraKey.builder()
                .jiraHost(info.getJiraHost())
                .jiraProjectKey(info.getJiraProjectKey())
                .jiraUsername(info.getJiraUsername())
                .jiraKey(info.getJiraKey())
                .build();
    }
}
