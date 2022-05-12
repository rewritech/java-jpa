package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
//@Table(UniqueConstraint=) 유니크 제약은 보통 여기에 준다
public class Member {
    @Id
    private Long id;

    // unique도 있지만 이름이 랜덤으로 지정되어 에러시 파악 어렵기에 안 씀
    @Column(name = "name", nullable = false, updatable = false)
    private String username;

    @Column()
    private Integer age;

    @Enumerated(EnumType.STRING)    // DB에 enum 없기에 지정
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)   // 시간 데이터 지정
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob    // 큰 데이터
    private String description;

    @Transient  // DB미사용
    private String temp;

    public Member() {
        
    }
}