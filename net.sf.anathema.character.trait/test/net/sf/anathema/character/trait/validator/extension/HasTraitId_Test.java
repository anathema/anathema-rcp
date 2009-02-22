package net.sf.anathema.character.trait.validator.extension;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.trait.BasicTrait;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class HasTraitId_Test {

  private static final String TRAIT_ID = "test.trait";
  private IPredicate<IExtensionElement> predicate;

  @Before
  public void createPredicate() {
    this.predicate = new HasTraitId(new BasicTrait(TRAIT_ID));
  }

  @Test
  public void evalutesElementWithDefinedTraitIdPositive() throws Exception {
    assertEvaluatesElementWithId(TRAIT_ID, true);
  }

  @Test
  public void evalutesElementWithUndefinedTraitIdNegative() throws Exception {
    assertEvaluatesElementWithId("undefinedId", false);
  }

  private void assertEvaluatesElementWithId(String id, boolean expected) throws ExtensionException {
    IExtensionElement element = createExtensionElement(new MockStringAttribute("traitId", id));
    assertThat(predicate.evaluate(element), is(expected));
  }
}