/******************************************************************************* 
 * Copyright (c) 2016 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.fusesource.ide.projecttemplates.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.fusesource.ide.projecttemplates.internal.ProjectTemplatesActivator;

/**
 * a basic project creator which only creates the project folder
 * and some basic configuration
 * 
 * @author lhein
 */
public class BasicProjectCreator {

	private IProject project;
	private NewProjectMetaData metadata;
	
	/**
	 * 
	 */
	public BasicProjectCreator(NewProjectMetaData metadata) {
		this.metadata = metadata;
	}

	/**
	 * creates the project
	 * 
	 * @param monitor
	 * @return	true on success
	 */
	public boolean create(IProgressMonitor monitor) {
		try {
			// first create the project
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			project = root.getProject(metadata.getProjectName());
			project.create(monitor);
			project.open(monitor);
		} catch (CoreException ex) {
			ProjectTemplatesActivator.pluginLog().logError(ex);
			return false;
		} 
		return true;
	}	
	
	/**
	 * returns the created project
	 * 
	 * @return
	 */
	public IProject getProject() {
		return project;
	}
}
