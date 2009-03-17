package net.sf.anathema.character.trait.validator.extension;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.character.trait.validator.ValidationDtoObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.FakeExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockIntegerAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class UnconditionalValidatorFactory_MaximumTest {

  private static final int MAXIMUM = 3;
  private static final String TRAIT_ID = "test.traitId";
  private FakeExtensionElement element;

  @Before
  public void createMaximumElement() throws Exception {
    IExtensionElement maximumElement = createExtensionElement(
        new MockStringAttribute("traitId", TRAIT_ID),
        new MockIntegerAttribute("value", MAXIMUM));
    element = new FakeExtensionElement();
    element.addElement("maximum", maximumElement);
  }

  @Test
  public void createsNoValidatorsForUnknownTrait() throws Exception {
    List<IValidator> validators = createValidatorsForTraitId("other.traitId");
    assertThat(validators.size(), is(0));
  }

  @Test
  public void createsOneValidatorForKnownTrait() throws Exception {
    List<IValidator> validators = createValidatorsForTraitId(TRAIT_ID);
    assertThat(validators.size(), is(1));
  }

  @Test
  public void createsMaximumValidator() throws Exception {
    List<IValidator> validators = createValidatorsForTraitId(TRAIT_ID);
    assertThat(validators.get(0), is(instanceOf(ValidateMaximalValue.class)));
  }

  @Test
  public void createsValidatorWithCorrectMaximumValue() throws Exception {
    List<IValidator> validators = createValidatorsForTraitId(TRAIT_ID);
    assertThat(validators.get(0).getValidValue(MAXIMUM + 1), is(MAXIMUM));
  }

  private List<IValidator> createValidatorsForTraitId(String traitId) {
    ValidationDto dto = ForTrait(traitId);
    return new UnconditionalValidatorFactory(element, dto).create();
  }
}