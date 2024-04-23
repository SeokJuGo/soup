package io.ssafy.soupapi.domain.member;

import io.ssafy.soupapi.domain.BaseEntity;
import io.ssafy.soupapi.domain.chat.Chat;
import io.ssafy.soupapi.domain.noti.Noti;
import io.ssafy.soupapi.domain.projectauth.ProjectAuth;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type Member.
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "member")
@AttributeOverrides({
        @AttributeOverride(name = "status", column = @Column(name = "member_status")),
        @AttributeOverride(name = "createdAt", column = @Column(name = "member_created_at")),
        @AttributeOverride(name = "modifiedAt", column = @Column(name = "member_modified_at"))
})
@SQLRestriction("status=TRUE")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "member_id")
    private UUID id;
    @Column(name = "member_phone", length = 20)
    private String phone;
    @Column(name = "member_email", length = 50, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", nullable = false)
    private SocialType type;
    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Chat> chatList = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Noti> notiList = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<ProjectAuth> projectAuthList = new ArrayList<>();

    /**
     * Chat Entity 추가 메서드
     * Member - Chat Entity 사이 일관성 유지
     *
     * @param chat chat element to be appended to this list
     */
    public void addChat(Chat chat) {
        this.chatList.add(chat);
        if (chat.getMember() != this) {
            chat.setMember(this);
        }
    }

    /**
     * Noti Entity 추가 메서드
     * Member - Noti Entity 사이 일관성 유지
     *
     * @param noti noti element to be appended to this list
     */
    public void addNoti(Noti noti) {
        this.notiList.add(noti);
        if (noti.getMember() != this) {
            noti.setMember(this);
        }
    }

    /**
     * ProjectAuth Entity 추가 메서드
     * Member - ProjectAuth Entity 사이 일관성 유지
     *
     * @param projectAuth projectAuth element to be appended to this list
     */
    public void addProjectAuth(ProjectAuth projectAuth) {
        this.projectAuthList.add(projectAuth);
        if (projectAuth.getMember() != this) {
            projectAuth.setMember(this);
        }
    }
}
