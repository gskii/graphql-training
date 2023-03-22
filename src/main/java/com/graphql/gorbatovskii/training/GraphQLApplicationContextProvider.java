package com.graphql.gorbatovskii.training;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class GraphQLApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        context = ac;
    }
}
