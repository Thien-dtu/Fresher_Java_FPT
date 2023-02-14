package main.java.com.service;

import java.util.List;

import main.java.com.entities.Candidate;

public interface ICandidarteService {
	<E extends Candidate> void saveCandidateAfterInputData(E e, List<String> list);
}
