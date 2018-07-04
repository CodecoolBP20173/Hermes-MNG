package com.mng.hermes.util.http;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Set;

@Component
public class ProxyInjector {

    private Object proxy;

    public ProxyInjector() {
        scanClasspath();
    }

    private void scanClasspath() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.mng.hermes"))
                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner())
        );
        Set<Class<?>> assemblers = reflections.getTypesAnnotatedWith(HttpRequestAssembler.class);
        Class[] proxyClasses = assemblers.toArray(new Class[0]);
        proxy = Proxy.newProxyInstance(
                ProxyInjector.class.getClassLoader(),
                proxyClasses,
                new RequestInvoker()
        );
        Test test = Test.class.cast(proxy);
        test.foo();
        AnotherTest anotherTest = AnotherTest.class.cast(proxy);
        anotherTest.bar("KEK");
    }
}
