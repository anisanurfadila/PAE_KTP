/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pae.umy.projectktp2.dataktp;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pae.umy.projectktp2.dataktp.exceptions.NonexistentEntityException;

/**
 *
 * @author Asus
 */
public class DataKtpJpaController implements Serializable {

    public DataKtpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
      private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pae.umy_projectktp2_jar_0.0.1-SNAPSHOTPU");

    public DataKtpJpaController() {
    }

      
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DataKtp dataKtp) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dataKtp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataKtp dataKtp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dataKtp = em.merge(dataKtp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dataKtp.getId();
                if (findDataKtp(id) == null) {
                    throw new NonexistentEntityException("The dataKtp with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DataKtp dataKtp;
            try {
                dataKtp = em.getReference(DataKtp.class, id);
                dataKtp.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dataKtp with id " + id + " no longer exists.", enfe);
            }
            em.remove(dataKtp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DataKtp> findDataKtpEntities() {
        return findDataKtpEntities(true, -1, -1);
    }

    public List<DataKtp> findDataKtpEntities(int maxResults, int firstResult) {
        return findDataKtpEntities(false, maxResults, firstResult);
    }

    private List<DataKtp> findDataKtpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DataKtp.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DataKtp findDataKtp(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataKtp.class, id);
        } finally {
            em.close();
        }
    }

    public int getDataKtpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DataKtp> rt = cq.from(DataKtp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
