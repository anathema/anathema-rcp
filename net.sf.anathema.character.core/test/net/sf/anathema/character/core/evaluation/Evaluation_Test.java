package net.sf.anathema.character.core.evaluation;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.properties.Evaluation;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class Evaluation_Test {

  private DummyPropertyMap propertyMap;
  private Evaluation evaluation;
  private IExtensionElement hasFunWithBallsElement;
  private IExtensionElement hasFunWithKnifesElement;

  @Before
  public void createEvaluation() throws Exception {
    propertyMap = new DummyPropertyMap();
    evaluation = new Evaluation(propertyMap);
  }

  @Before
  public void createFunElements() throws Exception {
    hasFunWithBallsElement = createPropertiesElement("hasFun", "withBalls");
    hasFunWithKnifesElement = createPropertiesElement("hasFun", "withKnifes");
  }

  private IExtensionElement createPropertiesElement(String id, String value) throws ExtensionException {
    MockName name = new MockName("property");
    MockStringAttribute idAttribute = new MockStringAttribute("id", id);
    MockStringAttribute valueAttribute = new MockStringAttribute("value", value);
    return createExtensionElement(name, idAttribute, valueAttribute);
  }

  @Test
  public void evaluationWithDirectProperty() throws Exception {
    propertyMap.propertiesById.put("hasFun", new DummyProperty("withKnifes"));
    assertThat(createEvaluationPredicate(hasFunWithBallsElement).evaluate(null), is(false));
    assertThat(createEvaluationPredicate(hasFunWithKnifesElement).evaluate(null), is(true));
  }

  @Test
  public void evaluationOrConnectedProperty() throws Exception {
    MockChildren mockChildren = new MockChildren(hasFunWithBallsElement, hasFunWithKnifesElement);
    IExtensionElement orElement = createExtensionElement(new MockName("or"), mockChildren);
    propertyMap.propertiesById.put("hasFun", new DummyProperty("withKnifes"));
    assertThat(createEvaluationPredicate(orElement).evaluate(null), is(true));
    propertyMap.propertiesById.put("hasFun", new DummyProperty("withBalls"));
    assertThat(createEvaluationPredicate(orElement).evaluate(null), is(true));
    propertyMap.propertiesById.put("hasFun", new DummyProperty("withChess"));
    assertThat(createEvaluationPredicate(orElement).evaluate(null), is(false));
  }

  private IPredicate<ICharacter> createEvaluationPredicate(IExtensionElement propertiesElement) throws Exception {
    MockChildren singleChild = new MockChildren(propertiesElement);
    IExtensionElement element = createExtensionElement(singleChild);
    return evaluation.create(element);
  }
}