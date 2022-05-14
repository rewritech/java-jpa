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

            Team team = new Team();
            team.set();
            tx.commit();        // Send SQL
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
