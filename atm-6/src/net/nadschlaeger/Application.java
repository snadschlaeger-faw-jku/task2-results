package net.nadschlaeger;

import java.util.Arrays;

import net.nadschlaeger.model.Fact;
import net.nadschlaeger.model.KnowledgeBase;
import net.nadschlaeger.model.Slot;
import net.nadschlaeger.model.WorkingMemory;
import net.nadschlaeger.xml.XMLParser;

public class Application {

	public static void main(String[] args) {
		XMLParser parser = XMLParser.getInstance();

		KnowledgeBase kb = parser.readData();

		WorkingMemory wm = new WorkingMemory();

		BruteForceIE ie = new BruteForceIE(wm, kb);
		ie.trigger(createCardFact());
	}

	private static Fact createCardFact() {
		Fact fact = new Fact();
		fact.setName("CreditCard");

		Slot cardNumberSlot = new Slot();
		cardNumberSlot.setName("cardNumber");
		cardNumberSlot.setType("java.lang.Integer");
		cardNumberSlot.setValue(123);

		Slot cardActiveSlot = new Slot();
		cardActiveSlot.setName("active");
		cardActiveSlot.setType("java.lang.Boolean");
		cardActiveSlot.setValue(true);

		fact.setSlots(Arrays.asList(cardNumberSlot, cardActiveSlot));

		return fact;
	}

}
