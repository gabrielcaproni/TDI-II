package br.edu.ifsuldeminas.mch.webii.crudmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.UserRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class InitializeDataBase implements CommandLineRunner  {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void run(String... args) throws Exception {
		User emerson = new User();
		emerson.setName("Emerson Carvalho");
		emerson.setGender("M");
		emerson.setEmail("emerson@mail.com");
		
		User lu = new User();
		lu.setName("Luiza Carvalho");
		lu.setGender("F");
		lu.setEmail("luiza@mail.com");
		
		userRepo.save(emerson);
		userRepo.save(lu);
	}
}
