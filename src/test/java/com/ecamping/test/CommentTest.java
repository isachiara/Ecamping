package com.ecamping.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ecamping.entidade.Camping;
import com.ecamping.entidade.Comment;
import com.ecamping.entidade.Rating;
import com.ecamping.entidade.User;
import com.ecamping.service.CampingService;
import com.ecamping.service.CommentService;
import com.ecamping.service.UserService;
import static com.ecamping.test.Teste.container;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author ramon
 */
public class CommentTest extends Teste {

    private CommentService commentService;
    private CampingService campingService;
    private UserService userService;

    @Before
    public void setUp() throws NamingException {
        commentService = (CommentService) container.getContext().lookup("java:global/classes/ejb/CommentService!com.ecamping.service.CommentService");
        campingService = (CampingService) container.getContext().lookup("java:global/classes/ejb/CampingService!com.ecamping.service.CampingService");
        userService = (UserService) container.getContext().lookup("java:global/classes/ejb/UserService!com.ecamping.service.UserService");
    }

    @After
    public void tearDown() {
        commentService = null;
    }

    //@Test
    public void createComment() {
        User user = userService.findId((long) 4);
        Camping camping = campingService.findId((long) 2);

        Comment comment = new Comment();
        comment.setCamping(camping);
        comment.setUser(user);
        comment.setMessage("Mensagem para o camping 02");
        comment.setPublish(Calendar.getInstance().getTime());
        comment.setUpdate(Calendar.getInstance().getTime());

        commentService.persistence(comment);

        assertTrue(commentService.exist(comment));
    }

    //@Test
    public void updateComment() {

        String novaMensagem = "Nossa o camping é muito da hora!";
        Comment comment = commentService.findId((long) 2);
        comment.setMessage(novaMensagem);
        commentService.update(comment);
        comment = commentService.findId((long) 2);

        assertEquals("Nossa o camping é muito da hora!", comment.getMessage());

    }

   // @Test
    public void deleteComment() {
        Comment comment = commentService.findId((long) 5);
        commentService.delete(comment);
        assertNull(commentService.findId((long) 5));
    }

  //  @Test
    public void commentList() {
        List<Comment> list = commentService.getAllComments();
        assertEquals(7, list.size());
    }

   
}
