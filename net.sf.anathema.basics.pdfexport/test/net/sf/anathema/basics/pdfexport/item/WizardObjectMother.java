package net.sf.anathema.basics.pdfexport.item;

import static org.easymock.EasyMock.*;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardContainer;

public class WizardObjectMother {

  public static IWizard createWizard(IWizardContainer wizardContainer) {
    IWizard wizard = createMock(IWizard.class);
    expect(wizard.getContainer()).andReturn(wizardContainer).anyTimes();
    replay(wizard);
    return wizard;
  }

}
