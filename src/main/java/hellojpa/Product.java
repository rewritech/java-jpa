package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id @GeneratedValue
    private Long id;

    private String name;

    // 조심! 예제일 뿐 쓰지 않는다. 매핑정보만 들어가고 갱신기록이나 기타 정보를 전혀 담을 수 없다
    // 대신 OneToMany & ManyToOne 을 따로 엔티티로 승격해 만들자(MemberProduct)
//    @ManyToMany(mappedBy = "products")
//    private List<Member> members = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<MemberProduct> memberProducts = new ArrayList<>();

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
