package com.ecamping.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ecamping.entidade.Camping;
import com.ecamping.entidade.Rating;
import com.ecamping.entidade.User;
import com.ecamping.service.CampingService;
import com.ecamping.service.RatingService;
import com.ecamping.service.UserService;
import static com.ecamping.test.Teste.container;
import java.util.Calendar;
import java.util.List;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author laura
 */
public class RatingTest extends Teste {

    private RatingService ratingService;
    private CampingService campingService;
    private UserService userService;

    @Before
    public void setUp() throws NamingException {
        ratingService = (RatingService) container.getContext().lookup("java:global/classes/ejb/RatingService!com.ecamping.service.RatingService");
        campingService = (CampingService) container.getContext().lookup("java:global/classes/ejb/CampingService!com.ecamping.service.CampingService");
        userService = (UserService) container.getContext().lookup("java:global/classes/ejb/UserService!com.ecamping.service.UserService");

    }

    @After
    public void tearDown() {
        ratingService = null;
    }

    //@Test
    public void createRating() {
        User user = userService.findId((long) 4);
        Camping camping = campingService.findId((long) 4);

        Rating rating = new Rating();
        rating.setCamping(camping);
        rating.setUser(user);
        rating.setValue(3);
        rating.setPublish(Calendar.getInstance().getTime());
        rating.setUpdate(Calendar.getInstance().getTime());

        ratingService.persistence(rating);

        assertTrue(ratingService.exist(rating));
    }

   // @Test
    public void updateRating() {
        int valor = 4;
        Rating r = ratingService.findId((long) 11);
        r.setValue(valor);
        ratingService.update(r);

        assertEquals(4, ratingService.findId((long) 11).getValue());

    }

   // @Test
    public void ratingDelete() {
        Rating r = ratingService.findId((long) 9);
        ratingService.delete(r);
        assertNull(ratingService.findId((long) 9));
    }

  //  @Test
    public void ratingList() {
        List<Rating> list = ratingService.getAllRatings();
        assertEquals(7, list.size());
    }

}
