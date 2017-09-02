package net.nadschlaeger;

import net.nadschlaeger.model.Fact;
import net.nadschlaeger.service.FactStorage;
import net.nadschlaeger.service.KnowledgeBase;

public class InferenceEngine extends BruteForceIE {

	public InferenceEngine(KnowledgeBase kb, FactStorage runtimeStorage) {
		super(kb, runtimeStorage);
		// TODO Auto-generated constructor stub
	}

	public void trigger(Fact cardFact) {
		runtimeStorage.assertFact(cardFact);
		checkRules();
	}


}
