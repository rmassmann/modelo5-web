
package br.com.renanmassmann.dao;

import br.com.renanmassmann.jpa.EntityManagerUtil;
import br.com.renanmassmann.modelo.Voo;
import br.com.renanmassmann.modelo.Aeroporto;
import br.com.renanmassmann.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

     
public class VooDAO<T> extends DAOGenerico<Voo> implements Serializable {
    
    public VooDAO(){
        super();
        super.setClassePersistence(Voo.class);
        //super.setOrdem("nome");
    }
}