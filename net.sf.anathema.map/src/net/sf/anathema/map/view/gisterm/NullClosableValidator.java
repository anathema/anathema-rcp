/**
 * 
 */
package net.sf.anathema.map.view.gisterm;

import de.disy.gis.gisterm.map.theme.ITheme;
import de.disy.gis.gisterm.map.theme.IThemeClosableValidator;
import de.disy.lib.validation.IValidatorResult;
import de.disy.lib.validation.ValidatorResult;

public final class NullClosableValidator implements IThemeClosableValidator {
  @Override
  public IValidatorResult validate(ITheme... arg0) {
    return new ValidatorResult(true, null);
  }
}