package net.sf.anathema.character.freebies.virtues;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.fake.DummyModelCollection;
import net.sf.anathema.character.freebies.virtues.internal.IVirtueFreebieLimit;
import net.sf.anathema.character.freebies.virtues.internal.VirtueFreebiesHandler;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitCollection;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class VirtueFreebiesHandler_Test {

  private VirtueFreebiesHandler handler;
  private DummyTraitCollection model;
  private DummyModelCollection models;

  @Before
  public void createHandler() throws Exception {
    model = new DummyTraitCollection();
    models = new DummyModelCollection();
    models.addModel("net.sf.anathema.character.spiritualtraits.model", model);
    IVirtueFreebieLimit limit = new IVirtueFreebieLimit() {
      @Override
      public int getFor(ICharacterId id) {
        return 3;
      }};
    handler = new VirtueFreebiesHandler(models,limit);
  }
  
  @Test
  public void hasDefinedCreditIdAsId() throws Exception {
    String creditId = handler.getCreditId();
    assertThat(creditId, is("net.sf.anathema.character.virtues.freebies"));
  }
  
  @Test
  public void usesOneCreditForFirstDotAbove1() throws Exception {
    createVirtuesWithValues(2,0,0,0);
    assertThat(handler.getPoints(null, 1), is(1));
  }
  
  @Test
  public void doesNotUseCreditIfNoVirtueFound() throws Exception {
    assertThat(handler.getPoints(null, 1), is(0));
  }
  
  @Test
  public void doesNotExceedCredit() throws Exception {
    createVirtuesWithValues(2,0,0,0);
    assertThat(handler.getPoints(null, 0), is(0));
  }
  
  @Test
  public void usesOneCreditForEachDotAbove1() throws Exception {
    createVirtuesWithValues(3,0,0,0);
    assertThat(handler.getPoints(null, 2), is(2));
  }
  
  @Test
  public void disregardsNonVirtueTraits() throws Exception {
    createTraitWithValue(2, "Essence");
    createVirtuesWithValues(0,0,0,0);
    assertThat(handler.getPoints(null, 1), is(0));
  }
  
  @Test
  public void calculatesCreditForAllVirtues() throws Exception {
    createVirtuesWithValues(2,2,0,0);
    assertThat(handler.getPoints(null, 2), is(2));
  }
  
  
  @Test
  public void doesNotUseCreditForValuesAbove3IfNoOtherLimitSpecified() throws Exception {
    createVirtuesWithValues(4,0,0,0);
    assertThat(handler.getPoints(null, 3), is(2));
  }
  

  @Test
  public void useCreditForValuesAbove3IfLimitSpecified() throws Exception {
    createVirtuesWithValues(4,0,0,0);
    IVirtueFreebieLimit limit = new IVirtueFreebieLimit() {
      @Override
      public int getFor(ICharacterId id) {
        return 4;
      }};
    VirtueFreebiesHandler limitedHandler = new VirtueFreebiesHandler(models, limit);
    assertThat(limitedHandler.getPoints(null, 3), is(3));
  }

  private void createVirtuesWithValues(int valor, int compassion, int conviction, int temperance) {
    createTraitWithValue(valor, "Valor");
    createTraitWithValue(compassion, "Compassion");
    createTraitWithValue(conviction, "Conviction");
    createTraitWithValue(temperance, "Temperance");
  }


  private void createTraitWithValue(int value, String traitId) {
    BasicTrait valor = new BasicTrait(traitId);
    valor.getCreationModel().setValue(value);
    model.addTrait(valor);
  }
}