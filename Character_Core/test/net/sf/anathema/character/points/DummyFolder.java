package net.sf.anathema.character.points;

import java.net.URI;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

public class DummyFolder implements IFolder {

  @Override
  public void create(boolean force, boolean local, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
  }

  @Override
  public void create(int updateFlags, boolean local, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void createLink(IPath localLocation, int updateFlags, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void createLink(URI location, int updateFlags, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(boolean force, boolean keepHistory, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public IFile getFile(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IFolder getFolder(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void move(IPath destination, boolean force, boolean keepHistory, IProgressMonitor monitor)
      throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean exists(IPath path) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public IFile[] findDeletedMembersWithHistory(int depth, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IResource findMember(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IResource findMember(IPath path) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IResource findMember(String name, boolean includePhantoms) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IResource findMember(IPath path, boolean includePhantoms) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getDefaultCharset() throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getDefaultCharset(boolean checkImplicit) throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IFile getFile(IPath path) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IFolder getFolder(IPath path) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IResource[] members() throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IResource[] members(boolean includePhantoms) throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IResource[] members(int memberFlags) throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setDefaultCharset(String charset) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setDefaultCharset(String charset, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void accept(IResourceVisitor visitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void accept(IResourceProxyVisitor visitor, int memberFlags) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void accept(IResourceVisitor visitor, int depth, boolean includePhantoms) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void accept(IResourceVisitor visitor, int depth, int memberFlags) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void clearHistory(IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void copy(IPath destination, boolean force, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void copy(IPath destination, int updateFlags, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void copy(IProjectDescription description, boolean force, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void copy(IProjectDescription description, int updateFlags, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public IMarker createMarker(String type) throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IResourceProxy createProxy() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(boolean force, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(int updateFlags, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteMarkers(String type, boolean includeSubtypes, int depth) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean exists() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public IMarker findMarker(long id) throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IMarker[] findMarkers(String type, boolean includeSubtypes, int depth) throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int findMaxProblemSeverity(String type, boolean includeSubtypes, int depth) throws CoreException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getFileExtension() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IPath getFullPath() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long getLocalTimeStamp() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public IPath getLocation() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public URI getLocationURI() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IMarker getMarker(long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long getModificationStamp() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IContainer getParent() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getPersistentProperty(QualifiedName key) throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IProject getProject() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IPath getProjectRelativePath() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IPath getRawLocation() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public URI getRawLocationURI() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResourceAttributes getResourceAttributes() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object getSessionProperty(QualifiedName key) throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getType() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public IWorkspace getWorkspace() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isAccessible() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isDerived() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isLinked() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isLinked(int options) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isLocal(int depth) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isPhantom() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isReadOnly() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isSynchronized(int depth) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isTeamPrivateMember() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void move(IPath destination, boolean force, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void move(IPath destination, int updateFlags, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void move(IProjectDescription description, int updateFlags, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void move(IProjectDescription description, boolean force, boolean keepHistory, IProgressMonitor monitor)
      throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void refreshLocal(int depth, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void revertModificationStamp(long value) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setDerived(boolean isDerived) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setLocal(boolean flag, int depth, IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public long setLocalTimeStamp(long value) throws CoreException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setPersistentProperty(QualifiedName key, String value) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setReadOnly(boolean readOnly) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setResourceAttributes(ResourceAttributes attributes) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setSessionProperty(QualifiedName key, Object value) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setTeamPrivateMember(boolean isTeamPrivate) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void touch(IProgressMonitor monitor) throws CoreException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Object getAdapter(Class adapter) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean contains(ISchedulingRule rule) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isConflicting(ISchedulingRule rule) {
    // TODO Auto-generated method stub
    return false;
  }

}
