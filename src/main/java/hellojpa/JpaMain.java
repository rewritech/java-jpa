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

            // Update Member
//            findMember.setName("updatedName");

            // Delete Member
//            em.remove(findMember);

            // JPQL: entity Object is targeted  (SQL DB Table is targeted)
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println(member.getName());
            }

            Member member1 = new Member(150l,"A");
            Member member2 = new Member(160l,"B");
            // 쓰기 지연: delay insert 구지 commit 직전에 모아서 insert 할 필요가 없다 와우!
            em.persist(member1);
            em.persist(member2);

            tx.commit();        // Send SQL
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
