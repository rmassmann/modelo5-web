
package br.com.renanmassmann.dao;

import br.com.renanmassmann.jpa.EntityManagerUtil;
import br.com.renanmassmann.modelo.Aeroporto;
import br.com.renanmassmann.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;


public class AeroportoDAO implements Serializable {

    private String mensagem = "";
    private EntityManager em;

    public AeroportoDAO(){
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<Aeroporto> getLista(){
        return em.createQuery("from Aeroporto order by nome").getResultList();
    }
    
    public boolean salvar(Aeroporto obj){
        try {
            em.getTransaction().begin();
            if (obj.getId() == null){
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        } catch (Exception e){
            if (em.getTransaction().isActive() == false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao persistir objeto: "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remover(Aeroporto obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        } catch (Exception e){
            if (em.getTransaction().isActive() == false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao remover objeto: "+Util.getMensagemErro(e);
            return false;
        }
    }    
    
    public Aeroporto localizar(Integer id){
        return em.find(Aeroporto.class, id);
    }
    
    
    
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
}
