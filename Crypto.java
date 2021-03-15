/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meowdoleon.enigma;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex Lebeau
 * @version 1.0, 11/30/20
 */
public class Crypto
{

    private int charCrypto; // Équivalence alphanumérique
    private int ordre; // Ordre des rotors à tourner lors de l'action cryptographique
    private int compteur; // Compte le nombre de fois qu'un rotor a tourné

    public Crypto()
    {
        charCrypto = 0;
        ordre = 0;
        compteur = 0;
    }

    /**
     * Procédure qui encrypte un message en clair. Elle permet aussi la
     * décryption, puisque l'encryption d'un message encrypté avec la même clé
     * et réflecteur permet de revenir au message en clair.
     *
     * @param ui
     * @param machine
     * @param lock
     * @throws java.lang.InterruptedException
     */
    public void encryption(EnigmaUI ui, Enigma machine, Object lock) throws InterruptedException
    {
        String texteCrypto;
        
        /* Prend le texte de la bonne zone de texte */
        if(ui.isEncryption())
        {
            texteCrypto = ui.getEncryptionTexte().getText();
        }
        else
        {
            texteCrypto = ui.getDecryptionTexte().getText();
        }
        
        String texteCryptoTraite = texteCrypto.replaceAll("[^a-zA-Z]", "").trim().toUpperCase(); // Enlève tout ce qui n'est pas alphabétique de la string
        
        String charCryptoFinal = "";

        /* Algorithme d'encryption/décryption */
        for(char c : texteCryptoTraite.toCharArray())
        {
            if(compteur % 26 != 0 && compteur != 0)
            {
                tourneRotCrypto(machine, ui, ordre);

                compteur++;
            }
            else if(compteur != 0)
            {
                tourneRotCrypto(machine, ui, ordre);

                ordre = (ordre + 1) % 3;

                tourneRotCrypto(machine, ui, ordre);
                
                compteur = compteur + 2;
            }
            else
            {
                compteur++;
            }

            switch(c)
            {
                case 'A':
                    charCrypto = 0;
                    break;
                case 'B':
                    charCrypto = 1;
                    break;
                case 'C':
                    charCrypto = 2;
                    break;
                case 'D':
                    charCrypto = 3;
                    break;
                case 'E':
                    charCrypto = 4;
                    break;
                case 'F':
                    charCrypto = 5;
                    break;
                case 'G':
                    charCrypto = 6;
                    break;
                case 'H':
                    charCrypto = 7;
                    break;
                case 'I':
                    charCrypto = 8;
                    break;
                case 'J':
                    charCrypto = 9;
                    break;
                case 'K':
                    charCrypto = 10;
                    break;
                case 'L':
                    charCrypto = 11;
                    break;
                case 'M':
                    charCrypto = 12;
                    break;
                case 'N':
                    charCrypto = 13;
                    break;
                case 'O':
                    charCrypto = 14;
                    break;
                case 'P':
                    charCrypto = 15;
                    break;
                case 'Q':
                    charCrypto = 16;
                    break;
                case 'R':
                    charCrypto = 17;
                    break;
                case 'S':
                    charCrypto = 18;
                    break;
                case 'T':
                    charCrypto = 19;
                    break;
                case 'U':
                    charCrypto = 20;
                    break;
                case 'V':
                    charCrypto = 21;
                    break;
                case 'W':
                    charCrypto = 22;
                    break;
                case 'X':
                    charCrypto = 23;
                    break;
                case 'Y':
                    charCrypto = 24;
                    break;
                case 'Z':
                    charCrypto = 25;
                    break;
                default:
                    charCrypto = 26;
                    break;
            }

            if(charCrypto >= 0 && charCrypto < 26)
            {
                ui.setCoulAlpha(charCrypto + 1, Color.red);

                ui.setCoulRot12(charCrypto + 1, Color.red);
                charCrypto = (charCrypto + ui.getRot12(charCrypto + 1)) % 26;

                /* Le modulo d'un négatif en Java reste négatif */
                if(charCrypto < 0)
                {
                    charCrypto = charCrypto + 26;
                }

                ui.setCoulRot22(charCrypto + 1, Color.red);
                charCrypto = (charCrypto + ui.getRot22(charCrypto + 1)) % 26;

                if(charCrypto < 0)
                {
                    charCrypto = charCrypto + 26;
                }

                ui.setCoulRot32(charCrypto + 1, Color.red);
                charCrypto = (charCrypto + ui.getRot32(charCrypto + 1)) % 26;

                if(charCrypto < 0)
                {
                    charCrypto = charCrypto + 26;
                }

                ui.setCoulRef(charCrypto + 1, Color.red);
                charCrypto = (charCrypto + ui.getRef(charCrypto + 1)) % 26;

                if(charCrypto < 0)
                {
                    charCrypto = charCrypto + 26;
                }

                ui.setCoulRot31(charCrypto + 1, Color.blue);
                charCrypto = (charCrypto + ui.getRot31(charCrypto + 1)) % 26;

                if(charCrypto < 0)
                {
                    charCrypto = charCrypto + 26;
                }

                ui.setCoulRot21(charCrypto + 1, Color.blue);
                charCrypto = (charCrypto + ui.getRot21(charCrypto + 1)) % 26;

                if(charCrypto < 0)
                {
                    charCrypto = charCrypto + 26;
                }

                ui.setCoulRot11(charCrypto + 1, Color.blue);
                charCrypto = (charCrypto + ui.getRot11(charCrypto + 1)) % 26;

                if(charCrypto < 0)
                {
                    charCrypto = charCrypto + 26;
                }

                ui.setCoulAlpha(charCrypto + 1, Color.blue);

                switch(charCrypto)
                {
                    case 0:
                        charCryptoFinal = "A";
                        break;
                    case 1:
                        charCryptoFinal = "B";
                        break;
                    case 2:
                        charCryptoFinal = "C";
                        break;
                    case 3:
                        charCryptoFinal = "D";
                        break;
                    case 4:
                        charCryptoFinal = "E";
                        break;
                    case 5:
                        charCryptoFinal = "F";
                        break;
                    case 6:
                        charCryptoFinal = "G";
                        break;
                    case 7:
                        charCryptoFinal = "H";
                        break;
                    case 8:
                        charCryptoFinal = "I";
                        break;
                    case 9:
                        charCryptoFinal = "J";
                        break;
                    case 10:
                        charCryptoFinal = "K";
                        break;
                    case 11:
                        charCryptoFinal = "L";
                        break;
                    case 12:
                        charCryptoFinal = "M";
                        break;
                    case 13:
                        charCryptoFinal = "N";
                        break;
                    case 14:
                        charCryptoFinal = "O";
                        break;
                    case 15:
                        charCryptoFinal = "P";
                        break;
                    case 16:
                        charCryptoFinal = "Q";
                        break;
                    case 17:
                        charCryptoFinal = "R";
                        break;
                    case 18:
                        charCryptoFinal = "S";
                        break;
                    case 19:
                        charCryptoFinal = "T";
                        break;
                    case 20:
                        charCryptoFinal = "U";
                        break;
                    case 21:
                        charCryptoFinal = "V";
                        break;
                    case 22:
                        charCryptoFinal = "W";
                        break;
                    case 23:
                        charCryptoFinal = "X";
                        break;
                    case 24:
                        charCryptoFinal = "Y";
                        break;
                    case 25:
                        charCryptoFinal = "Z";
                        break;
                    default:
                        break;
                }

                if(ui.isEncryption())
                {
                    if(ui.getDecryptionTexte().getText().contains("Zone de texte pour taper le message à décrypter ou pour afficher le résultat d'encryption"))
                    {
                        ui.getDecryptionTexte().setText(ui.getDecryptionTexte().getText().replaceAll("Zone de texte pour taper le message à décrypter ou pour afficher le résultat d'encryption", ""));
                    }

                    ui.getDecryptionTexte().setText(ui.getDecryptionTexte().getText() + charCryptoFinal);
                }
                else
                {
                    if(ui.getEncryptionTexte().getText().contains("Zone de texte pour taper le message à encrypter ou pour afficher le résultat de décryption"))
                    {
                        ui.getEncryptionTexte().setText(ui.getEncryptionTexte().getText().replaceAll("Zone de texte pour taper le message à encrypter ou pour afficher le résultat de décryption", ""));
                    }

                    ui.getEncryptionTexte().setText(ui.getEncryptionTexte().getText() + charCryptoFinal);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(ui, "Mauvais caractère entré.\r\n"
                        + "Veuillez seulement mettre des caractères dans le champ de décryption"
                        + " ou d'encryption.",
                        "Mauvaise Entrée de Caractère",
                        JOptionPane.ERROR_MESSAGE);
                
                ui.dispose();
            }

            synchronized(lock)
            {
                lock.wait();
            }
        }

        ui.getDecryptionTexte().setEnabled(true);
        ui.getEncryptionTexte().setEnabled(true);
        ui.getNext().setEnabled(false);

        if(ui.isEncryption())
        {
            JOptionPane.showMessageDialog(ui, "Encryption terminée.",
                "Fin de l'encryption",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(ui, "Décryption terminée.",
                "Fin de la décryption",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /* Simple procédure permettant d'alléger le code lors de la rotation d'un rotor. */
    private static void tourneRotCrypto(Enigma machine, EnigmaUI ui, int ordre)
    {
        if(machine.getRotor1().getOrdreDirection() == ordre)
        {
            machine.setRotor1(ui, machine.getRotor1().tourneRot(machine.getRotor1().getDirectionCrypto(), 1));
        }
        else if(machine.getRotor2().getOrdreDirection() == ordre)
        {
            machine.setRotor2(ui, machine.getRotor2().tourneRot(machine.getRotor2().getDirectionCrypto(), 1));
        }
        else
        {
            machine.setRotor3(ui, machine.getRotor3().tourneRot(machine.getRotor3().getDirectionCrypto(), 1));
        }
    }
}
