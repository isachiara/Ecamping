package com.ecamping.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ecamping.entidade.Booking;
import com.ecamping.entidade.Camping;
import com.ecamping.entidade.User;
import com.ecamping.service.BookingService;
import com.ecamping.service.CampingService;
import com.ecamping.service.UserService;
import static com.ecamping.test.Teste.container;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class BookingTest extends Teste {

    private BookingService bookingService;
    private CampingService campingService;
    private UserService userService;

    @Before
    public void setUp() throws NamingException {
        bookingService = (BookingService) container.getContext().lookup("java:global/classes/ejb/BookingService!com.ecamping.service.BookingService");
        userService = (UserService) container.getContext().lookup("java:global/classes/ejb/UserService!com.ecamping.service.UserService");
        campingService = (CampingService) container.getContext().lookup("java:global/classes/ejb/CampingService!com.ecamping.service.CampingService");
    }

    @After
    public void tearDown() {
        bookingService = null;
    }

    @Test
    public void createBooking() {
        User user = userService.findId((long) 2);
        Camping camping = campingService.findId((long) 2);
        Booking booking = new Booking();
        booking.setBookingDate(getData(31, 4, 2019));
        booking.setCamping(camping);
        booking.setUser(user);
        booking.setTent("INDIVIDUAL");
        bookingService.persistence(booking);

        assertTrue(bookingService.exist(booking));

    }

    private Date getData(Integer dia, Integer mes, Integer ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        return c.getTime();
    }

    @Test
    public void UpdateBooking() {
        Booking booking = bookingService.findId((long) 3);
        String tenda = "INDIVIDUAL";
        System.out.println(booking.getBookingDate());
        booking.setTent(tenda);
        bookingService.update(booking);
        booking = bookingService.findId((long) 3);

        assertEquals(tenda, booking.getTent());

    }

    @Test
    public void DeleteBooking() {
        Booking booking = bookingService.findId((long) 4);
        bookingService.delete(booking);
        assertNull(bookingService.findId((long) 4));
    }

    @Test
    public void bookingPorData() {
        Date data1 = getData(15, Calendar.FEBRUARY, 2019);
        Date data2 = getData(20, Calendar.DECEMBER, 2019);
        List<Booking> reservas = bookingService.getBookingPorData(data1, data2);
        assertEquals(9, reservas.size());
    }

    @Test
    public void bookingPorUsuario() {
        String usuario = "Fulano";
        List<Booking> reservas = bookingService.getBookingPorUser(usuario);
        assertEquals(2, reservas.size());
    }

    @Test
    public void bookingPorCamping() {
        String camping = "Camping 01";
        List<Booking> reservas = bookingService.getBookingPorCamping(camping);
        assertEquals(1, reservas.size());
    }

  /*  @Test
    public void atualizarInvalido() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(2010, Calendar.FEBRUARY, 23);
        
        
        Booking reserva = bookingService.findId((long) 5);
        reserva.setBookingDate(calendar.getTime()); //Tenda inválida
        try {
            bookingService.update(reserva);
            assertTrue(false);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(startsWith("Data informada inválida"),
                                startsWith("A data deve ser no futuro que a reserva está sendo efetuada.")));
            }
        }
    }*/

}
