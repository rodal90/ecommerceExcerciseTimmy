package com.core.timmy.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/*Para tener lenguajes distintos. PAra meter un nuevo idioma: message_XX_XX_properties, las primeras XX son el pais
 * las segundas XX son el lenguaje. */

//un único objeto de los patrones de controller, de service y de repositories, son singleton solo uno. Entitie si son mas.

@Component
@Slf4j
public class LanguageResourceBundleMessage {

	private final Resource[] resourceArray;

    public LanguageResourceBundleMessage(@Value("classpath:**/message_*.properties") Resource[] resourceArray) {//con el valor classpath value, deberia irse buscar los ficheros de imagenes en static img
    	//prestar mucha atencion a los patrones de busqueda, tiene que ser una direccion relativa. el gión bajo es para que obvie el message.poperties gneral
    	//los es_Es o en_US son todos objetos tipo Locale
        this.resourceArray = resourceArray;
    }
   
    public Set<Locale> getLocaleSetFromResourceArray() {
        log.info("resourceArray= " + resourceArray.length);
        return Arrays.stream(this.resourceArray)
                .peek(x -> log.info(x.getFilename()))
                .map(resource ->
                {
                    final String localeCode = resource.getFilename().split("message_")[1].split(".properties")[0]; // [1][0] se divide y se queda con un trozo lo divide al string.
                    return Locale.forLanguageTag(localeCode); //locale es una clase con métodos estáticos, forLanguageTag(lo penemos dentro el trozo) Es un metodo de la clase Locale.
                })
            .collect(Collectors.toSet());
    }

    public List<String> getLanguageTagStringListFromResourceArray() {
        log.info("resourceArray= " + resourceArray.length);
        return Arrays.stream(this.resourceArray)
            .peek(x -> log.info(x.getFilename()))
            .map(resource -> resource.getFilename().split("message_")[1].split(".properties")[0])
            .collect(Collectors.toList());
    }
   
}
