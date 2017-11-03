package net.snadschlaeger.inference.memory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.snadschlaeger.inference.model.Fact;
import net.snadschlaeger.inference.model.Rule;

public class KnowledgeBaseImpl implements KnowledgeBase {

	private Map<String, Fact> facts;

	private Map<String, Rule> rules;

	public KnowledgeBaseImpl() {
		facts = new LinkedHashMap<>();
		rules = new LinkedHashMap<>();
	}

	@Override
	public List<Fact> getAllFacts() {
		return new ArrayList<>(facts.values());
	}

	@Override
	public Fact getFact(String name) {
		return facts.get(name);
	}

	@Override
	public void addFact(Fact fact) {
		facts.put(fact.getName(), fact);
	}

	@Override
	public void removeFact(Fact fact) {
		facts.remove(fact.getName());
	}

	@Override
	public List<Rule> getAllRules() {
		return new ArrayList<>(rules.values());
	}

	@Override
	public Rule getRule(String name) {
		return rules.get(name);
	}

	@Override
	public void addRule(Rule rule) {
		rules.put(rule.getName(), rule);
	}

	@Override
	public void removeRule(Rule rule) {
		rules.remove(rule.getName());
	}

}
