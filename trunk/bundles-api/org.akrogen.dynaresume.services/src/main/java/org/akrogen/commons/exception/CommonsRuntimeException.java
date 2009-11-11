/*****************************************************************************
* Copyright (c) 2009
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     Angelo Zerr <angelo.zerr@gmail.com>
*     Pascal Leclercq <pascal.leclercq@gmail.com>
*******************************************************************************/

package org.akrogen.commons.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class CommonsRuntimeException extends RuntimeException {
	
	public static final long serialVersionUID = 1L;

	   private Throwable cause;

	   public CommonsRuntimeException() {
	   }

	   public CommonsRuntimeException(String message) {
	      super(message);
	   }

	   public CommonsRuntimeException(Throwable cause) {
	      super(cause.getMessage());
	      this.cause = cause;
	   }

	   public CommonsRuntimeException(String message, Throwable cause) {
	      super(message);
	      this.cause = cause;
	   }

	   public Throwable getCause() {
	      return this.cause;
	   }

	   public void printStackTrace(PrintStream s) {
	      synchronized (s) {
	         super.printStackTrace(s);
	         Throwable t = getCause();
	         if (t != null) {
	            s.println("Caused by : ");
	            t.printStackTrace(s);
	         }
	      }
	   }

	   public void printStackTrace(PrintWriter s) {
	      synchronized (s) {
	         super.printStackTrace(s);
	         Throwable t = getCause();
	         if (t != null) {
	            s.println("Caused by : ");
	            t.printStackTrace(s);
	         }
	      }
	   }

}
