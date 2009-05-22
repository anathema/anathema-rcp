package net.sf.anathema.character.spiritualtraits.anima;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.IClosure;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.properties.Evaluation;

public final class ReadAnimaPowerClosure implements IClosure<IExtensionElement> {
  private static final String TAG_EVALUATION = "evaluation"; //$NON-NLS-1$
  private static final String ATTIB_DESCRIPTION = "description"; //$NON-NLS-1$
  final List<String> powers = new ArrayList<String>();
  private final ICharacter character;
  private final Evaluation evaluation;

  public ReadAnimaPowerClosure(ICharacter character) {
    this.character = character;
    this.evaluation = new Evaluation();
  }

  @Override
  public void execute(IExtensionElement element) throws RuntimeException {
    IPredicate<ICharacter> predicate = evaluation.create(element.getElement(TAG_EVALUATION));
    if (predicate.evaluate(character)) {
      powers.add(element.getAttribute(ATTIB_DESCRIPTION));
    }
  }

  public String[] getPowers() {
    return powers.toArray(new String[powers.size()]);
  }
}