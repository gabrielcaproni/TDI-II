package br.edu.ifsuldeminas.mch.webii.crudmanager;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Address;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Mechanic;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Workshop;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.AddressRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.MechanicRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.UserRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.WorkshopRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class InitializeDataBase implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private WorkshopRepository workshopRepository;
	
	@Autowired
	private MechanicRepository mechanicRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User gabriel = new User();
		gabriel.setName("Gabriel Pegoraro");
		gabriel.setGender("M");
		gabriel.setEmail("gabriel@gmail.com");

		Address addressG = new Address();
		addressG.setPlace("Rua dos bobos");
		addressG.setNumber(250);
		addressG.setZipCode("37750-000");

		User guilherme = new User();
		guilherme.setName("Guilherme Henrique");
		guilherme.setGender("M");
		guilherme.setEmail("guilherme@gmail.com");

		Address addressGui = new Address();
		addressGui.setPlace("Rua dos Joazeiros");
		addressGui.setNumber(582);
		addressGui.setZipCode("25874-021");

		addressRepository.save(addressG);
		addressRepository.save(addressGui);
		addressRepository.flush();

		gabriel.setAddress(addressG);
		guilherme.setAddress(addressGui);

		userRepository.save(gabriel);
		userRepository.save(guilherme);
		
		// Criando primeira oficina
		
		Workshop of1 = new Workshop();
		of1.setWorkshopName("Simas Turbo");
		of1.setCapacity(5);
		
		Set<String> servicos = new HashSet<>();
        servicos.add("Troca de Óleo");
        servicos.add("Alinhamento");
        servicos.add("Pintura");
        
        System.out.println("Serviços disponíveis:");
        for (String servico : servicos) {
            System.out.println(servico);
        }
		
		Address locOf1 = new Address();
		locOf1.setPlace("Rua irmãos Germano");
		locOf1.setNumber(260);
		locOf1.setZipCode("37760-000");
		
		addressRepository.save(locOf1);
		addressRepository.flush();
		
		of1.setAddress(locOf1);
		
		workshopRepository.save(of1);
		
		Mechanic mec1 = new Mechanic();
		mec1.setName("Alceu Tavares");
		mec1.setSpecialty("Troca de óleo | Troca de pneu | Alinhamento");
		mec1.setOpeningHours("07:00 às 16:00 | Segunda á Sexta feira");
		mec1.setPhone("(35) 9 9999-9999");
		mec1.setWorkshop(of1);
		
		mechanicRepository.save(mec1);
	}

}
