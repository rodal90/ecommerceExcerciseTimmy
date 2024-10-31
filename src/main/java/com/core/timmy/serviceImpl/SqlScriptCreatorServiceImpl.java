package com.core.timmy.serviceImpl;

import java.sql.SQLException;

import org.h2.tools.Script;
import org.springframework.stereotype.Service;

import com.core.timmy.service.ISqlScriptCreatorService;

import lombok.extern.slf4j.Slf4j;



//Clase (service) para crear el script de sql,
//alternativa a la opción mediante application.properties.
// Incluye los datos mediante cláusulas 'insert into..'.

@Service
@Slf4j

public class SqlScriptCreatorServiceImpl implements ISqlScriptCreatorService {

	@Override
	public void dumpDB() {
		
		try {
			Script.process("jdbc:h2:mem:eventix","sas","sas","sql/dump.sql","","");
			
		}catch(SQLException e){
			
			log.info(e.getMessage());
			//e.printStackTrace();
		}
	
		
	}

}
