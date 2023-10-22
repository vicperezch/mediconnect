package com.mediconnect.db;

import java.io.File;

public class CSV {

    //Atributo de la clase
    private File file;

    /**
     * Constructor encargado de crear la instancia al archivo que
     * almacenara la informacion
     * @param fileName
     */
    public CSV(String fileName){
        this.file = new File(fileName);
    }
}
