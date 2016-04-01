package com.education.calculator.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final String ROOT = "/";

    @Override
    protected String[] getServletMappings() {
        return new String[]{ROOT};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{DatabaseConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }
}