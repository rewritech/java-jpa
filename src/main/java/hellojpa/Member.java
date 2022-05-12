package hellojpa;

//import javax.persistence.Column;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.Table;

@Entity
//@Entity(name = "Member")  // 클래스명이 기본값. 다른 패키지에 동일 클래스명이 있을 때 지정
//@Table(name = "Member")   // 테이블명 지정
public class Member {
    @Id
    private Long id;
//    @Column(name = "username")
    @Column(unique = true, length = 10)
    private String name;

    public Member() {}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
