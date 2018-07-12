/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.service;

import com.ecamping.entidade.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

/**
 *
 * @author isabella
 */
@Stateless(name = "ejb/UserService")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class UserService extends Service<User> {

    @PostConstruct
    public void init() {
        super.setClasse(User.class);
    }

    @Override
    public User create() {
        return new User();
    }

    @Override
    public boolean exist(@NotNull User user) {
        TypedQuery<User> query
                = entityManager.createNamedQuery(User.USER_POR_ID, classe);
        query.setParameter(1, user.getId());
        return !query.getResultList().isEmpty();
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User getUserPorEmail(String email){
        return super.findEntity(new Object[]{email}, User.USER_POR_EMAIL);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> getUserPorLetra(String letra){
        return super.findEntities(new Object[]{letra}, User.USER_POR_LETRA);
    }
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> getUserPorNome(String nome){
        return super.findEntities(new Object[]{nome}, User.USER_POR_NOME);
    }
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> getAllUsers(){
        return super.findEntities(User.ALL_USERS);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User getUserPorCPF(String cpf){
        return super.findEntity(new Object[]{cpf}, User.USER_POR_CPF);
    }
    
    
    

}
