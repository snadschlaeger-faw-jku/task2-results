package net.snadschlaeger.inference.memory;

import java.util.List;

import net.snadschlaeger.inference.model.Fact;
import net.snadschlaeger.inference.model.Rule;

public interface KnowledgeBase {

	public List<Fact> getAllFacts();

	public Fact getFact(String name);

	public void addFact(Fact fact);

	public void removeFact(Fact fact);

	public List<Rule> getAllRules();

	public Rule getRule(String name);

	public void addRule(Rule rule);

	public void removeRule(Rule rule);

}
