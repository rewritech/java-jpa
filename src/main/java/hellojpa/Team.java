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

    // 양방향 mappedBy 난이도가 있음 (양방향 매핑 가능하지만, 실무에서 가능한 단방향 매핑으로 끝내자.)
    // 연관관계 주인 개념 Team과 Members중 외래키 주인을 결정. 주인만 외래키 등록 수정 가능. 주인 아니면 읽기만 가능.
    // 주인이 아닌쪽에서 mappedBy 사용해 외래키 주인 지정
    // 주인 결정: 외래키 있는 쪽. 1 대 N 중 N(다) 쪽.
    // FK가 없는 Team을 연관관계 주인으로 두면, Team을 바꿨으나(member), Member 테이블에 update SQL이 일어남. 성능이슈도 있음
    // 즉, 다대일 중 다에 해당하는 객체에 연관관계 주인으로 두지 않는다.
    // 일대다 단방향 매핑보다는 다대인 양방향 매핑을 사용하자
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }

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

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                *주의: members에서 또 team.toString()을 호출하기에 무한루프에 빠진다.
//                lombok, JSON 생성 라이브러리 조심 <엔티티>를 반환하지 말고 DTO를 사용해라.
//                ", members=" + members +
                '}';
    }
}
