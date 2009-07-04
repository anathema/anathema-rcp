package net.sf.anathema.charms.extension;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockIntegerAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockNamedChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.data.cost.CostDto;
import net.sf.anathema.charms.providing.CostReader;

import org.junit.Test;

public class CostReader_Test {

  @Test
  public void readsMultipleLinearResources() throws Exception {
    IExtensionElement firstLinear = createLinearElement(1, "Tümer"); //$NON-NLS-1$
    IExtensionElement secondLinear = createLinearElement(2, "Hasen"); //$NON-NLS-1$
    IExtensionElement resource = createExtensionElement(new MockNamedChildren("linear", //$NON-NLS-1$
        firstLinear,
        secondLinear));
    IExtensionElement element = createExtensionElement(new MockNamedChildren("resource", resource)); //$NON-NLS-1$
    CostDto dto = new CostReader().read(element);
    assertThat(dto.resources.get(0).linearDto.size(), is(2));
  }

  private IExtensionElement createLinearElement(int amount, String unit) throws ExtensionException {
    return createExtensionElement(new MockIntegerAttribute("amount", amount), new MockStringAttribute( //$NON-NLS-1$
        "unit", //$NON-NLS-1$
        unit));
  }
}