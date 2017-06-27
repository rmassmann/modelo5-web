
package br.com.renanmassmann.controle;


import br.com.renanmassmann.dao.AeroportoDAO;
import br.com.renanmassmann.dao.VooDAO;
import br.com.renanmassmann.modelo.Aeroporto;
import br.com.renanmassmann.modelo.Voo;
import br.com.renanmassmann.modelo.VooAgendado;
import br.com.renanmassmann.util.Util;
import br.com.renanmassmann.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleVoo")
@ViewScoped
public class ControleVoo implements Serializable {
    
    private VooDAO<Voo> dao;
    private Voo objeto;
    private AeroportoDAO<Aeroporto> daoAeroporto;
    private Aeroporto aeroporto;
    private VooAgendado vooAgendado;
     private Boolean novoVooAgendado;
    
    public ControleVoo(){
        dao = new VooDAO<>();
        daoAeroporto = new AeroportoDAO<>();
    }
    
    
     public void removerAeroporto(int index){
        objeto.getEscalas().remove(index);
        Util.mensagemInformacao("Escala removida com sucesso!");
    }
    
    public void adicionarAeroporto(){
        if (aeroporto != null) {
            if (!objeto.getEscalas().contains(aeroporto)) {
                objeto.getEscalas().add(aeroporto);
                Util.mensagemInformacao("Escala adicionada com sucesso!");
            }else{
                Util.mensagemErro("Escala ja existe na lista de escalas!");
            }
        }
    }
    
    public String listar(){
        return "/privado/voo/listar?faces-redirect=true";
    }
    
    public void relatorio(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("Voo", parametros, dao.getListaTodos());
    }
    
    public void novo(){
        objeto = new Voo();
    }
    
    public void salvar(){
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
            
        }else{
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public void editar(Integer id){
        try {
            dao.rollback();
            objeto= dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro(e.getMessage());
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
    
     public void novoVooAgendado(){
        vooAgendado = new VooAgendado();
        novoVooAgendado = true;
    }
    
    public void alterarVooAgendado(int index){
        vooAgendado = objeto.getVoosAgendados().get(index);
        novoVooAgendado = false;
    }
    
    public void salvarVooAgendado(){
        if (novoVooAgendado) {
            objeto.adicionarVooAgendado(vooAgendado);            
        }
        Util.mensagemInformacao("VooAgendado salvo com sucesso!");
    }
    
    public void removerVooAgendado(int index){
        objeto.removerVooAgendado(index);
        Util.mensagemInformacao("Voo Agendado removido com sucesso!");
    }

    public VooDAO<Voo> getDao() {
        return dao;
    }

    public void setDao(VooDAO<Voo> dao) {
        this.dao = dao;
    }
    
    public Voo getObjeto() {
        return objeto;
    }

    public void setObjeto(Voo objeto) {
        this.objeto = objeto;
    }

    public AeroportoDAO<Aeroporto> getDaoAeroporto() {
        return daoAeroporto;
    }

    public void setDaoAeroporto(AeroportoDAO<Aeroporto> daoAeroporto) {
        this.daoAeroporto = daoAeroporto;
    }
    
    

    public Aeroporto getAeroporto() {
        return aeroporto;
    }

    public void setAeroporto(Aeroporto aeroporto) {
        this.aeroporto = aeroporto;
    }

    public VooAgendado getVooAgendado() {
        return vooAgendado;
    }

    public void setVooAgendado(VooAgendado vooAgendado) {
        this.vooAgendado = vooAgendado;
    }

    public Boolean getNovoVooAgendado() {
        return novoVooAgendado;
    }

    public void setNovoVooAgendado(Boolean novoVooAgendado) {
        this.novoVooAgendado = novoVooAgendado;
    }
    
    

  
    
    

}
