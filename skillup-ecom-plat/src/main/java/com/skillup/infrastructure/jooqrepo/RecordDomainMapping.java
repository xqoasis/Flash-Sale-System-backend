package com.skillup.infrastructure.jooqrepo;

public interface RecordDomainMapping<R, D> {
    D toDomain(R r);
    R toRecord(D d);
}
