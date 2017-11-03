package net.nadschlaeger;

import java.util.ArrayList;
import java.util.List;

public class WorkingMemory {
	
	public List<Fact> globalFacts = new ArrayList<>();
	public List<Fact> inferredFacts = new ArrayList<>();
	public KnowledgeBase kb;
	
	public WorkingMemory(KnowledgeBase kb)
	{
		this.kb = kb;
	}
	
	
	public void assertFact(Fact fact)
	{
		inferredFacts.add(fact);		
	}
	
	public void retractFact(Fact fact)
	{
		inferredFacts.remove(fact);
	}
	
	
}
