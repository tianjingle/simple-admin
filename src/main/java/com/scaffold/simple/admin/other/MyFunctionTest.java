package com.scaffold.simple.admin.other;

import java.util.Objects;
import java.util.function.Function;

/**
 * @Author: tianjl
 * @Date: 2020/9/20 15:36
 * @Eamil: 2695062879@qq.com
 */
@FunctionalInterface
public interface MyFunctionTest<T,R> {

    R apply(T t);

    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        System.out.println("compose");
        return (V v) -> apply(before.apply(v));
    }

    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        System.out.println("and then");
        return (T t) -> after.apply(apply(t));
    }

    static <T> Function<T, T> identity() {
        System.out.println("end");
        return t -> t;
    }
}
