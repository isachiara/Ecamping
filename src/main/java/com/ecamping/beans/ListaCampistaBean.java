/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.beans;

import com.ecamping.entidade.Camping;
import com.ecamping.entidade.User;
import com.ecamping.service.CampingService;
import com.ecamping.service.UserService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ramon
 */
@ManagedBean(name = "listaCampistas")
@RequestScoped
public class ListaCampistaBean {
    
    private List<User> campistas;
    private User campista;

    @EJB
    UserService userService;

    public List<User> getCampistas() {
        campistas = userService.getAllUsers();
        return campistas;
    }
    
    public void removeCampista(User user){
        this.userService.delete(user);
        addMessage("Campista removido com sucesso!");
    }
    
    public String redirectToProfile(User campista){
        this.campista = campista;
        return "perfilCampista";
    }
    
    public void editProfile(){
        this.userService.update(campista);
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
