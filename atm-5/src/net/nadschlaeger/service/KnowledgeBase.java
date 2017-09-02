package net.nadschlaeger.service;

import java.util.List;

import net.nadschlaeger.model.Fact;
import net.nadschlaeger.model.Rule;

public class KnowledgeBase {

	private List<Rule> rules;
	private List<Fact> facts;

	public List<Fact> getFacts() {
		return facts;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setFacts(List<Fact> facts) {
		this.facts = facts;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

}
