
package br.com.renanmassmann.controle;

import br.com.renanmassmann.dao.CidadeDAO;
import br.com.renanmassmann.dao.AeroportoDAO;
import br.com.renanmassmann.modelo.Aeroporto;
import br.com.renanmassmann.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "controleAeroporto")
@SessionScoped
public class ControleAeroporto implements Serializable {
    
    private AeroportoDAO dao;
    private Aeroporto objeto;
     private CidadeDAO daoCidade;
    
   
    
    public ControleAeroporto(){
        dao = new AeroportoDAO();
        daoCidade = new CidadeDAO();
       
    }
    
    public String listar(){
        return "/privado/aeroporto/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Aeroporto();
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

    public AeroportoDAO getDao() {
        return dao;
    }

    public void setDao(AeroportoDAO dao) {
        this.dao = dao;
    }

    public Aeroporto getObjeto() {
        return objeto;
    }

    public void setObjeto(Aeroporto objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO daoCidade) {
        this.daoCidade = daoCidade;
    }


}
