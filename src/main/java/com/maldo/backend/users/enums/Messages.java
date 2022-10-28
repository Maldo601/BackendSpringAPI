package com.maldo.backend.users.enums;


import com.maldo.backend.utils.ParamUtils;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public enum Messages
{
	CORRECT_LOGIN("Usuario Logeado Correctamente!"),
	USERNAME_EXIST("El nombre de usuario ya existe."),
	EMAIL_EXIST("El Email ya existe."),
	REGISTER_SUCCES("Usuario Registrado correctamente!"),
	BAD_LOGIN("Credenciales Incorrectas.")
	;
	private static final ConcurrentHashMap<String, Messages> ERRORS = new ConcurrentHashMap<>();
	private final String msg;

	static
	{
		for (final Messages msg : values())
		{
			ERRORS.put(msg.msg, msg);
		}
	} Messages(final String msg){	this.msg = msg;	   }

	public static synchronized Optional<Messages> getMessages(final String msg)
	{
		Messages errorType = null;
		final Messages errorTypeEnum = ERRORS.get(msg);
		if (!ParamUtils.objectIsNull(errorTypeEnum))
		{
			errorType = errorTypeEnum;
		}

		return Optional.ofNullable(errorType);
	}

	public String getMsg() {	return msg;	   }
}
