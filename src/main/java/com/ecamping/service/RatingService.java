/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.service;

import com.ecamping.entidade.Rating;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

/**
 *
 * @author isabella
 */
@Stateless(name = "ejb/RatingService")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class RatingService extends Service<Rating>{

    @PostConstruct
    public void init() {
        super.setClasse(Rating.class);
    }

    @Override
    public Rating create() {
        return new Rating();
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Rating> getAllRatings(){
        return super.findEntities(Rating.ALL_RATINGS);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Rating> ratingsBetweenOneAndThree(){
        return super.findEntities("SELECT r FROM Rating r WHERE r.value BETWEEN 1 AND 3");
    }
}
