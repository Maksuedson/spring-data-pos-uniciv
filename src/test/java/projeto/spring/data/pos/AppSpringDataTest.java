package projeto.spring.data.pos;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.pos.dao.InterfaceSpringDataUser;
import projeto.spring.data.pos.dao.InterfaceTelefone;
import projeto.spring.data.pos.model.Telefone;
import projeto.spring.data.pos.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
	
	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;
	
	
	public void testInsert() {
		UsuarioSpringData usuario = new UsuarioSpringData();
		usuario.setNome("Foo fite");
		usuario.setLogin("foo");
		usuario.setSenha("111-222");
		usuario.setEmail("thecure@gmail.com");
		usuario.setIdade(33);
		
		interfaceSpringDataUser.save(usuario);
		System.out.println("Dados gravado com sucesso: "+usuario);
	}
	
	
	public void testConsulta() {
		Optional<UsuarioSpringData> usuario = interfaceSpringDataUser.findById(1L) ;
		System.out.println("Consultando dados: "+usuario);
	}
	
	
	public void testConsultaTodos() {
		List<UsuarioSpringData> lista =  (List<UsuarioSpringData>) interfaceSpringDataUser.findAll();
		for (UsuarioSpringData usuario : lista) {
			System.out.println("Consultando dados: "+usuario);
		}
		
	}
	
	
	public void testConsultaTodos2() {
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		for (UsuarioSpringData usuario : lista) {
			System.out.println("Listando todos: "+usuario);			
		}
		
	}
	
	
	public void testUpdate() {
		Optional<UsuarioSpringData> usuario = interfaceSpringDataUser.findById(4L) ;
		UsuarioSpringData data = usuario.get();
		data.setNome("Erick Gomes da Silva");
		
		interfaceSpringDataUser.save(data);
		
		System.out.println("Usuario alterado com sucesso: "+data);
		
		testConsultaTodos();
		
	}
	
	
	public void testDelete() {
		interfaceSpringDataUser.deleteById(6l);				
	}
	
	
	public void testDelete2() {
		Optional<UsuarioSpringData> usuarios = interfaceSpringDataUser.findById(8l) ;
		
		interfaceSpringDataUser.delete(usuarios.get());
		System.out.println("Deletando usuário: "+usuarios.get().getNome());
	}	
	
	
	public void testConsultaPorNome() {
		List<UsuarioSpringData> lista = interfaceSpringDataUser.buscaPorNome("Leonardo");
		
		for (UsuarioSpringData usuarios : lista) {
			System.out.println("Consultando nome: "+usuarios);
		}
	}
	
	
	public void testConsultaPorNomeParam() {
		UsuarioSpringData usuario = interfaceSpringDataUser.buscaPorNomeParam("Leonardo");
		System.out.println("Consulta por parâmetro: "+usuario.getNome());
		
	}
	
	
	public void testDeletarPorNome() {
		interfaceSpringDataUser.deletarPorNome("The cure2");
	}
	
	
	public void testUpdateEmailPorNome() {
		interfaceSpringDataUser.atualizarEmailPorNome("foofighters@hotmail.com", "Foo fite");
	}
	
//-----------------------------------------------------------------------------------------------
	
	
	public void testInsertTelefone() {
		
		Optional<UsuarioSpringData> usuario = interfaceSpringDataUser.findById(1L);
		
		Telefone telefone = new Telefone();
		telefone.setNumero("(62)9 9457-2398");
		telefone.setTipo("Trabalho");
		telefone.setUsuario(usuario.get());
		
		interfaceTelefone.save(telefone);
		
	}
	
	@Test
	public void testConsultaComTelefone() {
		Optional<UsuarioSpringData> usuario = interfaceSpringDataUser.findById(1L) ;
		System.out.println("Consultando dados: "+usuario);
		
		for (Telefone telefone : usuario.get().getTelefones()) {
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getTipo());
		}
	}	
	
}
