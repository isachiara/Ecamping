/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.ecamping.entidade.Camping;
import com.ecamping.entidade.User;
import com.ecamping.entidade.Rating;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laura
 */
public class RatingTest {
    
    public RatingTest() {
    }
    
    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        emf = Persistence.createEntityManagerFactory("ecampingPersistence");
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        beginTransaction();
    }

    @After
    public void tearDown() {
        commitTransaction();
        em.close();
    }

    private void beginTransaction() {
        et = em.getTransaction();
        et.begin();
    }

    private void commitTransaction() {
        try {
            et.commit();
        } catch (Exception ex) {
            if (et.isActive()) {
                et.rollback();
            }  
        }
    }
    
    //TEST CLASSES
    @Test
    public void addRating(){
       
        User user = em.find(User.class, 1L);
        Camping camping = new Camping();
        camping.setName("Camping 08");
        camping.setInfo("informacao pequena");
        camping.setPhone("(81) 98502-3814");
        
        User u1 = new User();
        u1.setName("Joao da Silva Oliveira");
        u1.setCpf("10826570429");
        u1.setEmail("joao_oliveira@gmail.com");
        u1.setPassword("Senh@_32");

        Rating rating1 = new Rating();
        rating1.setValue(2);
        rating1.setUser(u1);
        rating1.setCamping(camping);
        rating1.setPublish(Calendar.getInstance().getTime());
        rating1.setUpdate(Calendar.getInstance().getTime());
        try{

            em.persist(rating1);
            em.flush();
            assertNotNull(rating1);
            
        } catch(ConstraintViolationException ex){
        
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            
            
            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}\n\n", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }
            assertEquals(1, constraintViolations.size());
        }
    }
    
    @Test
    public void updateRating(){
        

        TypedQuery<Rating> q = em.createQuery("UPDATE Rating r SET r.value = :value WHERE r.value = :id", Rating.class);
        q.setParameter("value", 4);
        q.setParameter("id", 1);
        q.executeUpdate();
        
        assertEquals(4, q.getParameterValue("value"));
        
    }
    
    @Test
    public void ratingList(){
        
        Query ratings = em.createNamedQuery("Rating.AllRating", Rating.class);
        List<Rating> list = ratings.getResultList();
        assertEquals(8, list.size());
    }
    
    @Test
    public void ratingsBetweenOneAndThree(){ 
        TypedQuery<Rating> q = em.createQuery("SELECT r FROM Rating r WHERE r.value BETWEEN 1 AND 3", Rating.class);
        List<Rating> resultado = q.getResultList();
        assertEquals(3, resultado.size());
    }
    
    @Test
    public void ratingDelete() {
        
        Query q = em.createQuery("DELETE FROM Rating r WHERE r.publish LIKE '2013-12-07'");

        q.executeUpdate();
        
        TypedQuery<Rating> query = em.createQuery("SELECT r FROM Rating r WHERE r.publish LIKE '2013-12-07'", Rating.class);
        List<Rating> rating = query.getResultList();
        assertEquals(0, rating.size());
    }
    
}
