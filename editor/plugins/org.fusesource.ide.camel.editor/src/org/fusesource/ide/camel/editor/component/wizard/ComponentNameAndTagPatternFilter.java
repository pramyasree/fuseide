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
package org.fusesource.ide.camel.editor.component.wizard;

import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.dialogs.PatternFilter;
import org.fusesource.ide.camel.model.service.core.catalog.components.Component;

/**
 * @author Aurelien Pupier
 *
 */
final class ComponentNameAndTagPatternFilter extends PatternFilter {

	public ComponentNameAndTagPatternFilter() {
		setIncludeLeadingWildcard(true);
	}

	@Override
	protected boolean isLeafMatch(Viewer viewer, Object element) {
		return super.isLeafMatch(viewer, element) || isMatchingTag(element);
	}

	private boolean isMatchingTag(Object element) {
		if (element instanceof Component) {
			final List<String> tags = ((Component) element).getTags();
			if (tags != null) {
				for (String tag : tags) {
					if (wordMatches(tag)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}