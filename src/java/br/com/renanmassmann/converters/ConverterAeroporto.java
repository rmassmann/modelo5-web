/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.renanmassmann.converters;

import br.com.renanmassmann.jpa.EntityManagerUtil;
import br.com.renanmassmann.modelo.Aeroporto;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

/**
 *
 * @author Renan
 */
@FacesConverter(value="converterAeroporto")
public class ConverterAeroporto implements Serializable, Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string ==null || string.equals("Seleciona um registro")){
            return null;
        }
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            return em.find(Aeroporto.class, Integer.parseInt(string));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        Aeroporto obj = (Aeroporto)o;
        return obj.getId().toString();
    }
    
}
