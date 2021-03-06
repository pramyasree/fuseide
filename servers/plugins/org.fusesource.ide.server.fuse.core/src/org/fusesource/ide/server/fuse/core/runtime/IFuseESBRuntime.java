/*******************************************************************************
 * Copyright (c) 2013 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.fusesource.ide.server.fuse.core.runtime;

import org.fusesource.ide.server.fuse.core.util.IFuseToolingConstants;
import org.fusesource.ide.server.karaf.core.runtime.IKarafRuntime;

/**
 * @author lhein
 */
public interface IFuseESBRuntime extends IKarafRuntime{
	
	static final String[] FUSEESB_RUNTIME_TYPES_SUPPORTED = new String[] {
		IFuseToolingConstants.RUNTIME_FUSE_60,
		IFuseToolingConstants.RUNTIME_FUSE_61,
		IFuseToolingConstants.RUNTIME_FUSE_62,
		IFuseToolingConstants.RUNTIME_FUSE_63
	};
}
