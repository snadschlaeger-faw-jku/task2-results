package net.nadschlaeger.model;

import java.util.ArrayList;
import java.util.List;

public class WorkingMemory {

	private List<Fact> inferredFacts = new ArrayList<>();

	public WorkingMemory() {
	}

	public List<Fact> getInferredFacts() {
		return inferredFacts;
	}

	public void assertFact(Fact fact) {
		inferredFacts.add(fact);
	}

	public void retractFact(Fact fact) {
		inferredFacts.remove(fact);
	}

}
