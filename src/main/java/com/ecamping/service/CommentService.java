/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.service;

import com.ecamping.entidade.Comment;
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
@Stateless(name = "ejb/CommentService")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class CommentService extends Service<Comment> {

    @PostConstruct
    public void init() {
        super.setClasse(Comment.class);
    }

    @Override
    public Comment create() {
        return new Comment();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Comment> getAllComments(){
        return super.findEntities(Comment.ALL_COMMENTS);
    }
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Comment getComentarioPorUserCamping(Long user, Long camping){
        return super.findEntity(new Object[]{user, camping}, Comment.COMENTARIO_POR_USER_CAMPING);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Comment> getCommentBetweenTwoDates(){
        return super.findEntities("SELECT c FROM Comment c WHERE c.publish BETWEEN '2015-03-19' AND '2017-03-02'");
    }
    
    
}
