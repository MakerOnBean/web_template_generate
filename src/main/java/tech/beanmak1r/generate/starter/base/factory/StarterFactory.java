package tech.beanmak1r.generate.starter.base.factory;

import tech.beanmak1r.generate.starter.base.starter.AbstractStarter;

/**
 * @author makeronbean
 * @createDate 2023-05-01  19:23
 * @description
 */

public interface StarterFactory {
    //<T> T getInstance(Class<T> clazz);
    <T extends AbstractStarter> T getInstance(Class<T> clazz);
}
