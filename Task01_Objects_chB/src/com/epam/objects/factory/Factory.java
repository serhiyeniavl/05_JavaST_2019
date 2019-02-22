package com.epam.objects.factory;

import com.epam.objects.entity.Geometry;

/**
 * Interface factory is a marker for factory methods implementation.
 * @param <T> type of object for factory implementation.
 */
public interface Factory<T extends Geometry> {
}
