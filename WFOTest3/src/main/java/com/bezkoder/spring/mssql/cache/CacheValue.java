package com.bezkoder.spring.mssql.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class CacheValue {
    Object value;
    Instant expireAt;

}
