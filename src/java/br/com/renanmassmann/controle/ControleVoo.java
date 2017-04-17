
package br.com.renanmassmann.controle;

import br.com.renanmassmann.dao.VooDAO;
import br.com.renanmassmann.modelo.Voo;
import br.com.renanmassmann.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "controleVoo")
@SessionScoped
public class ControleVoo implements Serializable {
    
    private VooDAO dao;
    private Voo objeto;
    
    
    public ControleVoo(){
        dao = new VooDAO();

    }
    
    public String listar(){
        return "/privado/voo/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Voo();
        return "formulario";
    }
    
    public String salvar(){
        if (dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public String editar(Integer id){
        try {
            objeto = dao.localizar(id);
            return "formulario";
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar";
        }
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public VooDAO getDao() {
        return dao;
    }

    public void setDao(VooDAO dao) {
        this.dao = dao;
    }

    public Voo getObjeto() {
        return objeto;
    }

    public void setObjeto(Voo objeto) {
        this.objeto = objeto;
    }

  
    
    

}
