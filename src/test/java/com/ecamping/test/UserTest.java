package com.ecamping.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ecamping.entidade.User;
import com.ecamping.service.UserService;
import static com.ecamping.test.Teste.container;
import java.util.List;
import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author isabella
 */
public class UserTest extends Teste{

    private UserService userService;

    @Before
    public void setUp() throws NamingException {
        userService = (UserService) container.getContext().lookup("java:global/classes/ejb/UserService!com.ecamping.service.UserService");
    }

    @After
    public void tearDown() {
        userService = null;
    }

    @Test
    public void createUser() {
        User u1 = new User();
        u1.setName("Joao da Silva Oliveira");
        u1.setCpf("727.060.615-37");
        u1.setEmail("joao_oliveira@gmail.com");
        u1.setPassword("Senh@_976");

        userService.persistence(u1);

        assertTrue(userService.exist(u1));
    }

    @Test
    public void UpdateUser() {
        String nome = "Germino";
        User usuario = userService.findId((long) 1);
        usuario.setName(nome);
        userService.update(usuario);
        assertEquals("Germino", userService.findId((long) 1).getName());
    }

    @Test
    public void DeleteUser() {
        User usuario = userService.findId((long) 3);
        userService.delete(usuario);
        assertNull(userService.findId((long) 3));
    }

    @Test
    public void UsuarioPorId() {
        User user = userService.create();
        user = userService.findId((long) 6);
        assertNotNull(user);
    }

    @Test
    public void UsuarioPorEmail() {
        String email = "frida_kahlo@hotmail.com";
        User user = userService.getUserPorEmail(email);
        assertEquals("Frida Kahlo", user.getName());
    }

    @Test
    public void UsuarioPorCPF() {
        String cpf = "045.619.010-43";
        User usuario = userService.getUserPorCPF(cpf);
        assertNotNull(usuario);

    }

    @Test
    public void UsuariosComecandoPelaLetraA() {
        String letra = "a%";
        List<User> list = userService.getUserPorLetra(letra);
        for (User usuario : list) {
            assertEquals("A", usuario.getName().substring(0, 1).toUpperCase());
        }

    }

    @Test
    public void SelecionarTodosUsuarios() {
        List<User> usuarios = userService.getAllUsers();
        Assert.assertEquals(11, usuarios.size());

    }
    
   /* @Test
    public void atualizarInvalido() {
        User usuario = userService.findId((long) 7);
        usuario.setPassword("12345"); //Senha inválida
        try {
            userService.update(usuario);
            assertTrue(false);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(startsWith("Senha informada inválida"),
                                startsWith("Senha infadsad")));
            }
        }
    }*/

}
