package com.lastminute.taxesquiz.language.domain.service;

import com.lastminute.taxesquiz.language.domain.DomainType;
import com.lastminute.taxesquiz.language.domain.GenericDomain;

import java.util.Collection;

public interface DomainService {

    <T extends GenericDomain> Collection<T> retrieveAllGenericDomain(DomainType domainType);

    <T extends GenericDomain>  T retrieveGenericDomain(DomainType domainType, String code, String policy);

    boolean contains(DomainType domainType, String code);

}
