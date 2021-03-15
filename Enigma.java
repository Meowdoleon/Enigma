/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meowdoleon.enigma;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * Classe définissant la machine Enigma
 *
 * @author Alex Lebeau
 * @version 1.0, 11/30/20
 */
public class Enigma
{

    private Rotor rotor1;
    private Rotor rotor2;
    private Rotor rotor3;

    private int[] rot11;
    private int[] rot12;
    private int[] rot21;
    private int[] rot22;
    private int[] rot31;
    private int[] rot32;

    public Rotor getRotor1()
    {
        return rotor1;
    }

    public void setRotor1(EnigmaUI ui, Rotor rotor1)
    {
        this.rotor1 = rotor1;
        envoyerRotorAuUI(ui, rotor1);
    }

    public Rotor getRotor2()
    {
        return rotor2;
    }

    public void setRotor2(EnigmaUI ui, Rotor rotor2)
    {
        this.rotor2 = rotor2;
        envoyerRotorAuUI(ui, rotor2);
    }

    public Rotor getRotor3()
    {
        return rotor3;
    }

    public void setRotor3(EnigmaUI ui, Rotor rotor3)
    {
        this.rotor3 = rotor3;
        envoyerRotorAuUI(ui, rotor3);
    }

    public int[] getRot11()
    {
        return rot11;
    }

    public void setRot11(int[] rot11)
    {
        this.rot11 = rot11;
    }

    public int[] getRot12()
    {
        return rot12;
    }

    public void setRot12(int[] rot12)
    {
        this.rot12 = rot12;
    }

    public int[] getRot21()
    {
        return rot21;
    }

    public void setRot21(int[] rot21)
    {
        this.rot21 = rot21;
    }

    public int[] getRot22()
    {
        return rot22;
    }

    public void setRot22(int[] rot22)
    {
        this.rot22 = rot22;
    }

    public int[] getRot31()
    {
        return rot31;
    }

    public void setRot31(int[] rot31)
    {
        this.rot31 = rot31;
    }

    public int[] getRot32()
    {
        return rot32;
    }

    public void setRot32(int[] rot32)
    {
        this.rot32 = rot32;
    }

    public Enigma(EnigmaUI ui)
    {
        /* Instancier les tableau des rotors */
        rot11 = new int[26];
        rot12 = new int[26];
        rot21 = new int[26];
        rot22 = new int[26];
        rot31 = new int[26];
        rot32 = new int[26];

        for(int i = 1; i <= 26; i++) // Populer les tableaux des rotors
        {
            rot11[i - 1] = ui.getRot11(i);
            rot12[i - 1] = ui.getRot12(i);
            rot21[i - 1] = ui.getRot21(i);
            rot22[i - 1] = ui.getRot22(i);
            rot31[i - 1] = ui.getRot31(i);
            rot32[i - 1] = ui.getRot32(i);
        }

        rotor1 = new Rotor(rot11, rot12, 1);
        rotor2 = new Rotor(rot21, rot22, 2);
        rotor3 = new Rotor(rot31, rot32, 3);
    }

    /**
     * Procédure permettant d'afficher un rotor à l'interface graphique.
     * 
     * @param rot
     * @param ui
     */
    public void envoyerRotorAuUI(EnigmaUI ui, Rotor rot)
    {
        if(rot.getId() == 1) // Envoyer le rotor 1 à l'interface graphique
        {
            for(int i = 1; i <= 26; i++)
            {
                if(rot.getRange1()[i - 1] < 0) // Vérifie si l'unité de la rangé est négatif
                {
                    ui.setRot11(i, String.valueOf(rot.getRange1()[i - 1]));
                }
                else
                {
                    ui.setRot11(i, "+" + String.valueOf(rot.getRange1()[i - 1]));
                }

                if(rot.getRange2()[i - 1] < 0)
                {
                    ui.setRot12(i, String.valueOf(rot.getRange2()[i - 1]));
                }
                else
                {
                    ui.setRot12(i, "+" + String.valueOf(rot.getRange2()[i - 1]));
                }
            }
        }
        else if(rot.getId() == 2) // Envoyer le rotor 2 à l'interface graphique
        {
            for(int i = 1; i <= 26; i++)
            {
                if(rot.getRange1()[i - 1] < 0)
                {
                    ui.setRot21(i, String.valueOf(rot.getRange1()[i - 1]));
                }
                else
                {
                    ui.setRot21(i, "+" + String.valueOf(rot.getRange1()[i - 1]));
                }

                if(rot.getRange2()[i - 1] < 0)
                {
                    ui.setRot22(i, String.valueOf(rot.getRange2()[i - 1]));
                }
                else
                {
                    ui.setRot22(i, "+" + String.valueOf(rot.getRange2()[i - 1]));
                }
            }
        }
        else if(rot.getId() == 3) // Envoyer le rotor 3 à l'interface graphique
        {
            for(int i = 1; i <= 26; i++)
            {
                if(rot.getRange1()[i - 1] < 0)
                {
                    ui.setRot31(i, String.valueOf(rot.getRange1()[i - 1]));
                }
                else
                {
                    ui.setRot31(i, "+" + String.valueOf(rot.getRange1()[i - 1]));
                }

                if(rot.getRange2()[i - 1] < 0)
                {
                    ui.setRot32(i, String.valueOf(rot.getRange2()[i - 1]));
                }
                else
                {
                    ui.setRot32(i, "+" + String.valueOf(rot.getRange2()[i - 1]));
                }
            }
        }
    }
}
