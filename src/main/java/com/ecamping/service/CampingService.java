/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.service;

import com.ecamping.entidade.Camping;
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
@Stateless(name = "ejb/CampingService")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class CampingService extends Service<Camping> {

    @PostConstruct
    public void init() {
        super.setClasse(Camping.class);
    }

    @Override
    public Camping create() {
        return new Camping();
    }

    @Override
    public boolean exist(@NotNull Camping camping) {
        TypedQuery<Camping> query
                = entityManager.createNamedQuery(Camping.CAMPING_POR_NOME, classe);
        query.setParameter(1, camping.getName());
        return !query.getResultList().isEmpty();
    }
    
    /*@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public boolean loginCamping(@NotNull Camping camping){
        
    }*/

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Camping> getCampingsSemReserva() {
        return super.findEntities(Camping.CAMPING_SEM_RESERVAS);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Camping getCampingsPorNome(String nome) {
        return super.findEntity(new Object[]{nome}, Camping.CAMPING_POR_NOME);
    }
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Camping> getAllCampings() {
        return super.findEntities(Camping.ALL_CAMPING);
    }

   

}
