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

            tx.commit();        // Send SQL
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
