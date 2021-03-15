/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meowdoleon.enigma;

import javax.swing.JOptionPane;

/**
 * Classe permettant la configuration des rotors
 *
 * @author Alex Lebeau
 * @version 1.0, 11/30/20
 */
public class RotConf
{

    private static char directionRot1;
    private static char directionRot2;
    private static char directionRot3;

    private static char directionCryptoTemp1;
    private static char directionCryptoTemp2;
    private static char directionCryptoTemp3;

    private static int nbUniteRot1 = 0;
    private static int nbUniteRot2 = 0;
    private static int nbUniteRot3 = 0;

    private static boolean initMov1 = false;
    private static boolean initMov2 = false;
    private static boolean initMov3 = false;

    /**
     * Procédure permettant la configuration des rotors selon la clé
     * cryptographique donnée.
     *
     * @param machine
     * @param ui
     * @throws java.lang.IndexOutOfBoundsException
     */
    public static void configureRotor(Enigma machine, EnigmaUI ui) throws IndexOutOfBoundsException
    {
        String[] cles = ui.getCleTexte().getText().trim().split("\\("); // Sépare la clé en trois sous-clés

        /* La fonction split sépare la clé en quatre strings, dont la première est vide.
           On vérie aussi que le message d'aide n'est pas affiché. */
        if(cles.length == 4 && cles[0].contentEquals(""))
        {
            /* Enlève les espaces blancs et les parenthèse fermantes des sous-clés*/
 /* Doit commencer les indices à 1, car la fonction split() crée un string vide à 0*/
            String cle1 = cles[1].replaceAll("\\s+", "").replaceAll("\\)", "").trim();
            String cle2 = cles[2].replaceAll("\\s+", "").replaceAll("\\)", "").trim();
            String cle3 = cles[3].replaceAll("\\s+", "").replaceAll("\\)", "").trim();

            /* Vérifie s'il y a un mouvement initial d'un rotor */
            if(cle1.substring(4).replaceAll("[^0-9]+", "").matches("\\d+"))
            {
                initMov1 = true;
            }

            if(cle2.substring(4).replaceAll("[^0-9]+", "").matches("\\d+"))
            {
                initMov2 = true;
            }

            if(cle3.substring(4).replaceAll("[^0-9]+", "").matches("\\d+"))
            {
                initMov3 = true;
            }

            /* Vérifie qu'il y a une direction valide du rotor */
            boolean cle1Dir = cle1.toUpperCase().startsWith("G", 3) || cle1.toUpperCase().startsWith("D", 3);
            boolean cle2Dir = cle2.toUpperCase().startsWith("G", 3) || cle2.toUpperCase().startsWith("D", 3);
            boolean cle3Dir = cle3.toUpperCase().startsWith("G", 3) || cle3.toUpperCase().startsWith("D", 3);

            /*Différentes vérifications triviales pour vérifier que l'on a une clé correctement composé*/
            if(!cle1.isEmpty() && !cle2.isEmpty() && !cle3.isEmpty() && cle1.toUpperCase().startsWith("R")
                    && cle2.toUpperCase().startsWith("R") && cle3.toUpperCase().startsWith("R")
                    && !cle1.substring(1, 2).contentEquals("-") && !cle2.substring(1, 2).contentEquals("-")
                    && !cle3.substring(1, 2).contentEquals("-") && cle1Dir && cle2Dir && cle3Dir)
            {
                /*Vérifie que chaque rotor n'est mentionné qu'une seule et unique fois dans la clé */
                boolean rot1XORCle = Integer.parseInt(cle1.substring(1, 2)) == 1
                        ^ Integer.parseInt(cle2.substring(1, 2)) == 1 ^ Integer.parseInt(cle3.substring(1, 2)) == 1;
                boolean rot2XORCle = Integer.parseInt(cle1.substring(1, 2)) == 2
                        ^ Integer.parseInt(cle2.substring(1, 2)) == 2 ^ Integer.parseInt(cle3.substring(1, 2)) == 2;
                boolean rot3XORCle = Integer.parseInt(cle1.substring(1, 2)) == 3
                        ^ Integer.parseInt(cle2.substring(1, 2)) == 3 ^ Integer.parseInt(cle3.substring(1, 2)) == 3;

                if(rot1XORCle && rot2XORCle && rot3XORCle)
                {
                    /* Affecter la direction du mouvement des rotors lors de la cryptographie (clés) */
                    if(cle1.toUpperCase().startsWith("G", 3))
                    {
                        directionCryptoTemp1 = 'G';
                    }
                    else
                    {
                        directionCryptoTemp1 = 'D';
                    }

                    if(cle2.toUpperCase().startsWith("G", 3))
                    {
                        directionCryptoTemp2 = 'G';
                    }
                    else
                    {
                        directionCryptoTemp2 = 'D';
                    }

                    if(cle3.toUpperCase().startsWith("G", 3))
                    {
                        directionCryptoTemp3 = 'G';
                    }
                    else
                    {
                        directionCryptoTemp3 = 'D';
                    }

                    /* Affecter la direction et l'ordre du mouvement des rotors lors de la cryptographie et l’ordre dans lequel ils vont tourner (rotors) */
                    if(cle1.toUpperCase().startsWith("R1"))
                    {
                        machine.getRotor1().setDirectionCrypto(directionCryptoTemp1);
                        machine.getRotor1().setOrdreDirection(0);
                    }
                    else if(cle1.toUpperCase().startsWith("R2"))
                    {
                        machine.getRotor2().setDirectionCrypto(directionCryptoTemp1);
                        machine.getRotor2().setOrdreDirection(0);
                    }
                    else
                    {
                        machine.getRotor3().setDirectionCrypto(directionCryptoTemp1);
                        machine.getRotor3().setOrdreDirection(0);
                    }

                    if(cle2.toUpperCase().startsWith("R1"))
                    {
                        machine.getRotor1().setDirectionCrypto(directionCryptoTemp2);
                        machine.getRotor1().setOrdreDirection(1);
                    }
                    else if(cle2.toUpperCase().startsWith("R2"))
                    {
                        machine.getRotor2().setDirectionCrypto(directionCryptoTemp2);
                        machine.getRotor2().setOrdreDirection(1);
                    }
                    else
                    {
                        machine.getRotor3().setDirectionCrypto(directionCryptoTemp2);
                        machine.getRotor3().setOrdreDirection(1);
                    }

                    if(cle3.toUpperCase().startsWith("R1"))
                    {
                        machine.getRotor1().setDirectionCrypto(directionCryptoTemp3);
                        machine.getRotor1().setOrdreDirection(2);
                    }
                    else if(cle3.toUpperCase().startsWith("R2"))
                    {
                        machine.getRotor2().setDirectionCrypto(directionCryptoTemp3);
                        machine.getRotor2().setOrdreDirection(2);
                    }
                    else
                    {
                        machine.getRotor3().setDirectionCrypto(directionCryptoTemp3);
                        machine.getRotor3().setOrdreDirection(2);
                    }

                    /* Affecter la direction du mouvement initial des rotors */
                    if(cle1.startsWith("-", 5))
                    {
                        directionRot1 = 'G';
                    }
                    else
                    {
                        directionRot1 = 'D';
                    }

                    if(cle2.startsWith("-", 5))
                    {
                        directionRot2 = 'G';
                    }
                    else
                    {
                        directionRot2 = 'D';
                    }

                    if(cle3.startsWith("-", 5))
                    {
                        directionRot3 = 'G';
                    }
                    else
                    {
                        directionRot3 = 'D';
                    }

                    /* Affecter le nombre d'unité que les rotors tournent lors du mouvement initial */
                    if(initMov1)
                    {
                        nbUniteRot1 = Integer.parseInt(cle1.substring(5).replaceAll("[^0-9]+", "")) % 26;
                    }

                    if(initMov2)
                    {
                        nbUniteRot2 = Integer.parseInt(cle2.substring(5).replaceAll("[^0-9]+", "")) % 26;
                    }

                    if(initMov3)
                    {
                        nbUniteRot3 = Integer.parseInt(cle3.substring(5).replaceAll("[^0-9]", "")) % 26;
                    }

                    boolean textBoxNotTuto = ui.getDecryptionTexte().getText().matches("[a-zA-Z]+")
                            && ui.getEncryptionTexte().getText().matches("[a-zA-Z]+");

                    boolean textBoxTuto = ui.getEncryptionTexte().getText().contains("Zone de texte pour taper le message "
                            + "à encrypter ou pour afficher le résultat de décryption")
                            && ui.getDecryptionTexte().getText().contains("Zone de texte pour taper le message "
                                    + "à décrypter ou pour afficher le résultat d'encryption");

                    boolean textBoxBlank = ui.getEncryptionTexte().getText().isBlank() || ui.getDecryptionTexte().getText().isBlank();

                    /* Vérifie qu'il y a une seule zone de texte utilisé */
                    if(textBoxTuto || textBoxBlank || textBoxNotTuto)
                    {
                        JOptionPane.showMessageDialog(ui, "Veuillez entrer votre message à décrypter ou à encrypter "
                                + "dans une seule des deux zones de texte prévues à cet effet.",
                                "",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    else if(textBoxNotTuto = ui.getEncryptionTexte().getText().contains("Zone de texte pour taper le message "
                            + "à encrypter ou pour afficher le résultat de décryption"))
                    {
                        /* Faire le mouvement initial des rotors dans la machine Enigma */
                        machine.setRotor1(ui, machine.getRotor1().tourneRot(directionRot1, nbUniteRot1));
                        machine.setRotor2(ui, machine.getRotor2().tourneRot(directionRot2, nbUniteRot2));
                        machine.setRotor3(ui, machine.getRotor3().tourneRot(directionRot3, nbUniteRot3));

                        ui.getRotConf().setEnabled(false);
                        ui.getDec().setEnabled(true);
                        ui.getDecryptionTexte().setEnabled(false);
                        ui.getEncryptionTexte().setEnabled(false);
                    }
                    else if(ui.getDecryptionTexte().getText().contains("Zone de texte pour taper le message "
                            + "à décrypter ou pour afficher le résultat d'encryption"))
                    {
                        /* Faire le mouvement initial des rotors dans la machine Enigma */
                        machine.setRotor1(ui, machine.getRotor1().tourneRot(directionRot1, nbUniteRot1));
                        machine.setRotor2(ui, machine.getRotor2().tourneRot(directionRot2, nbUniteRot2));
                        machine.setRotor3(ui, machine.getRotor3().tourneRot(directionRot3, nbUniteRot3));

                        ui.getRotConf().setEnabled(false);
                        ui.getEnc().setEnabled(true);
                        ui.getDecryptionTexte().setEnabled(false);
                        ui.getEncryptionTexte().setEnabled(false);
                    }
                }
                else
                {
                    ui.rotConfErreurCle(); // Affiche un message d'erreur de format de clé à l'écran
                }
            }
            else
            {
                ui.rotConfErreurCle();
            }
        }
        else
        {
            ui.rotConfErreurCle();
        }
    }

}
