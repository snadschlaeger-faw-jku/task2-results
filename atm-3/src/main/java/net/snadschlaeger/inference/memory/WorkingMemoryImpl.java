package net.snadschlaeger.inference.memory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.snadschlaeger.inference.model.Fact;

public class WorkingMemoryImpl implements WorkingMemory {

	private Map<String, Fact> inferredFacts;

	private KnowledgeBase knowledgeBase;

	public WorkingMemoryImpl(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
		inferredFacts = new LinkedHashMap<>();
	}

	@Override
	public void assertFact(Fact fact) {
		inferredFacts.put(fact.getName(), fact);
	}

	@Override
	public void retractFact(Fact fact) {
		inferredFacts.remove(fact.getName());
	}

	@Override
	public List<Fact> getAllFacts() {
		List<Fact> facts = getRuntimeFacts();
		facts.addAll(knowledgeBase.getAllFacts());
		return facts;
	}

	@Override
	public List<Fact> getRuntimeFacts() {
		return new ArrayList<>(inferredFacts.values());
	}

	@Override
	public void clear() {
		inferredFacts.clear();
	}

}
