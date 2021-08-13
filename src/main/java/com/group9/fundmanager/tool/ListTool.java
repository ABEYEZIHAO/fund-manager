package com.group9.fundmanager.tool;

import java.io.*;
import java.util.List;

/**
 * @author abe
 */
public class ListTool {
    /**
     * Deep copy an entity in order to avoid some errors we might get when update the entity information
     * @param src the original entity
     * @param <T> the entity type we try to copy (Fund, Manager, Position, Security)
     * @return the entity we get after deep copying
     * @throws IOException cuz the stream is used
     * @throws ClassNotFoundException <T> might not exist
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}
