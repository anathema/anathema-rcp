package net.sf.anathema.character.trait.validator.extension;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.character.trait.validator.ValidationDtoObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.FakeExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockExecutableExtensionAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.trait.validator.ISpecialValidator;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class UnconditionalValidatorFactory_SpecialTest {

  private static final int MAXIMUM = 3;
  private static final String TRAIT_ID = "test.traitId";
  private FakeExtensionElement element;
  private ValidationDto dto;

  @Before
  public void createMaximumElement() throws Exception {
    IExtensionElement specialElement = createExtensionElement(
        new MockStringAttribute("traitId", TRAIT_ID),
        new MockExecutableExtensionAttribute<ISpecialValidator>(
            "class",
            ISpecialValidator.class,
            new DummySpecialValidator()));
    element = new FakeExtensionElement();
    element.addElement("special", specialElement);
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
  public void createsInitializedSpecialValidator() throws Exception {
    List<IValidator> validators = createValidatorsForTraitId(TRAIT_ID);
    DummySpecialValidator validator = (DummySpecialValidator) validators.get(0);
    assertThat(validator.getValidationObject(), is(sameInstance(dto)));
  }

  private List<IValidator> createValidatorsForTraitId(String traitId) {
    dto = ForTrait(traitId);
    return new UnconditionalValidatorFactory(element, dto).create();
  }
}