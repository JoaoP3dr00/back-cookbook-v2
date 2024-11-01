package com.back.cookbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.back.cookbook.business.Receita;
import com.back.cookbook.dataac.impl.ReceitaDAOImpl;

@SpringBootApplication
public class CookbookApplication {
	public static void main(String[] args) {
		SpringApplication.run(CookbookApplication.class, args);
		
		/* TESTE DE ADICIONAR RECEITA
			Receita r = new Receita("Como Bolar 1", "Pegue la jamba, enrole e faca fumasa", "Jamba e Seda", "2-5min", "1-5", "baixo");
			ReceitaDAOImpl rDao = new ReceitaDAOImpl();

			System.out.println("JJJJJ--------------------------------------");
			rDao.addReceita(r);
		*/
	}

}
