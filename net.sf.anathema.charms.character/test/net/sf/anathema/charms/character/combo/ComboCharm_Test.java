package net.sf.anathema.charms.character.combo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import net.sf.anathema.charms.data.CharmDto;

import org.junit.Before;
import org.junit.Test;

public class ComboCharm_Test {

  private CharmDto charmDto;
  private ComboCharm comboCharm;

  @Before
  public void createCharm() throws Exception {
    this.charmDto = new CharmDto();
    this.comboCharm = new ComboCharm(charmDto);
  }
  
  @Test
  public void emptyCharmHasNoComboKeyword() throws Exception {
    assertThat(comboCharm.hasComboKeyword(), is(false));
    assertThat(comboCharm.isComboBasic(), is(false));
    assertThat(comboCharm.isComboOk(), is(false));
  }
  
  @Test
  public void recognizesComboOkAsComboKeyword() throws Exception {
    charmDto.keywords.add("Combo-OK");
    assertThat(comboCharm.hasComboKeyword(), is(true));
    assertThat(comboCharm.isComboOk(), is(true));
  }
  
  @Test
  public void recognizesComboBasicAsComboKeyword() throws Exception {
    charmDto.keywords.add("Combo-Basic");
    assertThat(comboCharm.hasComboKeyword(), is(true));
    assertThat(comboCharm.isComboBasic(), is(true));
  }
  
  @Test
  public void recognizesReflexiveCharms() throws Exception {
    assertThat(comboCharm.isReflexive(), is(false));
    charmDto.type = "Reflexive";
    assertThat(comboCharm.isReflexive(), is(true));
  }
  
  @Test
  public void recognizesSimpleCharms() throws Exception {
    assertThat(comboCharm.isSimple(), is(false));
    charmDto.type = "Simple";
    assertThat(comboCharm.isSimple(), is(true));
  }
  
  @Test
  public void recognizesExtraActionCharms() throws Exception {
    assertThat(comboCharm.isExtraAction(), is(false));
    charmDto.type = "ExtraAction";
    assertThat(comboCharm.isExtraAction(), is(true));
  }
}