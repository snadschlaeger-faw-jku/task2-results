package net.nadschlaeger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.nadschlaeger.model.Clause;
import net.nadschlaeger.model.Fact;
import net.nadschlaeger.model.KnowledgeBase;
import net.nadschlaeger.model.Rule;
import net.nadschlaeger.model.Slot;
import net.nadschlaeger.model.WorkingMemory;

class IE {
	public static void main(String[] args) {
		
		KnowledgeBase kb = new KnowledgeBase();

		String line;
		try {
			InputStream fis = new FileInputStream("facts");
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);

			while ((line = br.readLine()) != null) {

				List<Slot> properties = new ArrayList<Slot>();
				for (String prop : line.split(";")[1].split(",")) {
					Slot s = new Slot();
					s.setName(prop.split(":")[0]);
					s.setType("java.lang.Integer");
					s.setValue(prop.split(":")[1]);
					properties.add(s);
				}
				Fact f = new Fact();
				f.setName(line.split(";")[0]);
				f.setSlots(properties);
				kb.addFact(f);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}


		// ----
		Rule rule1 = new Rule();
		Clause condition1 = new Clause();
		condition1.setExpression(ClauseOperators.exists);
		condition1.setParameter(createCardFact());

		rule1.setConditions(Arrays.asList(condition1));

		Clause consiquence1 = new Clause();
		consiquence1.setExpression(ClauseOperators.assertFact);
		consiquence1.setParameter(createPinFact());
		
		rule1.setConsequences(Arrays.asList(consiquence1));
		// ----
		Rule rule2 = new Rule();
		Clause condition2_0 = new Clause();
		condition2_0.setExpression(ClauseOperators.take);
		condition2_0.setParameter(createPinFact());
		
		Clause condition2_1 = new Clause();
		condition2_1.setExpression(ClauseOperators.exists);

		rule2.setConditions(Arrays.asList(condition2_0,condition2_1));

		Clause consiquence2_1 = new Clause();
		consiquence2_1.setExpression(ClauseOperators.assertFact);
		consiquence2_1.setParameter(createWithdraw());

		rule2.setConsequences(Arrays.asList(consiquence2_1));
		
		kb.addRule(rule1);
		kb.addRule(rule2);


		WorkingMemory wm = new WorkingMemory();
		BruteForceIE ie = new BruteForceIE(wm, kb);

		ie.checkRules();
	}

	private static Fact createCardFact() {

		Fact fact = new Fact();
		fact.setName("credit-card");
		Slot cardNumberSlot = new Slot();
		cardNumberSlot.setName("card-number");
		cardNumberSlot.setType("java.lang.Integer");
		cardNumberSlot.setValue(123);
		fact.setSlots(Arrays.asList(cardNumberSlot));

		return fact;

	}

	private static Fact createPinFact() {

		Fact fact = new Fact();
		fact.setName("credit-card-pin");
		Slot cardNumberSlot = new Slot();
		cardNumberSlot.setName("card-number");
		cardNumberSlot.setType("java.lang.Integer");
		cardNumberSlot.setValue(123);
		Slot cardActiveSlot = new Slot();
		cardActiveSlot.setName("pin-number");
		cardActiveSlot.setType("java.lang.Integer");
		// cardActiveSlot.setValue(true);
		fact.setSlots(Arrays.asList(cardNumberSlot, cardActiveSlot));

		return fact;

	}
	
	
	private static Fact dummyPinFact() {

		Fact fact = new Fact();
		fact.setName("credit-card-pin");
		Slot cardNumberSlot = new Slot();
		fact.setSlots(Arrays.asList(cardNumberSlot));

		return fact;

	}
	
	private static Fact createWithdraw() {

		Fact fact = new Fact();
		fact.setName("withdraw-money");
		Slot amount = new Slot();
		amount.setName("amount");
		amount.setType("java.lang.Integer");
		fact.setSlots(Arrays.asList(amount));
		return fact;

	}

}