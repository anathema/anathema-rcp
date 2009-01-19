package net.sf.anathema.basics.repository.input.internal;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.item.text.TitledText;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemNameProvider_Test {

  private static final String NAME = "Name"; //$NON-NLS-1$
  private static final String UNTITLED = "Untitled"; //$NON-NLS-1$
  private ItemNameProvider provider;

  @Before
  public void createProvider() {
    this.provider = new ItemNameProvider(UNTITLED);
  }

  @Test
  public void returnsItemName() throws Exception {
    TitledText data = new TitledText();
    data.getName().setText(NAME);
    Assert.assertEquals(NAME, provider.getName(data));
  }

  @Test
  public void returnsEmptyNameForNullItem() throws Exception {
    assertThat(provider.getName(null), is("")); //$NON-NLS-1$
  }

  @Test
  public void returnsUntitledNameForUntitledItem() throws Exception {
    TitledText data = new TitledText();
    data.getName().setText(null);
    Assert.assertEquals(UNTITLED, provider.getName(data));
  }
}