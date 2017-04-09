
package br.com.renanmassmann.dao;

import br.com.renanmassmann.jpa.EntityManagerUtil;
import br.com.renanmassmann.modelo.Cidade;
import br.com.renanmassmann.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;


public class CidadeDAO implements Serializable {

    private String mensagem = "";
    private EntityManager em;

    public CidadeDAO(){
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<Cidade> getLista(){
        return em.createQuery("from Cidade order by nome").getResultList();
    }
    
    public boolean salvar(Cidade obj){
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
    
    public boolean remover(Cidade obj){
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
    
    public Cidade localizar(Integer id){
        return em.find(Cidade.class, id);
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
