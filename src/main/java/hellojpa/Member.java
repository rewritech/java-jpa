package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
public class Member {
    @Id
    private Long id;

    @Column(name = "name")
    private String username;

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