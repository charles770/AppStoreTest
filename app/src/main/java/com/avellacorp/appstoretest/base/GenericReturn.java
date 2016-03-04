package com.avellacorp.appstoretest.base;

import org.json.JSONObject;

/**
 * 
 * Esta clase se usa para establecer un estándar en la respuesta dada por el
 * servicio en cada una de las peticiones, y así poder manejar fácilmente
 * posibles errores.
 * 
 * @author Carlosas
 * 
 */
public final class GenericReturn {

	/**
	 * Mensaje de retorno
	 */
	public String message;

	/**
	 * Indica si se genero Error
	 */
	public Boolean exception;

	/**
	 * Códido del resultado de la operación 0 -> Ok -1 -> Fail ..
	 */
	public int codOperation;

	/**
	 * Datos adicionales
	 */
	public JSONObject data;
}
