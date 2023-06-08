package pl.taw.infrastructure.configuration;

/**
 * ObjectMapper configuration
 * 1. Serializacja - zamiana obiektów Java na JSON
 * 2. Deserializacja - przekształcenie JSON-ów na obiekty Java
 * 3. Konfiguracja - sporo możliwości konfiguracji
 * 4. Przetwarzanie drzewa JSON - można załadować dane jako drzewo i wykonać na nim różne operacje, takie jak wyszukiwanie, modyfikacji i usuwanie elementów
 * ############################
 * .registerModule(new JavaTimeModule()) -> wsparcie dla klas z pakietu java.time (LocalDate, LocalDateTime itp.), poprawne przekształcanie dat i czasu na JSON
 * .registerModule(new Jdk8Module()) -> wsparcie dla typów danych z rodziny Optional
 * .configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false) -> wyłączenie domyślnego zapisu daty jako TIMESTAMP (niezrozumiałego dla człowieka -> 16050540484406045504)
 * .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) -> wyłącza zgłaszanie błędu, gdy pojawi się nieznana właściwość, której nie będzie można zamienić na Javę
 * .setSerializationInclusion(JsonInclude.Include.NON_NULL) -> wartości NULL nie będą uwzględnianie podczas serializacji z Java na JSON
 */


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .registerModule(new Jdk8Module())
                .configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
