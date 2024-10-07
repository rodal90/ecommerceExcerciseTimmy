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

@Component
@Slf4j

/*Para tener lenguajes distintos. PAra meter un nuevo idioma: message_XX_XX_properties, las primeras XX son el pais
 * las segundas XX son el lenguaje. */
public class LanguageResourceBundleMessage {

	private final Resource[] resourceArray;

    public LanguageResourceBundleMessage(@Value("classpath:**/message_*.properties") Resource[] resourceArray) {
        this.resourceArray = resourceArray;
    }
   
    public Set<Locale> getLocaleSetFromResourceArray() {
        log.info("resourceArray= " + resourceArray.length);
        return Arrays.stream(this.resourceArray)
                .peek(x -> log.info(x.getFilename()))
                .map(resource ->
                {
                    final String localeCode = resource.getFilename().split("message_")[1].split(".properties")[0];
                    return Locale.forLanguageTag(localeCode);
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
