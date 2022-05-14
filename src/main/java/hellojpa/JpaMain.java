package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // create Member
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");     // 비영속: non-persist state
//            em.persist(member);           // 영속: persist state

            // Select Member
//            Member findMember = em.find(Member.class, 1l);

            // same object as persist
//            Member findMemberA = em.find(Member.class, 1l);
//            Member findMemberB = em.find(Member.class, 1l);
//            System.out.println(findMemberA == findMemberB); // true
//            em.detach(findMemberB);   // 일부 준영속: specific entity detach
//            em.clear();               // 전부 준영속: all entities detach

            // Update Member 변경 감지(Dirty Check)
//            findMember.setName("updatedName");
            //em.persist(findMember);   // 불필요: no need

            // Delete Member
//            em.remove(findMember);

            // JPQL: entity Object is targeted  (SQL DB Table is targeted)
            // Send em.flush(). For  insert
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(10)
//                    .getResultList();

//            Member member1 = new Member(150l,"A");
//            Member member2 = new Member(160l,"B");
            // 쓰기 지연: delay insert 구지 commit 직전에 모아서 insert 할 필요가 없다 와우!
//            em.persist(member1);
//            em.persist(member2);

//            em.flush();         // Send SQL. It's usually used for test.

//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setTeam(team);
//            em.persist(member);

//            Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = findMember.getTeam();
//            List<Member> members = findTeam.getMembers();

//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam);    // 참조 팀 변경

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
//            member.setTeam(team);             // *member는 연관관계 주인이기에 수정 권한이 있다.
//            team은 연관관계 주인이 아니기에 수정 권한이 없다. 객체 참조 위해 지정
//            -> member.setTeam(team)에 team.getMembers().add(this) 추가해 실수방지 (changeTeam으로 변경)
//            -> 혹은 team에 member.setTeam(this)를 지닌 addMember(member) 추가
//            -> 위 둘 중 하나만 사용한다.
//            -> Gim 나는 수정권한이 있는 member객체에 changeTeam 추가가 좋아보임
//            team.getMembers().add(member);
            member.changeTeam(team);             // *member는 연관관계 주인이기에 수정 권한이 있다.
//            team.addMember(member);
            em.persist(member);

            //  flush와 team.getMembers().add(member) 실행 안 하면, member 추가 안 된 1차 캐쉬 데이터를 들고 옴
            Team findTeam = em.find(Team.class, team.getId());
            List<Member> finedMembers = findTeam.getMembers();
            System.out.println("==============");
            System.out.println(finedMembers.get(0).getUsername());
            System.out.println("==============");

            em.flush();
            em.clear();

            tx.commit();        // Send SQL
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
