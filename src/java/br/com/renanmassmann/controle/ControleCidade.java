
package br.com.renanmassmann.controle;

import br.com.renanmassmann.dao.CidadeDAO;
import br.com.renanmassmann.modelo.Cidade;
import br.com.renanmassmann.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "controleCidade")
@SessionScoped
public class ControleCidade implements Serializable {
    
    private CidadeDAO dao;
    private Cidade objeto;
    
    
    public ControleCidade(){
        dao = new CidadeDAO();

    }
    
    public String listar(){
        return "/privado/cidade/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Cidade();
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

    public CidadeDAO getDao() {
        return dao;
    }

    public void setDao(CidadeDAO dao) {
        this.dao = dao;
    }

    public Cidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Cidade objeto) {
        this.objeto = objeto;
    }

  
    
    

}
