package net.sf.anathema.character.freebies.problem;

import static org.easymock.EasyMock.*;
import net.disy.commons.core.predicate.AcceptNothingPredicate;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.freebies.problem.IResourceMarker;
import net.sf.anathema.character.freebies.problem.ProblemModel;

import org.junit.Before;
import org.junit.Test;

public class ProblemModel_WithProblemTest {

  private IModelIdentifier modelIdentifier;
  private ProblemModel problemModel;

  @Before
  public void createModel() {
    modelIdentifier = EasyMockHelper.createMockAndReplay(IModelIdentifier.class);
    problemModel = new ProblemModel(modelIdentifier, new AcceptNothingPredicate<IModelIdentifier>());
  }

  @Test
  public void adjustsNothingOnNonExistingMarker() throws Exception {
    IResourceMarker marker = createStrictMock(IResourceMarker.class);
    expect(marker.exists()).andReturn(false);
    replay(marker);
    problemModel.adjustMarking(marker);
    verify(marker);
  }

  @Test
  public void deletesExistingMarker() throws Exception {
    IResourceMarker marker = createStrictMock(IResourceMarker.class);
    expect(marker.exists()).andReturn(true);
    marker.delete();
    replay(marker);
    problemModel.adjustMarking(marker);
    verify(marker);
  }
}