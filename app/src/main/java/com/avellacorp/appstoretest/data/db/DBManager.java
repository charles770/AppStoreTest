package com.avellacorp.appstoretest.data.db;

import android.app.Activity;
import android.content.Context;

/**
 * Esta clase es opcional usarla. Se usa para instanciar solo una vez la base de
 * datos, allá se abre la conexión a la bd y permite que no se tenga que abrir y
 * cerrar la conexión cada vez que se hace una consulta. Si esta clase es usada,
 * se debe tener especial cuidado con los cursores usados en la aplicación, ya
 * que es necesario usar try - finally para cerrar los cursores al final de
 * usarlos y no generar problemas en memoria.
 * 
 * @author Carlosas
 * 
 */
public class DBManager {

	private static DBAdapter cnData = null;

	public static DBAdapter getInstance(Context context) {
		// singleton
		if (cnData == null) {
			cnData = new DBAdapter(context);
			cnData.open();
		}
		return cnData;

	}


}
