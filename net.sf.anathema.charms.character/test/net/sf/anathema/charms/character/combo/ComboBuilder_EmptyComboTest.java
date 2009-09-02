package net.sf.anathema.charms.character.combo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Test;

public class ComboBuilder_EmptyComboTest extends AbstractComboBuilderTestCase {

  @Test
  public void charmWithoutComboKeywordIsNotValid() throws Exception {
    CharmDto charmData = createCharmData("ExtraAction");
    ICharmId charmId = setCharmDataForId("test", charmData);
    assertThat(builder.isValid(charmId), is(false));
  }

  @Test
  public void comboBasicsCharmIsValidForEmptyCombo() throws Exception {
    CharmDto charmData = createComboBasicCharm("ExtraAction");
    ICharmId charmId = setCharmDataForId("test", charmData);
    assertThat(builder.isValid(charmId), is(true));
  }

  @Test
  public void comboOkCharmIsValidForEmptyCombo() throws Exception {
    CharmDto charmData = createComboOkCharm("ExtraAction");
    ICharmId charmId = setCharmDataForId("test", charmData);
    assertThat(builder.isValid(charmId), is(true));
  }
}