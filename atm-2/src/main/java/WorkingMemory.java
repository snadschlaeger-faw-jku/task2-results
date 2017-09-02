package main.java;

import java.util.ArrayList;
import java.util.List;

import main.java.model.Fact;

public class WorkingMemory {

	public static List<Fact> globalFacts = new ArrayList<>();
	public static List<Fact> inferredFacts = new ArrayList<>();

	public static void assertFact(Fact fact) {
		inferredFacts.add(fact);
	}

	public static void retract(Fact fact) {
		inferredFacts.remove(fact);
	}

}
