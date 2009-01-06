package net.sf.anathema.charms.xml;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.sf.anathema.basics.eclipse.resource.ResourceUtils;

public class Localizer {

  private final String bundleName;
  private final Locale locale;

  public Localizer(String bundleName, Locale locale) {
    this.bundleName = bundleName;
    this.locale = locale;
  }

  public URL getPropertyUrl(String urlBase) {
    List<String> baseList = new ArrayList<String>();
    baseList.add(getPreferredBase(urlBase));
    baseList.add(urlBase);
    for (String base : baseList) {
      String localizationBundle = MessageFormat.format("{0}.properties", base); //$NON-NLS-1$
      URL preferredUrl = getUrl(localizationBundle);
      if (preferredUrl != null) {
        return preferredUrl;
      }
    }
    return null;
  }

  private String getPreferredBase(String urlBase) {
    String localeSuffix = locale.toString();
    if (localeSuffix.length() > 0) {
      return MessageFormat.format("{0}_{1}", urlBase, localeSuffix); //$NON-NLS-1$
    }
    return urlBase;
  }

  private URL getUrl(String preferredBundleName) {
    return ResourceUtils.getResourceUrl(bundleName, preferredBundleName);
  }
}