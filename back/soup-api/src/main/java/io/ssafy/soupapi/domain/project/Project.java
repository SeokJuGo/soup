package io.ssafy.soupapi.domain.project;

import io.ssafy.soupapi.domain.BaseEntity;
import io.ssafy.soupapi.domain.chat.Chat;
import io.ssafy.soupapi.domain.projectauth.ProjectAuth;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "project")
@AttributeOverrides({
        @AttributeOverride(name = "status", column = @Column(name = "project_status")),
        @AttributeOverride(name = "createdAt", column = @Column(name = "project_created_at")),
        @AttributeOverride(name = "modifiedAt", column = @Column(name = "project_modified_at"))
})
@SQLRestriction("status=TRUE")
public class Project extends BaseEntity {
    @Id
    @Column(name = "project_id")
    private String id;
    @Column(name = "project_file_uri")
    private String fileUri;
    @Column(name = "project_jira_username")
    private String jiraUsername;
    @Column(name = "project_jira_key")
    private String jiraKey;
    @Builder.Default
    @OneToMany(mappedBy = "project")
    private List<Chat> chatList = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "project")
    private List<ProjectAuth> projectAuthList = new ArrayList<>();

    /**
     * Chat Entity 추가 메서드
     * Project - Chat Entity 사이 일관성 유지
     *
     * @param chat chat element to be appended to this list
     */
    public void addChat(Chat chat) {
        this.chatList.add(chat);

        if (chat.getProject() != this) {
            chat.setProject(this);
        }
    }

    /**
     * ProjectAuth Entity 추가 메서드
     * Project - ProjectAuth Entity 사이 일관성 유지
     *
     * @param projectAuth project element to be appended to this list
     */
    public void addProjectAuth(ProjectAuth projectAuth) {
        this.projectAuthList.add(projectAuth);

        if (projectAuth.getProject() != this) {
            projectAuth.setProject(this);
        }
    }
}
