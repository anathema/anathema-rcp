package net.sf.anathema.character.caste;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.caste.model.Caste;

public class CasteObjectMother {

  public static ICaste createCaste(String id, String printName) throws ExtensionException {
    MockStringAttribute idAttribute = new MockStringAttribute("casteId", id); //$NON-NLS-1$
    MockStringAttribute printNameAttribute = new MockStringAttribute("printName", printName); //$NON-NLS-1$
    return new Caste(ExtensionObjectMother.createExtensionElementWithAttributes(idAttribute, printNameAttribute));
  }

}
