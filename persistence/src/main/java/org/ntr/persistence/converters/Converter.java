package org.ntr.persistence.converters;

public interface Converter<E, D> {

    E toEntity(final D dto);

    D toDto(final E entity);

}
