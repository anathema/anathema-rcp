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

import org.junit.Test;

public class CostReader_Test {

  @Test
  public void readsMultipleLinearResources() throws Exception {
    IExtensionElement firstLinear = createLinearElement(1, "Tümer");
    IExtensionElement secondLinear = createLinearElement(2, "Hasen");
    IExtensionElement resource = createExtensionElementWithAttributes(new MockNamedChildren(
        "linear",
        firstLinear,
        secondLinear));
    IExtensionElement element = createExtensionElementWithAttributes(new MockNamedChildren("resource", resource));
    CostDto dto = new CostReader().read(element);
    assertThat(dto.resources.get(0).linearDto.size(), is(2));
  }

  private IExtensionElement createLinearElement(int amount, String unit) throws ExtensionException {
    IExtensionElement firstLinear = createExtensionElementWithAttributes(
        new MockIntegerAttribute("amount", amount),
        new MockStringAttribute("unit", unit));
    return firstLinear;
  }
}