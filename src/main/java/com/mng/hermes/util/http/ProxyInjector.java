package com.mng.hermes.util.http;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

@Component
public class ProxyInjector {

    private Object proxy;
    private RandomImpl ri;

    public ProxyInjector(RandomImpl ri) {
        this.ri = ri;
        scanClasspath();
    }

    private void scanClasspath() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.mng.hermes"))
                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner(), new FieldAnnotationsScanner())
        );
        Set<Class<?>> assemblers = reflections.getTypesAnnotatedWith(HttpRequestAssembler.class);
        Set<Field> injectionPoints = reflections.getFieldsAnnotatedWith(HttpRequestAssembler.class);
        Class[] proxyClasses = assemblers.toArray(new Class[0]);
        proxy = Proxy.newProxyInstance(
                ProxyInjector.class.getClassLoader(),
                proxyClasses,
                new RequestInvoker()
        );
//        Test test = Test.class.cast(proxy);
//        OtherTest anotherTest = OtherTest.class.cast(proxy);
        for (Field ip : injectionPoints) {
            ip.setAccessible(true);
            try {
                ip.set(ri, ip.getType().cast(proxy));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        ri.getSecretField1().getJsonWithBearer();
        ri.getSecretField2().postJsonWithHeadersAndQueryAndBody("KEK");
    }
}
