/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Partido;


/**
 *
 * @author Zatonio
 */
public class PartidoJpaController implements Serializable {

    public PartidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    @PersistenceContext
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Partido partido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
         
            em.persist(partido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Partido partido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Partido persistentPartido = em.find(Partido.class, partido.getId());
            
            if(persistentPartido == null) {
                throw new Exception();
            }
            
            partido = em.merge(partido);
            em.getTransaction().commit();
            
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = partido.getId();
                if (findPartido(id) == null) {
                    throw new NonexistentEntityException("El partido con id " + id + " no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Partido partido;
            try {
                partido = em.getReference(Partido.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            
            em.remove(partido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Partido> findPartidoEntities() {
        return PartidoJpaController.this.findPartidoEntities(true, -1, -1);
    }

    public List<Partido> findPartidoEntities(int maxResults, int firstResult) {
        return PartidoJpaController.this.findPartidoEntities(false, maxResults, firstResult);
    }

    private List<Partido> findPartidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Partido.class));
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
    
   
    
    public List<Partido> findPartidoJugadosporEquipoTerminados(Long idEquipo) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Partido> query = em.createNamedQuery("findPartidoJugadosporEquipoTerminados", Partido.class);
           query.setParameter("id", idEquipo);
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    
    
    
    
    public Partido findPartido(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Partido.class, id);
        } finally {
            em.close();
        }
    }
    
    

    
    
    /*public int getLigaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Liga> rt = cq.from(Liga.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }*/
    
}
