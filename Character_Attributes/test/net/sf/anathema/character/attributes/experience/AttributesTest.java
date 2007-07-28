package net.sf.anathema.character.attributes.experience;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IIntValueModel;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class AttributesTest {

  private BasicTrait trait;
  private Attributes attributes;

  @Before
  public void createCleanAttributesWithTrait() throws Exception {
    this.trait = new BasicTrait(new Identificate("Trait")); //$NON-NLS-1$
    this.attributes = new Attributes(trait);
    this.attributes.setClean();
  }
  
  @Test
  public void isInitallyClean() throws Exception {
    assertFalse(attributes.isDirty());
  }
  
  @Test
  public void attributeCreationValueChangeDirties() throws Exception {
    IIntValueModel valueModel = trait.getCreationModel();
    valueModel.setValue(valueModel.getValue() + 1);
    assertTrue(attributes.isDirty());
  }
  
  @Test
  public void attributeExperienceValueChangeDirties() throws Exception {
    IIntValueModel valueModel = trait.getExperiencedModel();
    valueModel.setValue(valueModel.getValue() + 1);
    assertTrue(attributes.isDirty());
  }
}