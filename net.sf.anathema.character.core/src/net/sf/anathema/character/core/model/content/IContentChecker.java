package net.sf.anathema.character.core.model.content;

public interface IContentChecker {

  public String getContentValue(String definition);

  public IModelContentCheck getCheck(String definition);

}