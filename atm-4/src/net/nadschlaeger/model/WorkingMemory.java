package net.nadschlaeger.model;

import java.util.ArrayList;
import java.util.List;

public class WorkingMemory {

	List<Fact> globalFacts;
	List<Fact> inferredFacts;

	public WorkingMemory() {
		globalFacts = new ArrayList<Fact>();
		inferredFacts = new ArrayList<Fact>();
	}

	public void assertFact(Fact fact) {
		inferredFacts.add(fact);
	}

	public List<Fact> getInferredFacts() {
		return inferredFacts;
	}

	public void clearInferredFacts() {
		inferredFacts = new ArrayList<Fact>();
	}

}
