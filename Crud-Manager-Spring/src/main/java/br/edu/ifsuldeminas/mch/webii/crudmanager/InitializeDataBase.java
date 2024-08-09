package br.edu.ifsuldeminas.mch.webii.crudmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.UserRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class InitializeDataBase implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void run(String... args) throws Exception {
		User gabriel = new User();
		gabriel.setName("Gabriel");
		gabriel.setGender("M");
		gabriel.setEmail("gabriel@gmail.com");
		
		User lu = new User();
		lu.setName("Luiza Carvalho");
		lu.setGender("F");
		lu.setEmail("lu@gmail.com");
		
		userRepo.save(gabriel);
		userRepo.save(lu);
		
	}
	
}
