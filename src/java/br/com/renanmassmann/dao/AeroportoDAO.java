
package br.com.renanmassmann.dao;

import br.com.renanmassmann.jpa.EntityManagerUtil;
import br.com.renanmassmann.modelo.Aeroporto;
import br.com.renanmassmann.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;


public class AeroportoDAO<T> extends DAOGenerico<Aeroporto> implements Serializable {

     public AeroportoDAO(){
        super();
        super.setClassePersistence(Aeroporto.class);
        super.setOrdem("nome");
    }
    
    
}
