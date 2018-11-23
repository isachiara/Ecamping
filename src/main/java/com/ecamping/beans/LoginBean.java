/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.beans;

import com.ecamping.entidade.User;
import com.ecamping.service.SessionContext;
import com.ecamping.service.UserService;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.sound.midi.SysexMessage;

/**
 *
 * @author isabella
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

    private String login;
    private String senha;
   // private static Logger logger = Logger.getLogger(LoginBean.class);
    @EJB
    UserService userService;

    public User getUser() {
        return (User) SessionContext.getInstance().getUsuarioLogado();
    }

    public String Autenticar() {
        System.out.println("Chamado");
        User usuario = new User();
        List<User> usuarios = userService.getAllUsers();
        for (User u : usuarios) {
            if (u.getEmail().equals(login) && u.getPassword().equals(senha)) {
                usuario = u;
                SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
                return "perfilCampista";
            }
        }
        FacesMessage fm = new FacesMessage("Login ou senha inválidos");
        FacesContext.getCurrentInstance().addMessage("msg", fm);
        return "loginCampista";
    }

    public String doLogout() {
        return null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
