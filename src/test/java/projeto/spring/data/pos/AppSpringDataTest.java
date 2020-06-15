package projeto.spring.data.pos;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.pos.dao.InterfaceSpringDataUser;
import projeto.spring.data.pos.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
	
	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	
	public void testInsert() {
		UsuarioSpringData usuario = new UsuarioSpringData();
		usuario.setNome("Jackson Kless");
		usuario.setLogin("jackson");
		usuario.setSenha("102030");
		usuario.setEmail("jackson@gmail.com");
		usuario.setIdade(37);
		
		interfaceSpringDataUser.save(usuario);
		System.out.println("Dados gravado com sucesso: "+usuario);
	}
	
	@Test
	public void testConsulta() {
		Optional<UsuarioSpringData> usuario = interfaceSpringDataUser.findById(1L) ;
		System.out.println("Consultando dados: "+usuario);
	}
	
	

}
