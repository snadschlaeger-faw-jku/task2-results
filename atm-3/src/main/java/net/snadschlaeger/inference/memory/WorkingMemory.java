package net.snadschlaeger.inference.memory;

import java.util.List;

import net.snadschlaeger.inference.model.Fact;

public interface WorkingMemory {

	public void assertFact(Fact fact);

	public void retractFact(Fact fact);

	public List<Fact> getAllFacts();

	public List<Fact> getRuntimeFacts();

	public void clear();

}
