package net.sf.anathema.editor.styledtext;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManagerOverrides;
import org.eclipse.jface.action.IToolBarManager;

public class TestToolBarManager implements IToolBarManager {

  private List<IAction> allActions = new ArrayList<IAction>();

  @Override
  public void add(IAction action) {
    allActions.add(action);
  }

  @Override
  public void add(IContributionItem item) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public void appendToGroup(String groupName, IAction action) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public void appendToGroup(String groupName, IContributionItem item) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public IContributionItem find(String id) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public IContributionItem[] getItems() {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public IContributionManagerOverrides getOverrides() {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public void insertAfter(String id, IAction action) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public void insertAfter(String id, IContributionItem item) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public void insertBefore(String id, IAction action) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public void insertBefore(String id, IContributionItem item) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public boolean isDirty() {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public boolean isEmpty() {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public void markDirty() {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public void prependToGroup(String groupName, IAction action) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public void prependToGroup(String groupName, IContributionItem item) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public IContributionItem remove(String id) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public IContributionItem remove(IContributionItem item) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public void removeAll() {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  @Override
  public void update(boolean force) {
    throw new UnsupportedOperationException("FakeObject"); //$NON-NLS-1$
  }

  public List<IAction> getActions() {
    return allActions;
  }
}