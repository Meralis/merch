package org.klim.istock.converter;

public interface IConverter<D, E> {
    E toEntity(D dto);
    D toDto(E entity);
}
