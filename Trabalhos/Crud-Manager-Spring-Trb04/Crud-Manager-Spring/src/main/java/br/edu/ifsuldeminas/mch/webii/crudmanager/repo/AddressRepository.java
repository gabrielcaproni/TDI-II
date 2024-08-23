package br.edu.ifsuldeminas.mch.webii.crudmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
