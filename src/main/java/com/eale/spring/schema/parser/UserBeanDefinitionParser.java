package com.eale.spring.schema.parser;

import com.eale.spring.schema.pojo.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;


/**
 * @Author HLD
 * @Date 2021/8/4 0004
 **/
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element){
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder bean){
        String userName = element.getAttribute("userName");
        String email = element.getAttribute("email");
        if (StringUtils.hasText(userName)){
            bean.addPropertyValue("userName",userName);
        }
        if (StringUtils.hasText(email)){
            bean.addPropertyValue("email",email);
        }
    }


}
