package edu.esprit.tests;



import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceMessagerie;
import edu.esprit.utils.DataSource;
import org.apache.pdfbox.util.Matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args)
    {


        DataSource.getInstance();
        //(int id_utilisateur, int cin, String nom , String prenom, String adresse, String mdp)
        Utilisateur user=new Utilisateur(1,11417264,"hachem","dhawadi","bizerte","1663","123456789");
        Utilisateur user2=new Utilisateur(9,11417264,"hachem","dhawadi","bizetz","15156","123456789");
       // Publication pub=new Publication(1,1,5,"mhaf","#mhaf","seen","mhaf.png","19/02/2024");

        ServiceMessagerie sm=new ServiceMessagerie();
        //System.out.println(sm.getAllMessagesByReciverAndSender(1));
        //sm.ajouter(new Messagerie("text","chna7welek yal gafsi",new Date(),user,user2));
        //int status, String type, String titre, String description, String image, Date date, Publication pub, Utilisateur user
        //sr.ajouter(new Reclamation(0,"son image","son image","son image",new Date(),pub,user));
        //sr.modifier(new Reclamation(1,1,"spam","modification mhafa","modification","mhaf.png",new Date(),pub,user));
        //ServiceCategorie sc = new ServiceCategorie();
        //sc.ajouter(new Categorie("Web","all belogns to Web"));
        //sc.modifier(new Categorie(1,"new Mobile",sc.getOneByID(1).getDescription()));
       /* ServiceReclamation sr=new ServiceReclamation();
        System.out.println(sr.getOneByID(1));
        ServicePublication pb = new ServicePublication();
        System.out.println(pb.getOneByID(1));
        ServiceUtilisateur ss = new ServiceUtilisateur();
        System.out.println(ss.getOneByID(1));*/
        //System.out.println(sr.getOneByID(1));
        //System.out.println(sc.getOneByID(2));*/
        //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
       /* String apiKey = "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";
        String userPrompt = "can you write me a song";

        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);

            String requestBody = String.format("{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}", userPrompt);

            connection.getOutputStream().write(requestBody.getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.lines().collect(Collectors.joining());
                System.out.println("Chatbot response: " + response);
            } else {
                System.out.println("Error: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        //twilio CRPHA6F53T698LCKVYRLWKWZ

        // Your Vonage API credentials
      /*  final String VONAGE_API_KEY = "4323801";
        final String VONAGE_API_SECRET = "9f2d0f1f5ca8b64aa2c09ace123da43438c3845c";

        // Vonage phone numbers and call details
        final String VONAGE_NUMBER = "447700900000"; // Your Vonage number
        final String TO_NUMBER = "+21626212515"; // The number you want to call
        final String ANSWER_URL = "https://nexmo-community.github.io/ncco-examples/talk.json";

        // Build the VonageClient instance
        VonageClient client = VonageClient.builder()
                .apiKey(VONAGE_API_KEY)
                .apiSecret(VONAGE_API_SECRET)
                .build();

        // Create a voice call
        client.getVoiceClient().createCall(new Call(TO_NUMBER, VONAGE_NUMBER, ANSWER_URL));*/


    }

    }

