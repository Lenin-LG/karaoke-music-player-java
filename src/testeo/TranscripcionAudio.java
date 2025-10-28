package testeo;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;


import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.result.Result;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import testeo.CancionTranscrita;

public class TranscripcionAudio {
	
    public static void main(String[] args) {
    	   List<TranscripcionCancion> transcripcion = null;
		try {
			transcripcion = CancionTranscrita.obtenerTranscripcionDeCancion();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


         // Ruta al archivo de audio
         String rutaAudio = "/IntentoKaraoke/miBuenAmor.wav";

         try {
             Configuration configuration = new Configuration();
             configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
             configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
             configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

             LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
             recognizer.startRecognition(true);

             System.out.println("Comenzando el reconocimiento de la grabación...");

             while (true) {
                 String resultado = recognizer.getResult().getHypothesis();
                 if (resultado != null) {
                     System.out.println("Transcripción de la grabación: " + resultado);

                     // Comparar con la transcripción predefinida
                     compararTranscripciones(transcripcion, resultado);
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

	
    public static void compararTranscripciones(List<TranscripcionCancion> transcripcion, String resultado) {
        String[] palabrasTranscripcion = resultado.split("\\s+");
        int palabrasTotales = palabrasTranscripcion.length;
        int coincidencias = 0;

        for (TranscripcionCancion linea : transcripcion) {
            String[] palabrasPredefinidas = linea.getLetra().split("\\s+");
            
            for (String palabraPredefinida : palabrasPredefinidas) {
                for (String palabraTranscripcion : palabrasTranscripcion) {
                    if (palabraPredefinida.equalsIgnoreCase(palabraTranscripcion)) {
                        coincidencias++;
                        break;
                    }
                }
            }
        }

        double precision = (double) coincidencias / palabrasTotales * 100.0;
        System.out.println("Precisión de la transcripción: " + precision + "%");
    }

}
