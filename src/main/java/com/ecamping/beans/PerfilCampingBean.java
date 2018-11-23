/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.beans;

import com.ecamping.entidade.Address;
import com.ecamping.entidade.Camping;
import javax.faces.context.FacesContext;
import com.ecamping.entidade.Camping;
import com.ecamping.entidade.User;
import com.ecamping.service.AddressService;
import com.ecamping.service.BookingService;
import com.ecamping.service.CampingService;
import com.ecamping.service.UserService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author isabella
 */
@ManagedBean(name = "perfilcampingBean")
@SessionScoped
public class PerfilCampingBean implements Serializable {

    private Camping camping;
    private Camping perfil;
    private Address endereco;
    
    @EJB
    private CampingService campingService;
    
    @EJB
    private AddressService enderecoService;

    @PostConstruct
    public void iniciar() {
        endereco = enderecoService.create();
        perfil = new Camping();
        camping = (Camping) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("camping");
    }

    public String MudarDePerfil(Camping camping) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("camping", camping);
        return "perfilCamping?faces-redirect=true";
    }
    
    public Camping getCamping(){
        return camping;
    }

    public Camping getPerfil() {
         List<Camping> campings = campingService.getAllCampings();
        for(Camping c : campings){
            if(c.equals(camping)){
                perfil = camping;
                return perfil;
            }
        }
        return perfil;
    }

    public void setPerfil(Camping perfil) {
        this.perfil = perfil;
    }

    public Address getEndereco() {
        return endereco;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
    }
    
    
    
}
