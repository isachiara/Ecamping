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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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

    public String salvar() {
        this.camping.setAddress(this.endereco);
        this.campingService.persistence(this.camping);
        this.camping = new Camping();
        return "index?faces-redirect=true";
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

}
