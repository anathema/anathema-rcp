package net.sf.anathema.character.freebies.problem;

import static org.easymock.EasyMock.*;
import net.disy.commons.core.predicate.AcceptAllPredicate;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.freebies.problem.IResourceMarker;
import net.sf.anathema.character.freebies.problem.ProblemModel;

import org.junit.Before;
import org.junit.Test;

public class ProblemModel_WithoutProblemTest {

  private IModelIdentifier modelIdentifier;
  private ProblemModel problemModel;

  @Before
  public void createModel() {
    modelIdentifier = EasyMockHelper.createMockAndReplay(IModelIdentifier.class);
    problemModel = new ProblemModel(modelIdentifier, new AcceptAllPredicate<IModelIdentifier>());
  }

  @Test
  public void adjustsNothingOnExistingMarker() throws Exception {
    IResourceMarker marker = createStrictMock(IResourceMarker.class);
    expect(marker.exists()).andReturn(true);
    replay(marker);
    problemModel.adjustMarking(marker);
    verify(marker);
  }

  @Test
  public void createsNonExistingMarker() throws Exception {
    IResourceMarker marker = createStrictMock(IResourceMarker.class);
    expect(marker.exists()).andReturn(false);
    marker.create();
    replay(marker);
    problemModel.adjustMarking(marker);
    verify(marker);
  }
}