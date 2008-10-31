package net.sf.anathema.character.caste.trait;

import static org.junit.Assert.*;

import java.util.ArrayList;

import net.sf.anathema.character.caste.fake.DummyCaste;
import net.sf.anathema.character.caste.model.CasteModel;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class CasteStatusUpdater_SetCasteTest {

  private static final String CASTE_ID = "myCasteId"; //$NON-NLS-1$
  private CasteModel casteModel;
  private ICharacterId id;
  private ArrayList<IIdentificate> casteTraitTypes;
  private ITraitCollectionModel traitCollection;

  @Before
  public void createCasteModel() {
    casteTraitTypes = new ArrayList<IIdentificate>();
    casteTraitTypes.add(new Identificate("caste")); //$NON-NLS-1$
    DummyCaste dummyCaste = new DummyCaste(CASTE_ID);
    for (IIdentificate traitType : casteTraitTypes) {
      dummyCaste.addTraitType(traitType);
    }
    casteModel = new CasteModel(new CasteTemplate(null, dummyCaste));
  }

  @Before
  public void createTraitCollection() {
    traitCollection = EasyMock.createNiceMock(ITraitCollectionModel.class);
  }

  @Before
  public void createCasteId() {
    id = EasyMock.createMock(ICharacterId.class);
  }

  @Test
  public void casteStatusIsSetForCasteTraits() throws Exception {
    casteModel.setCasteById(CASTE_ID);
    traitCollection.setStatusFor(new CasteStatus(), casteTraitTypes);
    EasyMock.replay(traitCollection);
    new CasteStatusUpdater(casteModel, createTraitCollectionProvider(), id).stateChanged();
    EasyMock.verify(traitCollection);
  }

  @Test
  public void setsEmptyTraitListToCasteStatusForNullCaste() throws Exception {
    traitCollection.setStatusFor(new CasteStatus(), new ArrayList<IIdentificate>());
    EasyMock.replay(traitCollection);
    new CasteStatusUpdater(casteModel, createTraitCollectionProvider(), id).stateChanged();
    EasyMock.verify(traitCollection);
  }

  @Test
  public void leavesCleanTraitCollectionClean() throws Exception {
    casteModel.setCasteById(CASTE_ID);
    traitCollection = new TraitCollection(new BasicTrait(new Identificate("caste"))); //$NON-NLS-1$
    traitCollection.setClean();
    new CasteStatusUpdater(casteModel, createTraitCollectionProvider(), id).stateChanged();
    assertFalse(traitCollection.isDirty());
  }

  @Test
  public void leavesDirtyTraitCollectionDirty() throws Exception {
    casteModel.setCasteById(CASTE_ID);
    BasicTrait basicTrait = new BasicTrait(new Identificate("caste")); //$NON-NLS-1$
    traitCollection = new TraitCollection(basicTrait);
    basicTrait.getCreationModel().setValue(basicTrait.getCreationModel().getValue() + 1);
    new CasteStatusUpdater(casteModel, createTraitCollectionProvider(), id).stateChanged();
    assertTrue(traitCollection.isDirty());
  }

  private ITraitCollectionProvider createTraitCollectionProvider() {
    ITraitCollectionProvider traitProvider = EasyMock.createMock(ITraitCollectionProvider.class);
    EasyMock.expect(traitProvider.getModel(id)).andReturn(traitCollection).anyTimes();
    EasyMock.replay(traitProvider);
    return traitProvider;
  }
}