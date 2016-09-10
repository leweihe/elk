package com.newclear.game.exception;

public class ClickOutOfBoardException extends Exception {
	private static final long serialVersionUID = -2532611428477940148L;

	public ClickOutOfBoardException() {
		super();
	}

	public ClickOutOfBoardException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ClickOutOfBoardException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClickOutOfBoardException(String message) {
		super(message);
	}

	public ClickOutOfBoardException(Throwable cause) {
		super(cause);
	}

}
