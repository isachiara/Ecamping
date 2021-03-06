/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.beans;

import com.ecamping.entidade.Address;
import com.ecamping.entidade.Camping;
import com.ecamping.service.AddressService;
import com.ecamping.service.CampingService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author isabella
 */
@ManagedBean(name = "campingBean")
@RequestScoped
public class CampingBean implements Serializable {

    private Camping camping;
    private Address endereco;
    private List<Camping> campings;

    @EJB
    CampingService campingService;

    @PostConstruct
    public void iniciar() {
        camping = campingService.create();
        endereco = new Address();
    }

    public void salvar() {
        this.camping.setAddress(this.endereco);
        this.campingService.persistence(this.camping);
        this.camping = new Camping();
        addMessage("Camping cadastrado com sucesso!");
        
        this.endereco = null;
        this.camping = null;

    }

    public List<Camping> getCampings() {
        campings = campingService.getAllCampings();
        return campings;
    }

    public Address getEndereco() {
        return endereco;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
    }

    public Camping getCamping() {
        return camping;
    }

    public void setCamping(Camping camping) {
        this.camping = camping;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
