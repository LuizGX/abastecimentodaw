package br.edu.iftm.upt.abastecimentodaw.formatter; 
  
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class LocalDateFormatter extends TemporalFormatter<LocalDate> {

	private static final Logger logger = LoggerFactory.getLogger(LocalDateFormatter.class);
	
	public LocalDateFormatter() {
		logger.debug(">>>> LocalDateFormatter -> Construtor padrao executado");
	}
	
	@Autowired
	private Environment env;
	
	@Override
	public String pattern(Locale locale) {
		logger.debug(">>>> Entrou em pattern do LocalDateFormatter -> Locale atual: {}", locale);
		return env.getProperty("localdate.format-" + locale, "dd/MM/yyyy");
	}

	@Override
	public LocalDate parse(String text, DateTimeFormatter formatter) {
		logger.debug(">>>> Entrou em parse do LocalDateFormatter -> text: {} formatter: {}", text, formatter);
		try {
			return LocalDate.parse(text, formatter);
		}catch (DateTimeParseException e) {
			logger.debug(">>>> O formato padrão não deu certo, tentando o Americano yyyy-MM-dd (usado no HTML)");
			return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
	}

}
