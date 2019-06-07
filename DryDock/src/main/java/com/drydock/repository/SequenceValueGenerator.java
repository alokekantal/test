package com.drydock.repository;

import org.springframework.stereotype.Component;

@Component
public interface SequenceValueGenerator {
	
	public Long getNextTransactionNumberFromSequence(String seqName) throws Exception;

}
