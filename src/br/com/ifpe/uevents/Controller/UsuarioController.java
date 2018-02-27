package br.com.ifpe.uevents.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifpe.uevents.Dao.UsuarioDao;
import br.com.ifpe.uevents.Model.Atividade;
import br.com.ifpe.uevents.Model.Usuario;

@Controller
public class UsuarioController {
	
	@RequestMapping("/cadasUsuario")
	public String cadastro(){
		return "telas/cadasUsuario";
	}
	@RequestMapping("inserirUsuario")
	public String inserirUser(Usuario usuario){
		UsuarioDao dao = new UsuarioDao();
		dao.cadastrar(usuario);
		return "telas/cadasUsuario";
	}
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session, Model model) {
		UsuarioDao dao = new UsuarioDao();
		Usuario usuarioLogado = dao.buscarUsuario(usuario);
		if (usuarioLogado != null) {
			//usuarioLogado.setAtividades(dao.listarAtvs(usuario));
			//List<Atividade> atvsUsuarioLogado = new UsuarioDao().listarAtvs(usuarioLogado);
			//model.addAttribute("atvsUsuarioLogado", atvsUsuarioLogado);
			session.setAttribute("usuarioLogado", usuarioLogado);
		    return "telas/inicialEvento";
		}
		model.addAttribute("msg", "Login e/ou senha inválidos.");
		return "telas/index";
	}
	 @RequestMapping("logout")
	 public String logout(HttpSession session) {
	  session.invalidate();
	  return "telas/index";
	}

}
