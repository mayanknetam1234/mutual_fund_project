package com.mayank.mutualFund.authentication.mapper;

public interface Mapper <X,Y>{
    X convertToEntity(Y a);
    Y convertToDto(X a);
}
