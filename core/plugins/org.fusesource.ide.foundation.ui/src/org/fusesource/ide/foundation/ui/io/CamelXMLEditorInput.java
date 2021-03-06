/******************************************************************************* 
 * Copyright (c) 2015 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.fusesource.ide.foundation.ui.io;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.fusesource.ide.foundation.ui.internal.FoundationUIActivator;

/**
 * @author lhein
 */
public class CamelXMLEditorInput implements IEditorInput, IPersistableElement {
	
	public static final String KEY_CONTEXT_FILE = "camel.context.file.path";
	public static final String KEY_SELECTED_CONTAINER_ID = "camel.context.container.id";
	
	private IFile camelContextFile;
	private String selectedContainerId;
	
	public CamelXMLEditorInput(IFile contextFile, String containerId) {
		this.camelContextFile = contextFile;
		this.selectedContainerId = containerId;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@Override
	public <T> T getAdapter(Class<T> adapter) {
		if (adapter.equals(IFile.class) || adapter.equals(IResource.class)) {
			return (T)this.camelContextFile;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	@Override
	public boolean exists() {
		return this.camelContextFile != null && this.camelContextFile.exists();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		return FoundationUIActivator.getDefault().getSharedImages().descriptor(FoundationUIActivator.IMAGE_CAMEL_ICON);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	@Override
	public String getName() {
		return camelContextFile.getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	@Override
	public IPersistableElement getPersistable() {
		if (camelContextFile != null) {
			return this;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	@Override
	public String getToolTipText() {
		return String.format("%s%s%s", camelContextFile.getProject().getName(), File.separator, camelContextFile.getProjectRelativePath().toString());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPersistable#saveState(org.eclipse.ui.IMemento)
	 */
	@Override
	public void saveState(IMemento memento) {
		// Do not store anything for deleted objects
		boolean exists = exists();
		if (!exists) {
			return;
		}
		// Store object name, URI and diagram type provider ID
		memento.putString(KEY_CONTEXT_FILE, this.camelContextFile.getFullPath().toOSString());
		memento.putString(KEY_SELECTED_CONTAINER_ID, this.selectedContainerId);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPersistableElement#getFactoryId()
	 */
	@Override
	public String getFactoryId() {
		return CamelXMLEditorInputFactory.class.getName();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getCamelContextFile() == null) ? 0 : this.getCamelContextFile().getLocation().toOSString().hashCode());
		result = prime * result + ((this.getSelectedContainerId() == null) ? 0 : this.getSelectedContainerId().hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CamelXMLEditorInput) {
			CamelXMLEditorInput input2 = (CamelXMLEditorInput)obj;
			if (this.getCamelContextFile() != null && input2.getCamelContextFile() != null) {
				if (getSelectedContainerId() != null && input2.getSelectedContainerId() != null) {
					if (getSelectedContainerId().equals(input2.getSelectedContainerId()) == false) return false;
				} else if ((getSelectedContainerId() == null && input2.getSelectedContainerId() != null) ||
						   (getSelectedContainerId() != null && input2.getSelectedContainerId() == null)) {
					return false;
				}
				if (this.getCamelContextFile() != null && this.getCamelContextFile().getLocation() != null &&
					input2 != null && input2.getCamelContextFile() != null && input2.getCamelContextFile().getLocation() != null) {
					return this.getCamelContextFile().getLocation().toOSString().equals(input2.getCamelContextFile().getLocation().toOSString());	
				}				
			}
		}
		return false;
	}
	
	/**
	 * @return the selectedContainerId
	 */
	public String getSelectedContainerId() {
		return this.selectedContainerId;
	}
	
	public IFile getCamelContextFile() {
		return this.camelContextFile;
	}
	
	/**
	 * @param selectedContainerId the selectedContainerId to set
	 */
	public void setSelectedContainerId(String selectedContainerId) {
		this.selectedContainerId = selectedContainerId;
	}
	
	/**
	 * 
	 */
	public void onEditorInputSave() {
	}
}
