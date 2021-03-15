/**
 *
 * @author Alex Lebeau
 * @version 1.0, 11/30/20
 */
package com.meowdoleon.enigma;

public class Main
{
    /**
     * @param args les arguments de la ligne de commande
     */
    public static void main(String args[])
    {
        /* Création du "Front End" */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new EnigmaUI().setVisible(true);
            }
        });
    }
}