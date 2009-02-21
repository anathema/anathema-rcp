package net.sf.anathema.character.trait.validator.extension;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.character.trait.validator.ValidationDtoObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockNamedChild;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

import org.junit.Test;

@SuppressWarnings("nls")
public class ExtensionWhere_Test {

  @Test
  public void returnsTrueForDefinedCharacterTypeCharacter() throws Exception {
    ExtensionWhere where = createWhereForIdentifiedChild("characterType", "my.charactertype");
    assertThat(where.evaluate(ForCharacterType("my.charactertype")), is(true));
  }

  @Test
  public void returnsFalseForUndefinedCharacterTypeCharacter() throws Exception {
    ExtensionWhere where = createWhereForIdentifiedChild("characterType", "my.charactertype");
    assertThat(where.evaluate(ForCharacterType("other.charactertype")), is(false));
  }

  private ExtensionWhere createWhereForIdentifiedChild(String tagName, String id) throws ExtensionException {
    IExtensionElement idElement = createExtensionElementWithAttributes(new MockStringAttribute("id", id));
    IExtensionElement element = createExtensionElementWithAttributes(new MockNamedChild(tagName, idElement));
    return new ExtensionWhere(element);
  }
}