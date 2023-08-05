package br.com.tiagohenriqueramos.autocenterfullstack.cofig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@EnableWebMvc
public class WebConfig  implements WebMvcConfigurer  {
	
	 @Override
	    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	        configurer.defaultContentType(MediaType.APPLICATION_XML);
	    }

	  /*  @Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        converters.add(mappingJackson2XmlHttpMessageConverter());
	    }*/

	    @Bean
	    public HttpMessageConverter<Object> jaxb2RootElementHttpMessageConverter() {
	        Jaxb2RootElementHttpMessageConverter converter = new Jaxb2RootElementHttpMessageConverter();
	        List<MediaType> supportedMediaTypes = new ArrayList<>();
	        supportedMediaTypes.add(MediaType.APPLICATION_XML);
	        converter.setSupportedMediaTypes(supportedMediaTypes);
	        return converter;
	    }
	    
	  /*  @Bean
	    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
	        MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
	        List<MediaType> supportedMediaTypes = new ArrayList<>();
	        supportedMediaTypes.add(MediaType.APPLICATION_XML);
	        converter.setSupportedMediaTypes(supportedMediaTypes);
	        return converter;
	    }*/
		
}
