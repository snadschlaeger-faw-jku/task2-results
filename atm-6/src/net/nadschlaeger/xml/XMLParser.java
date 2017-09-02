package net.nadschlaeger.xml;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.nadschlaeger.RuleOperator;
import net.nadschlaeger.model.Clause;
import net.nadschlaeger.model.Fact;
import net.nadschlaeger.model.KnowledgeBase;
import net.nadschlaeger.model.Rule;
import net.nadschlaeger.model.Slot;

public class XMLParser {

	private static XMLParser instance = null;

	protected XMLParser() {
		// Exists only to defeat instantiation.
	}

	public static XMLParser getInstance() {
		if (instance == null) {
			instance = new XMLParser();
		}
		return instance;
	}

	public KnowledgeBase readData() {
		KnowledgeBase kb = new KnowledgeBase();

		kb.setFacts(readFacts());
		kb.setRules(readRules());

		return kb;
	}

	private List<Fact> readFacts() {
		List<Fact> facts = new ArrayList<Fact>();
		try {

			URL resource = getClass().getResource("facts.xml");
			File fXmlFile = new File(resource.getFile());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList factList = doc.getElementsByTagName("fact");
			for (int temp = 0; temp < factList.getLength(); temp++) {
				Node fNode = factList.item(temp);
				facts.add(getFact(fNode));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return facts;
	}

	private List<Rule> readRules() {
		List<Rule> rules = new ArrayList<Rule>();
		try {
			URL resource = getClass().getResource("rules.xml");
			File fXmlFile = new File(resource.getFile());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList ruleList = doc.getElementsByTagName("rule");
			for (int f = 0; f < ruleList.getLength(); f++) {
				Node ruleNode = ruleList.item(f);
				if (ruleNode.getNodeType() == Node.ELEMENT_NODE) {
					Element ruleElement = (Element) ruleNode;
					Rule r = new Rule();
					r.setName(ruleElement.getAttribute("name"));
					r.setRuleOperator(RuleOperator.valueOf(ruleElement.getAttribute("operator")));

					List<Clause> conditions = new ArrayList<Clause>();
					NodeList conditionList = ruleElement.getElementsByTagName("condition");

					for (int i = 0; i < conditionList.getLength(); i++) {
						Node cNode = conditionList.item(i);
						conditions.add(getClause(cNode));
					}
					r.setConditions(conditions);

					List<Clause> consequences = new ArrayList<Clause>();
					NodeList consequenceList = ruleElement.getElementsByTagName("consequence");

					for (int i = 0; i < consequenceList.getLength(); i++) {
						Node cNode = consequenceList.item(i);
						consequences.add(getClause(cNode));
					}
					r.setConsequences(consequences);

					rules.add(r);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rules;
	}

	private Clause getClause(Node cNode) {
		Element cElement = (Element) cNode;
		Clause c = new Clause(cElement.getAttribute("expression"));
		NodeList parameterList = cElement.getElementsByTagName("parameter");

		for (int i = 0; i < parameterList.getLength(); i++) {
			Node pNode = parameterList.item(i);
			Element pElement = (Element) pNode;

			NodeList factList = pElement.getElementsByTagName("fact");
			for (int h = 0; h < factList.getLength(); h++) {
				Node fNode = factList.item(h);
				c.setParameter(getFact(fNode));
			}

			NodeList textList = pElement.getElementsByTagName("text");
			for (int h = 0; h < textList.getLength(); h++) {
				Node textNode = textList.item(h);
				Element tElement = (Element) textNode;
				c.setParameter(tElement.getAttribute("value"));
			}
		}
		return c;
	}

	private Fact getFact(Node nodeFact) {
		Element eElement = (Element) nodeFact;
		Fact f = new Fact();
		f.setName(eElement.getAttribute("name"));

		List<Slot> slots = new ArrayList<>();
		NodeList nList2 = eElement.getElementsByTagName("slot");

		for (int i = 0; i < nList2.getLength(); i++) {
			Node n = nList2.item(i);
			Element e = (Element) n;
			Slot s = new Slot();
			s.setName(e.getAttribute("name"));
			s.setType(e.getAttribute("type"));
			Constructor<?> ctor;
			try {
				ctor = Class.forName(e.getAttribute("type")).getConstructor(String.class);
				s.setValue(ctor.newInstance(e.getAttribute("value")));
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			s.setInputRequired(Boolean.valueOf(e.getAttribute("inputRequired")));
			slots.add(s);
		}
		f.setSlots(slots);
		return f;
	}

}
