package com.mall.common.util.thread;

/**
 *
 */
public interface SensibleClone<T extends SensibleClone<T>> extends Cloneable {
    public T sensibleClone();
}
