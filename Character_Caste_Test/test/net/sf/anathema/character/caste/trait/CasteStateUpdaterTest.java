package net.sf.anathema.character.caste.trait;

import java.util.ArrayList;

import net.sf.anathema.character.caste.fake.DummyCaste;
import net.sf.anathema.character.caste.model.CasteModel;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class CasteStateUpdaterTest {

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
    casteModel = new CasteModel(new CasteTemplate(dummyCaste));
    casteModel.setCasteById(CASTE_ID);
  }

  @Before
  public void createTraitCollection() {
    traitCollection = EasyMock.createMock(ITraitCollectionModel.class);
  }

  @Before
  public void createCasteId() {
    id = EasyMock.createMock(ICharacterId.class);
  }

  @Test
  public void casteStatusIsSetForCasteTraits() throws Exception {
    traitCollection.setStatusFor(new CasteStatus(), casteTraitTypes);
    EasyMock.replay(traitCollection);
    new CasteStateUpdater(casteModel, createTraitCollctionProvider(), id).stateChanged();
    EasyMock.verify(traitCollection);
  }

  private ITraitCollectionProvider createTraitCollctionProvider() {
    ITraitCollectionProvider traitProvider = EasyMock.createMock(ITraitCollectionProvider.class);
    EasyMock.expect(traitProvider.getModel(id)).andReturn(traitCollection).anyTimes();
    EasyMock.replay(traitProvider);
    return traitProvider;
  }
}