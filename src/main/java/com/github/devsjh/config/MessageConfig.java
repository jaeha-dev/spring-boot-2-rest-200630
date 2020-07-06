package com.github.devsjh.config;

import net.rakugakibox.util.YamlResourceBundle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @title  : 다국어 메시지 설정 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Configuration
public class MessageConfig implements WebMvcConfigurer {

    @Bean // 세션에 지역 설정 (기본 값은 대한한국이다.)
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.KOREA);

        return resolver;
    }

    @Bean // 지역 설정을 변경하는 인터셉터 (요청 시, lang 정보를 지정하여 전달하면 지역 언어가 변경된다.)
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");

        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean(name = "messageSource") // YAML 파일을 참조하는 MessageSoure 선언
    public MessageSource messageSource(@Value("${spring.messages.basename}") String basename,
                                       @Value("${spring.messages.encoding}") String encoding) {
        YamlMessageSource messageSource = new YamlMessageSource();
        messageSource.setBasename(basename);
        messageSource.setDefaultEncoding(encoding);
        // messageSource.setCacheSeconds(300); // 프로퍼티 파일의 변경을 감지할 시간 간격
        messageSource.setAlwaysUseMessageFormat(true);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setFallbackToSystemLocale(true);

        return messageSource;
    }

    // Locale 정보에 따라 다른 YAML 파일을 읽도록 지정한다.
    private static class YamlMessageSource extends ResourceBundleMessageSource {

        @Override
        protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
            return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
        }
    }
}