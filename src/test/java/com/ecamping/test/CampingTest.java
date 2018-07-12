package com.ecamping.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ecamping.entidade.Address;
import com.ecamping.entidade.Camping;
import com.ecamping.service.AddressService;
import com.ecamping.service.CampingService;
import static com.ecamping.test.Teste.container;
import java.util.List;
import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ramon
 */
public class CampingTest extends Teste {

    private CampingService campingService;
    private AddressService addressService;

    @Before
    public void setUp() throws NamingException {
        campingService = (CampingService) container.getContext().lookup("java:global/classes/ejb/CampingService!com.ecamping.service.CampingService");
        addressService = (AddressService) container.getContext().lookup("java:global/classes/ejb/AddressService!com.ecamping.service.AddressService");
    }

    @After
    public void tearDown() {
        campingService = null;
        addressService = null;
    }

    @Test
    public void CreateCamping() {
        Camping camping = gerarCamping();
        campingService.persistence(camping);
        assertEquals(true, campingService.exist(camping));

    }

    @Test
    public void DeleteCamping() {
        Camping camping = campingService.create();
        camping = campingService.findId((long) 6);
        campingService.delete(camping);
        assertNull(campingService.findId((long) 6));
    }

    @Test
    public void CampingSemReservas() {
        assertEquals(2, campingService.getCampingsSemReserva().size());
    }

    @Test
    public void CampingPorNome() {
        String nome = "Camping 03";
        Camping camping = campingService.getCampingsPorNome(nome);
        assertNotNull(camping);
        assertEquals(nome, camping.getName());
    }

    @Test
    public void UpdateEndereco() {
        String cidade = "Olinda";
        String cep = "98234-943";

        Address endereco = addressService.create();
        endereco = addressService.getEnderecoPorCEP(cep);
        endereco.setCidade(cidade);
        addressService.update(endereco);

        endereco = addressService.getEnderecoPorCEP(cep);
        assertEquals("Olinda", endereco.getCidade());

    }

    @Test
    public void EnderecoPorId() {
        Address endereco = addressService.findId(new Long(5));
        assertNotNull(endereco);
        assertEquals("28920-012", endereco.getCep());
    }

    @Test
    public void EnderecoPorCidade() {
        String cidade = "Recife";
        List<Address> resultados = addressService.getEnderecoPorCidade(cidade);
        for (Address endereco : resultados) {
            assertEquals("Recife", endereco.getCidade());
        }
    }

    @Test
    public void EnderecoPorEstado() {
        String estado = "Pernambuco";
        List<Address> resultados = addressService.getEnderecoPorEstado(estado);
        for (Address endereco : resultados) {
            assertEquals("Pernambuco", endereco.getEstado());
        }
    }

    @Test
    public void JPQLretornaAddressQueIniciamComR() {
        List<Address> lista = addressService.getAddressQueIniciamComR("r%");
        assertEquals(2, lista.size());
    }

    private Camping gerarCamping() {

        Address address = new Address();
        address.setEstado("Estado random");
        address.setCidade("Cidade random");
        address.setCep("23441-432");
        address.setBairro("Um bairro ai");
        address.setNumero("453");
        address.setRua("Rua de teste para create Camping");

        Camping camping = new Camping();
        camping.setName("Camping 08");
        camping.setInfo("informacao não tão pequena");
        camping.setPhone("(11) 98542-1923");
        camping.setAddress(address);
        return camping;
    }

    @Test
    public void UpdateCamping() {
        String update = "info teste";
        Camping camping = campingService.findId((long) 8);
        System.out.println(camping.getName());
        camping.setInfo(update);
        System.out.println(camping.getName());
        campingService.update(camping);
        camping = campingService.findId((long) 8);

        assertEquals(update, camping.getInfo());
    }

  /*  @Test
    public void atualizarInvalido() {
        Camping camping = campingService.findId((long) 5);
        camping.setPhone("1199667865"); //Telefone inválido
        try {
            campingService.update(camping);
            assertTrue(false);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(startsWith("Telefone inserido de forma incorreta"),
                                startsWith("forma correta: (xx) xxxxx-xxxx")));
            }
        }
    }*/

}
