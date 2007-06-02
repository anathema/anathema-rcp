package net.sf.anathema.basics.repository.input;

import net.sf.anathema.basics.item.data.BasicItemData;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemNameProviderTest {

  private static final String NAME = "Name"; //$NON-NLS-1$
  private static final String UNTITLED = "Untitled"; //$NON-NLS-1$
  private ItemNameProvider provider;

  @Before
  public void createProvider() {
    this.provider = new ItemNameProvider(UNTITLED);
  }

  @Test
  public void returnsItemName() throws Exception {
    BasicItemData data = new BasicItemData();
    data.getName().setText(NAME);
    Assert.assertEquals(NAME, provider.getName(data));
  }

  @Test
  public void returnsNullNameForNullItem() throws Exception {
    Assert.assertEquals(null, provider.getName(null));
  }

  @Test
  public void returnsUntitledNameForUntitledItem() throws Exception {
    BasicItemData data = new BasicItemData();
    data.getName().setText(null);
    Assert.assertEquals(UNTITLED, provider.getName(data));
  }
}