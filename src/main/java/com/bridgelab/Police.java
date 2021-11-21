package com.bridgelab;

import java.util.ArrayList;

/**
 * Purpose :
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */

public class Police {
    static ArrayList whiteCars = new ArrayList();
    public static void getAllWhiteCars(int spotNo) {
           whiteCars.add(spotNo);
    }
}
