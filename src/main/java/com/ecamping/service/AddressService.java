/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.service;

import com.ecamping.entidade.Address;
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
@Stateless(name = "ejb/AddressService")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class AddressService extends Service<Address>{

    @PostConstruct
    public void init() {
        super.setClasse(Address.class);
    }
    @Override
    public Address create() {
        return new Address();
    }
   
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Address getEnderecoPorCEP(String cep){
        return super.findEntity(new Object[] {cep}, Address.ENDERECO_POR_CEP);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Address> getEnderecoPorCidade(String cidade){
        return super.findEntities(new Object[] {cidade}, Address.ENDERECO_POR_CIDADE);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Address> getEnderecoPorEstado(String estado){
        return super.findEntities(new Object[] {estado}, Address.ENDERECO_POR_ESTADO);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Address> getAddressQueIniciamComR(String letra){
        return super.findEntitiesJQL(new Object[] {letra}, "SELECT a FROM Address a WHERE a.cidade LIKE ?1 ORDER BY a.id DESC");
    }
    
    

   
}
