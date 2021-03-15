/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meowdoleon.enigma;

/**
 * Classe définissant un rotor
 *
 * @author Alex Lebeau
 * @version 1.0, 11/30/20
 */
public class Rotor
{

    private int[] range1 = new int[26];
    private int[] range2 = new int[26];

    private int id;
    private int ordreDirection;
    private int tempPermutationR1;
    private int tempPermutationR2;

    private char directionCrypto;

    public Rotor(int[] r1, int[] r2, int paramID)
    {
        this.range1 = r1;
        this.range2 = r2;
        this.id = paramID;
    }

    public int getId()
    {
        return id;
    }

    public int[] getRange1()
    {
        return range1;
    }

    public int[] getRange2()
    {
        return range2;
    }

    public int getOrdreDirection()
    {
        return ordreDirection;
    }

    public void setOrdreDirection(int ordreDirection)
    {
        this.ordreDirection = ordreDirection;
    }

    public char getDirectionCrypto()
    {
        return directionCrypto;
    }

    public void setDirectionCrypto(char directionCrypto)
    {
        this.directionCrypto = directionCrypto;
    }

    /**
     * Fonction qui tourne le rotor dans la direction et d'un nombre d'unité indiqué.
     *
     * @param direction 'G' pour gauche, 'D' pour droite.
     * @param nbUnite Entier positif.
     * @return
     * @throws java.lang.IllegalArgumentException
     */
    public Rotor tourneRot(char direction, int nbUnite) throws IllegalArgumentException
    {
        if(direction != 'D' && direction != 'G')
        {
            throw new IllegalArgumentException("Mauvais caractère entré pour la direction");
        }

        if(nbUnite < 0) // 0 fonctionne, même s'il est inutile de le faire.
        {
            throw new IllegalArgumentException("Un entier positif doit être entré pour le nombre d'unité.");
        }

        if(direction == 'D' && nbUnite != 0)
        {
            for(int i = 0; i < nbUnite; i++)
            {
                tempPermutationR1 = range1[25];
                tempPermutationR2 = range2[25];

                for(int j = 25; j >= 0; j--)
                {
                    if(j != 0)
                    {
                        range1[j] = range1[j - 1];
                        range2[j] = range2[j - 1];
                    }
                    else
                    {
                        range1[0] = tempPermutationR1;
                        range2[0] = tempPermutationR2;
                    }
                }
            }

            return this;
        }
        else if(direction == 'G' && nbUnite != 0)
        {
            for(int i = 0; i < nbUnite; i++)
            {
                tempPermutationR1 = range1[0];
                tempPermutationR2 = range2[0];

                for(int j = 0; j < 26; j++)
                {
                    if(j != 25)
                    {
                        range1[j] = range1[j + 1];
                        range2[j] = range2[j + 1];
                    }
                    else
                    {
                        range1[25] = tempPermutationR1;
                        range2[25] = tempPermutationR2;
                    }
                }
            }

            return this;
        }
        else
        {
            return this;
        }
    }
}
