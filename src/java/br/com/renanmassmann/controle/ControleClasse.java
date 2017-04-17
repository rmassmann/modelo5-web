
package br.com.renanmassmann.controle;

import br.com.renanmassmann.dao.ClasseDAO;
import br.com.renanmassmann.modelo.Classe;
import br.com.renanmassmann.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "controleClasse")
@SessionScoped
public class ControleClasse implements Serializable {
    
    private ClasseDAO dao;
    private Classe objeto;
    
    
    public ControleClasse(){
        dao = new ClasseDAO();

    }
    
    public String listar(){
        return "/privado/classe/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Classe();
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

    public ClasseDAO getDao() {
        return dao;
    }

    public void setDao(ClasseDAO dao) {
        this.dao = dao;
    }

    public Classe getObjeto() {
        return objeto;
    }

    public void setObjeto(Classe objeto) {
        this.objeto = objeto;
    }

  
    
    

}
