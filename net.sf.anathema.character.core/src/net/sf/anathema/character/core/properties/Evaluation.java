package net.sf.anathema.character.core.properties;

import static java.text.MessageFormat.*;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.ITransformer;
import net.disy.commons.core.util.IdentityTransformer;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.properties.concrete.OrPredicate;
import net.sf.anathema.character.core.properties.concrete.PropertiesExtensionPoint;
import net.sf.anathema.character.core.properties.evaluation.HasProperty;

@SuppressWarnings("nls")
public class Evaluation {

  private static final String ATTRIB_VALUE = "value";
  private static final String ATTRIB_ID = "id";
  private final IPropertyMap propertyMap;

  public Evaluation() {
    this(new PropertiesExtensionPoint());
  }

  public Evaluation(IPropertyMap propertyMap) {
    this.propertyMap = propertyMap;
  }

  public IPredicate<ICharacter> create(IExtensionElement evaluationElement) {
    return create(evaluationElement, new IdentityTransformer<String>());
  }

  public IPredicate<ICharacter> create(
      IExtensionElement evaluationElement,
      ITransformer<String, String> propertyTransformer) {
    IExtensionElement singleElement = evaluationElement.getElements()[0];
    if (singleElement.getName().equalsIgnoreCase("or")) {
      List<IPredicate<ICharacter>> subPredicates = new ArrayList<IPredicate<ICharacter>>();
      for (IExtensionElement subElement : singleElement.getElements()) {
        IPredicate<ICharacter> subPredicate = readProperty(subElement, propertyTransformer);
        subPredicates.add(subPredicate);
      }
      return new OrPredicate(subPredicates);
    }
    return readProperty(singleElement, propertyTransformer);
  }

  private IPredicate<ICharacter> readProperty(
      IExtensionElement propertyElement,
      ITransformer<String, String> propertyTransformer) {
    String propertyId = propertyElement.getAttribute(ATTRIB_ID);
    IProperty property = propertyMap.getProperty(propertyId);
    Ensure.ensureNotNull(format("No property definition found for id {0}.", propertyId), property);
    String propertyValue = propertyElement.getAttribute(ATTRIB_VALUE);
    return new HasProperty(property, propertyTransformer.transform(propertyValue));
  }
}