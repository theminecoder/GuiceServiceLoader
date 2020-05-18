package me.theminecoder;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.util.ServiceLoader;
import java.util.stream.Stream;

@Singleton
public class GuiceServiceLoader {

    private final Injector injector;

    @Inject
    public GuiceServiceLoader(Injector injector) {
        this.injector = injector;
    }

    public <T> Stream<? extends T> load(Class<T> service) {
        return this.load(service, Thread.currentThread().getContextClassLoader());
    }

    public <T> Stream<? extends T> load(Class<T> service, ClassLoader loader) {
        return ServiceLoader.load(service, loader).stream().map(ServiceLoader.Provider::type).map(injector::getInstance);
    }

}
