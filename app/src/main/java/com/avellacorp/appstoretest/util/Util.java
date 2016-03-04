package com.avellacorp.appstoretest.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Esta clase posee varios métodos, procedimientos y funciones que son útiles
 * para el flujo de la aplicación.
 *
 * @author Carlosas
 */
public class Util {

    private Context mContext = null;

    public Util(Context context) {
        mContext = context;
    }

    /**
     * Metodo encargado de verificar si hay conexion a Internet IMPORTANTE: No
     * consumir dentro de un ASYNCTASK
     *
     * @return Tiene o no conexión
     */
    public boolean isNetworkAvailable() {

        Context context = mContext.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;

    }


    /**
     * Eta funcion permite borrar un archivo o folder y sus archivos o
     * subdirectorios internos.
     *
     * @param folder Folder a borrar
     */
    public void BorrarDirectorio(File folder, Boolean directorio) {

        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    BorrarDirectorio(f, true);
                } else {
                    f.delete();
                }
            }
        }
        if (directorio)
            folder.delete();

    }


    /**
     * Eta funcion permite borrar la base de datos interna de la aplicación
     *
     * @param nombreDatabase
     */
    public void BorrarDatabase(String nombreDatabase) {

        String dbPath = mContext.getDatabasePath(nombreDatabase).getAbsolutePath();
        File db = new File(dbPath);
        if (db.exists()) {
            db.delete();
        }

    }


    /**
     * Esta función permite leer el contenido de texto plano que se encuentra en
     * el docuemntos que se desea abrir.
     *
     * @param myFile Archivo que se desea leer
     * @return String Texto del archivo leído
     */
    public String readFromFile(File myFile) {

        String ret = "";

        try {

            InputStream inputStream = new FileInputStream(myFile);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(
                        inputStream);
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                    stringBuilder.append("\r\n");
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("Lectura de html", "File not found: " + e.toString());
            return "NotFound";
        } catch (IOException e) {
            Log.e("Lectura de html", "Can not read file: " + e.toString());
        }

        return ret;
    }


    public String ObtenerAlmacenamientoExterno() {

        File baseDir = mContext.getExternalFilesDir(null);
        if (baseDir == null) {
            baseDir = mContext.getFilesDir();
        }
        return baseDir.getAbsolutePath();

    }


    public void Comprimir(List<String> fileList, String dir, String fileName) {

        File zipFile = new File(dir + "/" + fileName);
        if (zipFile.exists()) {
            zipFile.delete();
        }

        byte[] buffer = new byte[1024];

        try {

            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (String file : fileList) {

                ZipEntry ze = new ZipEntry(file);
                zos.putNextEntry(ze);

                FileInputStream in = new FileInputStream(dir + File.separator
                        + file);

                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }

                in.close();
            }

            zos.closeEntry();
            // remember close it
            zos.close();

            System.out.println("Done");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


}
