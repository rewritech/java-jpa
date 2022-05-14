package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    // mappedBy 난이도가 있음
    // 연관관계 주인 개념 Team과 Members중 외래키 주인을 결정. 주인만 외래키 등록 수정 가능. 주인 아니면 읽기만 가능.
    // 주인이 아닌쪽에서 mappedBy 사용해 외래키 주인 지정
    // 주인 결정: 외래키 있는 쪽. 1 대 N 중 N(다) 쪽.
    // (FK가 없는 Team을 연관관계 주인으로 두면, Team을 바꿨으나(member), Member 테이블에 update가 일어남. 성능이슈도 있음)
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
