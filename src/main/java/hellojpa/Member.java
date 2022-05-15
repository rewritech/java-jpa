package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 1)
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member {
    // Id는 시스템이 사라지는 아주 먼 미래까지 변해서는 안된다.
    // Long + 대체키 + 키 혹은 시퀀스 혹은 자동증가생성을 이용하자
    @Id @GeneratedValue
//    DB에 위임: 자동 생성 1,2,3,4 ...
//    JPA영속성관점에서 commit 전에 ID가 없기에 IDENTITY 전략은 persist 순간에 INSERT 쿼리 날려서 ID 생성한다.
//    즉, 모아서 inert 불가. 다행히도 한 트랜젝션에서 insert를 여러번 날려도 네트워크 부하가 그렇게 크지는 않다.
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // DB 시퀀스 오프젝트 생성 & 이용. int, Integer 사용하지 않음 10억 넘으면 초기화
    // persist 시점에서 DB Sequence 다음 값 생성해 pk Id로서 취득 -> commit 때 해당 시퀀스 사용
    // allocationSize를 기본 50 정도로 설정하면, 미리 sequence를 확보해 시퀀스 취득 DB 통신을 확 줄임
    // 시퀀스 1 -> 51 : 어플리케이션 Id 1,2,3,4,5,... // 51이 되면 다시 다음 DB 시퀀스 생성 취득
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    // 시퀀스용 테이블 만들어서 Key 생성관리. 모든 DB 가능하지만, 성능 낮아 운영환경에서 사용하기 부담스럽다
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    // unique도 있지만 이름이 랜덤으로 지정되어 에러시 파악 어렵기에 안 씀
//    @Column(name = "name", nullable = false, updatable = false)
    @Column(name = "USERNAME")
    private String username;

    //    @Column(name = "TEAM_ID")
//    private Long tramId;)

//    @OneToOne // 일대일 관계: 주 테이블이나 대상 테이블에 외래키 설정. DB FK에 유니크 설정됨
    // Member : Lock가 [다:일]로 관계가 바뀌면, Lock에 FK를 두고 유니크 조건을 빼면 된다.
    // 하지만, 비즈니스적으로 보통 Member를 많이 Select하기에 FK를 Member가 Nullable로 들고 있는 편이 좋다.
    // 만약 대상 테이블인 Locker에 FK를 둔 양방향에서 지연 로딩 설정에도 항상 즉시 로딩
    // -> Member 취득에 Locker를 탐색해야 하기때문에  (차후에 더 설명 추가 예정)
    @ManyToOne  // 다대일 명시 -> 다대일 양방향 사용하자. (일대다 단방향 혹은 양방향 쓰지말자)
    @JoinColumn(name = "TEAM_ID")   // 연관관계 주인으로 JoinColumn 필수. 없으면 조인테이블 생성.
//    @JoinColumn(name = "TEAM_ID", updatable = false, insertable = false)   // 복잡한 비지니스에서 이렇게 양방향 설정하기도..
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;


    // DB에 enum 없기에 지정. enum 순서기준 0,1,2로 등록되기에 EnumType.STRING 필수
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)   // 시간 데이터 지정
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 데이터타입 보고 Date 및 Time 등록
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob    // 큰 데이터
    private String description;

    @Transient  // DB미사용
    private String temp;

    public Member() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); // team은 연관관계 주인이 아니기에 수정 권한이 없다. 객체 참조 위해 지정
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public LocalDate getTestLocalDate() {
        return testLocalDate;
    }

    public void setTestLocalDate(LocalDate testLocalDate) {
        this.testLocalDate = testLocalDate;
    }

    public LocalDateTime getTestLocalDateTime() {
        return testLocalDateTime;
    }

    public void setTestLocalDateTime(LocalDateTime testLocalDateTime) {
        this.testLocalDateTime = testLocalDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}