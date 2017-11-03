package net.nadschlaeger;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBase {
	
	public List<Fact> facts = new ArrayList<>();
	public List<Rule> rules = new ArrayList<>();
	
	
	public List<Fact> getAllFacts()
	{			
		return this.facts;		
	}
	
	public Fact getFact(String name)
	{
		for(Fact f:facts)
		{
			if (f.getName().equals(name)) return f;
		}
		return null;
	}
	
	public void addFact(Fact fact)
	{
		facts.add(fact);
	}
	
	public void setFacts(List <Fact> facts)
	{
		this.facts = facts;		
	}
	
	
	public void removeFact(Fact fact)
	{
		facts.remove(fact);
	}
	
	public List<Rule> getAllRules()
	{
		return this.rules;
	}
	
	public Rule getRule(String name)
	{
		for(Rule r:rules)
		{
			if (r.getName().equals(name)) return r;
		}
		return null;
		
	}
	
	public void addRule(Rule rule)
	{
		rules.add(rule);
	}
	
	public void setRules(List <Rule> rules)
	{
		this.rules = rules;		
	}
	
	public void removeRule(Rule rule)
	{
		rules.remove(rule);
	}
	
	
	

}
