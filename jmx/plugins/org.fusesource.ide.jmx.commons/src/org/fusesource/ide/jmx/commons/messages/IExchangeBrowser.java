/*******************************************************************************
 * Copyright (c) 2016 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.fusesource.ide.jmx.commons.messages;

import java.util.List;

/**
 * Supports browsing of exchanges or messages such as endpoints or routes with tracing
 *
 */
public interface IExchangeBrowser {

	public List<IExchange> browseExchanges();
	

}
